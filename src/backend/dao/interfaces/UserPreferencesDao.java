package backend.dao.interfaces;

import backend.model.UserPreferences;
import backend.exceptions.DatabaseException;

public interface UserPreferencesDao {
    // CRUD Operations
    UserPreferences create(UserPreferences preferences) throws DatabaseException;
    UserPreferences findByUserId(int userId) throws DatabaseException;
    boolean update(UserPreferences preferences) throws DatabaseException;
    boolean delete(int userId) throws DatabaseException;
    
    // Specific updates
    boolean updateTheme(int userId, String theme) throws DatabaseException;
    boolean updateLanguage(int userId, String language) throws DatabaseException;
    boolean updateStudyGoal(int userId, int minutes) throws DatabaseException;
    boolean updateNotifications(int userId, boolean email, boolean push) throws DatabaseException;
    
    // Default preferences
    UserPreferences getDefaultPreferences(int userId);
}