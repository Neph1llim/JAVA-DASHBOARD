package main.component;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import main.interfaces.Notes;

public class cardCarousel extends javax.swing.JPanel {
    // Store CarouselCard objects instead of Note objects
    private ArrayList<CarouselCard> carouselCards = new ArrayList<>();
    private int currentIndex = 0;
    private final int MAX_VISIBLE_CARDS = 4; // CHANGED: Now showing 4 note cards + Add Card
    private final int MAX_TOTAL_CARDS = 20;
    private JPanel addCardPlaceholder;
    private javax.swing.JPanel holder; 
    
    // Reference to Notes panel
    private Notes notesPanel;
    
    // Card size variables
    private final int CARD_WIDTH = 275;
    private final int CARD_HEIGHT = 300;
    
    // Padding - Simplified and consistent
    private final int CARD_PADDING = 5; // Padding between cards AND inside cards
    private final int CONTAINER_PADDING = 2; // Outer padding for the entire carousel
    private final int INSET = 35;    
    private final int CONTAINER_HEIGHT = CARD_HEIGHT + (CONTAINER_PADDING * 2) + INSET;
    
    // MIN/MAX size for holder
    private final int MAX_CARDS_WIDTH = (CARD_WIDTH + CARD_PADDING) * (MAX_VISIBLE_CARDS); // Max 5 cards width
    
    // Track currently focused card
    private CarouselCard currentlyFocusedCard = null;
    
    // Dark theme colors - Make card colors darker than background
    private final Color CAROUSEL_BG = new Color(81, 84, 89);
    private final Color TRANSPARENT_BG = new Color(0, 0, 0, 0);
    private final Color DARK_CARD_BACKGROUND = new Color(60, 63, 68);
    private final Color ADD_CARD_BACKGROUND = new Color(60, 63, 68, 220);
    private final Color DARK_CARD_HOVER = new Color(70, 73, 78, 240);
    private final Color DARK_CARD_FOCUS = new Color(80, 90, 100, 240);
    private final Color DARK_TEXT = new Color(230, 230, 230);
    private final Color THEME_BORDER = new Color(120, 123, 128);
    private final Color THEME_BORDER_HOVER = new Color(140, 143, 148);
    private final Color BUTTON_BG = new Color(70, 70, 75, 200);
    private final Color BUTTON_HOVER = new Color(90, 90, 95, 220);
    private final Color BUTTON_INDICATOR = new Color(28, 151, 234);
    private final Color TITLE_SEPARATOR_COLOR = new Color(100, 103, 108);
    
    // Constructor
    public cardCarousel(Notes notesPanel) {
        this.notesPanel = notesPanel;
        initialize();
    }

    public cardCarousel() {
        initialize();
    }

    private void initialize() {
        initComponents();
        holder = new JPanel();
        setupButtons();
        createAddCardPlaceholder();
        createPanel();
        updateDisplay();
    }

    private void createPanel() {
        configureHolder();
        setupHolderMouseListener();
        addHolderToPanel();
        applyDarkTransparentTheme();
    }
    
