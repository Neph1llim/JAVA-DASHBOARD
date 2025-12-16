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

    /**
     * Creates new form CalendarPanel
     */
    public CalendarPanel() {
        initComponents();
        initCalendar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskList = new javax.swing.JList<>();
        calendarTable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel2.setLayout(new java.awt.BorderLayout());

        taskList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(taskList);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.LINE_START);

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
        jTable1.setShowGrid(false);
        calendarTable.setViewportView(jTable1);

        add(calendarTable, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane calendarTable;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JList<String> taskList;
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
        
        // Make the table transparent
        jTable1.setOpaque(false);
        jTable1.setBackground(new Color(0, 0, 0, 0));
        
        // Make grid completely transparent
        jTable1.setGridColor(new Color(0, 0, 0, 0)); // Fully transparent
        jTable1.setShowGrid(false); // Hide grid lines entirely
        
        // Make the table header transparent
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(0, 0, 0, 0));
        jTable1.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        
        calendarTable.setBackground(new Color(0, 0, 0, 0));
        
        updateCalendar();
    }
    
//    private void transparentTableHeader(){
//        calendarTable.setBackground(new Color(0, 0, 0, 0));
//    }
    
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

            // Call parent method to get the component
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

            // Remove all borders (to prevent any visible grid lines)
            label.setBorder(BorderFactory.createEmptyBorder());

            // Make the label transparent
            label.setOpaque(false);

            // Center the text
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);

            if (value instanceof Integer) {
                int day = (Integer) value;
                LocalDate date = currentMonth.atDay(day);

                // Set text
                label.setText(String.valueOf(day));

                // Set foreground color (text color)
                label.setForeground(Color.WHITE);

                // Weekend colors
                if (column == 0) { // Sunday
                    label.setForeground(Color.GRAY);
                } else if (column == 6) { // Saturday
                    label.setForeground(Color.GRAY);
                }

                // Highlight today - make this cell opaque with background color
                if (date.equals(LocalDate.now())) {
                    label.setOpaque(true);
                    label.setBackground(new Color(173, 216, 230, 200)); // Semi-transparent light blue
                }

            } else {
                label.setText("");
                label.setForeground(Color.WHITE);
            }

            // Optional: Add a subtle margin between cells for visual separation
            // (without using visible grid lines)
            label.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

            return label;
        }
    }
}
