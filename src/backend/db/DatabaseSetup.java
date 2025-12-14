package backend.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Setup utility to add dashboard tables to existing note_app database
 */
public class DatabaseSetup {
    
    /**
     * Add dashboard tables to note_app database
     */
    public static boolean addDashboardTables() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║    ADDING DASHBOARD TABLES TO note_app               ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Check if already exists before creating
            boolean usersExists = tableExists(conn, "Users");
            
            if (usersExists) {
                System.out.println("⚠ Dashboard tables already exist. Skipping...");
                return true;
            }
            
            // Disable foreign key checks temporarily
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            
            // 1. Users table (if doesn't exist)
            createUsersTable(stmt);
            
            // 2. Courses table
            createCoursesTable(stmt);
            
            // 3. Notes table (enhance if exists, or create new)
            createNotesTable(stmt);
            
            // 4. Tags table
            createTagsTable(stmt);
            
            // 5. Note-Tag mapping
            createNoteTagMappingTable(stmt);
            
            // 6. Note files
            createNoteFilesTable(stmt);
            
            // 7. Todo list
            createTodoTable(stmt);
            
            // 8. Calendar events
            createCalendarTable(stmt);
            
            // 9. Widgets
            createWidgetsTable(stmt);
            
            // 10. User preferences
            createPreferencesTable(stmt);
            
            // Re-enable foreign key checks
            stmt.execute("SET FOREIGN_KEY_CHECKS=1");
            
            System.out.println("\n✓ All dashboard tables created successfully!");
            
            // Insert default data
            insertDefaultData(stmt);
            
