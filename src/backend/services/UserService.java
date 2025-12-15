package backend.services;

import backend.dao.interfaces.UserDao;
import backend.dao.impl.UserDaoImpl;
import backend.model.User;
import backend.utils.PasswordUtil;
import backend.utils.ValidationUtil;
import backend.utils.SessionManager;
import backend.exceptions.AuthenticationException;
import backend.exceptions.ValidationException;
import backend.exceptions.DatabaseException;
import backend.exceptions.DuplicateEntryException;
import java.util.List;

public class UserService {
    private final UserDao userDao;
    
    public UserService() {
        this.userDao = new UserDaoImpl();
    }
    
    /**
     * Register a new user with validation
     */
    public User register(String username, String email, String password, String confirmPassword, Integer courseId) 
            throws ValidationException, DatabaseException, DuplicateEntryException {
        
        // 1. Validate inputs
        validateRegistration(username, email, password, confirmPassword);
        
        // 2. Hash password
        String passwordHash = PasswordUtil.hashPassword(password);
        
        // 3. Create user object
        User user = new User(username, email, passwordHash);
        user.setCourseId(courseId);
        
        // 4. Save to database
        return userDao.create(user);
    }
    
    /**
     * Register without course ID
     */
    public User register(String username, String email, String password, String confirmPassword) 
            throws ValidationException, DatabaseException, DuplicateEntryException {
        return register(username, email, password, confirmPassword, null);
    }
    
    /**
     * Login user with authentication
     */
    public User login(String username, String password) 
            throws AuthenticationException, DatabaseException {
        
        // 1. Find user by username
        User user = userDao.findByUsername(username);
        
        if (user == null) {
            throw new AuthenticationException("Invalid username or password");
        }
        
        // 2. Verify password
        if (!PasswordUtil.checkPassword(password, user.getPasswordHash())) {
            throw new AuthenticationException("Invalid username or password");
        }
        
        // 3. Set session
        SessionManager.setCurrentUser(user);
        
        return user;
    }
    
    /**
     * Get users by course ID
     */
    public List<User> getUsersByCourse(int courseId) throws DatabaseException {
        return userDao.findByCourseId(courseId);
    }
    
    /**
     * Update user's course ID
     */
    public boolean updateUserCourse(int userId, Integer courseId) throws DatabaseException {
        return userDao.updateCourseId(userId, courseId);
    }
    
    /**
     * Get current user's course ID
     */
    public Integer getCurrentUserCourseId() {
        User user = getCurrentUser();
        return user != null ? user.getCourseId() : null;
    }
    
    // ... REST OF THE UserService METHODS REMAIN THE SAME ...
    // Just add the new methods above and keep all existing methods
    
    /**
     * Logout current user
     */
    public void logout() {
        SessionManager.clearSession();
    }
    
    /**
     * Get current logged-in user
     */
    public User getCurrentUser() {
        return SessionManager.getCurrentUser();
    }
    
    /**
     * Check if user is logged in
     */
    public boolean isLoggedIn() {
        return SessionManager.isLoggedIn();
    }
    
    /**
     * Get current user ID
     */
    public int getCurrentUserId() {
        User user = getCurrentUser();
        return user != null ? user.getUserId() : -1;
    }
    
    /**
     * Update user profile
     */
    public boolean updateProfile(int userId, String username, String email) 
            throws ValidationException, DatabaseException {
        
        // Validate inputs
        if (!ValidationUtil.isValidUsername(username)) {
            throw new ValidationException("Invalid username format");
        }
        
        if (!ValidationUtil.isValidEmail(email)) {
            throw new ValidationException("Invalid email format");
        }
        
        // Get user
        User user = userDao.findById(userId);
        if (user == null) {
            throw new ValidationException("User not found");
        }
        
        // Update user info
        user.setUsername(username);
        user.setEmail(email);
        
        return userDao.update(user);
    }
    
    /**
     * Change password
     */
    public boolean changePassword(int userId, String currentPassword, String newPassword, String confirmPassword) 
            throws AuthenticationException, ValidationException, DatabaseException {
        
        // Validate new password
        if (!newPassword.equals(confirmPassword)) {
            throw new ValidationException("New passwords do not match");
        }
        
        if (!PasswordUtil.isStrongPassword(newPassword)) {
            throw new ValidationException("Password must be at least 8 characters with uppercase, lowercase, and numbers");
        }
        
        // Get user
        User user = userDao.findById(userId);
        if (user == null) {
            throw new AuthenticationException("User not found");
        }
        
        // Verify current password
        if (!PasswordUtil.checkPassword(currentPassword, user.getPasswordHash())) {
            throw new AuthenticationException("Current password is incorrect");
        }
        
        // Update password
        String newPasswordHash = PasswordUtil.hashPassword(newPassword);
        user.setPasswordHash(newPasswordHash);
        
        return userDao.update(user);
    }
    
    /**
     * Search users
     */
    public List<User> searchUsers(String keyword) throws DatabaseException {
        return userDao.searchUsers(keyword);
    }
    
    /**
     * Get all users (admin function)
     */
    public List<User> getAllUsers() throws DatabaseException {
        return userDao.findAll();
    }
    
    /**
     * Delete user (admin function)
     */
    public boolean deleteUser(int userId) throws DatabaseException {
        return userDao.delete(userId);
    }
    
    // Private validation methods
    private void validateRegistration(String username, String email, String password, String confirmPassword) 
            throws ValidationException {
        
        // Check required fields
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("Username is required");
        }
        
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        
        // Validate formats
        if (!ValidationUtil.isValidUsername(username)) {
            throw new ValidationException("Username must be 3-20 characters (letters, numbers, underscore, space only)");
        }
        
        if (!ValidationUtil.isValidEmail(email)) {
            throw new ValidationException("Invalid email format");
        }
        
        // Check password strength
        if (!PasswordUtil.isStrongPassword(password)) {
            throw new ValidationException("Password must be at least 8 characters with uppercase, lowercase, and numbers");
        }
        
        // Check password confirmation
        if (!password.equals(confirmPassword)) {
            throw new ValidationException("Passwords do not match");
        }
    }
}