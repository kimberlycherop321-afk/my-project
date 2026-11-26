# Event-Driven Programming in Java

Java activities and desktop projects covering event-driven programming, Swing, JavaFX, and a university management capstone. The sections below are ordered from Week 1 through Week 10 and list the tracked files for each week.

## Requirements

- JDK 17 or newer.
- Maven for JavaFX projects (Weeks 6–10).
- A graphical desktop environment for GUI applications.

## Week 1 — Event-Driven Basics

**Run:** Run individual Java exercises from the repository root; see the linked self-assessment and bug-fix notes.

<details>
<summary>Show all files</summary>

- [Bug1_BlockingEDT.java](./Bug1_BlockingEDT.java)
- [Bug2_MissingListener.java](./Bug2_MissingListener.java)
- [Bug3_WrongThread.java](./Bug3_WrongThread.java)
- [Bug4_MemoryLeak.java](./Bug4_MemoryLeak.java)
- [BugFixes.md](./BugFixes.md)
- [EventDrivenCalculator.java](./EventDrivenCalculator.java)
- [ProceduralCalculator.java](./ProceduralCalculator.java)
- [SelfAssessment.md](./SelfAssessment.md)
- [SmartHomePrototype.java](./SmartHomePrototype.java)

</details>

## Week 2 — Observer Pattern

**Run:** Use the guide listed below for run instructions and launch scripts.

<details>
<summary>Show all files</summary>

- [Week02-observer-pattern.md](./Week02-observer-pattern.md)
- [Week02-observer-pattern/activity1-weather-station/CurrentConditionsDisplay.java](./Week02-observer-pattern/activity1-weather-station/CurrentConditionsDisplay.java)
- [Week02-observer-pattern/activity1-weather-station/ForecastDisplay.java](./Week02-observer-pattern/activity1-weather-station/ForecastDisplay.java)
- [Week02-observer-pattern/activity1-weather-station/StatisticsDisplay.java](./Week02-observer-pattern/activity1-weather-station/StatisticsDisplay.java)
- [Week02-observer-pattern/activity1-weather-station/WeatherData.java](./Week02-observer-pattern/activity1-weather-station/WeatherData.java)
- [Week02-observer-pattern/activity1-weather-station/WeatherObserver.java](./Week02-observer-pattern/activity1-weather-station/WeatherObserver.java)
- [Week02-observer-pattern/activity1-weather-station/WeatherStationMain.java](./Week02-observer-pattern/activity1-weather-station/WeatherStationMain.java)
- [Week02-observer-pattern/activity2-legacy-observer/LegacyDisplay.java](./Week02-observer-pattern/activity2-legacy-observer/LegacyDisplay.java)
- [Week02-observer-pattern/activity2-legacy-observer/LegacyLimitations.md](./Week02-observer-pattern/activity2-legacy-observer/LegacyLimitations.md)
- [Week02-observer-pattern/activity2-legacy-observer/LegacyObserverMain.java](./Week02-observer-pattern/activity2-legacy-observer/LegacyObserverMain.java)
- [Week02-observer-pattern/activity2-legacy-observer/LegacyWeatherModel.java](./Week02-observer-pattern/activity2-legacy-observer/LegacyWeatherModel.java)
- [Week02-observer-pattern/activity3-custom-observer/BarChartDisplay.java](./Week02-observer-pattern/activity3-custom-observer/BarChartDisplay.java)
- [Week02-observer-pattern/activity3-custom-observer/CpuMonitor.java](./Week02-observer-pattern/activity3-custom-observer/CpuMonitor.java)
- [Week02-observer-pattern/activity3-custom-observer/LabelDisplay.java](./Week02-observer-pattern/activity3-custom-observer/LabelDisplay.java)
- [Week02-observer-pattern/activity3-custom-observer/RefactoredMain.java](./Week02-observer-pattern/activity3-custom-observer/RefactoredMain.java)
- [Week02-observer-pattern/activity3-custom-observer/SystemObserver.java](./Week02-observer-pattern/activity3-custom-observer/SystemObserver.java)
- [Week02-observer-pattern/activity3-custom-observer/TextLogger.java](./Week02-observer-pattern/activity3-custom-observer/TextLogger.java)
- [Week02-observer-pattern/activity3-custom-observer/TightlyCoupledSystem.java](./Week02-observer-pattern/activity3-custom-observer/TightlyCoupledSystem.java)
- [Week02-observer-pattern/activity4-comparison/Comparison.md](./Week02-observer-pattern/activity4-comparison/Comparison.md)
- [Week02-observer-pattern/activity4-comparison/ComparisonMain.java](./Week02-observer-pattern/activity4-comparison/ComparisonMain.java)
- [Week02-observer-pattern/activity4-comparison/CustomTemperatureSubject.java](./Week02-observer-pattern/activity4-comparison/CustomTemperatureSubject.java)
- [Week02-observer-pattern/activity4-comparison/PropertyChangeExample.java](./Week02-observer-pattern/activity4-comparison/PropertyChangeExample.java)
- [Week02-observer-pattern/scripts/run-week2.ps1](./Week02-observer-pattern/scripts/run-week2.ps1)
- [Week02-observer-pattern/scripts/run-week2.sh](./Week02-observer-pattern/scripts/run-week2.sh)
- [Week02-observer-pattern/SelfAssessment.md](./Week02-observer-pattern/SelfAssessment.md)
- [Week02-observer-pattern/Week2Launcher.java](./Week02-observer-pattern/Week2Launcher.java)

