package main.interfaces;

/* Import statements */
import main.component.AddNotes;
import main.component.Note;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.StyledDocument;

public class Notes extends javax.swing.JPanel {
    private static final int CARD_WIDTH = 250;  // Fixed width
    private static final int CARD_HEIGHT = 250; // Fixed height
    private static final int HORIZONTAL_GAP = 15;
    private static final int VERTICAL_GAP = 15;
    
    private JPanel cardsContainer;
    private JScrollPane scrollPane;
    private List<Note> noteCards = new ArrayList<>();
    private Note noteToEdit = null; // Track which note is being edited
    
    /* Constructors*/
    public Notes() {
        initComponents();
        setupNotePanel();
    }
    
    private void setupNotePanel() {
        // Use FlowLayout with wrapping
        cardsContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(21, 21, 23));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        cardsContainer.setBackground(new Color(21, 21, 23)); // Match your dark theme
        cardsContainer.setOpaque(true);

        // Create scroll pane
        scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.getViewport().setBackground(new Color(21, 21, 23)); // Match dark theme
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Clear and setup notePanel
        notePanel.removeAll();
        notePanel.setOpaque(true);
        notePanel.setBackground(new Color(21, 21, 23)); // Match dark theme
        notePanel.setLayout(new BorderLayout());
        notePanel.add(scrollPane, BorderLayout.CENTER);

        notePanel.revalidate();
        notePanel.repaint();
    }
    
    /**
     * Adds a new note card to the grid
     */
    public void addNoteCard(Note noteCard) {
        if (cardsContainer != null) {
            // Set fixed size for the card
            noteCard.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
            noteCard.setMaximumSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
            
            // Add click listener for editing
            noteCard.setNoteClickListener(new Note.NoteClickListener() {
                @Override
                public void onNoteClicked(Note note) {
                    editNoteCard(note);
                }
            });
            
            // Add to container and list
            cardsContainer.add(noteCard);
            noteCards.add(noteCard);
            
            // Refresh layout
            cardsContainer.revalidate();
            cardsContainer.repaint();
            
            // Scroll to show new card
            SwingUtilities.invokeLater(() -> {
                JScrollBar vertical = scrollPane.getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
            });
        }
    }
    
    /**
     * Opens editor to edit an existing note
     */
    private void editNoteCard(Note noteCard) {
        // Store reference to note being edited
        noteToEdit = noteCard;
        
        // Create editor with existing note content
        AddNotes editNoteEditor = new AddNotes(this, noteCard);
        
        // Show editor
        showAddNoteEditor(editNoteEditor);
    }
    
    /**
     * Updates an existing note card
     */
    public void updateNoteCard(String newTitle, String newContent, StyledDocument styledDoc) {
         if (noteToEdit != null) {
            // Update the existing note directly
            noteToEdit.updateContent(newTitle, newContent, styledDoc);

            // Clear reference
            noteToEdit = null;

            // Refresh container
            cardsContainer.revalidate();
            cardsContainer.repaint();
        }
    }
    
    /**
     * Cancels editing without changes
     */
    public void cancelEdit() {
        noteToEdit = null;
    }
    
    /**
     * Removes a note card
     */
    public void removeNoteCard(Note noteCard) {
        if (cardsContainer != null && noteCards.contains(noteCard)) {
            cardsContainer.remove(noteCard);
            noteCards.remove(noteCard);
            
            // Refresh layout
            cardsContainer.revalidate();
            cardsContainer.repaint();
        }
    }
    
    private void showAddNoteEditor(AddNotes editor) {
        // Add to parent container with CardLayout
        Container parent = getParent();
        if (parent != null) {
            parent.add(editor, "addNoteEditor");
            CardLayout cl = (CardLayout) parent.getLayout();
            cl.show(parent, "addNoteEditor");
        }
    }
    
    /* Built-in codes and functions */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        notes = new javax.swing.JPanel();
        notePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        button1 = new main.component.Button();

        setLayout(new java.awt.CardLayout());

        notes.setBackground(new java.awt.Color(21, 21, 23));
        notes.setPreferredSize(new java.awt.Dimension(1230, 860));
        notes.setLayout(new java.awt.GridBagLayout());

        notePanel.setBackground(new java.awt.Color(102, 102, 102));
        notePanel.setOpaque(false);
        notePanel.setPreferredSize(new java.awt.Dimension(750, 540));

        javax.swing.GroupLayout notePanelLayout = new javax.swing.GroupLayout(notePanel);
        notePanel.setLayout(notePanelLayout);
        notePanelLayout.setHorizontalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 961, Short.MAX_VALUE)
        );
        notePanelLayout.setVerticalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 400;
        gridBagConstraints.ipady = 300;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        notes.add(notePanel, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setOpaque(false);

        button1.setBackground(new java.awt.Color(153, 153, 153));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(871, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        notes.add(jPanel1, gridBagConstraints);

        add(notes, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // Create AddNotes editor (passing 'this' reference for callback)
        AddNotes addNoteEditor = new AddNotes(this);
        
        // Show in card layout or separate frame
        showAddNoteEditor(addNoteEditor);

    }//GEN-LAST:event_button1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Button button1;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel notePanel;
    private javax.swing.JPanel notes;
    // End of variables declaration//GEN-END:variables
}
