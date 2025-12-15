package main.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.interfaces.Notes;
import javax.swing.SwingUtilities;

public class cardCarousel extends javax.swing.JPanel {
    private ArrayList<JPanel> cards = new ArrayList<>();
    private ArrayList<Note> noteCards = new ArrayList<>(); // Store actual Note objects
    private int currentIndex = 0;
    private final int MAX_VISIBLE_CARDS = 4;
    private final int MAX_TOTAL_CARDS = 20;
    private JPanel placeholderCard;
    
    // Reference to Notes panel
    private Notes notesPanel;
    
    // Card size variables
    private final int CARD_WIDTH = 250;
    private final int CARD_HEIGHT = 250;
    
    // Padding
    private final int CARD_PADDING = 10;
    private final int CONTAINER_PADDING = 20;
    private final int HOLDER_PADDING = 10;
    
    // Container size calculations
    private final int CONTAINER_WIDTH = (CARD_WIDTH + CARD_PADDING) * MAX_VISIBLE_CARDS + 100;
    private final int CONTAINER_HEIGHT = CARD_HEIGHT + CONTAINER_PADDING;
    private final int HOLDER_WIDTH = (CARD_WIDTH + CARD_PADDING) * MAX_VISIBLE_CARDS;
    private final int HOLDER_HEIGHT = CARD_HEIGHT + HOLDER_PADDING;
    
    // Constructor without Notes panel (backward compatibility)
    public cardCarousel() {
        initComponents();
        setupButtons();
        createPlaceholderCard();
        updateDisplay();
    }
    
    // Constructor with Notes panel reference
    public cardCarousel(Notes notesPanel) {
        this.notesPanel = notesPanel;
        initComponents();
        setupButtons();
        createPlaceholderCard();
        updateDisplay();
    }
    
