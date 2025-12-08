/* TO DO:
- change add button to button.java
*/
package main.interfaces;

/* Import statements */
import main.component.Panel;
import java.awt.*;
import javax.swing.*;


public class Notes extends javax.swing.JPanel {

    /* Constructors*/
    public Notes() {
        initComponents();
        
        // to round the add button // remove this when button is changed to button.java
        change.putClientProperty("JButton.buttonType", "roundRect");
        change.putClientProperty("JComponent.roundRect.arc", 100);        
        
    }

    /* Built-in codes and functions */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        notes = new javax.swing.JPanel();
        notePanel = new javax.swing.JPanel();
        change = new javax.swing.JButton();

        notes.setBackground(new java.awt.Color(21, 21, 23));
        notes.setPreferredSize(new java.awt.Dimension(1230, 860));
        notes.setLayout(new java.awt.GridBagLayout());

        notePanel.setBackground(new java.awt.Color(27, 27, 28));
        notePanel.setPreferredSize(new java.awt.Dimension(750, 540));

        javax.swing.GroupLayout notePanelLayout = new javax.swing.GroupLayout(notePanel);
        notePanel.setLayout(notePanelLayout);
        notePanelLayout.setHorizontalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        notePanelLayout.setVerticalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 400;
        gridBagConstraints.ipady = 300;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        notes.add(notePanel, gridBagConstraints);

        change.setBackground(new java.awt.Color(102, 102, 102));
        change.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/add.png"))); // NOI18N
        change.setBorderPainted(false);
        change.setFocusPainted(false);
        change.addActionListener(this::changeActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 20, 20);
        notes.add(change, gridBagConstraints);

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

    private void changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeActionPerformed
        JPanel round1 = new Panel(35, new Color(50,50,50));
        round1.setPreferredSize(new Dimension(250, 250));

        notePanel.add(round1);
        
        // Configuration for panels
        notePanel.setLayout(new FlowLayout());
        notePanel.revalidate();
        notePanel.repaint();
    }//GEN-LAST:event_changeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton change;
    private javax.swing.JPanel notePanel;
    private javax.swing.JPanel notes;
    // End of variables declaration//GEN-END:variables
}
