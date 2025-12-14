
package main.component;

import javax.swing.*;
import java.awt.*;

public class ToDo extends javax.swing.JPanel {

    private JPanel todoContainer;
    private JScrollPane scrollPane;
    
    public ToDo() {
        initComponents();
        setupTodoContainer();
    }
    
    private void setupTodoContainer() {
        // For the list to go downwards instead of going right
        todoContainer = new JPanel();
        todoContainer.setLayout(new BoxLayout(todoContainer, BoxLayout.Y_AXIS));
        todoContainer.setBackground(new Color(81, 84, 89));
        
        // Scroll bar
        scrollPane = new JScrollPane(todoContainer);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(true);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Clear and rebuild panel12
        panel12.removeAll();
        panel12.setLayout(new BorderLayout());
        
        // Header
        panel12.add(panel1, BorderLayout.NORTH);
        
        // Scrollable list
        panel12.add(scrollPane, BorderLayout.CENTER);
        
        // button at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(81, 84, 89));
        buttonPanel.add(Add);
        panel12.add(buttonPanel, BorderLayout.SOUTH);
        
        panel12.revalidate();
        panel12.repaint();
    }
    
    private void addNewTodoItem() {
        AddToDo newTodo = new AddToDo();
        
        // Set width to account for scrollbar (make it 20px narrower)
        newTodo.setMaximumSize(new Dimension(600, 112)); // Changed from Integer.MAX_VALUE
        newTodo.setPreferredSize(new Dimension(600, 112));
        
        // Add to container at top
        todoContainer.add(newTodo, 0);
        
        // Adds some spacing
        todoContainer.add(Box.createRigidArea(new Dimension(0, 2)), 1);
        
        todoContainer.revalidate();
        todoContainer.repaint();
        
        // Scroll to top
        SwingUtilities.invokeLater(() -> {
            scrollPane.getVerticalScrollBar().setValue(0);
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel11 = new main.component.Panel();
        panel12 = new main.component.Panel();
        panel1 = new main.component.Panel();
        jLabel21 = new javax.swing.JLabel();
        Add = new main.component.Button();

        setOpaque(false);
        setLayout(new java.awt.CardLayout());

        panel12.setPanelBackground(new java.awt.Color(81, 84, 89));

        panel1.setPanelBackground(new java.awt.Color(27, 27, 28));

        jLabel21.setFont(new java.awt.Font("Segoe UI Variable", 1, 23)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("To Do List");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(251, 251, 251))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/add.png"))); // NOI18N
        Add.addActionListener(this::AddActionPerformed);

        javax.swing.GroupLayout panel12Layout = new javax.swing.GroupLayout(panel12);
        panel12.setLayout(panel12Layout);
        panel12Layout.setHorizontalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        panel12Layout.setVerticalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12Layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        Add.getAccessibleContext().setAccessibleName("add");

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel12, javax.swing.GroupLayout.PREFERRED_SIZE, 600, Short.MAX_VALUE)
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(panel11, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        addNewTodoItem();
    }//GEN-LAST:event_AddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Button Add;
    private javax.swing.JLabel jLabel21;
    private main.component.Panel panel1;
    private main.component.Panel panel11;
    private main.component.Panel panel12;
    // End of variables declaration//GEN-END:variables

}