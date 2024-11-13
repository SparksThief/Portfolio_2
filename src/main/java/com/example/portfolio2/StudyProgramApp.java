// StudyProgramApp.java

package com.example.portfolio2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudyProgramApp extends Application {
    private final CourseModel courseModel = new CourseModel(); // Connects to the database
    private final StudyProgramController controller = new StudyProgramController(); // Controller instance

    private ComboBox<String> programBox;
    private ComboBox<String> subject1Box;
    private ComboBox<String> subject2Box;
    private ComboBox<String> electiveBox;
    private ListView<String> selectedProgramCoursesListView;
    private ListView<String> selectedSubject1CoursesListView;
    private ListView<String> selectedSubject2CoursesListView;
    private ListView<String> selectedElectiveCoursesListView;
    private Label programEctsLabel;
    private Label subject1EctsLabel;
    private Label subject2EctsLabel;
    private Label electiveEctsLabel;
    private Button addCourseButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Study Program");

        // Setup UI components
        programBox = new ComboBox<>();
        subject1Box = new ComboBox<>();
        subject2Box = new ComboBox<>();
        electiveBox = new ComboBox<>();
        selectedProgramCoursesListView = new ListView<>();
        selectedSubject1CoursesListView = new ListView<>();
        selectedSubject2CoursesListView = new ListView<>();
        selectedElectiveCoursesListView = new ListView<>();
        programEctsLabel = new Label("ECTS: 0");
        subject1EctsLabel = new Label("ECTS: 0");
        subject2EctsLabel = new Label("ECTS: 0");
        electiveEctsLabel = new Label("ECTS: 0");
        addCourseButton = new Button("Add Course");

        // Set up the controller with references to the UI components
        controller.setUIComponents(
                programBox, subject1Box, subject2Box, electiveBox,
                selectedProgramCoursesListView, selectedSubject1CoursesListView,
                selectedSubject2CoursesListView, selectedElectiveCoursesListView,
                programEctsLabel, subject1EctsLabel, subject2EctsLabel, electiveEctsLabel,
                courseModel);

        // Load courses from database into ComboBoxes
        controller.loadCourses();  // Use the controller to fill the ComboBoxes

        // Action for the Add Course button (delegated to controller)
        addCourseButton.setOnAction(event -> controller.addSelectedCourses());

        // Layout setup
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        // Adding UI elements to the layout
        grid.add(new Label("Program"), 0, 0);
        grid.add(programBox, 0, 1);
        grid.add(new Label("Subject 1"), 1, 0);
        grid.add(subject1Box, 1, 1);
        grid.add(new Label("Subject 2"), 2, 0);
        grid.add(subject2Box, 2, 1);
        grid.add(new Label("Elective"), 3, 0);
        grid.add(electiveBox, 3, 1);

        grid.add(addCourseButton, 0, 2, 4, 1);

        // Add ListView and ECTS labels for each category
        grid.add(new Label("Selected Program Courses"), 0, 3);
        grid.add(selectedProgramCoursesListView, 0, 4);
        grid.add(programEctsLabel, 0, 5);

        grid.add(new Label("Selected Subject 1 Courses"), 1, 3);
        grid.add(selectedSubject1CoursesListView, 1, 4);
        grid.add(subject1EctsLabel, 1, 5);

        grid.add(new Label("Selected Subject 2 Courses"), 2, 3);
        grid.add(selectedSubject2CoursesListView, 2, 4);
        grid.add(subject2EctsLabel, 2, 5);

        grid.add(new Label("Selected Elective Courses"), 3, 3);
        grid.add(selectedElectiveCoursesListView, 3, 4);
        grid.add(electiveEctsLabel, 3, 5);

        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
