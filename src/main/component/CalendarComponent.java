package main.component;

import javax.swing.JSplitPane;

public class CalendarComponent extends javax.swing.JPanel {
    private CalendarNav calendarNav;
    private CalendarPanel calendarPanel;
    private calendarTask taskPanel; // Add calendarTask panel
    private JSplitPane splitPane; // For resizable panels
    
    public CalendarComponent() {
        initComponents();
        setupCustomComponents();
        connectComponents();
    }
    
    private void setupCustomComponents() {
        // Remove the auto-generated layout and components
        this.removeAll();
        
        // Set our own layout
        this.setLayout(new java.awt.BorderLayout());
        
        // Create and add our components
        calendarNav = new CalendarNav();
        calendarPanel = new CalendarPanel();
        taskPanel = new calendarTask();
        
        // Create main content panel
        javax.swing.JPanel contentPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        contentPanel.add(calendarPanel, java.awt.BorderLayout.CENTER);
        contentPanel.add(taskPanel, java.awt.BorderLayout.EAST);
        
        // Set fixed width for task panel
        taskPanel.setPreferredSize(new java.awt.Dimension(300, 0));
        
        this.add(calendarNav, java.awt.BorderLayout.NORTH);
        this.add(contentPanel, java.awt.BorderLayout.CENTER);
        
        // Connect them
        connectComponents();
    }
    
    private void connectComponents() {
        // Check if calendarNav exists
        if (calendarNav == null) {
            System.err.println("ERROR: calendarNav is null in connectComponents()");
            return;
        }
        
        calendarNav.addMonthChangeListener(newMonth -> {
            if (calendarPanel != null) {
                calendarPanel.setMonth(newMonth);
            }
        });
        
        System.out.println("Calendar components connected successfully");
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
