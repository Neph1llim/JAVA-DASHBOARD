
package main.interfaces;

public class ToDo extends javax.swing.JPanel {

    /**
     * Creates new form ToDo
     */
    public ToDo() {
        initComponents();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel10 = new main.component.Panel();
        jLabel19 = new javax.swing.JLabel();
        TaskName9 = new javax.swing.JTextField();
        AddTask9 = new main.component.Button();
        jLabel20 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        panel10.setArc(0);
        panel10.setPanelBackground(new java.awt.Color(0, 0, 0));
        panel10.setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("To Do List");

        TaskName9.addActionListener(this::TaskNameActionPerformed);

        AddTask9.setText("ADD");
        AddTask9.addActionListener(this::AddTaskActionPerformed);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Task List");

        javax.swing.GroupLayout panel10Layout = new javax.swing.GroupLayout(panel10);
        panel10.setLayout(panel10Layout);
        panel10Layout.setHorizontalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel10Layout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addGroup(panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel10Layout.createSequentialGroup()
                        .addGroup(panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel10Layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(jLabel19))
                            .addGroup(panel10Layout.createSequentialGroup()
                                .addComponent(TaskName9, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(AddTask9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(131, 131, 131))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel10Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(261, 261, 261))))
        );
        panel10Layout.setVerticalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel10Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TaskName9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddTask9, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addContainerGap(443, Short.MAX_VALUE))
        );

        add(panel10, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void AddTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTaskActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddTaskActionPerformed

    private void TaskNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaskNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TaskNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Button AddTask;
    private main.component.Button AddTask1;
    private main.component.Button AddTask2;
    private main.component.Button AddTask3;
    private main.component.Button AddTask4;
    private main.component.Button AddTask5;
    private main.component.Button AddTask6;
    private main.component.Button AddTask7;
    private main.component.Button AddTask8;
    private main.component.Button AddTask9;
    private javax.swing.JTextField TaskName;
    private javax.swing.JTextField TaskName1;
    private javax.swing.JTextField TaskName2;
    private javax.swing.JTextField TaskName3;
    private javax.swing.JTextField TaskName4;
    private javax.swing.JTextField TaskName5;
    private javax.swing.JTextField TaskName6;
    private javax.swing.JTextField TaskName7;
    private javax.swing.JTextField TaskName8;
    private javax.swing.JTextField TaskName9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private main.component.Panel panel1;
    private main.component.Panel panel10;
    private main.component.Panel panel2;
    private main.component.Panel panel3;
    private main.component.Panel panel4;
    private main.component.Panel panel5;
    private main.component.Panel panel6;
    private main.component.Panel panel7;
    private main.component.Panel panel8;
    private main.component.Panel panel9;
    // End of variables declaration//GEN-END:variables

}

