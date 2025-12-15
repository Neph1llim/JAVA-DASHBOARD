/**
 *
 * @author Cyrus Wilson
 */
package backend.exceptions;

public class ValidationException extends Exception {
    private String fieldName;
    private String validationRule;
    
    public ValidationException(String message) {
        super(message);
    }
    
    public ValidationException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
    
    public ValidationException(String message, String fieldName, String validationRule) {
        super(message);
        this.fieldName = fieldName;
        this.validationRule = validationRule;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    public String getValidationRule() {
        return validationRule;
    }
    
    @Override
    public String toString() {
        if (fieldName != null && validationRule != null) {
            return "ValidationException [field=" + fieldName + ", rule=" + validationRule + "]: " + getMessage();
        } else if (fieldName != null) {
            return "ValidationException [field=" + fieldName + "]: " + getMessage();
        }
        return "ValidationException: " + getMessage();
    }
}