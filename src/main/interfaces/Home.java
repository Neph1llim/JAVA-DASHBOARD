package main.interfaces;
        
import main.MainFrame;
import main.component.cardCarousel;

        
public class Home extends javax.swing.JPanel {
    private MainFrame mainFrame;
    private cardCarousel carousel; // Make it a field

    public Home() {
        initComponents();
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        connectCarousel();
    }

    private void connectCarousel() {
        Notes notesPanel = mainFrame.getNotesPanel();

        // Remove any existing components from jPanel4
        jPanel4.removeAll();

        // Create new carousel with Notes panel reference
        carousel = new cardCarousel(notesPanel);

        // Add it to the layout - CENTER position in BorderLayout
        jPanel4.add(carousel, java.awt.BorderLayout.CENTER);

        // Refresh the display
        jPanel4.revalidate();
        jPanel4.repaint();
        this.revalidate();
        this.repaint();
    }

    /* Built-in codes and functions */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        toDo2 = new main.component.ToDo();
        jPanel3 = new javax.swing.JPanel();
        calendarComponent1 = new main.component.CalendarComponent();
        jPanel4 = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(21, 21, 23));
        jPanel1.setPreferredSize(new java.awt.Dimension(1230, 860));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.CardLayout());
        jPanel2.add(toDo2, "card2");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel3.setLayout(new java.awt.CardLayout());

        calendarComponent1.setPreferredSize(new java.awt.Dimension(571, 300));
        jPanel3.add(calendarComponent1, "card2");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jPanel4, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1552, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.CalendarComponent calendarComponent1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private main.component.ToDo toDo2;
    // End of variables declaration//GEN-END:variables
}
