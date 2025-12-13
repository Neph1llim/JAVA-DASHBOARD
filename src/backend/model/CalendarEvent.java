package backend.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class CalendarEvent {
    private int eventId;
    private String title;
    private String description;
    private LocalDate eventDate;
    private LocalTime eventTime;      
    private int reminderMinutes = 15; 
    private String color = "#5686FE";
    private int userId;
    
    // Constructors
    public CalendarEvent() {
        this.color = "#5686FE"; // Default from your schema
        this.reminderMinutes = 15; // Default from your schema
    }
    
    public CalendarEvent(String title, String description, LocalDate eventDate, 
                        LocalTime eventTime, int userId) {
        this();
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.userId = userId;
    }
    
    // Getters and Setters
    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public LocalTime getEventTime() { return eventTime; }
    public void setEventTime(LocalTime eventTime) { this.eventTime = eventTime; }
    
    // Helper method for local date time (Not needed as of now but soon)
//    public LocalDateTime getEventDateTime() {
//        if (eventDate != null && eventTime != null) {
//            return LocalDateTime.of(eventDate, eventTime);
//        }
//        return null;
//    }
    
    public int getReminderMinutes() { return reminderMinutes; }
    public void setReminderMinutes(int reminderMinutes) { this.reminderMinutes = reminderMinutes; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    @Override
    public String toString() {
        return "CalendarEvent{id=" + eventId + ", title='" + title + "', date=" + eventDate + 
               ", time=" + eventTime + ", user=" + userId + "}";
    }
}