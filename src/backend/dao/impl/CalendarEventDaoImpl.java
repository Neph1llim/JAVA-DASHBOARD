package backend.dao.impl;

import backend.dao.interfaces.CalendarEventDao;
import backend.model.CalendarEvent;
import backend.exceptions.DatabaseException;
import backend.db.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarEventDaoImpl implements CalendarEventDao {
    private final DatabaseConnection dbConnection;
    
    public CalendarEventDaoImpl() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    
    @Override
    public CalendarEvent create(CalendarEvent event) throws DatabaseException {
        String sql = "INSERT INTO Calendar_Events (user_id, title, description, " +
                    "event_date, event_time, reminder_minutes, color) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, event.getUserId());
            pstmt.setString(2, event.getTitle());
            pstmt.setString(3, event.getDescription());
            pstmt.setDate(4, Date.valueOf(event.getEventDate()));
            pstmt.setTime(5, Time.valueOf(event.getEventTime()));
            pstmt.setInt(6, event.getReminderMinutes());
            pstmt.setString(7, event.getColor());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        event.setEventId(rs.getInt(1));
                    }
                }
            }
            
            return event;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create calendar event: " + e.getMessage(), e);
        }
    }
    
    @Override
    public CalendarEvent findById(int eventId) throws DatabaseException {
        String sql = "SELECT * FROM Calendar_Events WHERE event_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, eventId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToEvent(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find calendar event: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CalendarEvent> findByUserId(int userId) throws DatabaseException {
        List<CalendarEvent> events = new ArrayList<>();
        String sql = "SELECT * FROM Calendar_Events WHERE user_id = ? ORDER BY event_date, event_time";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                events.add(mapResultSetToEvent(rs));
            }
            
            return events;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find user events: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CalendarEvent> findByDate(int userId, LocalDate date) throws DatabaseException {
        List<CalendarEvent> events = new ArrayList<>();
        String sql = "SELECT * FROM Calendar_Events WHERE user_id = ? AND event_date = ? " +
                    "ORDER BY event_time";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setDate(2, Date.valueOf(date));
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                events.add(mapResultSetToEvent(rs));
            }
            
            return events;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find events by date: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CalendarEvent> findByDateRange(int userId, LocalDate start, LocalDate end) throws DatabaseException {
        // FIXED: Added missing method implementation
        List<CalendarEvent> events = new ArrayList<>();
        String sql = "SELECT * FROM Calendar_Events WHERE user_id = ? AND " +
                    "event_date BETWEEN ? AND ? ORDER BY event_date, event_time";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setDate(2, Date.valueOf(start));
            pstmt.setDate(3, Date.valueOf(end));
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                events.add(mapResultSetToEvent(rs));
            }
            
            return events;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find events by date range: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CalendarEvent> findUpcomingEvents(int userId, int days) throws DatabaseException {
        // FIXED: Updated to use LocalDate instead of LocalDateTime
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(days);
        
        return findByDateRange(userId, today, futureDate);
    }
    
    @Override
    public boolean update(CalendarEvent event) throws DatabaseException {
        String sql = "UPDATE Calendar_Events SET title = ?, description = ?, " +
                    "event_date = ?, event_time = ?, reminder_minutes = ?, color = ? " +
                    "WHERE event_id = ? AND user_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, event.getTitle());
            pstmt.setString(2, event.getDescription());
            pstmt.setDate(3, Date.valueOf(event.getEventDate()));
            pstmt.setTime(4, Time.valueOf(event.getEventTime()));
            pstmt.setInt(5, event.getReminderMinutes());
            pstmt.setString(6, event.getColor());
            pstmt.setInt(7, event.getEventId());
            pstmt.setInt(8, event.getUserId());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update calendar event: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean delete(int eventId) throws DatabaseException {
        String sql = "DELETE FROM Calendar_Events WHERE event_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, eventId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete calendar event: " + e.getMessage(), e);
        }
    }
    
    private CalendarEvent mapResultSetToEvent(ResultSet rs) throws SQLException {
        CalendarEvent event = new CalendarEvent();
        
        event.setEventId(rs.getInt("event_id"));
        event.setUserId(rs.getInt("user_id"));
        event.setTitle(rs.getString("title"));
        event.setDescription(rs.getString("description"));
        
        // Map event_date and event_time columns
        Date eventDate = rs.getDate("event_date");
        if (eventDate != null) {
            event.setEventDate(eventDate.toLocalDate());
        }
        
        Time eventTime = rs.getTime("event_time");
        if (eventTime != null) {
            event.setEventTime(eventTime.toLocalTime());
        }
        
        event.setReminderMinutes(rs.getInt("reminder_minutes"));
        event.setColor(rs.getString("color"));
        
        return event;
    }
}