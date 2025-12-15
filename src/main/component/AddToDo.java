package main.component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.*;
import javax.swing.*;


public class AddToDo extends javax.swing.JPanel {
  
    private boolean isEditing = false;
    private JTextField editField;
    
    public AddToDo() {
        initComponents();
        setupEditField();
        
        // Remove fixed width settings to make it flexible
        setMinimumSize(new Dimension(100, 60)); // Minimum width
        setPreferredSize(new Dimension(400, 60)); // Default preferred size
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Can expand horizontally
        
        // Also set for panel2 - make it flexible
        panel2.setMinimumSize(new Dimension(100, 60));
        panel2.setPreferredSize(new Dimension(400, 60));
        panel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
    }
    
    private void setupEditField() {
        // Creates a text field instead of using a dialog box
        editField = new JTextField();
        editField.setFont(new java.awt.Font("Segoe UI Variable", 1, 14));
        editField.setForeground(new java.awt.Color(255, 255, 255));
        editField.setBackground(new java.awt.Color(53, 54, 56));
        editField.setBorder(null);
        editField.setVisible(false);
        
        // Add to panel2 with proper constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new java.awt.Insets(5, 10, 5, 5);
        panel2.add(editField, gbc);
        
        // Add Enter key listener
        editField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    finishEditing();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cancelEditing();
                }
            }
        });
        
        // Focus listener
        editField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                // Small delay to allow Enter key to be processed first
                SwingUtilities.invokeLater(() -> {
                    if (isEditing) {
                        finishEditing();
                    }
                });
            }
        });
    }
    
    public void startEditing() {
        isEditing = true;
        
        editField.setText(jCheckBox1.getText());
        
        editField.setBounds(jCheckBox1.getBounds());
        
        editField.setVisible(true);
        jCheckBox1.setVisible(false);
        
        editField.requestFocusInWindow();
        editField.selectAll();
    }
    
    private void finishEditing() {
        if (!isEditing) return;
        
        isEditing = false;
        String newText = editField.getText().trim();
        
        // Update checkbox if text is not empty
        if (!newText.isEmpty()) {
            jCheckBox1.setText(newText);
        }
        
        // Switch back to checkbox
        editField.setVisible(false);
        jCheckBox1.setVisible(true);
    }
    
    private void cancelEditing() {
        isEditing = false;
        editField.setVisible(false);
        jCheckBox1.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel2 = new main.component.Panel();
        jCheckBox1 = new javax.swing.JCheckBox();
        button1 = new main.component.Button();
        button2 = new main.component.Button();

        setMinimumSize(new java.awt.Dimension(200, 50));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(300, 100));

        panel2.setMinimumSize(new java.awt.Dimension(250, 80));
        panel2.setPanelBackground(new java.awt.Color(53, 54, 56));
        panel2.setPreferredSize(new java.awt.Dimension(250, 70));
        panel2.setLayout(new java.awt.GridBagLayout());

        jCheckBox1.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Type something...");
        jCheckBox1.addActionListener(this::jCheckBox1ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        panel2.add(jCheckBox1, gridBagConstraints);

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/pencil.png"))); // NOI18N
        button1.addActionListener(this::button1ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        panel2.add(button1, gridBagConstraints);

        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/trash.png"))); // NOI18N
        button2.addActionListener(this::button2ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 5, 9);
        panel2.add(button2, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // Works as is
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
    // Delete button - removes a todo item
    if (getParent() != null) {
        javax.swing.JPanel parent = (javax.swing.JPanel) getParent();
        parent.remove(this);
        parent.revalidate();
        parent.repaint();
        
        if (parent.getParent() != null) {
            parent.getParent().revalidate();
            parent.getParent().repaint();
        }
    }     
    }//GEN-LAST:event_button2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        startEditing();
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Button button1;
    private main.component.Button button2;
    public javax.swing.JCheckBox jCheckBox1;
    private main.component.Panel panel2;
    // End of variables declaration//GEN-END:variables
}
