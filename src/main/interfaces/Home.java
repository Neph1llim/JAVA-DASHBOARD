package main.interfaces;

// temporary lang to see if ToDo works


import main.component.ToDo;
import main.component.CalendarComponent;
import java.awt.GridBagConstraints;
import javax.swing.Box;

public class Home extends javax.swing.JPanel {

    private ToDo todoComponent;
    
    public Home() {
        initComponents();
        addToDoComponent();
        addCalendarComponent();
    }
    
    private void addToDoComponent() {
        todoComponent = new ToDo();
        
        todoComponent.setPreferredSize(new java.awt.Dimension(600, 600));
        
        GridBagConstraints filler = new GridBagConstraints();
        filler.gridx = 0;
        filler.gridy = 0;
        filler.weightx = 1;
        filler.weighty = 0;
        filler.fill = GridBagConstraints.BOTH;
        jPanel1.add(Box.createGlue(), filler);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.weightx = 0; 
        gbc.weighty = 0; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.insets = new java.awt.Insets(0, 10, 10, 10); 
        
        jPanel1.add(todoComponent, gbc);
        
        // Refresh
        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    private void addCalendarComponent() {
        CalendarComponent calendarComponent = new CalendarComponent();
        calendarComponent.setPreferredSize(new java.awt.Dimension(700, 600));
        
        // Add calendar to the right of ToDo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;  // Next column
        gbc.gridy = 0;  // Same row
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new java.awt.Insets(0, 10, 10, 10);
        
        jPanel1.add(calendarComponent, gbc);
        
        // Add a filler on the right to push everything left
        GridBagConstraints rightFiller = new GridBagConstraints();
        rightFiller.gridx = 2;
        rightFiller.gridy = 0;
        rightFiller.weightx = 1;
        rightFiller.weighty = 0;
        rightFiller.fill = GridBagConstraints.BOTH;
        jPanel1.add(Box.createGlue(), rightFiller);
        
        // Refresh
        jPanel1.revalidate();
        jPanel1.repaint();
    }


    /* Built-in codes and functions */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(21, 21, 23));
        jPanel1.setPreferredSize(new java.awt.Dimension(1230, 860));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1440, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
