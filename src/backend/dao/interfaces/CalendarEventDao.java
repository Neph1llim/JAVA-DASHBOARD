package backend.dao.interfaces;

import backend.model.CalendarEvent;
import backend.exceptions.DatabaseException;
import java.time.LocalDate;
import java.util.List;

public interface CalendarEventDao {
    // Basic CRUD Operations
    CalendarEvent create(CalendarEvent event) throws DatabaseException;
    CalendarEvent findById(int eventId) throws DatabaseException;
    List<CalendarEvent> findByUserId(int userId) throws DatabaseException;
    boolean update(CalendarEvent event) throws DatabaseException;
    boolean delete(int eventId) throws DatabaseException;
    
    // Date-based queries (simplified for your schema)
    List<CalendarEvent> findByDate(int userId, LocalDate date) throws DatabaseException;
    List<CalendarEvent> findByDateRange(int userId, LocalDate start, LocalDate end) throws DatabaseException;
    List<CalendarEvent> findUpcomingEvents(int userId, int days) throws DatabaseException;
}