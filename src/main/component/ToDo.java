
package main.component;

import javax.swing.*;
import java.awt.*;

public class ToDo extends javax.swing.JPanel { 
    private int height = 80;
    private int itemCount = 0;
    private final int MAX_MAIN_HEIGHT = 378; // Max height before scrolling
    
    public ToDo() {
        initComponents();
        
        // Set main panel to have 0 height initially
        main.setPreferredSize(new Dimension(0, 0));
        main.setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
    }
    
  
    private void addNewTodoItem() {
        AddToDo newTodo = new AddToDo();
        newTodo.setMainFrame(this);
        newTodo.setAlignmentX(Component.LEFT_ALIGNMENT);
        newTodo.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        newTodo.setPreferredSize(new Dimension(0, height));

        // Add spacing before the new item (if not the first item)
        if (itemCount > 0) {
            container.add(Box.createRigidArea(new Dimension(0, 2)));
        }

        // Add new item to the END (bottom) of the container
        container.add(newTodo);
        itemCount++;

        // Update the container and main panel sizes
        updateContainerSize();

        // Start editing the new todo item
        newTodo.startEditing();
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }

    private void updateContainerSize() {
        // Revalidate and repaint the container
        container.revalidate();
        container.repaint();

        // Calculate new height for main panel: height per item + spacing
        // Each AddToDo is 80px, plus 2px spacing between items
        int newMainHeight = itemCount * height + Math.max(0, (itemCount - 1) * 2);

        if (newMainHeight <= MAX_MAIN_HEIGHT) {
            // Still growing - set fixed height
            main.setPreferredSize(new Dimension(0, newMainHeight));
            main.setMaximumSize(new Dimension(Integer.MAX_VALUE, newMainHeight));
        } else {
            // Reached max height - let scroll pane handle it
            main.setPreferredSize(new Dimension(0, MAX_MAIN_HEIGHT));
            main.setMaximumSize(new Dimension(Integer.MAX_VALUE, MAX_MAIN_HEIGHT));
        }

        // Force layout recalculations
        main.revalidate();
        main.repaint();
        panel12.revalidate();
        panel12.repaint();
    }

    
    public void bawasanNotes(int ilan){
        itemCount = itemCount - ilan;
        updateContainerSize();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel12 = new main.component.Panel();
        panel1 = new main.component.Panel();
        jLabel21 = new javax.swing.JLabel();
        panel2 = new main.component.Panel();
        Add = new main.component.Button();
        main = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        container = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 1000));

        setMinimumSize(new java.awt.Dimension(310, 50));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(444, 50));
        setLayout(new java.awt.GridBagLayout());

        panel12.setMinimumSize(new java.awt.Dimension(310, 10));
        panel12.setPanelBackground(new java.awt.Color(81, 84, 89));
        panel12.setPreferredSize(new java.awt.Dimension(444, 10));
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(6, 5, 0, 5);
        panel12.add(panel1, gridBagConstraints);

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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panel2.add(Add, gridBagConstraints);
        Add.getAccessibleContext().setAccessibleName("add");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panel12.add(panel2, gridBagConstraints);

        main.setMaximumSize(new java.awt.Dimension(2147483647, 0));
        main.setMinimumSize(new java.awt.Dimension(310, 0));
        main.setOpaque(false);
        main.setPreferredSize(new java.awt.Dimension(0, 0));
        main.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(300, 0));
        jScrollPane1.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(container);

        container.setBackground(new Color(81,84,89));
        container.setMaximumSize(new java.awt.Dimension(1000, 500));
        container.setLayout(new javax.swing.BoxLayout(container, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(container);

        main.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panel12.add(main, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 1.3;
        panel12.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        add(panel12, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        addNewTodoItem();
    }//GEN-LAST:event_AddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Button Add;
    private javax.swing.JPanel container;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main;
    private main.component.Panel panel1;
    private main.component.Panel panel12;
    private main.component.Panel panel2;
    // End of variables declaration//GEN-END:variables

}