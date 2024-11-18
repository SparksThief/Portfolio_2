// StudyProgramApp.java

package com.example.portfolio2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudyProgramApp extends Application {
    // Opret instansvariabler til kursusmodellen og controlleren
    private final CourseModel courseModel = new CourseModel(); // Forbinder til databasen
    private final StudyProgramController controller = new StudyProgramController(); // Controller instans

    // Deklarer UI-komponenterne (ComboBoxes, ListViews, Labels, Button)
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
        // Start-metoden: Opsæt applikationens primære vindue
        primaryStage.setTitle("Study Program");

        // Setup UI-komponenter
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

        // Sæt controlleren op med referencer til UI-komponenterne, så controlleren kan styre logikken
        controller.setUIComponents(
                programBox, subject1Box, subject2Box, electiveBox,
                selectedProgramCoursesListView, selectedSubject1CoursesListView,
                selectedSubject2CoursesListView, selectedElectiveCoursesListView,
                programEctsLabel, subject1EctsLabel, subject2EctsLabel, electiveEctsLabel,
                courseModel);

        // Indlæs kurser fra databasen til ComboBoxes ved brug af controlleren
        controller.loadCourses();  // Brug controlleren til at fylde ComboBoxes

        // Tilføj en event-handler til knappen "Add Course" for at tilføje de valgte kurser til de respektive lister
        addCourseButton.setOnAction(event -> controller.addSelectedCourses());

        // Layout-opsætning ved brug af GridPane med margener, rækker og kolonner
        GridPane grid = new GridPane();
        grid.setHgap(10); // Indstil vandret afstand mellem elementer
        grid.setVgap(10); // Indstil lodret afstand mellem elementer
        grid.setPadding(new Insets(20)); // Indstil indvendige margener for layoutet

        // Tilføj UI-elementer til layoutet for at bygge brugergrænsefladen
        grid.add(new Label("Program"), 0, 0); // Tilføj label for programvalg
        grid.add(programBox, 0, 1); // Tilføj ComboBox til programvalg
        grid.add(new Label("Subject 1"), 1, 0); // Tilføj label for emne 1
        grid.add(subject1Box, 1, 1); // Tilføj ComboBox til emne 1
        grid.add(new Label("Subject 2"), 2, 0); // Tilføj label for emne 2
        grid.add(subject2Box, 2, 1); // Tilføj ComboBox til emne 2
        grid.add(new Label("Elective"), 3, 0); // Tilføj label for valgfag
        grid.add(electiveBox, 3, 1); // Tilføj ComboBox til valgfag

        // Tilføj "Add Course"-knappen til layoutet
        grid.add(addCourseButton, 0, 2, 4, 1); // Tilføj knappen og lad den strække sig over alle fire kolonner

        // Tilføj ListView og ECTS labels for hver kategori
        grid.add(new Label("Selected Program Courses"), 0, 3); // Label for valgte kurser i programmet
        grid.add(selectedProgramCoursesListView, 0, 4); // ListView for valgte programkurser
        grid.add(programEctsLabel, 0, 5); // Label for ECTS point i programmet

        grid.add(new Label("Selected Subject 1 Courses"), 1, 3); // Label for valgte kurser i emne 1
        grid.add(selectedSubject1CoursesListView, 1, 4); // ListView for valgte kurser i emne 1
        grid.add(subject1EctsLabel, 1, 5); // Label for ECTS point i emne 1

        grid.add(new Label("Selected Subject 2 Courses"), 2, 3); // Label for valgte kurser i emne 2
        grid.add(selectedSubject2CoursesListView, 2, 4); // ListView for valgte kurser i emne 2
        grid.add(subject2EctsLabel, 2, 5); // Label for ECTS point i emne 2

        grid.add(new Label("Selected Elective Courses"), 3, 3); // Label for valgte valgfag
        grid.add(selectedElectiveCoursesListView, 3, 4); // ListView for valgte valgfag
        grid.add(electiveEctsLabel, 3, 5); // Label for ECTS point i valgfag

        // Opret scenen med layoutet og vis applikationen
        Scene scene = new Scene(grid, 800, 600); // Opret en scene med GridPane-layoutet
        primaryStage.setScene(scene); // Sæt scenen på det primære vindue
        primaryStage.show(); // Vis det primære vindue
    }

    public static void main(String[] args) {
        launch(args); // Start JavaFX-applikationen
    }
}
