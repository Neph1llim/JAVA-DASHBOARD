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
import javax.swing.JPanel;

public class cardCarousel extends javax.swing.JPanel {
    private ArrayList<JPanel> cards = new ArrayList<>();
    private int currentIndex = 0;
    private final int MAX_VISIBLE_CARDS = 4;
    private final int MAX_TOTAL_CARDS = 20;
    private JPanel placeholderCard;
    
    // Card size variables
    private final int CARD_WIDTH = 250;
    private final int CARD_HEIGHT = 250;
    
    // Reduced padding to make cards taller relative to container
    private final int CARD_PADDING = 10;  // Reduced from 15
    private final int CONTAINER_PADDING = 20;  // Reduced from 60
    private final int HOLDER_PADDING = 10;  // Reduced from 30
    
    // Container size calculations with reduced padding
    private final int CONTAINER_WIDTH = (CARD_WIDTH + CARD_PADDING) * MAX_VISIBLE_CARDS + 100;
    private final int CONTAINER_HEIGHT = CARD_HEIGHT + CONTAINER_PADDING;  // Much closer to card height
    private final int HOLDER_WIDTH = (CARD_WIDTH + CARD_PADDING) * MAX_VISIBLE_CARDS;
    private final int HOLDER_HEIGHT = CARD_HEIGHT + HOLDER_PADDING;  // Much closer to card height
    
    public cardCarousel() {
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
            BorderFactory.createEmptyBorder(CARD_PADDING, CARD_PADDING, CARD_PADDING, CARD_PADDING)  // Use variable
        ));
        placeholderCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Plus icon
        JLabel plusLabel = new JLabel("+", JLabel.CENTER);
        plusLabel.setFont(new java.awt.Font("Segoe UI", 1, 64));
        plusLabel.setForeground(new Color(100, 100, 200));
        
        // Instruction text
        JLabel instructionLabel = new JLabel("<html><center>Click to add<br>new card</center></html>", JLabel.CENTER);
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
                if (cards.size() - 1 < MAX_TOTAL_CARDS) { // -1 because placeholder counts as a card
                    addRealCard();
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
    
    // Add a real card (to the left of placeholder)
    private void addRealCard() {
        // Remove placeholder temporarily
        cards.remove(placeholderCard);
        
        // Create and add new real card
        JPanel realCard = createRealCard("Card " + (getRealCardCount() + 1));
        cards.add(realCard);
        
        // Add placeholder back at the end
        cards.add(placeholderCard);
        
        // Update display
        updateDisplay();
    }
    
    // Get count of real cards (excluding placeholder)
    private int getRealCardCount() {
        return cards.size() - 1; // Subtract placeholder
    }
    
    // Create a real card
    private JPanel createRealCard(String title) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 180, 220), 2),
            BorderFactory.createEmptyBorder(CARD_PADDING, CARD_PADDING, CARD_PADDING, CARD_PADDING)  // Use variable
        ));
        
        // Title
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 16));
        titleLabel.setForeground(new Color(60, 80, 120));
        card.add(titleLabel, BorderLayout.CENTER);
        
        // Card number - FIXED: Use real card count
        JLabel numberLabel = new JLabel("#" + getRealCardCount(), JLabel.CENTER);
        numberLabel.setFont(new java.awt.Font("Segoe UI", 0, 14));
        numberLabel.setForeground(Color.GRAY);
        card.add(numberLabel, BorderLayout.SOUTH);
        
        return card;
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
        
        // Set layout for holder - use consistent spacing
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
