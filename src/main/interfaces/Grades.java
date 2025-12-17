package main.interfaces;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import main.component.Button;
import main.component.GradeTab;
import backend.services.CourseService;
import backend.services.AssessmentService;
import backend.model.Course;
import java.util.List;

public class Grades extends javax.swing.JPanel {
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<String> subName = new ArrayList<>();
    private String currentTab;
    private CourseService courseService;
    private AssessmentService assessmentService;
    private int currentUserId = 1;
    
    int buttonWidth = 150;
    int buttonHeight = 40;
    int minButtonWidth = 50;
    
    public Grades() {
        initComponents();
        gradeContainer.removeAll();
        gradeContainer.setLayout(new CardLayout());
        initializeDatabaseServices();
    }
    
    private void initializeDatabaseServices() {
        try {
            this.courseService = new CourseService();
            this.assessmentService = new AssessmentService();
            loadSavedCoursesFromDatabase();
        } catch (Exception e) {
            System.err.println("Failed to initialize database services: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                "Database not available. Grades will be saved locally only.",
                "Offline Mode",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void addSub() {
        String name = JOptionPane.showInputDialog(
            this,
            "Enter subject name:",
            "Add Subject",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (name == null) return;
        name = name.trim();
        
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Subject name cannot be empty!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        if (courseService != null) {
            saveCourseToDatabase(name);
        } else {
            addButton(name);
        }
    }
    
    private void saveCourseToDatabase(String courseName) {
        try {
            Course course = courseService.createCourse(currentUserId, courseName);
            if (course != null) {
                addCourseButton(courseName, course.getCourseId());
            } else {
                JOptionPane.showMessageDialog(this,
                    "Failed to save course to database. Adding locally only.",
                    "Database Error",
                    JOptionPane.WARNING_MESSAGE);
                addButton(courseName);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Database error: " + e.getMessage() + "\nAdding locally only.",
                "Database Error",
                JOptionPane.WARNING_MESSAGE);
            addButton(courseName);
        }
    }
    
    private void loadSavedCoursesFromDatabase() {
        if (courseService == null) return;
        
        try {
            List<Course> courses = courseService.getUserCourses(currentUserId);
            if (courses != null) {
                for (Course course : courses) {
                    addCourseButton(course.getCourseName(), course.getCourseId());
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading courses from database: " + e.getMessage());
        }
    }
    
    private void addCourseButton(String name, int courseId) {
        if (subName.contains(name)) {
            JOptionPane.showMessageDialog(
                this,
                "Subject already exists!",
                "Duplicate",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        subName.add(name);
        
        Button btn = new Button(name);
        buttons.add(btn);
        
        btn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btn.setMinimumSize(new Dimension(minButtonWidth, buttonHeight));
        btn.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        
        btn.addActionListener(e -> showTab(name));
        
        buttonContainer.add(btn);
        updateButtonContainerSize();
        buttonContainer.revalidate();
        buttonContainer.repaint();
        
        GradeTab gradeTab = new GradeTab();
        gradeTab.setCourseName(name);
        gradeTab.setCourseId(courseId);
        gradeTab.setName(name);
        
        if (!(gradeContainer.getLayout() instanceof CardLayout)) {
            gradeContainer.setLayout(new CardLayout());
        }
        
        gradeContainer.add(gradeTab, name);
        gradeContainer.revalidate();
        gradeContainer.repaint();
        
        revalidate();
        repaint();
        showTab(name);
    }
    
    public void addButton(String name) {
        addCourseButton(name, -1);
    }
    
    private void showTab(String name) {
        if (gradeContainer.getLayout() instanceof CardLayout card) {
            card.show(gradeContainer, name);
            currentTab = name;
            
            for (int i = 0; i < subName.size(); i++) {
                Button btn = buttons.get(i);
                if (btn != null) {
                    if (subName.get(i).equals(name)) {
                        btn.setBackground(new java.awt.Color(100, 100, 100));
                        btn.setForeground(new java.awt.Color(255, 255, 255));
                    } else {
                        btn.setBackground(new java.awt.Color(153, 153, 153));
                        btn.setForeground(new java.awt.Color(0, 0, 0));
                    }
                }
            }
        }
    }
    
    private void deleteTab() {
        if (currentTab == null) {
            JOptionPane.showMessageDialog(this,
                "No course selected to delete.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Store the course name and tab before deletion
        String courseNameToDelete = currentTab;  // Store this before any changes
        int indexToDelete = subName.indexOf(courseNameToDelete);

        if (indexToDelete == -1) {
            JOptionPane.showMessageDialog(this,
                "Could not find the course to delete.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Find the GradeTab to get the course ID
        GradeTab currentGradeTab = null;
        for (java.awt.Component comp : gradeContainer.getComponents()) {
            if (comp instanceof GradeTab && comp.getName() != null && 
                comp.getName().equals(courseNameToDelete)) {
                currentGradeTab = (GradeTab) comp;
                break;
            }
        }

        if (currentGradeTab == null) {
            JOptionPane.showMessageDialog(this,
                "Could not find the course component to delete.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        int courseId = currentGradeTab.getCourseId();

        // Ask for confirmation
        String message;
        if (courseId > 0) {
            message = String.format(
                "Are you sure you want to permanently delete the course:\n\n" +
                "Course: %s\n" +
                "This will delete ALL assessments for this course.\n" +
                "This action cannot be undone!",
                courseNameToDelete);
        } else {
            message = String.format(
                "Are you sure you want to delete the course:\n\n" +
                "Course: %s\n\n" +
                "This action cannot be undone!",
                courseNameToDelete);
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            message,
            "Confirm Permanent Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        boolean databaseDeleted = false;
        boolean databaseError = false;
        String errorMessage = "";

        // Delete from database if we have a valid course ID
        if (courseId > 0) {
            if (courseService == null || assessmentService == null) {
                JOptionPane.showMessageDialog(this,
                    "Database service not available. Course will be removed locally only.",
                    "Offline Mode",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    // Delete assessments first
                    boolean assessmentsDeleted = assessmentService.deleteAssessmentsByCourse(courseId);
                    System.out.println("Assessments deleted for course " + courseId + ": " + assessmentsDeleted);

                    // Delete the course
                    boolean courseDeleted = courseService.deleteCourse(courseId);
                    System.out.println("Course deleted " + courseId + ": " + courseDeleted);

                    if (courseDeleted) {
                        databaseDeleted = true;
                    } else {
                        databaseError = true;
                        errorMessage = "Failed to delete course from database. It may have already been deleted.";
                    }

                } catch (Exception e) {
                    databaseError = true;
                    errorMessage = "Error deleting from database: " + e.getMessage();
                    e.printStackTrace();
                }
            }
        }

        // Remove from UI regardless of database status
        removeCourseFromUI(courseNameToDelete, indexToDelete);

        // Show appropriate message based on what happened
        if (databaseError) {
            JOptionPane.showMessageDialog(this,
                errorMessage + "\n\nCourse '" + courseNameToDelete + "' has been removed locally only.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        } else if (databaseDeleted) {
            JOptionPane.showMessageDialog(this,
                String.format("Course '%s' and all its assessments have been permanently deleted from the database.", 
                             courseNameToDelete),
                "Delete Successful",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Local-only course (courseId <= 0)
            JOptionPane.showMessageDialog(this,
                String.format("Course '%s' has been removed locally.", courseNameToDelete),
                "Removed Locally",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void removeCourseFromUI(String courseNameToDelete, int indexToDelete) {
        // Remove button
        if (indexToDelete < buttons.size()) {
            Button btn = buttons.get(indexToDelete);
            if (btn != null) {
                buttonContainer.remove(btn);
            }
            buttons.remove(indexToDelete);
        }

        // Remove from arrays
        subName.remove(indexToDelete);

        // Remove GradeTab component
        for (java.awt.Component comp : gradeContainer.getComponents()) {
            if (comp instanceof GradeTab && comp.getName() != null && 
                comp.getName().equals(courseNameToDelete)) {
                gradeContainer.remove(comp);
                break;
            }
        }

        // Update UI
        updateButtonContainerSize();
        buttonContainer.revalidate();
        buttonContainer.repaint();
        container.revalidate();
        container.repaint();
        gradeContainer.revalidate();
        gradeContainer.repaint();

        // Show next tab if available
        if (!subName.isEmpty()) {
            showTab(subName.get(0));
        } else {
            currentTab = null;
        }
    }
    
    private void updateButtonContainerSize() {
        int totalWidth = Math.max(minButtonWidth * buttons.size(), 100);
        buttonContainer.setPreferredSize(new Dimension(totalWidth, buttonHeight + 10));
    }
            

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel1 = new main.component.Panel();
        jPanel1 = new javax.swing.JPanel();
        button1 = new main.component.Button();
        button2 = new main.component.Button();
        container = new javax.swing.JScrollPane();
        buttonContainer = new main.component.Panel();
        gradeContainer = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(480, 255));
        setPreferredSize(new java.awt.Dimension(1440, 810));

        panel1.setArc(0);
        panel1.setMinimumSize(new java.awt.Dimension(480, 255));
        panel1.setPanelBackground(new java.awt.Color(0, 0, 0));
        panel1.setPreferredSize(new java.awt.Dimension(1440, 810));
        panel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1440, 50));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        button1.setBackground(new java.awt.Color(153, 153, 153));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setText("Add Subject");
        button1.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        button1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button1.setMargin(new java.awt.Insets(0, 4, 0, 4));
        button1.setMaximumSize(new java.awt.Dimension(100, 30));
        button1.setPreferredSize(new java.awt.Dimension(100, 30));
        button1.addActionListener(this::button1ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(button1, gridBagConstraints);

        button2.setBackground(new java.awt.Color(153, 153, 153));
        button2.setForeground(new java.awt.Color(0, 0, 0));
        button2.setText("Remove");
        button2.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        button2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        button2.setPreferredSize(new java.awt.Dimension(100, 30));
        button2.addActionListener(this::button2ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(button2, gridBagConstraints);

        container.setBackground(new java.awt.Color(153, 153, 255));
        container.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        container.setToolTipText("");
        container.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        container.setMaximumSize(null);
        container.setMinimumSize(new java.awt.Dimension(10, 50));
        container.setViewportView(buttonContainer);

        buttonContainer.setArc(0);
        buttonContainer.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        buttonContainer.setMinimumSize(new java.awt.Dimension(100, 25));
        buttonContainer.setPanelBackground(new java.awt.Color(51, 51, 51));
        buttonContainer.setLayout(new javax.swing.BoxLayout(buttonContainer, javax.swing.BoxLayout.LINE_AXIS));
        container.setViewportView(buttonContainer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(container, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        panel1.add(jPanel1, gridBagConstraints);

        gradeContainer.setBackground(new java.awt.Color(21, 21, 23));
        gradeContainer.setMaximumSize(null);
        gradeContainer.setMinimumSize(new java.awt.Dimension(1000, 500));

        javax.swing.GroupLayout gradeContainerLayout = new javax.swing.GroupLayout(gradeContainer);
        gradeContainer.setLayout(gradeContainerLayout);
        gradeContainerLayout.setHorizontalGroup(
            gradeContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1440, Short.MAX_VALUE)
        );
        gradeContainerLayout.setVerticalGroup(
            gradeContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panel1.add(gradeContainer, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        addSub();
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        deleteTab();
    }//GEN-LAST:event_button2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Button button1;
    private main.component.Button button2;
    private main.component.Panel buttonContainer;
    private javax.swing.JScrollPane container;
    private javax.swing.JPanel gradeContainer;
    private javax.swing.JPanel jPanel1;
    private main.component.Panel panel1;
    // End of variables declaration//GEN-END:variables
}