</details>

## Week 3 — Swing GUIs and Layouts

**Run:** Use the guide listed below for run instructions and launch scripts.

<details>
<summary>Show all files</summary>

- [week03-swing-gui.md](./week03-swing-gui.md)
- [week03-swing-gui/activity1-first-window/FirstWindow.java](./week03-swing-gui/activity1-first-window/FirstWindow.java)
- [week03-swing-gui/activity2-basic-components/ComponentShowcase.java](./week03-swing-gui/activity2-basic-components/ComponentShowcase.java)
- [week03-swing-gui/activity3-layout-managers/BorderLayoutDemo.java](./week03-swing-gui/activity3-layout-managers/BorderLayoutDemo.java)
- [week03-swing-gui/activity3-layout-managers/CombinedLayoutDemo.java](./week03-swing-gui/activity3-layout-managers/CombinedLayoutDemo.java)
- [week03-swing-gui/activity3-layout-managers/FlowLayoutDemo.java](./week03-swing-gui/activity3-layout-managers/FlowLayoutDemo.java)
- [week03-swing-gui/activity3-layout-managers/GridLayoutDemo.java](./week03-swing-gui/activity3-layout-managers/GridLayoutDemo.java)
- [week03-swing-gui/activity4-edt-rule/FixedDemo.java](./week03-swing-gui/activity4-edt-rule/FixedDemo.java)
- [week03-swing-gui/activity4-edt-rule/FreezeDemo.java](./week03-swing-gui/activity4-edt-rule/FreezeDemo.java)
- [week03-swing-gui/activity5-complete-mockup/StudentPortal.java](./week03-swing-gui/activity5-complete-mockup/StudentPortal.java)
- [week03-swing-gui/scripts/run-week3.ps1](./week03-swing-gui/scripts/run-week3.ps1)
- [week03-swing-gui/scripts/run-week3.sh](./week03-swing-gui/scripts/run-week3.sh)
- [week03-swing-gui/SelfAssessment.md](./week03-swing-gui/SelfAssessment.md)
- [week03-swing-gui/Week3Launcher.java](./week03-swing-gui/Week3Launcher.java)

</details>

## Week 4 — Event Handling in Swing

**Run:** Use the project guide and scripts listed below.

<details>
<summary>Show all files</summary>

