package backend.services;

import backend.dao.interfaces.CalendarEventDao;
import backend.dao.impl.CalendarEventDaoImpl;
import backend.model.CalendarEvent;
import backend.exceptions.ValidationException;
import backend.exceptions.DatabaseException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

public class CalendarService {
    private CalendarEventDao eventDao;
    
    public CalendarService() {
        this.eventDao = new CalendarEventDaoImpl();
    }
    
    // Simple validation helper methods
    private boolean isValidEventTitle(String title) {
        return title != null && !title.trim().isEmpty() && title.length() <= 255;
    }
    
    private boolean isValidColor(String color) {
        return color != null && color.matches("^#[0-9A-Fa-f]{6}$");
    }
    
    private boolean isValidReminderMinutes(int minutes) {
        return minutes >= 0 && minutes <= 1440; // 0 to 24 hours
    }
    
    public CalendarEvent createEvent(String title, String description, 
                                    LocalDate eventDate, LocalTime eventTime,
                                    int reminderMinutes, String color, int userId) 
            throws ValidationException {
        try {
            // Validation using your ValidationException class
            if (!isValidEventTitle(title)) {
                throw new ValidationException(
                    "Event title must be 1-255 characters", 
                    "title", 
                    "length(1-255)"
                );
            }
            
            if (eventDate == null) {
                throw new ValidationException("Event date is required", "eventDate", "required");
            }
            
            if (eventDate.isBefore(LocalDate.now())) {
                throw new ValidationException("Event date cannot be in the past", "eventDate", "future_date");
            }
            
            if (!isValidReminderMinutes(reminderMinutes)) {
                throw new ValidationException(
                    "Reminder minutes must be between 0 and 1440", 
                    "reminderMinutes", 
                    "range(0-1440)"
                );
            }
            
            if (color != null && !isValidColor(color)) {
                throw new ValidationException(
                    "Color must be in hex format (#RRGGBB)", 
                    "color", 
                    "hex_format"
                );
            }
            
            CalendarEvent event = new CalendarEvent();
            event.setTitle(title.trim());
            event.setDescription(description != null ? description.trim() : "");
            event.setEventDate(eventDate);
            event.setEventTime(eventTime != null ? eventTime : LocalTime.of(9, 0)); // Default 9 AM
            event.setReminderMinutes(reminderMinutes > 0 ? reminderMinutes : 15); // Default 15 mins
            event.setColor(color != null && isValidColor(color) ? color : "#5686FE"); // Default color
            event.setUserId(userId);
            
            return eventDao.create(event);
            
        } catch (DatabaseException e) {
            throw new RuntimeException("Failed to create calendar event: " + e.getMessage(), e);
        }
    }
    
    public List<CalendarEvent> getUserEvents(int userId) {
        try {
            return eventDao.findByUserId(userId);
        } catch (DatabaseException e) {
            throw new RuntimeException("Failed to get user events: " + e.getMessage(), e);
        }
    }
    
    public List<CalendarEvent> getEventsByDate(int userId, LocalDate date) {
        try {
            return eventDao.findByDate(userId, date);
        } catch (DatabaseException e) {
            throw new RuntimeException("Failed to get events by date: " + e.getMessage(), e);
        }
    }
    
    public List<CalendarEvent> getEventsByDateRange(int userId, LocalDate startDate, LocalDate endDate) {
        try {
            return eventDao.findByDateRange(userId, startDate, endDate);
        } catch (DatabaseException e) {
            throw new RuntimeException("Failed to get events by date range: " + e.getMessage(), e);
        }
    }
    
    public List<CalendarEvent> getUpcomingEvents(int userId, int daysAhead) {
        try {
            return eventDao.findUpcomingEvents(userId, daysAhead);
        } catch (DatabaseException e) {
            throw new RuntimeException("Failed to get upcoming events: " + e.getMessage(), e);
        }
    }
    
