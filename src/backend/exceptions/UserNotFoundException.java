package backend.exceptions;

public class UserNotFoundException extends Exception {
    private String username;
    private String email;
    
    public UserNotFoundException(String message) {
        super(message);
    }
    
    public UserNotFoundException(String message, String username) {
        super(message + ": " + username);
        this.username = username;
    }
    
    public UserNotFoundException(String message, String username, String email) {
        super(message + ": " + username + " (" + email + ")");
        this.username = username;
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
}