            return true;
            
        } catch (SQLException e) {
            System.err.println("\n✗ Failed to create tables");
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Check if table exists
     */
    private static boolean tableExists(Connection conn, String tableName) throws SQLException {
        var rs = conn.getMetaData().getTables(null, null, tableName, null);
        return rs.next();
    }
    
    /**
     * Create Users table
     */
    private static void createUsersTable(Statement stmt) throws SQLException {
        System.out.println("Creating Users table...");
        
        String sql = """
            CREATE TABLE IF NOT EXISTS Users (
                user_id INT AUTO_INCREMENT PRIMARY KEY,
                email VARCHAR(255) UNIQUE NOT NULL,
                username VARCHAR(50) UNIQUE NOT NULL,
                password_hash VARCHAR(255) NULL,
                google_id VARCHAR(255) UNIQUE NULL,
                profile_picture_path VARCHAR(500) NULL,
                is_active BOOLEAN DEFAULT TRUE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                last_login TIMESTAMP NULL,
                
                INDEX idx_email (email),
                INDEX idx_username (username)
            ) ENGINE=InnoDB
            """;
        
        stmt.execute(sql);
        System.out.println("  ✓ Users table created");
    }
    
    /**
     * Create Courses table
     */
    private static void createCoursesTable(Statement stmt) throws SQLException {
        System.out.println("Creating Courses table...");
        
        String sql = """
            CREATE TABLE IF NOT EXISTS Courses (
                course_id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                course_name VARCHAR(100) NOT NULL,
                course_year VARCHAR(20),
                course_code VARCHAR(20),
                description TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                
                FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
                INDEX idx_user_courses (user_id)
            ) ENGINE=InnoDB
            """;
        
        stmt.execute(sql);
        System.out.println("  ✓ Courses table created");
    }
    
    /**
     * Create Notes table
     */
    private static void createNotesTable(Statement stmt) throws SQLException {
        System.out.println("Creating Notes table...");
        
        String sql = """
            CREATE TABLE IF NOT EXISTS Notes (
                note_id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                title VARCHAR(255) NOT NULL,
                content TEXT,
                color VARCHAR(7) DEFAULT '#FFFFFF',
                font_family VARCHAR(50) DEFAULT 'Segoe UI',
                font_size INT DEFAULT 14,
                is_pinned BOOLEAN DEFAULT FALSE,
                is_archived BOOLEAN DEFAULT FALSE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                deleted_at TIMESTAMP NULL,
                
                FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
                INDEX idx_user_notes (user_id, deleted_at),
                INDEX idx_pinned (user_id, is_pinned)
            ) ENGINE=InnoDB
            """;
        
        stmt.execute(sql);
        System.out.println("  ✓ Notes table created");
    }
    
    /**
     * Create Tags table
     */
    private static void createTagsTable(Statement stmt) throws SQLException {
        System.out.println("Creating Note_Tags table...");
        
        String sql = """
            CREATE TABLE IF NOT EXISTS Note_Tags (
                tag_id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                tag_name VARCHAR(50) NOT NULL,
                tag_color VARCHAR(7) DEFAULT '#808080',
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                
                FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
                UNIQUE KEY unique_user_tag (user_id, tag_name)
            ) ENGINE=InnoDB
            """;
        
        stmt.execute(sql);
        System.out.println("  ✓ Note_Tags table created");
    }
    
    /**
     * Create Note-Tag mapping table
     */
    private static void createNoteTagMappingTable(Statement stmt) throws SQLException {
        System.out.println("Creating Note_Tag_Mapping table...");
        
        String sql = """
            CREATE TABLE IF NOT EXISTS Note_Tag_Mapping (
                mapping_id INT AUTO_INCREMENT PRIMARY KEY,
                note_id INT NOT NULL,
                tag_id INT NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                
                FOREIGN KEY (note_id) REFERENCES Notes(note_id) ON DELETE CASCADE,
                FOREIGN KEY (tag_id) REFERENCES Note_Tags(tag_id) ON DELETE CASCADE,
                UNIQUE KEY unique_note_tag (note_id, tag_id)
            ) ENGINE=InnoDB
            """;
        
        stmt.execute(sql);
        System.out.println("  ✓ Note_Tag_Mapping table created");
    }
    
    /**
     * Create Note Files table
     */
    private static void createNoteFilesTable(Statement stmt) throws SQLException {
        System.out.println("Creating Note_Files table...");
        
        String sql = """
            CREATE TABLE IF NOT EXISTS Note_Files (
                file_id INT AUTO_INCREMENT PRIMARY KEY,
                note_id INT NOT NULL,
                file_path VARCHAR(500) NOT NULL,
                file_name VARCHAR(255) NOT NULL,
                file_type VARCHAR(50),
                file_size BIGINT,
                uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                
                FOREIGN KEY (note_id) REFERENCES Notes(note_id) ON DELETE CASCADE
            ) ENGINE=InnoDB
            """;
        
        stmt.execute(sql);
        System.out.println("  ✓ Note_Files table created");
    }
    
    /**
     * Create Todo table
     */
    private static void createTodoTable(Statement stmt) throws SQLException {
        System.out.println("Creating To_Do_List table...");
        
        String sql = """
            CREATE TABLE IF NOT EXISTS To_Do_List (
                todo_id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                task_title VARCHAR(255) NOT NULL,
                description TEXT,
                is_completed BOOLEAN DEFAULT FALSE,
                priority ENUM('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM',
                due_date DATETIME NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                completed_at TIMESTAMP NULL,
                
                FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
                INDEX idx_user_todos (user_id, is_completed)
            ) ENGINE=InnoDB
            """;
        
        stmt.execute(sql);
        System.out.println("  ✓ To_Do_List table created");
    }
    
    /**
     * Create Calendar table
     */
    private static void createCalendarTable(Statement stmt) throws SQLException {
        System.out.println("Creating Calendar_Events table...");
        
        String sql = """
            CREATE TABLE IF NOT EXISTS Calendar_Events (
                event_id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                title VARCHAR(255) NOT NULL,
                description TEXT,
                event_date DATE NOT NULL,
                event_time TIME NULL,
                reminder_minutes INT DEFAULT 15,
                color VARCHAR(7) DEFAULT '#5686FE',
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
                INDEX idx_user_events (user_id, event_date)
            ) ENGINE=InnoDB
            """;
        
        stmt.execute(sql);
        System.out.println("  ✓ Calendar_Events table created");
    }
    
    /**
     * Create Widgets table
     */
    private static void createWidgetsTable(Statement stmt) throws SQLException {
        System.out.println("Creating Widgets table...");
        
        String sql = """
            CREATE TABLE IF NOT EXISTS Widgets (
                widget_id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                widget_type VARCHAR(50) NOT NULL,
                position_x INT DEFAULT 0,
                position_y INT DEFAULT 0,
                width INT DEFAULT 200,
                height INT DEFAULT 200,
                is_visible BOOLEAN DEFAULT TRUE,
                settings_json TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                
                FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
            ) ENGINE=InnoDB
            """;
        
        stmt.execute(sql);
        System.out.println("  ✓ Widgets table created");
    }
    
    /**
     * Create User Preferences table
     */
    private static void createPreferencesTable(Statement stmt) throws SQLException {
        System.out.println("Creating User_Preferences table...");
        
        String sql = """
            CREATE TABLE IF NOT EXISTS User_Preferences (
                preference_id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                theme VARCHAR(20) DEFAULT 'DARK',
                primary_color VARCHAR(7) DEFAULT '#5686FE',
                secondary_color VARCHAR(7) DEFAULT '#1B1B1C',
                default_font VARCHAR(50) DEFAULT 'Segoe UI Semibold',
                sidebar_position ENUM('LEFT', 'RIGHT') DEFAULT 'LEFT',
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
                UNIQUE KEY unique_user_pref (user_id)
            ) ENGINE=InnoDB
            """;
        
        stmt.execute(sql);
        System.out.println("  ✓ User_Preferences table created");
    }
    
    public static boolean addMissingTables() {
    System.out.println("\n[DATABASE] Adding missing tables...");
    
    try (Connection conn = DatabaseConnection.getInstance().getConnection();
         Statement stmt = conn.createStatement()) {
        
        // 1. Note_Tag_Mapping (many-to-many relationship)
        stmt.execute("""
            CREATE TABLE IF NOT EXISTS Note_Tag_Mapping (
                mapping_id INT AUTO_INCREMENT PRIMARY KEY,
                note_id INT NOT NULL,
                tag_id INT NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                
                FOREIGN KEY (note_id) REFERENCES notes(note_id) ON DELETE CASCADE,
                FOREIGN KEY (tag_id) REFERENCES note_tags(tag_id) ON DELETE CASCADE,
                UNIQUE KEY unique_note_tag (note_id, tag_id),
                INDEX idx_note_tags (note_id),
                INDEX idx_tag_notes (tag_id)
            ) ENGINE=InnoDB
            """);
        System.out.println("  ✓ Note_Tag_Mapping created");
        
        // 2. Note_Files (file attachments)
        stmt.execute("""
            CREATE TABLE IF NOT EXISTS Note_Files (
                file_id INT AUTO_INCREMENT PRIMARY KEY,
                note_id INT NOT NULL,
                file_path VARCHAR(500) NOT NULL,
                file_name VARCHAR(255) NOT NULL,
                file_type VARCHAR(50),
                file_size BIGINT,
                uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                
                FOREIGN KEY (note_id) REFERENCES notes(note_id) ON DELETE CASCADE,
                INDEX idx_note_files (note_id)
            ) ENGINE=InnoDB
            """);
        System.out.println("  ✓ Note_Files created");
        
        // 3. Widgets
        stmt.execute("""
            CREATE TABLE IF NOT EXISTS Widgets (
                widget_id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                widget_type VARCHAR(50) NOT NULL,
                position_x INT DEFAULT 0,
                position_y INT DEFAULT 0,
                width INT DEFAULT 200,
                height INT DEFAULT 200,
                is_visible BIT(1) DEFAULT 1,
                settings_json TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                INDEX idx_user_widgets (user_id)
            ) ENGINE=InnoDB
            """);
        System.out.println("  ✓ Widgets created");
        
        System.out.println("\n✓ All missing tables added successfully!");
        return true;
        
    } catch (SQLException e) {
        System.err.println("✗ Failed to add missing tables");
        e.printStackTrace();
        return false;
    }
}
    
    /**
     * Insert default test data
     */
    private static void insertDefaultData(Statement stmt) throws SQLException {
        System.out.println("\nInserting default data...");
        
        // Insert test user (password: "password123")
        String insertUser = """
            INSERT IGNORE INTO Users (email, username, password_hash, is_active) 
            VALUES ('test@example.com', 'testuser', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewY5GyYPdY0aPNDK', TRUE)
            """;
        stmt.execute(insertUser);
        
        // Insert default preferences for user
        String insertPref = """
            INSERT IGNORE INTO User_Preferences (user_id) VALUES (1)
            """;
        stmt.execute(insertPref);
        
        System.out.println("  ✓ Default user created (email: test@example.com, password: password123)");
    }
}