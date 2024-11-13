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

            while (rs.next()) {
                programs.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return programs;
    }

    // Hent alle tilgængelige emner fra Subject-tabellen
    public List<String> getSubjects() {
        List<String> subjects = new ArrayList<>();
        String sql = "SELECT name FROM Subject";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                subjects.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    // Hent alle tilgængelige valgfag fra Elective-tabellen
    public List<String> getElectives() {
        List<String> electives = new ArrayList<>();
        String sql = "SELECT name FROM Elective";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                electives.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return electives;
    }

    // Get the activity ID based on the course name from the Activity table
    public int getActivityIdByName(String courseName) {
        String sql = "SELECT activity_id FROM Activity WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("activity_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if no activity_id is found
    }

    // Get the ECTS value for a specific activity by ID
    public int getEctsByActivityId(int activityId) {
        String sql = "SELECT ects FROM Activity WHERE activity_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, activityId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ects");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if no ECTS is found
    }

    // Save a selected course for a specific student in the StudentActivity table
    public void saveSelectedCourse(int studentId, int activityId) {
        String sql = "INSERT INTO StudentActivity (student_id, activity_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            pstmt.setInt(2, activityId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
