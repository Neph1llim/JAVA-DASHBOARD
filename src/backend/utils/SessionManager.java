package backend.utils;

import backend.model.User;

public class SessionManager {
    private static User currentUser;
    
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static void clearSession() {
        currentUser = null;
    }
    
    public static boolean isLoggedIn() {
        return getCurrentUser() != null;
    }
    
    public static int getCurrentUserId() {
        User user = getCurrentUser();
        return user != null ? user.getUserId() : -1;
    }
}