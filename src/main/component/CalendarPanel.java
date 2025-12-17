
package main.component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;


public class CalendarPanel extends javax.swing.JPanel {
    
    // Custom variables
    private DefaultTableModel tableModel;
    private YearMonth currentMonth;


    public CalendarPanel() {
        initComponents();
        initCalendar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calendarTable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(5, 0));
        setPreferredSize(new java.awt.Dimension(571, 81));
        setLayout(new java.awt.BorderLayout());

        calendarTable.setBackground(new java.awt.Color(153, 153, 153));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"
            }
        ));
        calendarTable.setViewportView(jTable1);

        add(calendarTable, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane calendarTable;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
    private void initCalendar() {
        currentMonth = YearMonth.now();
        
        // Get the model from the ACTUAL table (jTable1)
        tableModel = (DefaultTableModel) jTable1.getModel();
        
        // Set up the table
        jTable1.setRowHeight(50);
        jTable1.setDefaultRenderer(Object.class, new CalendarCellRenderer());
        
        // Optional: Style the table
        jTable1.setFont(new Font("Arial", Font.PLAIN, 12));
        jTable1.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        jTable1.setGridColor(Color.LIGHT_GRAY);
        
        updateCalendar();
    }
    
    public void setMonth(YearMonth month) {
        this.currentMonth = month;
        updateCalendar();
    }
    
    private void updateCalendar() {
        // Clear table
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                tableModel.setValueAt("", row, col);
            }
        }
        
        // Calculate and fill days
        LocalDate firstDay = currentMonth.atDay(1);
        int startCol = firstDay.getDayOfWeek().getValue() % 7; // 0 = Sunday
        int daysInMonth = currentMonth.lengthOfMonth();
        
        int row = 0;
        int col = startCol;
        
        for (int day = 1; day <= daysInMonth; day++) {
            tableModel.setValueAt(day, row, col);
            
            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }
        
        jTable1.repaint();
    }
    
    // Custom cell renderer
    private class CalendarCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
            
            c.setBackground(Color.WHITE);
            c.setForeground(Color.BLACK);
            setHorizontalAlignment(SwingConstants.CENTER);
            
            if (value instanceof Integer) {
                int day = (Integer) value;
                LocalDate date = currentMonth.atDay(day);
                
                // Highlight today
                if (date.equals(LocalDate.now())) {
                    c.setBackground(new Color(173, 216, 230)); // Light blue
                }
                
                // Weekend colors
                if (column == 0) { // Sunday
                    c.setForeground(Color.RED);
                } else if (column == 6) { // Saturday
                    c.setForeground(Color.BLUE);
                }
                
                setText(String.valueOf(day));
            } else {
                setText("");
            }
            
            return c;
        }
    }
}
