package backend.test;

import backend.db.DatabaseConnection;

import java.sql.*;
 
public class TestDatabaseTables {
    
    public static void main(String[] args) {
        System.out.println("=== DATABASE CONNECTIVITY AND TABLE STRUCTURE TEST ===\n");
        
        DatabaseConnection db = DatabaseConnection.getInstance();
        
        try (Connection conn = db.getConnection()) {
            System.out.println("1. Checking required tables...\n");
            
            // List of required tables
            String[] requiredTables = { 
                "users", "courses", "notes", 
                "note_tags", "to_do_list", 
                "calendar_events", "user_preferences"
            };
            
            for (String table : requiredTables) {
                checkTableExists(conn, table);
            }
            
            System.out.println("\n2. Checking table columns...\n");
            
            // Check specific columns for each table
            checkTableColumns(conn, "users", 
                "user_id", "username", "email", "password_hash", 
                "first_name", "last_name", "created_at", "is_active");
            
            checkTableColumns(conn, "notes",
                "note_id", "title", "content", "user_id", 
                "course_id", "created_at", "updated_at");
            
            checkTableColumns(conn, "courses",
                "course_id", "user_id", "course_name", "course_year", 
                "created_at", "updated_at");
            
            checkTableColumns(conn, "to_do_list",
                "course_id", "user_id", "course_name", "course_year", 
                "created_at", "updated_at");
            
            System.out.println("\n=== DATABASE STRUCTURE TEST COMPLETED ===");
            
        } catch (SQLException e) {
            System.err.println("\n✗ DATABASE TEST FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void checkTableExists(Connection   conn, String tableName) throws SQLException {
        String sql = "SHOW TABLES LIKE ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tableName);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("   ✓ Table exists: " + tableName);
            } else {
                System.err.println("   ✗ MISSING TABLE: " + tableName);
                System.err.println("     Run DatabaseSetup.java to create missing tables");
            }
        }
    }
    
    private static void checkTableColumns(Connection conn, String tableName, String... expectedColumns) 
            throws SQLException {
        
        System.out.println("   Checking table: " + tableName);
        
        String sql = "DESCRIBE " + tableName;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String columnName = rs.getString("Field");
                String columnType = rs.getString("Type");
                System.out.println("     - " + columnName + " : " + columnType);
            }
            
        } catch (SQLException e) {
            System.err.println("     ✗ Could not describe table: " + e.getMessage());
        }
    }
}