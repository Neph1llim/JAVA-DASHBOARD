package main.interfaces;

/* Import statements */
import java.awt.*;
import main.MainFrame;


public class Notes extends javax.swing.JPanel {

    /* Constructors*/
    public Notes() {
        initComponents();
    }

    private void showPanel(String name){
        CardLayout card = (CardLayout) MainFrame.Interface.getLayout();
        card.show(MainFrame.Interface, name);
    }
    
    /* Built-in codes and functions */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        notes = new javax.swing.JPanel();
        notePanel = new javax.swing.JPanel();
        button1 = new main.component.Button();

        notes.setBackground(new java.awt.Color(21, 21, 23));
        notes.setPreferredSize(new java.awt.Dimension(1230, 860));
        notes.setLayout(new java.awt.GridBagLayout());

        notePanel.setBackground(new java.awt.Color(102, 102, 102));
        notePanel.setPreferredSize(new java.awt.Dimension(750, 540));

        javax.swing.GroupLayout notePanelLayout = new javax.swing.GroupLayout(notePanel);
        notePanel.setLayout(notePanelLayout);
        notePanelLayout.setHorizontalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 857, Short.MAX_VALUE)
        );
        notePanelLayout.setVerticalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 400;
        gridBagConstraints.ipady = 300;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        notes.add(notePanel, gridBagConstraints);

        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/add.png"))); // NOI18N
        button1.setText("ADD ");
        button1.setArc(50);
        button1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        button1.setMargin(new java.awt.Insets(0, 5, 0, 0));
        button1.setMaximumSize(new java.awt.Dimension(84, 42));
        button1.setMinimumSize(new java.awt.Dimension(84, 42));
        button1.setPreferredSize(new java.awt.Dimension(84, 42));
        button1.addActionListener(this::button1ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 25, 25);
        notes.add(button1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notes, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notes, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        showPanel("addNote");
    }//GEN-LAST:event_button1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Button button1;
    public static javax.swing.JPanel notePanel;
    private javax.swing.JPanel notes;
    // End of variables declaration//GEN-END:variables
}
