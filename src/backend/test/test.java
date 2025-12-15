package backend.test;

import backend.db.DatabaseConnection;
import backend.db.DatabaseInspector;
import backend.db.DatabaseSetup;

/**
 * Complete database test suite for note_app
 */
public class test {
    
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║     DASHBOARD DATABASE TEST SUITE - note_app         ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        
        // Test 1: Connection
        testConnection();
        
        // Test 2: Check existing schema
        checkExistingSchema();
        
        // Test 3: Check required tables
        checkRequiredTables();
        
        // Test 4: Verify final setup
        verifySetup();
        
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║           ALL DATABASE TESTS COMPLETED               ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        
        System.out.println("\n✓ Next: Create models (User, Course, Note, etc.)");
    }
    
    private static void testConnection() {
        System.out.println("[TEST 1] Database Connection");
        System.out.println("─".repeat(60));
        
        DatabaseConnection dbConn = DatabaseConnection.getInstance();
        boolean success = dbConn.testConnection();
        
        if (!success) {
            System.err.println("\n✗ Connection test failed. Exiting...");
            System.exit(1);
        }
    }
    
    private static void checkExistingSchema() {
        System.out.println("\n[TEST 2] Existing Schema Analysis");
        System.out.println("─".repeat(60));
        
        DatabaseInspector.generateDatabaseReport();
    }
    
    private static void checkRequiredTables() {
        System.out.println("\n[TEST 3] Required Tables Check");
        System.out.println("─".repeat(60));
        
        DatabaseInspector.checkRequiredTables();
    }
    
    private static void verifySetup() {
        System.out.println("\n[TEST 4] Final Verification");
        System.out.println("─".repeat(60));
        
        DatabaseInspector.checkRequiredTables();
    }
}