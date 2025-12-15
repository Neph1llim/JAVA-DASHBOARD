package backend.test;

import backend.db.DatabaseConnection;
import java.sql.*;

public class DatabaseChecker {
    
    public static void main(String[] args) {
        System.out.println("=== DATABASE CHECK ===\n");
        
        DatabaseConnection db = DatabaseConnection.getInstance();
        
        try (Connection conn = db.getConnection()) {
            
            // Check users table
            System.out.println("1. Checking users table...");
            String sql = "SELECT user_id, username, email FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                if (!rs.isBeforeFirst()) {
                    System.out.println("   ✗ No users found in the database!");
                    System.out.println("   Please create a user first.");
                } else {
                    System.out.println("   Existing users:");
                    while (rs.next()) {
                        int userId = rs.getInt("user_id");
                        String username = rs.getString("username");
                        String email = rs.getString("email");
                        System.out.println("   - ID: " + userId + ", Username: " + username + ", Email: " + email);
                    }
                }
            }
            
            // Check notes table
            System.out.println("\n2. Checking notes table...");
            sql = "SELECT COUNT(*) as count FROM notes";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                if (rs.next()) {
                    System.out.println("   Total notes: " + rs.getInt("count"));
                }
            }
            
            // Check foreign key constraints
            System.out.println("\n3. Checking foreign key constraints...");
            sql = "SELECT TABLE_NAME, COLUMN_NAME, CONSTRAINT_NAME, REFERENCED_TABLE_NAME, REFERENCED_COLUMN_NAME " +
                  "FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE " +
                  "WHERE TABLE_SCHEMA = 'note_app' AND REFERENCED_TABLE_NAME IS NOT NULL";
            
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                System.out.println("   Foreign key constraints:");
                while (rs.next()) {
                    String table = rs.getString("TABLE_NAME");
                    String column = rs.getString("COLUMN_NAME");
                    String refTable = rs.getString("REFERENCED_TABLE_NAME");
                    String refColumn = rs.getString("REFERENCED_COLUMN_NAME");
                    System.out.println("   - " + table + "." + column + " → " + refTable + "." + refColumn);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}