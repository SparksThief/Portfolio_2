package com.example.portfolio2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseModel {

    // Hent alle tilgængelige programmer fra Program-tabellen
    public List<String> getPrograms() {
        List<String> programs = new ArrayList<>();
        String sql = "SELECT name FROM Program";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Gå gennem resultaterne og tilføj programmets navn til listen
            while (rs.next()) {
                programs.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Udskriv fejl, hvis der opstår en SQL undtagelse
        }

        return programs; // Returner listen over programmer
    }

    // Hent alle tilgængelige emner fra Subject-tabellen
    public List<String> getSubjects() {
        List<String> subjects = new ArrayList<>();
        String sql = "SELECT name FROM Subject";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Gå gennem resultaterne og tilføj emnets navn til listen
            while (rs.next()) {
                subjects.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Udskriv fejl, hvis der opstår en SQL undtagelse
        }

        return subjects; // Returner listen over emner
    }

    // Hent alle tilgængelige valgfag fra Elective-tabellen
    public List<String> getElectives() {
        List<String> electives = new ArrayList<>();
        String sql = "SELECT name FROM Elective";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Gå gennem resultaterne og tilføj valgfagets navn til listen
            while (rs.next()) {
                electives.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Udskriv fejl, hvis der opstår en SQL undtagelse
        }

        return electives; // Returner listen over valgfag
    }

    // Hent aktivitetens ID baseret på kursusnavn fra Activity-tabellen
    public int getActivityIdByName(String courseName) {
        String sql = "SELECT activity_id FROM Activity WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Sæt kursusnavnet som parameter i SQL-forespørgslen
            pstmt.setString(1, courseName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("activity_id"); // Returner aktivitetens ID, hvis den findes
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Udskriv fejl, hvis der opstår en SQL undtagelse
        }
        return -1; // Returner -1, hvis der ikke findes noget aktivitet_id
    }

    // Hent ECTS-værdien for en specifik aktivitet via ID
    public int getEctsByActivityId(int activityId) {
        String sql = "SELECT ects FROM Activity WHERE activity_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Sæt aktivitetens ID som parameter i SQL-forespørgslen
            pstmt.setInt(1, activityId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ects"); // Returner ECTS-værdien, hvis den findes
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Udskriv fejl, hvis der opstår en SQL undtagelse
        }
        return 0; // Returner 0, hvis der ikke findes nogen ECTS-værdi
    }

    // Gem et valgt kursus for en specifik studerende i StudentActivity-tabellen
    public void saveSelectedCourse(int studentId, int activityId) {
        String sql = "INSERT INTO StudentActivity (student_id, activity_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Sæt studenter-ID og aktivitetens ID som parametre i SQL-forespørgslen
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, activityId);
            pstmt.executeUpdate(); // Udfør SQL-forespørgslen for at gemme dataen

        } catch (SQLException e) {
            e.printStackTrace(); // Udskriv fejl, hvis der opstår en SQL undtagelse
        }
    }
}
