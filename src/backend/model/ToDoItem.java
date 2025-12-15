package backend.model;

import java.time.LocalDateTime;

public class ToDoItem {
    // Constants for status
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_CANCELLED = "CANCELLED";
    
    // Constants for priority
    public static final String PRIORITY_LOW = "LOW";
    public static final String PRIORITY_MEDIUM = "MEDIUM";
    public static final String PRIORITY_HIGH = "HIGH";
    public static final String PRIORITY_URGENT = "URGENT";
    
    private int itemId;
    private String title;
    private String description;
    private String priority;
    private String status;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private Integer courseId; // Optional
    private int userId;
    private boolean completed;
    
    // Constructors
    public ToDoItem() {
        this.priority = PRIORITY_MEDIUM;
        this.status = STATUS_PENDING;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }
    
    public ToDoItem(String title, String description, int userId) {
        this();
        this.title = title;
        this.description = description;
        this.userId = userId;
    }
    
    // Getters and Setters
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { 
        if (isValidPriority(priority)) {
            this.priority = priority;
        }
    }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { 
        if (isValidStatus(status)) {
            this.status = status;
            if (STATUS_COMPLETED.equals(status)) {
                this.completed = true;
                this.completedAt = LocalDateTime.now();
            } else if (STATUS_PENDING.equals(status) || STATUS_IN_PROGRESS.equals(status)) {
                this.completed = false;
            }
        }
    }
    
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }
    
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { 
        this.completed = completed;
        if (completed) {
            this.status = STATUS_COMPLETED;
            this.completedAt = LocalDateTime.now();
        }
    }
    
    // Validation methods
    private boolean isValidStatus(String status) {
        return STATUS_PENDING.equals(status) || 
               STATUS_IN_PROGRESS.equals(status) || 
               STATUS_COMPLETED.equals(status) || 
               STATUS_CANCELLED.equals(status);
    }
    
    private boolean isValidPriority(String priority) {
        return PRIORITY_LOW.equals(priority) || 
               PRIORITY_MEDIUM.equals(priority) || 
               PRIORITY_HIGH.equals(priority) || 
               PRIORITY_URGENT.equals(priority);
    }
    
    // Helper methods
    public boolean isOverdue() {
        if (dueDate == null || completed) return false;
        return LocalDateTime.now().isAfter(dueDate);
    }
    
    public boolean isDueToday() {
        if (dueDate == null || completed) return false;
        return dueDate.toLocalDate().equals(LocalDateTime.now().toLocalDate());
    }
    
    @Override
    public String toString() {
        return "ToDoItem{id=" + itemId + ", title='" + title + "', status=" + status + "}";
    }

    public String getTodoId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}