package com.example.portfolio2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class StudyProgramApp extends Application {
    private final CourseModel courseModel = new CourseModel(); // Connects to the database

    private ComboBox<String> programBox;
    private ComboBox<String> subject1Box;
    private ComboBox<String> subject2Box;
    private ComboBox<String> electiveBox;
    private ListView<String> selectedCoursesListView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Study Program");

        // Setup UI components
        programBox = new ComboBox<>();
        subject1Box = new ComboBox<>();
        subject2Box = new ComboBox<>();
        electiveBox = new ComboBox<>();
        selectedCoursesListView = new ListView<>();
        Button addCourseButton = new Button("Add Course");

        // Load courses from database into ComboBoxes
        loadCourses();

        // Action for the Add Course button
        addCourseButton.setOnAction(event -> addSelectedCourse());

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
        grid.add(addCourseButton, 0, 2);
        grid.add(new Label("Selected Courses:"), 0, 3);
        grid.add(selectedCoursesListView, 0, 4, 4, 1);

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadCourses() {
        // Fetch list of courses from database
        List<String> courses = courseModel.getCourses();

        // Populate each ComboBox with courses
        programBox.getItems().addAll(courses);
        subject1Box.getItems().addAll(courses);
        subject2Box.getItems().addAll(courses);
        electiveBox.getItems().addAll(courses);
    }

    private void addSelectedCourse() {
        // Get selected course from one of the ComboBoxes (subject1Box as an example)
        String selectedCourse = subject1Box.getValue();

        if (selectedCourse != null && !selectedCoursesListView.getItems().contains(selectedCourse)) {
            // Add selected course to ListView for display
            selectedCoursesListView.getItems().add(selectedCourse);

            // Get activity ID and save to the database for the student
            int activityId = courseModel.getActivityIdByName(selectedCourse);
            if (activityId != -1) {
                // Example student ID for a single student
                int studentId = 83742;
                courseModel.saveSelectedCourse(studentId, activityId);
            } else {
                System.out.println("Activity ID not found for course: " + selectedCourse);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