- [week04-event-handling/activity1-actionlistener-basics/FirstButton.java](./week04-event-handling/activity1-actionlistener-basics/FirstButton.java)
- [week04-event-handling/activity2-listener-techniques/EventSourceDemo.java](./week04-event-handling/activity2-listener-techniques/EventSourceDemo.java)
- [week04-event-handling/activity2-listener-techniques/ListenerDemo.java](./week04-event-handling/activity2-listener-techniques/ListenerDemo.java)
- [week04-event-handling/activity3-item-and-document-listeners/DocumentListenerDemo.java](./week04-event-handling/activity3-item-and-document-listeners/DocumentListenerDemo.java)
- [week04-event-handling/activity3-item-and-document-listeners/ItemListenerDemo.java](./week04-event-handling/activity3-item-and-document-listeners/ItemListenerDemo.java)
- [week04-event-handling/activity3-item-and-document-listeners/LiveSearchDemo.java](./week04-event-handling/activity3-item-and-document-listeners/LiveSearchDemo.java)
- [week04-event-handling/activity4-counter-app/CounterApp.java](./week04-event-handling/activity4-counter-app/CounterApp.java)
- [week04-event-handling/activity5-interactive-form/RegistrationForm.java](./week04-event-handling/activity5-interactive-form/RegistrationForm.java)
- [week04-event-handling/activity6-mouse-and-key/DrawingApp.java](./week04-event-handling/activity6-mouse-and-key/DrawingApp.java)
- [week04-event-handling/activity6-mouse-and-key/KeyboardMover.java](./week04-event-handling/activity6-mouse-and-key/KeyboardMover.java)
- [week04-event-handling/scripts/run-week4.ps1](./week04-event-handling/scripts/run-week4.ps1)
- [week04-event-handling/scripts/run-week4.sh](./week04-event-handling/scripts/run-week4.sh)
- [week04-event-handling/SelfAssessment.md](./week04-event-handling/SelfAssessment.md)
- [week04-event-handling/week04-event-handling.md](./week04-event-handling/week04-event-handling.md)
- [week04-event-handling/Week4Launcher.java](./week04-event-handling/Week4Launcher.java)

</details>

## Week 5 — Advanced Swing Components

**Run:** Use the project guide and scripts listed below.

<details>
<summary>Show all files</summary>

- [week05-advanced-swing/activity1-jlist-renderer/ContactListDemo.java](./week05-advanced-swing/activity1-jlist-renderer/ContactListDemo.java)
- [week05-advanced-swing/activity2-jtable/StudentTableDemo.java](./week05-advanced-swing/activity2-jtable/StudentTableDemo.java)
- [week05-advanced-swing/activity3-jtree-tabs/FileExplorerDemo.java](./week05-advanced-swing/activity3-jtree-tabs/FileExplorerDemo.java)
- [week05-advanced-swing/activity4-menus-dialogs/MiniEditor.java](./week05-advanced-swing/activity4-menus-dialogs/MiniEditor.java)
- [week05-advanced-swing/activity5-capstone-file-manager/FileManager.java](./week05-advanced-swing/activity5-capstone-file-manager/FileManager.java)
- [week05-advanced-swing/scripts/run-week5.ps1](./week05-advanced-swing/scripts/run-week5.ps1)
- [week05-advanced-swing/scripts/run-week5.sh](./week05-advanced-swing/scripts/run-week5.sh)
- [week05-advanced-swing/SelfAssessment.md](./week05-advanced-swing/SelfAssessment.md)
- [week05-advanced-swing/week05-advanced-swing.md](./week05-advanced-swing/week05-advanced-swing.md)
- [week05-advanced-swing/Week5Launcher.java](./week05-advanced-swing/Week5Launcher.java)

</details>

## Week 6 — Introduction to JavaFX

**Run:** `mvn -f week06-javafx/pom.xml javafx:run`

<details>
<summary>Show all files</summary>

