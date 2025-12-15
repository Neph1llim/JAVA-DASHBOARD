package main.interfaces;

/* import statements */
import java.awt.Desktop;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Dimension;
import javax.swing.JOptionPane;

public class Files extends javax.swing.JPanel {

    /* Contructors */
    public Files() {
        initComponents();
    }

    /* Built-in codes and functions */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        files = new javax.swing.JPanel();
        actionPanel = new main.component.Panel();
        addFile = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        fileScrollPane = new javax.swing.JScrollPane();
        filePanel = new javax.swing.JPanel();

        files.setBackground(new java.awt.Color(21, 21, 23));
        files.setPreferredSize(new java.awt.Dimension(1230, 860));
        files.setLayout(new java.awt.BorderLayout());

        actionPanel.setPanelBackground(new java.awt.Color(27, 27, 28));
        actionPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        addFile.setBackground(new java.awt.Color(102, 102, 102));
        addFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/add.png"))); // NOI18N
        addFile.setBorderPainted(false);
        addFile.setFocusPainted(false);
        addFile.addActionListener(this::addFileActionPerformed);
        actionPanel.add(addFile);

        deleteButton.setBackground(new java.awt.Color(102, 102, 102));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/trash.png"))); // NOI18N
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(this::deleteButtonActionPerformed);
        actionPanel.add(deleteButton);

        refreshButton.setBackground(new java.awt.Color(102, 102, 102));
        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/refresh.png"))); // NOI18N
        refreshButton.setBorderPainted(false);
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonActionPerformed);
        actionPanel.add(refreshButton);

        files.add(actionPanel, java.awt.BorderLayout.PAGE_END);

        fileScrollPane.setBorder(null);
        fileScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        filePanel.setBackground(new java.awt.Color(27, 27, 28));
        filePanel.setPreferredSize(new java.awt.Dimension(750, 540));
        filePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        fileScrollPane.setViewportView(filePanel);

        files.add(fileScrollPane, java.awt.BorderLayout.CENTER);

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
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setMultiSelectionEnabled(true);
    int result = fileChooser.showOpenDialog(this);
    
    if (result == JFileChooser.APPROVE_OPTION) {
        File[] selectedFiles = fileChooser.getSelectedFiles();
        
        for (File file : selectedFiles) {
            createFileButton(file);
        }
        
        filePanel.revalidate();
        filePanel.repaint();
    }
    }//GEN-LAST:event_addFileActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
    filePanel.revalidate();
    filePanel.repaint();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
    if (filePanel.getComponentCount() > 0) {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Delete all files?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            filePanel.removeAll();
            filePanel.revalidate();
            filePanel.repaint();
        }
    }
    }//GEN-LAST:event_deleteButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Panel actionPanel;
    private javax.swing.JButton addFile;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel filePanel;
    private javax.swing.JScrollPane fileScrollPane;
    private javax.swing.JPanel files;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables
private void createFileButton(File file) {
    main.component.Button fileButton = new main.component.Button();
    fileButton.setText(file.getName());
    fileButton.setToolTipText(file.getAbsolutePath());
    
    // Set size
    fileButton.setPreferredSize(new Dimension(150, 100));
    fileButton.setMaximumSize(new Dimension(150, 100));
    
    // Add click listener to open file
    fileButton.addActionListener(e -> openFile(file));
    
    filePanel.add(fileButton);
}

private void openFile(File file) {
    try {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, 
            "Cannot open file: " + ex.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
}
