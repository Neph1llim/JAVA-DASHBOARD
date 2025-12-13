package main.component;
import java.awt.event.*;
import javax.swing.*;


public class AddToDo extends javax.swing.JPanel {
  
    private boolean isEditing = false;
    private JTextField editField;
    
    public AddToDo() {
        initComponents();
        setupEditField();
    }
    
    private void setupEditField() {
        // Creates a text field instead of using a dialog box
        editField = new JTextField();
        editField.setFont(new java.awt.Font("Segoe UI Variable", 1, 14));
        editField.setForeground(new java.awt.Color(255, 255, 255));
        editField.setBackground(new java.awt.Color(53, 54, 56));
        editField.setBorder(null);
        editField.setVisible(false);
        
        // Position it exactly where checkbox is
        editField.setBounds(jCheckBox1.getBounds());
        
        // Add to panel2
        panel2.add(editField);
        
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
    
    private void startEditing() {
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

        panel2 = new main.component.Panel();
        jCheckBox1 = new javax.swing.JCheckBox();
        button1 = new main.component.Button();
        button2 = new main.component.Button();

        panel2.setPanelBackground(new java.awt.Color(53, 54, 56));

        jCheckBox1.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Type something...");
        jCheckBox1.addActionListener(this::jCheckBox1ActionPerformed);

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/pencil.png"))); // NOI18N
        button1.addActionListener(this::button1ActionPerformed);

        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/trash.png"))); // NOI18N
        button2.addActionListener(this::button2ActionPerformed);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JCheckBox jCheckBox1;
    private main.component.Panel panel2;
    // End of variables declaration//GEN-END:variables
}