- [scripts/run-week6.ps1](./scripts/run-week6.ps1)
- [scripts/run-week6.sh](./scripts/run-week6.sh)
- [week06-javafx.md](./week06-javafx.md)
- [week06-javafx/.gitignore](./week06-javafx/.gitignore)
- [week06-javafx/pom.xml](./week06-javafx/pom.xml)
- [week06-javafx/README.md](./week06-javafx/README.md)
- [week06-javafx/SelfAssessment.md](./week06-javafx/SelfAssessment.md)
- [week06-javafx/src/main/java/programmaticdemo/DashboardApp.java](./week06-javafx/src/main/java/programmaticdemo/DashboardApp.java)
- [week06-javafx/src/main/java/programmaticdemo/FXMLApp.java](./week06-javafx/src/main/java/programmaticdemo/FXMLApp.java)
- [week06-javafx/src/main/java/programmaticdemo/LayoutDemo.java](./week06-javafx/src/main/java/programmaticdemo/LayoutDemo.java)
- [week06-javafx/src/main/java/programmaticdemo/LoginController.java](./week06-javafx/src/main/java/programmaticdemo/LoginController.java)
- [week06-javafx/src/main/java/programmaticdemo/Main.java](./week06-javafx/src/main/java/programmaticdemo/Main.java)
- [week06-javafx/src/main/resources/programmaticdemo/login.fxml](./week06-javafx/src/main/resources/programmaticdemo/login.fxml)

</details>

## Week 7 — JavaFX Event Handling

**Run:** `mvn -f week07-javafx-events/pom.xml javafx:run`

<details>
<summary>Show all files</summary>

- [scripts/run-week7.ps1](./scripts/run-week7.ps1)
- [scripts/run-week7.sh](./scripts/run-week7.sh)
- [week07-javafx-events.md](./week07-javafx-events.md)
- [week07-javafx-events/.gitignore](./week07-javafx-events/.gitignore)
- [week07-javafx-events/pom.xml](./week07-javafx-events/pom.xml)
- [week07-javafx-events/README.md](./week07-javafx-events/README.md)
- [week07-javafx-events/SelfAssessment.md](./week07-javafx-events/SelfAssessment.md)
- [week07-javafx-events/src/main/java/com/example/CustomEventDemo.java](./week07-javafx-events/src/main/java/com/example/CustomEventDemo.java)
- [week07-javafx-events/src/main/java/com/example/DragDropDemo.java](./week07-javafx-events/src/main/java/com/example/DragDropDemo.java)
- [week07-javafx-events/src/main/java/com/example/EventPropagationDemo.java](./week07-javafx-events/src/main/java/com/example/EventPropagationDemo.java)
- [week07-javafx-events/src/main/java/com/example/LoginSuccessEvent.java](./week07-javafx-events/src/main/java/com/example/LoginSuccessEvent.java)
- [week07-javafx-events/src/main/java/com/example/MouseKeyDemo.java](./week07-javafx-events/src/main/java/com/example/MouseKeyDemo.java)

</details>

## Week 8 — JavaFX Properties and Bindings

**Run:** `mvn -f week08-javafx-bindings/pom.xml javafx:run`

<details>
<summary>Show all files</summary>

- [scripts/run-week8.ps1](./scripts/run-week8.ps1)
- [scripts/run-week8.sh](./scripts/run-week8.sh)
- [week08-javafx-bindings.md](./week08-javafx-bindings.md)
- [week08-javafx-bindings/.gitignore](./week08-javafx-bindings/.gitignore)
- [week08-javafx-bindings/pom.xml](./week08-javafx-bindings/pom.xml)
- [week08-javafx-bindings/README.md](./week08-javafx-bindings/README.md)
- [week08-javafx-bindings/SelfAssessment.md](./week08-javafx-bindings/SelfAssessment.md)
- [week08-javafx-bindings/src/main/java/com/example/BidirectionalDemo.java](./week08-javafx-bindings/src/main/java/com/example/BidirectionalDemo.java)
- [week08-javafx-bindings/src/main/java/com/example/ListenerDemo.java](./week08-javafx-bindings/src/main/java/com/example/ListenerDemo.java)
- [week08-javafx-bindings/src/main/java/com/example/Person.java](./week08-javafx-bindings/src/main/java/com/example/Person.java)
- [week08-javafx-bindings/src/main/java/com/example/ReactiveFormApp.java](./week08-javafx-bindings/src/main/java/com/example/ReactiveFormApp.java)
- [week08-javafx-bindings/src/main/java/com/example/UnidirectionalDemo.java](./week08-javafx-bindings/src/main/java/com/example/UnidirectionalDemo.java)

</details>

