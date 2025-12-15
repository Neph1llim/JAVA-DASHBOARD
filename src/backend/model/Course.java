package backend.model;

import java.time.LocalDate;

public class Course {
    private int courseId, courseYear;
    private String courseName;
    private double courseGrades;
    private LocalDate createdAt;
    private int userId;
    
    // Constructors
    public Course() {}
    
    public Course(String courseName, int courseYear, double courseGrades, LocalDate createdAt, int userId) {
        this.courseName = courseName;
        this.courseYear = courseYear;
        this.courseGrades = courseGrades;
        this.createdAt = createdAt;
        this.userId = userId;
    }
    
    // Getters and setters for all fields...
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    
    public int getCourseYear() { return courseYear; }
    public void setCourseYear(int courseYear){ this.courseYear = courseYear; }
    
    public double getCourseGrades() { return courseGrades; }
    public void setCourseGrades(double courseGrades) { this.courseGrades = courseGrades; }
    
    public LocalDate getCreatedDate() { return createdAt; }
    public void setCreatedDate(LocalDate createdAt) { this.createdAt = createdAt; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}