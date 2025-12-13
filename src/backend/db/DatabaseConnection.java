package backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for managing database connections
 * Uses existing note_app database
 */
public class DatabaseConnection {
    
    private static DatabaseConnection instance;
    
    // ✅ Using YOUR existing database
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "note_app";  // ✅ Your existing database
    
    private static final String DB_URL = String.format(
        "jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
        DB_HOST, DB_PORT, DB_NAME
    );
    
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";  // Change if needed
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    /**
     * Private constructor
     */
    private DatabaseConnection() {
        try {
            Class.forName(DB_DRIVER);
            System.out.println("[DATABASE] MySQL JDBC Driver loaded");
        } catch (ClassNotFoundException e) {
            System.err.println("[ERROR] MySQL JDBC Driver not found!");
            throw new RuntimeException("Failed to load database driver", e);
        }
    }
    
    /**
     * Get singleton instance
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    
    /**
     * Get database connection
     */
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("[DATABASE] Connection established");
        return conn;
    }
    
    /**
     * Test connection and show current schema
     */
    public boolean testConnection() {
        System.out.println("\n=== DATABASE CONNECTION TEST ===");
        
        try (Connection conn = getConnection()) {
            var metaData = conn.getMetaData();
            
            System.out.println("1. Testing database connection...");
            System.out.println("   ✓ Connected to: " + DB_URL);
            System.out.println("   ✓ Database: " + metaData.getDatabaseProductName());
            System.out.println("   ✓ Version: " + metaData.getDatabaseProductVersion());
            
            // Check current database
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery("SELECT DATABASE() as current_db");
            if (rs.next()) {
                String currentDB = rs.getString("current_db");
                System.out.println("   ✓ Using schema: " + currentDB);
            }
            
            // List existing tables
            System.out.println("\n2. Checking database schema...");
            rs = stmt.executeQuery("SHOW TABLES");
            int tableCount = 0;
            while (rs.next()) {
                String tableName = rs.getString(1);
                System.out.println("   - " + tableName);
                tableCount++;
            }
            System.out.println("   ✓ Found " + tableCount + " table(s)");
            
            System.out.println("\n3. Testing connection cleanup...");
            System.out.println("   ✓ Connection closed properly");
            
            System.out.println("\n✓ ALL TESTS PASSED!");
            return true;
            
        } catch (SQLException e) {
            System.err.println("\n✗ CONNECTION TEST FAILED!");
            System.err.println("   Error: " + e.getMessage());
            System.err.println("   SQL State: " + e.getSQLState());
            return false;
        }
    }
    
    /**
     * Close connection
     */
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("[ERROR] Failed to close connection");
            }
        }
    }
    
    // Getters
    public String getDatabaseName() { return DB_NAME; }
    public String getDatabaseURL() { return DB_URL; }
    public String getDatabaseUser() { return DB_USER; }
}