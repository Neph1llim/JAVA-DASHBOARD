package main.interfaces;

/* Import statements */
import java.awt.*;
import main.component.AddNotes;


public class Notes extends javax.swing.JPanel {

    /* Constructors*/
    public Notes() {
        initComponents();
    }

    private void showPanel(String name){
        CardLayout card = (CardLayout) getLayout();
        card.show(this, name);
    }
    
    /* Built-in codes and functions */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        notes = new javax.swing.JPanel();
        button1 = new main.component.Button();
        notePanel = new javax.swing.JPanel();
        addnote = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        notes.setBackground(new java.awt.Color(21, 21, 23));
        notes.setPreferredSize(new java.awt.Dimension(1230, 860));
        notes.setLayout(new java.awt.GridBagLayout());

        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/add.png"))); // NOI18N
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

        notePanel.setBackground(new java.awt.Color(102, 102, 102));
        notePanel.setPreferredSize(new java.awt.Dimension(750, 540));

        javax.swing.GroupLayout notePanelLayout = new javax.swing.GroupLayout(notePanel);
        notePanel.setLayout(notePanelLayout);
        notePanelLayout.setHorizontalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
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
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 11);
        notes.add(notePanel, gridBagConstraints);

        add(notes, "card2");

        javax.swing.GroupLayout addnoteLayout = new javax.swing.GroupLayout(addnote);
        addnote.setLayout(addnoteLayout);
        addnoteLayout.setHorizontalGroup(
            addnoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 981, Short.MAX_VALUE)
        );
        addnoteLayout.setVerticalGroup(
            addnoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 652, Short.MAX_VALUE)
        );

        add(addnote, "note");
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        //showPanel("addNote");
        AddNotes newNote = new AddNotes();
        
        addnote.add(newNote);
        
        showPanel("newNote");
    }//GEN-LAST:event_button1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addnote;
    private main.component.Button button1;
    public static javax.swing.JPanel notePanel;
    private javax.swing.JPanel notes;
    // End of variables declaration//GEN-END:variables
}
