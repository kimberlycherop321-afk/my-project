package com.university;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StudentController {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<Program> programComboBox;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Number> idColumn;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> programColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button clearButton;
    @FXML
    private Label statusLabel;

    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private final FilteredList<Student> filteredStudents = new FilteredList<>(students);
    private final SortedList<Student> sortedStudents = new SortedList<>(filteredStudents);
    private final BooleanProperty busy = new SimpleBooleanProperty(false);

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cell -> cell.getValue().idProperty());
        firstNameColumn.setCellValueFactory(cell -> cell.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cell -> cell.getValue().lastNameProperty());
        emailColumn.setCellValueFactory(cell -> cell.getValue().emailProperty());
        programColumn.setCellValueFactory(cell -> cell.getValue().programNameProperty());
        sortedStudents.comparatorProperty().bind(studentTable.comparatorProperty());
        studentTable.setItems(sortedStudents);

        addButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> busy.get()
                        || firstNameField.getText().isBlank()
                        || lastNameField.getText().isBlank()
                        || !isValidEmail(emailField.getText())
                        || programComboBox.getValue() == null,
                busy,
                firstNameField.textProperty(),
                lastNameField.textProperty(),
                emailField.textProperty(),
                programComboBox.valueProperty()));
        updateButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> busy.get() || studentTable.getSelectionModel().getSelectedItem() == null
                        || !isFormValid(),
                busy,
                studentTable.getSelectionModel().selectedItemProperty(),
                firstNameField.textProperty(),
                lastNameField.textProperty(),
                emailField.textProperty(),
                programComboBox.valueProperty()));
        deleteButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> busy.get() || studentTable.getSelectionModel().getSelectedItem() == null,
                busy,
                studentTable.getSelectionModel().selectedItemProperty()));
        clearButton.disableProperty().bind(busy);

        searchField.textProperty().addListener((observable, oldValue, newValue) ->
                updateSearch(newValue));
        studentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, selectedStudent) -> {
                    if (selectedStudent != null) {
                        populateForm(selectedStudent);
                    }
                });
        loadStudents();
    }

    @FXML
    private void addStudent() {
        if (!isFormValid()) {
            statusLabel.setText("Enter a valid name, email, and program before adding a student.");
            return;
        }
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        Program program = programComboBox.getValue();
        int programId = program.getId();
        String programName = program.getName();
        Task<Student> task = new Task<Student>() {
            @Override
            protected Student call() throws Exception {
                return UniversityRepository.insertStudent(
                        firstName, lastName, email, programId, programName);
            }
        };
        runTask(task, "Adding student...",
                created -> {
                    students.add(created);
                    clearForm();
                    statusLabel.setText("Added " + created.getFirstName() + " " + created.getLastName() + ".");
                });
    }

    @FXML
    private void updateStudent() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        Program program = programComboBox.getValue();
        if (selected == null || program == null || !isFormValid()) {
            statusLabel.setText("Select a student and complete the form before updating.");
            return;
        }
        int id = selected.getId();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        int programId = program.getId();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                UniversityRepository.updateStudent(id, firstName, lastName, email, programId);
                return null;
            }
        };
        runTask(task, "Updating student...", ignored -> {
            selected.setFirstName(firstName);
            selected.setLastName(lastName);
            selected.setEmail(email);
            selected.setProgramId(program.getId());
            selected.setProgramName(program.getName());
            statusLabel.setText("Updated " + firstName + " " + lastName + ".");
        });
    }

    @FXML
    private void deleteStudent() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText("Select a student before deleting.");
            return;
        }
        int id = selected.getId();
        String name = selected.getFirstName() + " " + selected.getLastName();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                UniversityRepository.deleteStudent(id);
                return null;
            }
        };
        runTask(task, "Deleting student...", ignored -> {
            students.removeIf(student -> student.getId() == id);
            clearForm();
            statusLabel.setText("Deleted " + name + ".");
        });
    }

    @FXML
    private void clearForm() {
        studentTable.getSelectionModel().clearSelection();
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        programComboBox.getSelectionModel().clearSelection();
    }

    private void loadStudents() {
        LoadStudentsTask task = new LoadStudentsTask();
        runTask(task, "Loading students...",
                loaded -> {
                    students.setAll(loaded);
                    statusLabel.setText("Loaded " + loaded.size() + " student(s).");
                    loadPrograms();
                });
    }

    private void loadPrograms() {
        Task<List<Program>> task = new Task<List<Program>>() {
            @Override
            protected List<Program> call() throws SQLException {
                return UniversityRepository.loadPrograms();
            }
        };
        runTask(task, "Loading programs...",
                programs -> {
                    programComboBox.getItems().setAll(programs);
                    Student selected = studentTable.getSelectionModel().getSelectedItem();
                    if (selected != null) {
                        populateForm(selected);
                    }
                    statusLabel.setText("Loaded " + students.size() + " student(s) and "
                            + programs.size() + " program(s).");
                });
    }

    private <T> void runTask(Task<T> task, String runningMessage, TaskSuccess<T> onSuccess) {
        busy.set(true);
        statusLabel.setText(runningMessage);
        task.setOnSucceeded(event -> {
            busy.set(false);
            onSuccess.accept(task.getValue());
        });
        task.setOnFailed(event -> {
            busy.set(false);
            statusLabel.setText("Database operation failed.");
            showDatabaseError(task.getException());
        });
        task.setOnCancelled(event -> {
            busy.set(false);
            statusLabel.setText("Database operation cancelled.");
        });
        Thread worker = new Thread(task, "university-database-task");
        worker.setDaemon(true);
        worker.start();
    }

    private boolean isFormValid() {
        return !firstNameField.getText().isBlank()
                && !lastNameField.getText().isBlank()
                && isValidEmail(emailField.getText())
                && programComboBox.getValue() != null;
    }

    private boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    private void updateSearch(String searchText) {
        String query = searchText == null ? "" : searchText.trim().toLowerCase(Locale.ROOT);
        filteredStudents.setPredicate(student -> query.isEmpty()
                || student.getFirstName().toLowerCase(Locale.ROOT).contains(query)
                || student.getLastName().toLowerCase(Locale.ROOT).contains(query)
                || student.getEmail().toLowerCase(Locale.ROOT).contains(query)
                || student.getProgramName().toLowerCase(Locale.ROOT).contains(query));
    }

    private void populateForm(Student student) {
        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        emailField.setText(student.getEmail());
        programComboBox.getItems().stream()
                .filter(program -> program.getId() == student.getProgramId())
                .findFirst()
                .ifPresent(programComboBox::setValue);
    }

    private void showDatabaseError(Throwable failure) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database error");
        alert.setHeaderText("The operation could not be completed.");
        String message = failure == null ? "No further error details were provided." : failure.getMessage();
        alert.setContentText(message == null || message.isBlank()
                ? failure.getClass().getSimpleName()
                : message);
        alert.show();
    }

    @FunctionalInterface
    private interface TaskSuccess<T> {
        void accept(T value);
    }
}
