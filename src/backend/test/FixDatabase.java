package backend.test;

import backend.db.DatabaseConnection;
import backend.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class FixDatabase {
    public static void fixCoursesTable() {
        try {
            DatabaseConnection db = DatabaseConnection.getInstance();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            
            System.out.println("Checking database structure...");
            
            // Check if final_grade column exists
            ResultSet rs = stmt.executeQuery(
                "SELECT COUNT(*) FROM information_schema.columns " +
                "WHERE table_schema = DATABASE() AND table_name = 'courses' AND column_name = 'final_grade'"
            );
            
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Adding missing 'final_grade' column to courses table...");
                stmt.execute("ALTER TABLE courses ADD COLUMN final_grade DECIMAL(5,2) DEFAULT 0.00 AFTER course_grades");
                System.out.println("Column added successfully!");
            } else {
                System.out.println("'final_grade' column already exists.");
            }
            
            // Also check assessments table
            rs = stmt.executeQuery(
                "SELECT COUNT(*) FROM information_schema.columns " +
                "WHERE table_schema = DATABASE() AND table_name = 'assessments' AND column_name = 'percentage'"
            );
            
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Adding missing 'percentage' column to assessments table...");
                stmt.execute("ALTER TABLE assessments ADD COLUMN percentage DECIMAL(5,2) DEFAULT 0.00 AFTER max_score");
                System.out.println("Column added successfully!");
            }
            
            // Show table structure
            System.out.println("\n=== Courses Table Structure ===");
            rs = stmt.executeQuery("DESCRIBE courses");
            while (rs.next()) {
                System.out.println(rs.getString("Field") + " | " + rs.getString("Type"));
            }
            
            System.out.println("\n=== Assessments Table Structure ===");
            rs = stmt.executeQuery("DESCRIBE assessments");
            while (rs.next()) {
                System.out.println(rs.getString("Field") + " | " + rs.getString("Type"));
            }
            
            conn.close();
            System.out.println("\nDatabase structure check completed!");
            
        } catch (Exception e) {
            System.err.println("Failed to fix database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        fixCoursesTable();
    }
}