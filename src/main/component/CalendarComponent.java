package main.component;

public class CalendarComponent extends javax.swing.JPanel {
    private CalendarNav calendarNav;
    private CalendarPanel calendarPanel;
    
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
        
        this.add(calendarNav, java.awt.BorderLayout.NORTH);
        this.add(calendarPanel, java.awt.BorderLayout.CENTER);
        
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
