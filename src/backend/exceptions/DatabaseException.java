/**
 *
 * @author Cyrus Wilson
 */
package backend.exceptions;

public class DatabaseException extends Exception {
    private String sqlState;
    
    public DatabaseException(String message) {
        super(message);
    }
    
    public DatabaseException(String message, Throwable cause){
        super(message, cause);
    }
    
    public DatabaseException(String message, String sqlState, Throwable cause) {
        super(message, cause);
        this.sqlState = sqlState;
    }
    
    public String getSqlState() {
        return sqlState;
    }
    
    @Override
    public String toString() {
        if (sqlState != null) {
            return "DatabaseException [sqlState=" + sqlState + "] " + getMessage();
        }
        return "DatabaseException: " + getMessage();
    }
}