    // Create the placeholder card
    private void createPlaceholderCard() {
        placeholderCard = new JPanel(new BorderLayout());
        placeholderCard.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        placeholderCard.setBackground(new Color(240, 240, 245));
        placeholderCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 2),
            BorderFactory.createEmptyBorder(CARD_PADDING, CARD_PADDING, CARD_PADDING, CARD_PADDING)
        ));
        placeholderCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Plus icon
        JLabel plusLabel = new JLabel("+", JLabel.CENTER);
        plusLabel.setFont(new java.awt.Font("Segoe UI", 1, 64));
        plusLabel.setForeground(new Color(100, 100, 200));
        
        // Instruction text - updated to show "Add from Notes"
        JLabel instructionLabel = new JLabel("<html><center>Click to add<br>from Notes</center></html>", JLabel.CENTER);
        instructionLabel.setFont(new java.awt.Font("Segoe UI", 0, 14));
        instructionLabel.setForeground(new Color(120, 120, 180));
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(240, 240, 245));
        centerPanel.add(plusLabel, BorderLayout.CENTER);
        centerPanel.add(instructionLabel, BorderLayout.SOUTH);
        
        placeholderCard.add(centerPanel, BorderLayout.CENTER);
        
        // Add click listener to placeholder
        placeholderCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (cards.size() - 1 < MAX_TOTAL_CARDS) {
                    showNoteSelectionDialog();
                } else {
                    JOptionPane.showMessageDialog(cardCarousel.this,
                        "Maximum number of cards reached (" + MAX_TOTAL_CARDS + ")",
                        "Limit Reached",
                        JOptionPane.WARNING_MESSAGE);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                placeholderCard.setBackground(new Color(230, 230, 240));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                placeholderCard.setBackground(new Color(240, 240, 245));
            }
        });
        
        // Add placeholder to cards list
        cards.add(placeholderCard);
    }
    
    /**
     * Shows a dialog to select notes from the Notes panel
     */
    private void showNoteSelectionDialog() {
        if (notesPanel == null) {
            JOptionPane.showMessageDialog(this,
                "Notes panel reference is not available",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Get all available notes from the Notes panel
        // We need to add a method to Notes panel to get all notes
        // For now, we'll assume there's a way to access them
        
        // Create a simple selection dialog
        String[] options = {"Select from Existing Notes", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this,
            "Select a note to add to carousel",
            "Add Note to Carousel",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);
        
        if (choice == 0) {
            // In a real implementation, you would show a list of notes
            // For now, we'll simulate adding a note
            addNoteToCarousel("Sample Note Title", "Sample content...");
        }
    }
    
    /**
     * Adds a Note object to the carousel
     */
    public void addNoteToCarousel(Note note) {
        if (note == null) return;
        
        // Remove placeholder temporarily
        cards.remove(placeholderCard);
        
        // Create a carousel card from the Note
        JPanel carouselCard = createCarouselCardFromNote(note);
        cards.add(carouselCard);
        
        // Store the Note object
        noteCards.add(note);
        
        // Add placeholder back at the end
        cards.add(placeholderCard);
        
        // Update display
        updateDisplay();
        
        // Show success message
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this,
                "Note added to carousel: " + note.getNoteTitle(),
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        });
    }
    
    /**
     * Overloaded method for adding by title and content
     */
    public void addNoteToCarousel(String title, String content) {
        // Remove placeholder temporarily
        cards.remove(placeholderCard);
        
        // Create a new Note object
        Note note = new Note(title, content);
        
        // Create carousel card
        JPanel carouselCard = createCarouselCardFromNote(note);
        cards.add(carouselCard);
        
        // Store the Note object
        noteCards.add(note);
        
        // Add placeholder back at the end
        cards.add(placeholderCard);
        
        // Update display
        updateDisplay();
    }
    
    /**
     * Creates a carousel card from a Note object
     */
    private JPanel createCarouselCardFromNote(Note note) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 180, 220), 2),
            BorderFactory.createEmptyBorder(CARD_PADDING, CARD_PADDING, CARD_PADDING, CARD_PADDING)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Create a wrapper to display note content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        
        // Title (truncated if too long)
        String displayTitle = note.getNoteTitle();
        if (displayTitle.length() > 20) {
            displayTitle = displayTitle.substring(0, 17) + "...";
        }
        
        JLabel titleLabel = new JLabel("<html><center>" + displayTitle + "</center></html>", JLabel.CENTER);
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 16));
        titleLabel.setForeground(new Color(60, 80, 120));
        
        // Content preview (truncated)
        String contentPreview = note.getNoteContent();
        if (contentPreview.length() > 50) {
            contentPreview = contentPreview.substring(0, 47) + "...";
        }
        
        JLabel contentLabel = new JLabel("<html><center><small>" + contentPreview + "</small></center></html>", JLabel.CENTER);
        contentLabel.setFont(new java.awt.Font("Segoe UI", 0, 12));
        contentLabel.setForeground(Color.GRAY);
        
        // Note indicator
        JLabel noteLabel = new JLabel("Note #" + (noteCards.size() + 1), JLabel.CENTER);
        noteLabel.setFont(new java.awt.Font("Segoe UI", 0, 12));
        noteLabel.setForeground(new Color(100, 130, 200));
        
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(contentLabel, BorderLayout.CENTER);
        contentPanel.add(noteLabel, BorderLayout.SOUTH);
        
        card.add(contentPanel, BorderLayout.CENTER);
        
        // Add click listener to view/edit the note
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showNoteDetails(note);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(245, 245, 250));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
            }
        });
        
        return card;
    }
    
    /**
     * Shows details of a note when clicked
     */
    private void showNoteDetails(Note note) {
        String message = "<html><b>Title:</b> " + note.getNoteTitle() + 
                        "<br><b>Content:</b> " + note.getNoteContent().substring(0, Math.min(100, note.getNoteContent().length())) + 
                        (note.getNoteContent().length() > 100 ? "..." : "") + 
                        "</html>";
        
        String[] options = {"Edit Note", "Remove from Carousel", "Close"};
        int choice = JOptionPane.showOptionDialog(this,
            message,
            "Note Details",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[2]);
        
        if (choice == 0) {
            // Edit note - would open the note in editor
            JOptionPane.showMessageDialog(this,
                "Edit functionality would open the note editor",
                "Edit Note",
                JOptionPane.INFORMATION_MESSAGE);
        } else if (choice == 1) {
            // Remove from carousel
            removeCardByNote(note);
        }
    }
    
    /**
     * Removes a card from the carousel by its Note object
     */
    private void removeCardByNote(Note note) {
        int index = noteCards.indexOf(note);
        if (index >= 0) {
            // Remove from both lists
            noteCards.remove(index);
            cards.remove(index); // Same index because cards and noteCards are aligned
            
            // Update display
            updateDisplay();
            
            JOptionPane.showMessageDialog(this,
                "Note removed from carousel",
                "Removed",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Get count of real cards (excluding placeholder)
    private int getRealCardCount() {
        return cards.size() - 1; // Subtract placeholder
    }
    
    // Get all Note cards in the carousel
    public ArrayList<Note> getNoteCards() {
        return new ArrayList<>(noteCards);
    }
    
    // Get a specific Note by index
    public Note getNoteAt(int index) {
        if (index >= 0 && index < noteCards.size()) {
            return noteCards.get(index);
        }
        return null;
    }
    
    // Move left
    private void moveLeft() {
        if (currentIndex > 0) {
            currentIndex--;
            updateDisplay();
        }
    }
    
    // Move right
    private void moveRight() {
        if (cards.size() > MAX_VISIBLE_CARDS && currentIndex < cards.size() - MAX_VISIBLE_CARDS) {
            currentIndex++;
            updateDisplay();
        }
    }
    
    // Setup button listeners
    private void setupButtons() {
        // Left button (<)
        jButton1.addActionListener(e -> moveLeft());
        
        // Right button (>)
        jButton2.addActionListener(e -> moveRight());
    }
    
    // Update the display
    private void updateDisplay() {
        // Clear holder
        holder.removeAll();
        
        // Set layout for holder
        holder.setLayout(new FlowLayout(FlowLayout.LEFT, CARD_PADDING, CARD_PADDING));
        
        // Add visible cards (up to MAX_VISIBLE_CARDS)
        int endIndex = Math.min(currentIndex + MAX_VISIBLE_CARDS, cards.size());
        for (int i = currentIndex; i < endIndex; i++) {
            JPanel card = cards.get(i);
            holder.add(card);
        }
        
        // Update button states
        updateButtonStates();
        
        // Refresh the display
        holder.revalidate();
        holder.repaint();
    }
    
    // Update button enabled states
    private void updateButtonStates() {
        // Left button enabled if we can scroll left
        jButton1.setEnabled(currentIndex > 0);
        
        // Right button enabled if we can scroll right
        boolean canScrollRight = cards.size() > MAX_VISIBLE_CARDS && 
                                currentIndex < cards.size() - MAX_VISIBLE_CARDS;
        jButton2.setEnabled(canScrollRight);
        
        // Visual feedback when buttons are disabled
        if (!jButton1.isEnabled()) {
            jButton1.setForeground(Color.GRAY);
            jButton1.setBackground(new Color(220, 220, 220));
        } else {
            jButton1.setForeground(Color.BLACK);
            jButton1.setBackground(new Color(240, 240, 240));
        }
        
        if (!jButton2.isEnabled()) {
            jButton2.setForeground(Color.GRAY);
            jButton2.setBackground(new Color(220, 220, 220));
        } else {
            jButton2.setForeground(Color.BLACK);
            jButton2.setBackground(new Color(240, 240, 240));
        }
    }
    
    // Setter for Notes panel (can be called after construction)
    public void setNotesPanel(Notes notesPanel) {
        this.notesPanel = notesPanel;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        holder = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(CONTAINER_WIDTH, CONTAINER_HEIGHT));
        setPreferredSize(new java.awt.Dimension(CONTAINER_WIDTH, CONTAINER_HEIGHT));
        setRequestFocusEnabled(false);

        jScrollPane1.setOpaque(true);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        holder.setMinimumSize(new java.awt.Dimension(HOLDER_WIDTH, HOLDER_HEIGHT));
        holder.setPreferredSize(new java.awt.Dimension(HOLDER_WIDTH, HOLDER_HEIGHT));

        javax.swing.GroupLayout holderLayout = new javax.swing.GroupLayout(holder);
        holder.setLayout(holderLayout);
        holderLayout.setHorizontalGroup(
            holderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1034, Short.MAX_VALUE)
        );
        holderLayout.setVerticalGroup(
            holderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(holder, gridBagConstraints);

        jButton1.setText("<");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setText(">");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(jButton2, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel holder;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