    private void configureHolder() {
        // Use a panel with FlowLayout for proper card placement
        holder.setLayout(new FlowLayout(FlowLayout.LEFT, CARD_PADDING, 0));
        holder.setBackground(TRANSPARENT_BG);
        holder.setOpaque(false);
        holder.setPreferredSize(new Dimension(MAX_CARDS_WIDTH, CARD_HEIGHT));
        holder.setMaximumSize(new Dimension(MAX_CARDS_WIDTH, CARD_HEIGHT));
        holder.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));  
    }
    
    private void setupHolderMouseListener() {
        holder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loseFocusOnCards();
            }
        });
    }
    
    private void addHolderToPanel() {
        GridBagConstraints gridBagConstraints = createHolderConstraints();
        jPanel1.add(holder, gridBagConstraints);
    }
    
    private GridBagConstraints createHolderConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(CONTAINER_PADDING + INSET, CONTAINER_PADDING, (CONTAINER_PADDING + 41), CONTAINER_PADDING);
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }
    
    private void applyDarkTransparentTheme() {
        setBackground(CAROUSEL_BG);
        jPanel1.setBackground(CAROUSEL_BG);
        styleNavigationButton(jButton1);
        styleNavigationButton(jButton2);
    }
    
    private void styleNavigationButton(JButton button) {
        button.setBackground(BUTTON_BG);
        button.setForeground(DARK_TEXT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled()) button.setBackground(BUTTON_HOVER);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (button.isEnabled()) button.setBackground(BUTTON_BG);
            }
        });
    }
    
    // Create the "Add Card" placeholder panel
    private void createAddCardPlaceholder() {
        addCardPlaceholder = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw background with square corners
                g2d.setColor(ADD_CARD_BACKGROUND);
                g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                
                // Draw dashed border
                g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 
                                              0, new float[]{5, 5}, 0));
                g2d.setColor(THEME_BORDER);
                g2d.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
                
                // Draw plus sign
                drawPlusSign(g2d);
                
                // Draw text
                drawAddCardText(g2d);
            }
            
            private void drawPlusSign(Graphics2D g2d) {
                g2d.setColor(THEME_BORDER);
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int plusSize = 40;
                int lineWidth = 4;
                
                g2d.setStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2d.drawLine(centerX - plusSize/2, centerY, centerX + plusSize/2, centerY);
                g2d.drawLine(centerX, centerY - plusSize/2, centerX, centerY + plusSize/2);
            }
            
            private void drawAddCardText(Graphics2D g2d) {
                g2d.setColor(DARK_TEXT);
                g2d.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                String text = "Add Card";
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(text);
                g2d.drawString(text, getWidth()/2 - textWidth/2, getHeight()/2 + 60);
            }
        };
        
        setCardSize(addCardPlaceholder);
        addCardPlaceholder.setBackground(TRANSPARENT_BG);
        addCardPlaceholder.setOpaque(false);
        addCardPlaceholder.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        addCardPlaceholder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showNoteSelectionDialog();
                loseFocusOnCards();
            }
        });
    }
    
    private void setCardSize(JPanel card) {
        card.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        card.setMaximumSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        card.setMinimumSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
    }
    
    // Show note selection dialog
    private void showNoteSelectionDialog() {
        if (notesPanel == null) {
            showErrorDialog("Notes panel reference is not available", "Error");
            return;
        }

        List<Note> availableNotes = getAvailableNotes();
        
        if (availableNotes.isEmpty()) {
            // CENTERED: Show centered "No new notes available" message
            JOptionPane optionPane = new JOptionPane("No new notes available.", 
                    JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, 
                    new Object[]{}, null);
            
            JDialog dialog = optionPane.createDialog("No New Notes Found");
            dialog.setLocationRelativeTo(null); // Center on screen
            dialog.setVisible(true);
            return;
        }

        showSelectionDialog(availableNotes);
    }
    
    private List<Note> getAvailableNotes() {
        List<Note> allNotes = notesPanel.getAllNoteCards();
        List<Note> availableNotes = new ArrayList<>();
        
        for (Note note : allNotes) {
            if (!isNoteInCarousel(note)) {
                availableNotes.add(note);
            }
        }
        return availableNotes;
    }
    
    private void showSelectionDialog(List<Note> availableNotes) {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Add Notes to Carousel");
        
        JPanel selectionPanel = createSelectionPanel();
        
        JPanel notesGrid = createNotesGrid(availableNotes);
        JScrollPane scrollPane = new JScrollPane(notesGrid);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.getViewport().setBackground(CAROUSEL_BG);
        
        // Center the scroll pane
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(CAROUSEL_BG);
        centerPanel.add(scrollPane);
        
        selectionPanel.add(centerPanel, BorderLayout.CENTER);

        dialog.setContentPane(selectionPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Center on screen
        dialog.setVisible(true);
    }
    
    private JPanel createSelectionPanel() {
        JPanel selectionPanel = new JPanel(new BorderLayout(10, 10));
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(CONTAINER_PADDING, CONTAINER_PADDING, 
                                                                 CONTAINER_PADDING, CONTAINER_PADDING));
        selectionPanel.setBackground(CAROUSEL_BG);

        JLabel titleLabel = new JLabel("Select Notes to Add to Carousel");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(DARK_TEXT);
        selectionPanel.add(titleLabel, BorderLayout.NORTH);
        
        return selectionPanel;
    }
    
    private JPanel createNotesGrid(List<Note> availableNotes) {
        JPanel notesGrid = new JPanel(new GridLayout(0, 2, CARD_PADDING , CARD_PADDING));
        notesGrid.setBorder(BorderFactory.createEmptyBorder(CARD_PADDING, CARD_PADDING, 
                                                            CARD_PADDING, CARD_PADDING));
        notesGrid.setBackground(CAROUSEL_BG);

        for (int i = 0; i < availableNotes.size(); i++) {
            Note note = availableNotes.get(i);
            notesGrid.add(createNotePreviewPanel(note, i));
        }
        
        return notesGrid;
    }
    
    private JPanel createNotePreviewPanel(Note note, int index) {
        JPanel previewPanel = new JPanel(new BorderLayout(5, 5));
        previewPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(THEME_BORDER),
            BorderFactory.createEmptyBorder(CARD_PADDING, CARD_PADDING, 
                                           CARD_PADDING, CARD_PADDING)
        ));
        previewPanel.setBackground(DARK_CARD_BACKGROUND);
        previewPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel titleLabel = new JLabel((index + 1) + ". " + note.getNoteTitle());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(DARK_TEXT);
        
        String content = note.getNoteContent();
        String preview = content.length() > 100 ? content.substring(0, 97) + "..." : content;
        JTextArea contentArea = createContentArea(preview);
        
        previewPanel.add(titleLabel, BorderLayout.NORTH);
        previewPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);
        
        previewPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNoteToCarousel(note);
                Window window = SwingUtilities.getWindowAncestor(previewPanel);
                if (window != null) window.dispose();
            }
        });
        
        return previewPanel;
    }
    
    private JTextArea createContentArea(String text) {
        JTextArea contentArea = new JTextArea(text);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setBackground(DARK_CARD_BACKGROUND);
        contentArea.setForeground(DARK_TEXT);
        contentArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return contentArea;
    }
    
    private void showErrorDialog(String message, String title) {
        JOptionPane optionPane = new JOptionPane(message, 
                JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog(title);
        dialog.setLocationRelativeTo(null); // Center on screen
        dialog.setVisible(true);
    }
    
    private boolean isNoteInCarousel(Note note) {
        for (CarouselCard card : carouselCards) {
            if (card.getOriginalNote() == note) {
                return true;
            }
        }
        return false;
    }
    
    // Add a note to carousel
    public void addNoteToCarousel(Note note) {
        if (note == null) return;

        if (carouselCards.size() >= MAX_TOTAL_CARDS) {
            JOptionPane.showMessageDialog(this, 
                "Maximum number of cards reached (" + MAX_TOTAL_CARDS + ")", 
                "Limit Reached", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        CarouselCard card = new CarouselCard(note.getNoteTitle(), note.getNoteContent(), note);
        carouselCards.add(card);
        refreshCarousel();
    }
    
    // Set focused card
    private void setFocusedCard(CarouselCard card) {
        if (currentlyFocusedCard != null && currentlyFocusedCard != card) {
            currentlyFocusedCard.setFocusedState(false);
        }
        
        currentlyFocusedCard = card;
        card.setFocusedState(true);
    }
    
    // Lose focus on all cards
    private void loseFocusOnCards() {
        if (currentlyFocusedCard != null) {
            currentlyFocusedCard.setFocusedState(false);
            currentlyFocusedCard = null;
        }
    }
    
    // Edit card
    private void editCard(CarouselCard card) {
        if (notesPanel == null) {
            return;
        }
        notesPanel.findAndEditNote(card.getOriginalNote());
    }
    
    // Remove card
    private void removeCard(CarouselCard card) {
        carouselCards.remove(card);
        if (currentlyFocusedCard == card) {
            currentlyFocusedCard = null;
        }
        refreshCarousel();
    }
    
    // Create action button
    private JButton createActionButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_BG);
        button.setForeground(DARK_TEXT);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_BG);
            }
        });
        
        return button;
    }
    
    // Update display - Show 4 note cards + Add Card when possible
    private void updateDisplay() {
        holder.removeAll();
        
        // Calculate how many note cards to show
        int noteCardsToShow = Math.min(MAX_VISIBLE_CARDS, carouselCards.size() - currentIndex);
        
        // Show note cards
        for (int i = currentIndex; i < currentIndex + noteCardsToShow && i < carouselCards.size(); i++) {
            holder.add(carouselCards.get(i));
        }

        // ALWAYS show Add Card when we're at the end
        if (shouldShowAddCard()) {
            holder.add(addCardPlaceholder);
        }

        updateButtonStates();
        holder.revalidate();
        holder.repaint();
    }
    
    private boolean shouldShowAddCard() {
        if (carouselCards.size() >= MAX_TOTAL_CARDS) {
            return false;
        }
        
        // Show Add Card if we're at the end of the note cards list
        int noteCardsToShow = Math.min(MAX_VISIBLE_CARDS, carouselCards.size() - currentIndex);
        return (currentIndex + noteCardsToShow) >= carouselCards.size();
    }
    
    private int calculateNoteCardsToShow() {
        int totalCards = carouselCards.size();
        
        // We can show up to MAX_VISIBLE_CARDS note cards
        // But we need to leave room for Add Card if we're at the end
        int maxNoteCardsToShow = MAX_VISIBLE_CARDS;
        
        // If we're at the end and would show Add Card, show one less note card
        if (shouldShowAddCard(Math.min(maxNoteCardsToShow, totalCards - currentIndex))) {
            maxNoteCardsToShow = MAX_VISIBLE_CARDS - 1;
        }
        
        if (totalCards <= maxNoteCardsToShow) {
            return totalCards;
        } else {
            return Math.min(maxNoteCardsToShow, totalCards - currentIndex);
        }
    }
    
    private boolean shouldShowAddCard(int noteCardsToShow) {
        if (carouselCards.size() >= MAX_TOTAL_CARDS) {
            return false;
        }
        
        // Always show Add Card when we're at the end of the list
        boolean atEndOfList = (currentIndex + noteCardsToShow) >= carouselCards.size();
        return atEndOfList;
    }
    
    // Move left/right
    private void moveLeft() {
        if (currentIndex > 0) {
            currentIndex--;
            updateDisplay();
        }
    }
    
    private void moveRight() {
        int totalCards = carouselCards.size();
        
        // We can move right if there are more note cards to show
        // Note: We're showing up to MAX_VISIBLE_CARDS note cards at a time
        if (currentIndex < totalCards - MAX_VISIBLE_CARDS) {
            currentIndex++;
            updateDisplay();
        }
        // Special case: When we have exactly MAX_VISIBLE_CARDS note cards and Add Card is showing,
        // we can still move right to show the last note card
        else if (totalCards == MAX_VISIBLE_CARDS && currentIndex == 0) {
            currentIndex++;
            updateDisplay();
        }
    }
    
    private void setupButtons() {
        jButton1.addActionListener(e -> moveLeft());
        jButton2.addActionListener(e -> moveRight());
    }
    
    private void updateButtonStates() {
        int totalCards = carouselCards.size();
        
        // Left button: enabled if we're not at the beginning
        boolean leftEnabled = currentIndex > 0;
        
        // Right button: enabled if:
        // 1. We have more than MAX_VISIBLE_CARDS note cards and not at the end
        // OR 2. We have exactly MAX_VISIBLE_CARDS note cards and Add Card is showing
        boolean rightEnabled = false;
        
        if (totalCards > MAX_VISIBLE_CARDS) {
            // More cards than we can show at once
            rightEnabled = currentIndex < totalCards - MAX_VISIBLE_CARDS;
        } else if (totalCards == MAX_VISIBLE_CARDS) {
            // Exactly MAX_VISIBLE_CARDS cards - can scroll to show Add Card + last card
            rightEnabled = currentIndex == 0;
        }
        
        updateButtonState(jButton1, leftEnabled);
        updateButtonState(jButton2, rightEnabled);
    }
        
    private void updateButtonState(JButton button, boolean enabled) {
        button.setEnabled(enabled);
        if (enabled) {
            button.setBackground(BUTTON_INDICATOR);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(BUTTON_BG);
            button.setForeground(new Color(150, 150, 150));
        }   
    }
    
    // Refresh carousel
    public void refreshCarousel() {
        updateDisplay();
    }
    
    // Refresh when notes are updated
    public void refreshAllNotesFromPanel() {
        if (notesPanel == null) return;
        
        List<Note> panelNotes = notesPanel.getAllNoteCards();
        boolean changed = false;
        
        for (CarouselCard card : carouselCards) {
            Note originalNote = card.getOriginalNote();
            for (Note panelNote : panelNotes) {
                if (panelNote == originalNote) {
                    if (!card.getTitle().equals(panelNote.getNoteTitle()) ||
                        !card.getContent().equals(panelNote.getNoteContent())) {
                        card.updateContent(panelNote.getNoteTitle(), panelNote.getNoteContent());
                        changed = true;
                    }
                    break;
                }
            }
        }
        
        if (changed) {
            refreshCarousel();
        }
    }
    
    // Getters
    public int getRealCardCount() {
        return carouselCards.size();
    }
    
    public List<CarouselCard> getCarouselCards() {
        return new ArrayList<>(carouselCards);
    }
    
    // Custom Card Panel Class
    private class CarouselCard extends JPanel {
        private String title;
        private String content;
        private final Note originalNote;
        private boolean showActions = false;
        private JTextPane contentPane;
        private JPanel contentWrapper;
        
        public CarouselCard(String title, String content, Note originalNote) {
            this.title = title;
            this.content = content;
            this.originalNote = originalNote;
            initCard();
        }
        
        private void initCard() {
            setCardSize(this);
            setLayout(new BorderLayout(CARD_PADDING, CARD_PADDING));
            setOpaque(false);
            setFocusable(true);
            setRequestFocusEnabled(true);
            
            JPanel cardPanel = createCardPanel(false);
            cardPanel.setLayout(new BorderLayout());
            cardPanel.setBorder(BorderFactory.createEmptyBorder(CARD_PADDING, CARD_PADDING, 
                                                                  CARD_PADDING, CARD_PADDING));
            
            JPanel titlePanel = createTitlePanel(title);
            cardPanel.add(titlePanel, BorderLayout.NORTH);
            
            // Create content wrapper
            contentWrapper = new JPanel(new BorderLayout());
            contentWrapper.setOpaque(false);
            contentWrapper.setBorder(BorderFactory.createEmptyBorder());

            contentPane = createContentPane(content);
            contentWrapper.add(contentPane, BorderLayout.CENTER);
            contentWrapper.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
            cardPanel.add(contentWrapper, BorderLayout.CENTER);

            add(cardPanel, BorderLayout.CENTER);
            
            addCardMouseListeners();
        }
        
        private JPanel createCardPanel(boolean focused) {
            return new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    Color bgColor = focused ? DARK_CARD_FOCUS : DARK_CARD_BACKGROUND;
                    g2d.setColor(bgColor);
                    g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                    
                    Color borderColor = focused ? THEME_BORDER_HOVER : THEME_BORDER;
                    g2d.setColor(borderColor);
                    g2d.setStroke(new BasicStroke(focused ? 2 : 1));
                    g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                }
            };
        }
        
        private JPanel createTitlePanel(String titleText) {
            JPanel titlePanel = new JPanel(new BorderLayout());
            titlePanel.setOpaque(false);
            
            JLabel titleLabel = createTitleLabel(titleText);
            titlePanel.add(titleLabel, BorderLayout.NORTH);
            
            JSeparator separator = createTitleSeparator();
            titlePanel.add(separator, BorderLayout.SOUTH);
            
            return titlePanel;
        }
        
        private JLabel createTitleLabel(String text) {
            JLabel titleLabel = new JLabel(text);
            
            try {
                if (originalNote != null) {
                    Font originalFont = originalNote.getFont();
                    if (originalFont != null) {
                        titleLabel.setFont(new Font(originalFont.getName(), Font.BOLD, 16));
                    }
                    
                    Color originalForeground = originalNote.getForeground();
                    if (originalForeground != null) {
                        titleLabel.setForeground(originalForeground);
                    } else {
                        titleLabel.setForeground(DARK_TEXT);
                    }
                } else {
                    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    titleLabel.setForeground(DARK_TEXT);
                }
            } catch (Exception e) {
                titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                titleLabel.setForeground(DARK_TEXT);
            }
            
            titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
            return titleLabel;
        }
        
        private JSeparator createTitleSeparator() {
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setForeground(TITLE_SEPARATOR_COLOR);
            separator.setBackground(TITLE_SEPARATOR_COLOR);
            separator.setPreferredSize(new Dimension(0, 2));
            separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
            return separator;
        }
        
        private JTextPane createContentPane(String text) {
            JTextPane pane = new JTextPane() {
                @Override
                public boolean isFocusable() {
                    return false;
                }
                
                @Override
                public boolean isRequestFocusEnabled() {
                    return false;
                }
                
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                }
            };
            
            pane.setEditable(false);
            applyStyledContent(pane, text);
            pane.setBackground(TRANSPARENT_BG);
            pane.setCaretColor(DARK_TEXT);
            pane.setFocusable(false);
            pane.setRequestFocusEnabled(false);
            pane.setBorder(BorderFactory.createEmptyBorder());
            pane.setMargin(new Insets(4, 4, 4, 4));
            
            // Disable caret completely
            pane.setCaret(new javax.swing.text.DefaultCaret() {
                @Override
                public void setSelectionVisible(boolean vis) {}
                @Override
                public void setVisible(boolean vis) {}
            });
            
            return pane;
        }
        
        private void applyStyledContent(JTextPane pane, String text) {
            try {
                if (originalNote != null) {
                    // Get the basic font and color from the original note
                    Font originalFont = originalNote.getFont();
                    Color originalColor = originalNote.getForeground();

                    pane.setText(text);

                    if (originalFont != null) {
                        pane.setFont(originalFont);
                    } else {
                        pane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                    }

                    if (originalColor != null) {
                        pane.setForeground(originalColor);
                    } else {
                        pane.setForeground(DARK_TEXT);
                    }

                    pane.setCaretPosition(0);
                    return;
                }
            } catch (Exception e) {
                System.err.println("Error applying styled content: " + e.getMessage());
            }

            // Fallback to plain text
            pane.setText(text);
            pane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            pane.setForeground(DARK_TEXT);
        }
        
        private void addCardMouseListeners() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (currentlyFocusedCard != CarouselCard.this) {
                        setHoverState(true);
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (currentlyFocusedCard != CarouselCard.this) {
                        setHoverState(false);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    setFocusedCard(CarouselCard.this);
                }
            });
        }
        
        public void setHoverState(boolean hover) {
            Component[] components = getComponents();
            if (components.length > 0 && components[0] instanceof JPanel) {
                JPanel cardPanel = (JPanel) components[0];
                cardPanel.setBackground(hover ? DARK_CARD_HOVER : DARK_CARD_BACKGROUND);
                cardPanel.repaint();
            }
        }
        
        public void setFocusedState(boolean focused) {
            showActions = focused;
            removeAll();
            
            JPanel cardPanel = createCardPanel(focused);
            cardPanel.setLayout(new BorderLayout());
            cardPanel.setBorder(BorderFactory.createEmptyBorder(CARD_PADDING, CARD_PADDING, 
                                                                  CARD_PADDING, CARD_PADDING));
            
            JPanel titlePanel = createTitlePanel(title);
            cardPanel.add(titlePanel, BorderLayout.NORTH);
            
            if (contentPane == null) {
                contentPane = createContentPane(content);
            }
            
            // Recreate the content wrapper
            contentWrapper = new JPanel(new BorderLayout());
            contentWrapper.setOpaque(false);
            contentWrapper.setBorder(BorderFactory.createEmptyBorder());
            contentWrapper.add(contentPane, BorderLayout.CENTER);
            cardPanel.add(contentWrapper, BorderLayout.CENTER);
            
            if (focused) {
                JPanel actionPanel = createActionPanel();
                cardPanel.add(actionPanel, BorderLayout.SOUTH);
            }
            
            setLayout(new BorderLayout());
            add(cardPanel, BorderLayout.CENTER);
            
            revalidate();
            repaint();
        }
        
        private JPanel createActionPanel() {
            JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
            actionPanel.setOpaque(false);
            
            JButton editButton = createActionButton("Edit");
            editButton.addActionListener(e -> editCard(CarouselCard.this));
            
            JButton deleteButton = createActionButton("Delete");
            deleteButton.addActionListener(e -> removeCard(CarouselCard.this));
            
            actionPanel.add(editButton);
            actionPanel.add(deleteButton);
            
            return actionPanel;
        }
        
        public String getTitle() { return title; }
        public String getContent() { return content; }
        public Note getOriginalNote() { return originalNote; }
        
        public void updateContent(String newTitle, String newContent) {
            this.title = newTitle;
            this.content = newContent;

            if (contentPane != null) {
                applyStyledContent(contentPane, newContent); // Use the styled content method
            }

            setFocusedState(showActions);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(0, CONTAINER_HEIGHT));
        setPreferredSize(new java.awt.Dimension(0, CONTAINER_HEIGHT));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("<");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText(">");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(jButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
