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
    public User register(String email, String password, Integer courseId) 
            throws ValidationException, DatabaseException, DuplicateEntryException {
        
        //Validate inputs
        validateRegistration(email, password);
        
        String username = generateUsernameFromEmail(email);
        
        //Hash password
        String passwordHash = PasswordUtil.hashPassword(password);
        
        //Create user object
        User user = new User(username, email, passwordHash); 
        user.setCourseId(courseId);
        
        //Save to database
        return userDao.create(user);
    }
    
    /**
     * Generate username from email (extract part before @)
     */
    private String generateUsernameFromEmail(String email) {
        if (email == null || !email.contains("@")) {
            // Fallback if email is invalid
            return "user_" + (System.currentTimeMillis() % 10000);
        }
        
        String username = email.split("@")[0];
        
        // Clean up - remove special characters, keep only letters, numbers, underscores
        username = username.replaceAll("[^a-zA-Z0-9_]", "_");
        
        // Ensure proper length
        if (username.length() < 3) {
            username = username + "_" + (int)(Math.random() * 1000);
        } else if (username.length() > 20) {
            username = username.substring(0, 20);
        }
        
        return username;
    }
    
    /**
     * Register without course ID
     */
    public User register(String email, String password) 
            throws ValidationException, DatabaseException, DuplicateEntryException {
        return register(email, password, null);
    }
    
    /**
     * NEW: Login user with email
     */
    public User loginWithEmail(String email, String password) 
            throws AuthenticationException, DatabaseException {
        
        // 1. Find user by email
        User user = userDao.findByEmail(email);
        
        if (user == null) {
            throw new AuthenticationException("Invalid email or password");
        }
        
        // 2. Verify password
        if (!PasswordUtil.checkPassword(password, user.getPasswordHash())) {
            throw new AuthenticationException("Invalid email or password");
        }
        
        // 3. Set session
        SessionManager.setCurrentUser(user);
        
        return user;
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
    private void validateRegistration(String email, String password) 
            throws ValidationException {
        
        // Check required fields
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        
        // Validate email format
        if (!ValidationUtil.isValidEmail(email)) {
            throw new ValidationException("Invalid email format");
        }
        
        // Check password strength
        if (!PasswordUtil.isStrongPassword(password)) {
            throw new ValidationException(
                "Password must be at least 8 characters with uppercase, lowercase, and numbers"
            );
        }
    }
    
    /**
    * Get current user details
    */
   public User getCurrentUserDetails() throws DatabaseException {
       User user = getCurrentUser();
       if (user == null) {
           throw new DatabaseException("No user is logged in");
       }

       // Get fresh data from database
       return userDao.findById(user.getUserId());
   }

   /**
    * Update user account (username, email, and optionally password)
    */
   public boolean updateUserAccount(String newUsername, String newEmail, String newPassword) 
           throws ValidationException, DatabaseException {

       User currentUser = getCurrentUser();
       if (currentUser == null) {
           throw new DatabaseException("No user is logged in");
       }

       int userId = currentUser.getUserId();

       // Validate inputs
       if (!ValidationUtil.isValidUsername(newUsername)) {
           throw new ValidationException("Invalid username format");
       }

       if (!ValidationUtil.isValidEmail(newEmail)) {
           throw new ValidationException("Invalid email format");
       }

       // Get user from database
       User user = userDao.findById(userId);
       if (user == null) {
           throw new DatabaseException("User not found");
       }

       // Check if new username is already taken by another user
       User existingUser = userDao.findByUsername(newUsername);
       if (existingUser != null && existingUser.getUserId() != userId) {
           throw new ValidationException("Username already taken");
       }

       // Check if new email is already taken by another user
       existingUser = userDao.findByEmail(newEmail);
       if (existingUser != null && existingUser.getUserId() != userId) {
           throw new ValidationException("Email already taken");
       }

       // Update user info
       user.setUsername(newUsername);
       user.setEmail(newEmail);

       // Update password if provided
       if (newPassword != null && !newPassword.trim().isEmpty()) {
           if (!PasswordUtil.isStrongPassword(newPassword)) {
               throw new ValidationException(
                   "Password must be at least 8 characters with uppercase, lowercase, and numbers"
               );
           }
           String passwordHash = PasswordUtil.hashPassword(newPassword);
           user.setPasswordHash(passwordHash);
       }

       // Save to database
       boolean updated = userDao.update(user);

       // Update session with new user data
       if (updated) {
           SessionManager.setCurrentUser(user);
       }

       return updated;
   }

   /**
    * Get current user's email
    */
   public String getCurrentUserEmail() {
       User user = getCurrentUser();
       return user != null ? user.getEmail() : "";
   }

   /**
    * Get current user's username
    */
   public String getCurrentUsername() {
       User user = getCurrentUser();
       return user != null ? user.getUsername() : "";
   }
}