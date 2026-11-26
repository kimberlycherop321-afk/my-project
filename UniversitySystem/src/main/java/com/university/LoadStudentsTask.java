package com.university;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class LoadStudentsTask extends Task<ObservableList<Student>> {
    @Override
    protected ObservableList<Student> call() throws Exception {
        DatabaseUtil.initializeDatabase();
        return FXCollections.observableArrayList(UniversityRepository.loadStudents());
    }
}