## Week 9 — Asynchronous Programming in JavaFX

**Run:** `mvn -f week09-async-javafx/pom.xml javafx:run`

<details>
<summary>Show all files</summary>

- [scripts/run-week9.ps1](./scripts/run-week9.ps1)
- [scripts/run-week9.sh](./scripts/run-week9.sh)
- [week09-async-javafx.md](./week09-async-javafx.md)
- [week09-async-javafx/.gitignore](./week09-async-javafx/.gitignore)
- [week09-async-javafx/pom.xml](./week09-async-javafx/pom.xml)
- [week09-async-javafx/README.md](./week09-async-javafx/README.md)
- [week09-async-javafx/SelfAssessment.md](./week09-async-javafx/SelfAssessment.md)
- [week09-async-javafx/src/main/java/com/example/FreezeDemo.java](./week09-async-javafx/src/main/java/com/example/FreezeDemo.java)
- [week09-async-javafx/src/main/java/com/example/ProgressDemo.java](./week09-async-javafx/src/main/java/com/example/ProgressDemo.java)
- [week09-async-javafx/src/main/java/com/example/ServiceDemo.java](./week09-async-javafx/src/main/java/com/example/ServiceDemo.java)
- [week09-async-javafx/src/main/java/com/example/TaskDemo.java](./week09-async-javafx/src/main/java/com/example/TaskDemo.java)

</details>

## Week 10 — University Management System

**Run:** `mvn -f UniversitySystem/pom.xml javafx:run` or `npm run start:university`; configure `DATABASE_URL` first.

<details>
<summary>Show all files</summary>

- [scripts/run-university.ps1](./scripts/run-university.ps1)
- [scripts/run-university.sh](./scripts/run-university.sh)
- [scripts/run-university-java.js](./scripts/run-university-java.js)
- [UniversitySystem/.gitignore](./UniversitySystem/.gitignore)
- [UniversitySystem/lib/postgresql-42.7.3.jar](./UniversitySystem/lib/postgresql-42.7.3.jar)
- [UniversitySystem/pom.xml](./UniversitySystem/pom.xml)
- [UniversitySystem/README.md](./UniversitySystem/README.md)
- [UniversitySystem/src/main/java/com/university/App.java](./UniversitySystem/src/main/java/com/university/App.java)
- [UniversitySystem/src/main/java/com/university/DatabaseUtil.java](./UniversitySystem/src/main/java/com/university/DatabaseUtil.java)
- [UniversitySystem/src/main/java/com/university/LoadStudentsTask.java](./UniversitySystem/src/main/java/com/university/LoadStudentsTask.java)
- [UniversitySystem/src/main/java/com/university/MainController.java](./UniversitySystem/src/main/java/com/university/MainController.java)
- [UniversitySystem/src/main/java/com/university/Program.java](./UniversitySystem/src/main/java/com/university/Program.java)
- [UniversitySystem/src/main/java/com/university/SeedStudents.java](./UniversitySystem/src/main/java/com/university/SeedStudents.java)
- [UniversitySystem/src/main/java/com/university/Student.java](./UniversitySystem/src/main/java/com/university/Student.java)
- [UniversitySystem/src/main/java/com/university/StudentController.java](./UniversitySystem/src/main/java/com/university/StudentController.java)
- [UniversitySystem/src/main/java/com/university/UniversityRepository.java](./UniversitySystem/src/main/java/com/university/UniversityRepository.java)
- [UniversitySystem/src/main/java/com/university/UniversitySystemTest.java](./UniversitySystem/src/main/java/com/university/UniversitySystemTest.java)
- [UniversitySystem/src/main/resources/com/university/MainView.fxml](./UniversitySystem/src/main/resources/com/university/MainView.fxml)
- [UniversitySystem/src/main/resources/com/university/StudentView.fxml](./UniversitySystem/src/main/resources/com/university/StudentView.fxml)
- [week10-university-system.md](./week10-university-system.md)

</details>

## Shared Repository Files

These files support setup or project-wide commands rather than one specific week.

- [.env.example](./.env.example)
- [.gitignore](./.gitignore)
- [package.json](./package.json)
