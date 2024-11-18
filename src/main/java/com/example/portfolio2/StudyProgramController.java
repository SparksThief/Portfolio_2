package com.example.portfolio2;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.util.List;

public class StudyProgramController {
    // Deklarer UI-komponenterne, som skal bruges af controlleren
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
    private CourseModel courseModel;
    private int programEcts = 0;
    private int subject1Ects = 0;
    private int subject2Ects = 0;
    private int electiveEcts = 0;

    // Sætter UI-komponenterne fra StudyProgramApp
    public void setUIComponents(ComboBox<String> programBox, ComboBox<String> subject1Box, ComboBox<String> subject2Box,
                                ComboBox<String> electiveBox,
                                ListView<String> selectedProgramCoursesListView, ListView<String> selectedSubject1CoursesListView,
                                ListView<String> selectedSubject2CoursesListView, ListView<String> selectedElectiveCoursesListView,
                                Label programEctsLabel, Label subject1EctsLabel, Label subject2EctsLabel, Label electiveEctsLabel,
                                CourseModel courseModel) {
        // Initialiserer controllerens UI-komponenter, så de kan bruges senere i logikken
        this.programBox = programBox;
        this.subject1Box = subject1Box;
        this.subject2Box = subject2Box;
        this.electiveBox = electiveBox;
        this.selectedProgramCoursesListView = selectedProgramCoursesListView;
        this.selectedSubject1CoursesListView = selectedSubject1CoursesListView;
        this.selectedSubject2CoursesListView = selectedSubject2CoursesListView;
        this.selectedElectiveCoursesListView = selectedElectiveCoursesListView;
        this.programEctsLabel = programEctsLabel;
        this.subject1EctsLabel = subject1EctsLabel;
        this.subject2EctsLabel = subject2EctsLabel;
        this.electiveEctsLabel = electiveEctsLabel;
        this.courseModel = courseModel;
    }

    // Henter kurser fra modellen og fylder ComboBoxene
    public void loadCourses() {
        // Hent kurser fra databasen baseret på type og fyld hver ComboBox
        List<String> programCourses = courseModel.getPrograms();
        List<String> subject1Courses = courseModel.getSubjects();
        List<String> subject2Courses = courseModel.getSubjects();
        List<String> electiveCourses = courseModel.getElectives();

        // Debug udskrivning for at se, om kurserne bliver hentet korrekt
        System.out.println("Fetched Programs: " + programCourses);
        System.out.println("Fetched Subject 1: " + subject1Courses);
        System.out.println("Fetched Subject 2: " + subject2Courses);
        System.out.println("Fetched Electives: " + electiveCourses);

        // Tilføj kurserne til de relevante ComboBoxes
        programBox.getItems().addAll(programCourses);
        subject1Box.getItems().addAll(subject1Courses);
        subject2Box.getItems().addAll(subject2Courses);
        electiveBox.getItems().addAll(electiveCourses);
    }

    // Tilføjer valgte kurser fra alle ComboBoxes
    public void addSelectedCourses() {
        // Tilføjer valgte kurser til de respektive ListViews og opdaterer ECTS labels
        addCourseFromComboBox(programBox, selectedProgramCoursesListView, programEctsLabel, "program");
        addCourseFromComboBox(subject1Box, selectedSubject1CoursesListView, subject1EctsLabel, "subject1");
        addCourseFromComboBox(subject2Box, selectedSubject2CoursesListView, subject2EctsLabel, "subject2");
        addCourseFromComboBox(electiveBox, selectedElectiveCoursesListView, electiveEctsLabel, "elective");
    }

    // Metode til at tilføje kursus fra en specifik ComboBox
    private void addCourseFromComboBox(ComboBox<String> comboBox, ListView<String> listView, Label ectsLabel, String type) {
        String selectedCourse = comboBox.getValue(); // Hent det valgte kursus fra ComboBoxen

        // Tjek om der er valgt et kursus, og om det allerede findes i listen
        if (selectedCourse != null && !listView.getItems().contains(selectedCourse)) {
            // Tilføj valgt kursus til ListView for visning
            listView.getItems().add(selectedCourse);

            // Hent aktivitetens ID og opdater ECTS
            int activityId = courseModel.getActivityIdByName(selectedCourse);
            if (activityId != -1) {
                int ects = courseModel.getEctsByActivityId(activityId);
                // Opdater den relevante ECTS label baseret på typen af kursus
                switch (type) {
                    case "program":
                        programEcts += ects;
                        ectsLabel.setText("ECTS: " + programEcts);
                        break;
                    case "subject1":
                        subject1Ects += ects;
                        ectsLabel.setText("ECTS: " + subject1Ects);
                        break;
                    case "subject2":
                        subject2Ects += ects;
                        ectsLabel.setText("ECTS: " + subject2Ects);
                        break;
                    case "elective":
                        electiveEcts += ects;
                        ectsLabel.setText("ECTS: " + electiveEcts);
                        break;
                }
            }
        }
    }
}
