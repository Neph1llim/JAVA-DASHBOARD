package backend.dao.impl;

import backend.dao.interfaces.UserPreferencesDao;
import backend.model.UserPreferences;
import backend.exceptions.DatabaseException;
import backend.db.DatabaseConnection;

import java.sql.*;

public class UserPreferencesDaoImpl implements UserPreferencesDao {
    private final DatabaseConnection dbConnection;
    public UserPreferencesDaoImpl() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    
    @Override
    public UserPreferences create(UserPreferences preferences) throws DatabaseException {
        String sql = "INSERT INTO user_preferences (user_id, theme, language, daily_study_goal, " +
                    "email_notifications, push_notifications, default_view, pomodoro_duration, " +
                    "break_duration) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, preferences.getUserId());
            pstmt.setString(2, preferences.getTheme());
            pstmt.setString(3, preferences.getLanguage());
            pstmt.setInt(4, preferences.getDailyStudyGoal());
            pstmt.setBoolean(5, preferences.isEmailNotifications());
            pstmt.setBoolean(6, preferences.isPushNotifications());
            pstmt.setString(7, preferences.getDefaultView());
            pstmt.setInt(8, preferences.getPomodoroDuration());
            pstmt.setInt(9, preferences.getBreakDuration());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        preferences.setPreferenceId(rs.getInt(1));
                    }
                }
            }
            
            return preferences;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create user preferences: " + e.getMessage(), e);
        }
    }
    
    @Override
    public UserPreferences findByUserId(int userId) throws DatabaseException {
        String sql = "SELECT * FROM user_preferences WHERE user_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToPreferences(rs);
            }
            
            // Return default preferences if not found
            return getDefaultPreferences(userId);
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find user preferences: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean update(UserPreferences preferences) throws DatabaseException {
        String sql = "UPDATE user_preferences SET theme = ?, language = ?, daily_study_goal = ?, " +
                    "email_notifications = ?, push_notifications = ?, default_view = ?, " +
                    "pomodoro_duration = ?, break_duration = ? WHERE user_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, preferences.getTheme());
            pstmt.setString(2, preferences.getLanguage());
            pstmt.setInt(3, preferences.getDailyStudyGoal());
            pstmt.setBoolean(4, preferences.isEmailNotifications());
            pstmt.setBoolean(5, preferences.isPushNotifications());
            pstmt.setString(6, preferences.getDefaultView());
            pstmt.setInt(7, preferences.getPomodoroDuration());
            pstmt.setInt(8, preferences.getBreakDuration());
            pstmt.setInt(9, preferences.getUserId());
            
            int affectedRows = pstmt.executeUpdate();
            
            // If no rows were updated, create new preferences
            if (affectedRows == 0) {
                create(preferences);
                return true;
            }
            
            return affectedRows > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update user preferences: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean updateTheme(int userId, String theme) throws DatabaseException {
        String sql = "UPDATE user_preferences SET theme = ? WHERE user_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, theme);
            pstmt.setInt(2, userId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update theme: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean delete(int userId) throws DatabaseException {
        String sql = "DELETE FROM user_preferences WHERE user_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete user preferences: " + e.getMessage(), e);
        }
    }
    
    @Override
    public UserPreferences getDefaultPreferences(int userId) {
        UserPreferences preferences = new UserPreferences();
        preferences.setUserId(userId);
        preferences.setTheme("light");
        preferences.setLanguage("en");
        preferences.setDailyStudyGoal(120); // 2 hours
        preferences.setEmailNotifications(true);
        preferences.setPushNotifications(true);
        preferences.setDefaultView("dashboard");
        preferences.setPomodoroDuration(25);
        preferences.setBreakDuration(5);
        return preferences;
    }
    
    private UserPreferences mapResultSetToPreferences(ResultSet rs) throws SQLException {
        UserPreferences preferences = new UserPreferences();
        preferences.setPreferenceId(rs.getInt("preference_id"));
        preferences.setUserId(rs.getInt("user_id"));
        preferences.setTheme(rs.getString("theme"));
        preferences.setLanguage(rs.getString("language"));
        preferences.setDailyStudyGoal(rs.getInt("daily_study_goal"));
        preferences.setEmailNotifications(rs.getBoolean("email_notifications"));
        preferences.setPushNotifications(rs.getBoolean("push_notifications"));
        preferences.setDefaultView(rs.getString("default_view"));
        preferences.setPomodoroDuration(rs.getInt("pomodoro_duration"));
        preferences.setBreakDuration(rs.getInt("break_duration"));
        return preferences;
    }
    
    @Override
    public boolean updateNotifications(int userId, boolean emailNotifications, boolean pushNotifications) 
            throws DatabaseException {
        String sql = "UPDATE user_preferences SET email_notifications = ?, push_notifications = ? " +
                    "WHERE user_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, emailNotifications);
            pstmt.setBoolean(2, pushNotifications);
            pstmt.setInt(3, userId);

            int affectedRows = pstmt.executeUpdate();

            // If no preferences exist, create default ones with these notification settings
            if (affectedRows == 0) {
                UserPreferences defaultPrefs = getDefaultPreferences(userId);
                defaultPrefs.setEmailNotifications(emailNotifications);
                defaultPrefs.setPushNotifications(pushNotifications);
                create(defaultPrefs);
                return true;
            }

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to update notifications: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean updateLanguage(int userId, String language) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean updateStudyGoal(int userId, int minutes) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
