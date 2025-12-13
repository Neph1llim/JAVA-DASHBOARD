package backend.exceptions;


public class DuplicateEntryException extends Exception {
    private String fieldName; //Declaration of field name ro handle duplicate entry
    private String duplicateValue; //Declaration of duplicate values
    
    public DuplicateEntryException(String message) {
    super (message);
    }
    
    // Optional, for test lang, will remove later if works wihtout this. Because error with only one constructor so I added one
    public DuplicateEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    
    public DuplicateEntryException(String message, String fieldName, String duplicateValue){
        super(message + " " + duplicateValue + "nag eexist na" + fieldName);
        this.fieldName = fieldName;
        this.duplicateValue = duplicateValue;
    }
    
    public String getFieldName(){
        return fieldName;   
    }
    
    public String getDuplicateView(){
        
        return fieldName;
    }
}
