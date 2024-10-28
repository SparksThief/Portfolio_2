package com.example.portfolio2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseModel {
    // Fetches all available courses (from the Activity table) for the ComboBoxes
    public List<String> getCourses() {
        List<String> courses = new ArrayList<>();
        String sql = "SELECT name FROM Activity";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                courses.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
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
}
