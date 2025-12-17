package backend.model;

import java.time.LocalDate;

public class Course {
    private int courseId;
    private String courseName;
    private double courseGrades;
    private double finalGrade;
    private LocalDate createdAt;
    private int userId;
    
    // Constructors
    public Course() {}
    
    public Course(String courseName, double courseGrades, 
                  double finalGrade, LocalDate createdAt, int userId) {
        this.courseName = courseName;
        this.courseGrades = courseGrades;
        this.finalGrade = finalGrade;
        this.createdAt = createdAt;
        this.userId = userId;
    }
    
    // Getters and setters
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    
    public double getCourseGrades() { return courseGrades; }
    public void setCourseGrades(double courseGrades) { this.courseGrades = courseGrades; }
    
    public double getFinalGrade() { return finalGrade; }
    public void setFinalGrade(double finalGrade) { this.finalGrade = finalGrade; }
    
    public LocalDate getCreatedDate() { return createdAt; }
    public void setCreatedDate(LocalDate createdAt) { this.createdAt = createdAt; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}