    public boolean updateEvent(CalendarEvent event) throws ValidationException {
        try {
            if (event == null) {
                throw new ValidationException("Event cannot be null", "event", "required");
            }
            
            if (!isValidEventTitle(event.getTitle())) {
                throw new ValidationException(
                    "Event title must be 1-255 characters", 
                    "title", 
                    "length(1-255)"
                );
            }
            
            if (event.getEventDate() == null) {
                throw new ValidationException("Event date is required", "eventDate", "required");
            }
            
            if (!isValidReminderMinutes(event.getReminderMinutes())) {
                throw new ValidationException(
                    "Reminder minutes must be between 0 and 1440", 
                    "reminderMinutes", 
                    "range(0-1440)"
                );
            }
            
            if (event.getColor() != null && !isValidColor(event.getColor())) {
                throw new ValidationException(
                    "Color must be in hex format (#RRGGBB)", 
                    "color", 
                    "hex_format"
                );
            }
            
            return eventDao.update(event);
            
        } catch (DatabaseException e) {
            throw new RuntimeException("Failed to update event: " + e.getMessage(), e);
        }
    }
    
    public boolean deleteEvent(int eventId) {
        try {
            return eventDao.delete(eventId);
        } catch (DatabaseException e) {
            throw new RuntimeException("Failed to delete event: " + e.getMessage(), e);
        }
    }
    
    public CalendarEvent getEventById(int eventId) {
        try {
            return eventDao.findById(eventId);
        } catch (DatabaseException e) {
            throw new RuntimeException("Failed to get event by ID: " + e.getMessage(), e);
        }
    }
    
    public boolean setEventReminder(int eventId, int reminderMinutes) throws ValidationException {
        try {
            CalendarEvent event = eventDao.findById(eventId);
            if (event == null) {
                throw new ValidationException("Event not found", "eventId", "exists");
            }
            
            if (!isValidReminderMinutes(reminderMinutes)) {
                throw new ValidationException(
                    "Reminder minutes must be between 0 and 1440", 
                    "reminderMinutes", 
                    "range(0-1440)"
                );
            }
            
            event.setReminderMinutes(reminderMinutes);
            return eventDao.update(event);
            
        } catch (DatabaseException e) {
            throw new RuntimeException("Failed to set reminder: " + e.getMessage(), e);
        }
    }
    
    public boolean updateEventColor(int eventId, String color) throws ValidationException {
        try {
            CalendarEvent event = eventDao.findById(eventId);
            if (event == null) {
                throw new ValidationException("Event not found", "eventId", "exists");
            }
            
            if (color != null && !isValidColor(color)) {
                throw new ValidationException(
                    "Color must be in hex format (#RRGGBB)", 
                    "color", 
                    "hex_format"
                );
            }
            
            event.setColor(color != null ? color : "#5686FE");
            return eventDao.update(event);
            
        } catch (DatabaseException e) {
            throw new RuntimeException("Failed to update event color: " + e.getMessage(), e);
        }
    }
    
    public boolean hasConflictingEvents(int userId, LocalDate date, LocalTime time) {
        try {
            List<CalendarEvent> events = eventDao.findByDate(userId, date);
            if (events.isEmpty()) {
                return false;
            }
            
            LocalTime checkTime = time != null ? time : LocalTime.of(9, 0);
            for (CalendarEvent event : events) {
                LocalTime eventTime = event.getEventTime();
                if (eventTime != null && 
                    Math.abs(eventTime.getHour() - checkTime.getHour()) < 2) {
                    return true;
                }
            }
            
            return false;
            
        } catch (DatabaseException e) {
            throw new RuntimeException("Failed to check for conflicts: " + e.getMessage(), e);
        }
    }
    
    public String getCalendarStatistics(int userId) {
        try {
            List<CalendarEvent> allEvents = eventDao.findByUserId(userId);
            List<CalendarEvent> upcomingEvents = eventDao.findUpcomingEvents(userId, 7);
            
            int totalEvents = allEvents.size();
            int upcomingCount = upcomingEvents.size();
            int todayCount = eventDao.findByDate(userId, LocalDate.now()).size();
            
            return String.format("Total: %d events, Today: %d, Next 7 days: %d", 
                               totalEvents, todayCount, upcomingCount);
            
        } catch (DatabaseException e) {
            return "Error loading statistics";
        }
    }
    
    // Test method
    public static void main(String[] args) {
        CalendarService service = new CalendarService();
        System.out.println("=== CALENDAR SERVICE TEST ===");
        
        try {
            // Example usage
            CalendarEvent testEvent = service.createEvent(
                "Test Meeting",
                "Team sync meeting",
                LocalDate.now().plusDays(1),
                LocalTime.of(14, 30),
                30,
                "#FF5733",
                1
            );
            
            System.out.println("Event created with ID: " + testEvent.getEventId());
            
        } catch (ValidationException e) {
            System.out.println("Validation error: " + e.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}