
package main.component;

import javax.swing.*;
import java.awt.*;

public class ToDo extends javax.swing.JPanel { 
    public ToDo() {
        initComponents();
        
        container.setPreferredSize(new Dimension(0, 0));
        container.setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
    }
    
    
    private void addNewTodoItem() {
        AddToDo newTodo = new AddToDo();
    
        newTodo.setAlignmentX(Component.LEFT_ALIGNMENT);

        newTodo.setMinimumSize(new Dimension(Integer.MAX_VALUE, 80));
        newTodo.setPreferredSize(new Dimension(0, 80));
        
        if (container.getComponentCount() == 0) {
            container.setPreferredSize(null); // Reset to natural size
            container.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        }
        
        container.add(Box.createRigidArea(new Dimension(0, 3)), 0);
        container.add(newTodo,1);

        container.revalidate();
        container.repaint();
        
        newTodo.startEditing();
        
        // Scroll to show the new item
        SwingUtilities.invokeLater(() -> {
            Rectangle rect = newTodo.getBounds();
            container.scrollRectToVisible(rect);
        });
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel12 = new main.component.Panel();
        panel1 = new main.component.Panel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        container = new javax.swing.JPanel();
        panel2 = new main.component.Panel();
        Add = new main.component.Button();

        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        panel12.setPanelBackground(new java.awt.Color(81, 84, 89));
        panel12.setLayout(new java.awt.GridBagLayout());

        panel1.setMinimumSize(new java.awt.Dimension(300, 80));
        panel1.setPanelBackground(new java.awt.Color(27, 27, 28));
        panel1.setPreferredSize(new java.awt.Dimension(434, 75));
        panel1.setLayout(new java.awt.GridBagLayout());

        jLabel21.setFont(new java.awt.Font("Segoe UI Variable", 1, 23)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("To Do List");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(26, 155, 34, 172);
        panel1.add(jLabel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 5, 0, 5);
        panel12.add(panel1, gridBagConstraints);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(300, 1));
        jScrollPane1.setOpaque(true);
        jScrollPane1.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(container);

        container.setBackground(new Color(81,84,89));
        container.setMaximumSize(new java.awt.Dimension(1000, 1000));
        container.setLayout(new javax.swing.BoxLayout(container, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(container);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panel12.add(jScrollPane1, gridBagConstraints);

        panel2.setMaximumSize(new java.awt.Dimension(2147483647, 60));
        panel2.setMinimumSize(new java.awt.Dimension(200, 60));
        panel2.setPanelBackground(new java.awt.Color(51, 51, 51));
        panel2.setPreferredSize(new java.awt.Dimension(200, 60));
        panel2.setVerifyInputWhenFocusTarget(false);
        panel2.setLayout(new java.awt.GridBagLayout());

        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/add.png"))); // NOI18N
        Add.addActionListener(this::AddActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panel2.add(Add, gridBagConstraints);
        Add.getAccessibleContext().setAccessibleName("add");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panel12.add(panel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        add(panel12, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        addNewTodoItem();
    }//GEN-LAST:event_AddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Button Add;
    private javax.swing.JPanel container;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JScrollPane jScrollPane1;
    private main.component.Panel panel1;
    private main.component.Panel panel12;
    private main.component.Panel panel2;
    // End of variables declaration//GEN-END:variables

}