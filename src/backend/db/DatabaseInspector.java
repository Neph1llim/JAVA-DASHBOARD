package backend.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility to inspect existing database schema
 */
public class DatabaseInspector {
    
    /**
     * Get all tables in the database
     */
    public static List<String> getAllTables() {
        List<String> tables = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
            
        } catch (SQLException e) {
            System.err.println("[ERROR] Cannot retrieve tables");
            e.printStackTrace();
        }
        
        return tables;
    }
    
    /**
     * Get columns for a specific table
     */
    public static void showTableStructure(String tableName) {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║  TABLE: " + tableName);
        System.out.println("╠═══════════════════════════════════════════════════════╣");
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            
            // Get columns
            ResultSet columns = metaData.getColumns(null, null, tableName, "%");
            
            System.out.printf("%-25s %-20s %-10s %-10s%n", "COLUMN", "TYPE", "SIZE", "NULLABLE");
            System.out.println("─────────────────────────────────────────────────────────");
            
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String dataType = columns.getString("TYPE_NAME");
                int columnSize = columns.getInt("COLUMN_SIZE");
                String isNullable = columns.getString("IS_NULLABLE");
                
                System.out.printf("%-25s %-20s %-10d %-10s%n", 
                    columnName, dataType, columnSize, isNullable);
            }
            
            // Get primary keys
            System.out.println("\nPRIMARY KEYS:");
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
            while (primaryKeys.next()) {
                System.out.println("  • " + primaryKeys.getString("COLUMN_NAME"));
            }
            
            // Get foreign keys
            System.out.println("\nFOREIGN KEYS:");
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
            while (foreignKeys.next()) {
                String fkColumn = foreignKeys.getString("FKCOLUMN_NAME");
                String pkTable = foreignKeys.getString("PKTABLE_NAME");
                String pkColumn = foreignKeys.getString("PKCOLUMN_NAME");
                System.out.println("  • " + fkColumn + " → " + pkTable + "." + pkColumn);
            }
            
            System.out.println("╚═══════════════════════════════════════════════════════╝");
            
        } catch (SQLException e) {
            System.err.println("[ERROR] Cannot retrieve table structure");
            e.printStackTrace();
        }
    }
    
    /**
     * Generate report of entire database
     */
    public static void generateDatabaseReport() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║         DATABASE SCHEMA REPORT - note_app            ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        
        List<String> tables = getAllTables();
        
        System.out.println("Total Tables: " + tables.size());
        System.out.println("\n" + "=".repeat(60));
        
        for (String table : tables) {
            showTableStructure(table);
            System.out.println();
        }
    }
    
    /**
     * Check which dashboard tables are missing
     */
    public static void checkRequiredTables() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║    CHECKING REQUIRED TABLES FOR DASHBOARD           ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        
        String[] requiredTables = {
            "Users",
            "Courses", 
            "Notes",
            "Note_Tags",
            "To_Do_List",
            "Calendar_Events",
            "User_Preferences"
        };
        
        List<String> existingTables = getAllTables();
        
        int foundCount = 0;
        int missingCount = 0;
        
        for (String required : requiredTables) {
            boolean exists = existingTables.stream()
                .anyMatch(t -> t.equalsIgnoreCase(required));
            
            if (exists) {
                System.out.println("  ✓ " + required + " - EXISTS");
                foundCount++;
            } else {
                System.out.println("  ✗ " + required + " - MISSING");
                missingCount++;
            }
        }
        
        System.out.println("\n" + "─".repeat(60));
        System.out.println("Summary:");
        System.out.println("  Found: " + foundCount + "/" + requiredTables.length);
        System.out.println("  Missing: " + missingCount + "/" + requiredTables.length);
        
        if (missingCount > 0) {
            System.out.println("\n⚠ You need to create " + missingCount + " table(s)");
            System.out.println("  Run: DatabaseSetup.addDashboardTables()");
        } else {
            System.out.println("\n✓ All required tables exist!");
        }
    }
}