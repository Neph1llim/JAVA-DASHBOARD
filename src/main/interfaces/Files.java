package main.interfaces;

/* import statements */
import java.awt.*;
import javax.swing.*;
import main.component.Button;

public class Files extends javax.swing.JPanel {

    /* Contructors */
    public Files() {
        initComponents();
    }
    
    /* Built-in codes and functions */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        files = new javax.swing.JPanel();
        filePanel = new javax.swing.JPanel();
        addFile = new javax.swing.JButton();

        files.setBackground(new java.awt.Color(21, 21, 23));
        files.setPreferredSize(new java.awt.Dimension(1230, 860));
        files.setLayout(new java.awt.GridBagLayout());

        filePanel.setBackground(new java.awt.Color(27, 27, 28));
        filePanel.setPreferredSize(new java.awt.Dimension(750, 540));

        javax.swing.GroupLayout filePanelLayout = new javax.swing.GroupLayout(filePanel);
        filePanel.setLayout(filePanelLayout);
        filePanelLayout.setHorizontalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        filePanelLayout.setVerticalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 400;
        gridBagConstraints.ipady = 300;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        files.add(filePanel, gridBagConstraints);

        addFile.setBackground(new java.awt.Color(102, 102, 102));
        addFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/add.png"))); // NOI18N
        addFile.setBorderPainted(false);
        addFile.setFocusPainted(false);
        addFile.addActionListener(this::addFileActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 20, 20);
        files.add(addFile, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(files, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(files, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFileActionPerformed
        JButton file = new Button();
        file.setSize(125, 100);
        
        filePanel.add(file);
        // Configuration for panels
        filePanel.setLayout(new FlowLayout());
        filePanel.revalidate();
        filePanel.repaint();
    }//GEN-LAST:event_addFileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFile;
    private javax.swing.JPanel filePanel;
    private javax.swing.JPanel files;
    // End of variables declaration//GEN-END:variables
}
