package main.interfaces;

/* import statements */
import java.awt.Desktop;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.awt.Color;
import java.awt.Font;


public class Files extends javax.swing.JPanel {
    private final Map<main.component.Button, File> buttonFileMap = new HashMap<>();
    private final Map<main.component.Button, Color> originalButtonColors = new HashMap<>();
    private final Set<main.component.Button> selectedButtons = new HashSet<>();
    private boolean selectionMode = false;

    /* Contructors */
    public Files() {
        initComponents();
        setActionButtonFonts(); // Add this line
    }

// Add this method:
private void setActionButtonFonts() {
    try {
        Font segoeFont = new Font("Segoe UI Variable", Font.PLAIN, 12);
        addFile.setFont(segoeFont);
        deleteButton.setFont(segoeFont);
    } catch (Exception e) {
        // Fallback font
        Font fallbackFont = new Font("Dialog", Font.PLAIN, 12);
        addFile.setFont(fallbackFont);
        deleteButton.setFont(fallbackFont);
    }
}

    /* Built-in codes and functions */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        files = new javax.swing.JPanel();
        actionPanel = new main.component.Panel();
        addFile = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        fileScrollPane = new javax.swing.JScrollPane();
        filePanel = new javax.swing.JPanel();

        files.setBackground(new java.awt.Color(21, 21, 23));
        files.setPreferredSize(new java.awt.Dimension(1230, 860));
        files.setLayout(new java.awt.BorderLayout());

        actionPanel.setArc(0);
        actionPanel.setPanelBackground(new java.awt.Color(21, 21, 23));
        actionPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        addFile.setBackground(new java.awt.Color(102, 102, 102));
        addFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/add.png"))); // NOI18N
        addFile.setBorderPainted(false);
        addFile.setFocusPainted(false);
        addFile.addActionListener(this::addFileActionPerformed);
        actionPanel.add(addFile);

        deleteButton.setBackground(new java.awt.Color(102, 102, 102));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/trash.png"))); // NOI18N
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(this::deleteButtonActionPerformed);
        actionPanel.add(deleteButton);

        files.add(actionPanel, java.awt.BorderLayout.SOUTH);

        fileScrollPane.setBorder(null);
        fileScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        filePanel.setBackground(new java.awt.Color(21, 21, 23));
        filePanel.setPreferredSize(new java.awt.Dimension(750, 540));
        filePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        fileScrollPane.setViewportView(filePanel);

        files.add(fileScrollPane, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(files, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(files, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (!selectionMode) {
            // Enter selection mode
            selectionMode = true;
            deleteButton.setBackground(new Color(214, 72, 72)); // Red color for selection mode
            updateDeleteButtonText(); // This will set text to "Cancel Selection" initially

            JOptionPane.showMessageDialog(this,
                "Selection Mode Activated!\n\n" +
                "• Click files to select/deselect (turns red)\n" +
                "• Click 'Cancel Selection' to exit without deleting\n" +
                "• Click 'Delete Selected' when ready to delete red files",
                "Selection Mode",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Already in selection mode - check if files are selected
            if (selectedButtons.isEmpty()) {
                // No files selected, just cancel selection mode
                exitSelectionMode();
                JOptionPane.showMessageDialog(this,
                    "Selection mode cancelled.",
                    "Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Files are selected, show delete confirmation
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete " + selectedButtons.size() + " selected file(s) from the list?\n" +
                    "Files marked in red will be removed.\n" +
                    "(This does NOT delete the actual files)",
                    "Confirm Delete Selected",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    deleteSelectedFiles();
                }
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();

            for (File file : selectedFiles) {
                createFileButton(file);
            }

            filePanel.revalidate();
            filePanel.repaint();
        }
    }//GEN-LAST:event_addFileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Panel actionPanel;
    private javax.swing.JButton addFile;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel filePanel;
    private javax.swing.JScrollPane fileScrollPane;
    private javax.swing.JPanel files;
    // End of variables declaration//GEN-END:variables

// Exit selection mode without deleting
private void exitSelectionMode() {
    // Deselect all selected files (reset their background)
    for (main.component.Button button : selectedButtons) {
        button.setBackground(null); // Reset to default
        button.setOpaque(false); // Make transparent
        button.setContentAreaFilled(false); // Don't fill area
        button.setBorder(null); // Remove any border
        button.repaint(); // Force repaint
    }
    
    // Clear selection
    selectedButtons.clear();
    
    // Reset selection mode
    selectionMode = false;
    
    // Reset delete button to original state (icon only, no text)
    deleteButton.setText("");
    deleteButton.setBackground(new Color(102, 102, 102)); // Original gray color
    deleteButton.repaint();
}

private void createFileButton(File file) {
    main.component.Button fileButton = new main.component.Button();
    fileButton.setText(file.getName());
    fileButton.setToolTipText(file.getAbsolutePath());
    
    // Set font to Segoe UI Variable, 14px
    try {
        // Try to create Segoe UI Variable font
        Font segoeFont = new Font("Segoe UI Variable", Font.PLAIN, 14);
        fileButton.setFont(segoeFont);
    } catch (Exception e) {
        // Fallback to a standard font if Segoe UI Variable is not available
        Font fallbackFont = new Font("Dialog", Font.PLAIN, 14);
        fileButton.setFont(fallbackFont);
    }
    
    // Store original background color
    originalButtonColors.put(fileButton, fileButton.getBackground());
    
    // ADD ICON BASED ON FILE TYPE
    ImageIcon fileIcon = getFileIcon(file);
    if (fileIcon != null) {
        ImageIcon scaledIcon = scaleIcon(fileIcon, 48, 48);
        fileButton.setIcon(scaledIcon);
        fileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fileButton.setIconTextGap(5);
    }
    
    // Set size
    fileButton.setPreferredSize(new Dimension(150, 120));
    fileButton.setMaximumSize(new Dimension(150, 120));
    
    // Click behavior
    fileButton.addActionListener(e -> {
        if (selectionMode) {
            toggleFileSelection(fileButton);
        } else {
            openFile(file);
        }
    });
    
    // RIGHT-CLICK CONTEXT MENU
    fileButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                showFileContextMenu(fileButton, file, e.getX(), e.getY());
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
                showFileContextMenu(fileButton, file, e.getX(), e.getY());
            }
        }
    });
    
    // Store button-file relationship
    buttonFileMap.put(fileButton, file);
    
    filePanel.add(fileButton);
}

private void openFile(File file) {
    try {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, 
            "Cannot open file: " + ex.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}

// ADDED: Get appropriate icon for file type
private ImageIcon getFileIcon(File file) {
    String fileName = file.getName().toLowerCase();
    
    // Check file extension and return appropriate icon from YOUR icons
    if (fileName.endsWith(".txt")) {
        return getIconIfExists("/main/resource/icons/txt.png");
    } else if (fileName.endsWith(".pdf")) {
        return getIconIfExists("/main/resource/icons/pdf.png");
    } else if (fileName.endsWith(".ppt") || fileName.endsWith(".pptx")) {
        return getIconIfExists("/main/resource/icons/ppt.png");
    } else if (fileName.endsWith(".doc") || fileName.endsWith(".docx")) {
        return getIconIfExists("/main/resource/icons/word.png");
    } else if (fileName.endsWith(".java") || fileName.endsWith(".docx")) {
        return getIconIfExists("/main/resource/icons/word.png");
    } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || 
               fileName.endsWith(".png") || fileName.endsWith(".gif") || 
               fileName.endsWith(".bmp") || fileName.endsWith(".svg") || 
               fileName.endsWith(".ico") || fileName.endsWith(".tiff")) {
        return getIconIfExists("/main/resource/icons/image.png");
    } else if (fileName.endsWith(".mp4") || fileName.endsWith(".avi") || 
               fileName.endsWith(".mkv") || fileName.endsWith(".mov") || 
               fileName.endsWith(".wmv") || fileName.endsWith(".flv")) {
        return getIconIfExists("/main/resource/icons/video.png");    
    }
    
    // For other file types, no icon (or add more as you get more icons)
    return getIconIfExists("/main/resource/icons/default.png");
}

// ADDED: Scale icon to desired size
private ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
    if (icon == null) return null;
    
    java.awt.Image img = icon.getImage();
    java.awt.Image scaledImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImg);
}

// ADDED: Safe way to get icons (won't crash if icon doesn't exist)
private ImageIcon getIconIfExists(String path) {
    try {
        java.net.URL iconURL = getClass().getResource(path);
        if (iconURL != null) {
            return new ImageIcon(iconURL);
        }
    } catch (Exception e) {
        // Icon not found, continue
    }
    return null; // Return null if icon doesn't exist
}

// ADDED: Context menu for individual file operations
private void showFileContextMenu(main.component.Button button, File file, int x, int y) {
    // Create popup menu
    javax.swing.JPopupMenu popupMenu = new javax.swing.JPopupMenu();
    
    // Open menu item
    javax.swing.JMenuItem openItem = new javax.swing.JMenuItem("Open");
    openItem.addActionListener(e -> openFile(file));
    popupMenu.add(openItem);
    
    // Select/Deselect menu item (only in selection mode)
    if (selectionMode) {
        javax.swing.JMenuItem selectItem = new javax.swing.JMenuItem(
            selectedButtons.contains(button) ? "Deselect" : "Select");
        selectItem.addActionListener(e -> toggleFileSelection(button));
        popupMenu.add(selectItem);
    }
    
    // Remove from list menu item (always available)
    javax.swing.JMenuItem removeItem = new javax.swing.JMenuItem("Remove from List");
    removeItem.addActionListener(e -> removeSingleFile(button, file));
    popupMenu.add(removeItem);
    
    // Show the popup menu
    popupMenu.show(button, x, y);
}

// ADDED: Toggle file selection (for multi-select)
private void toggleFileSelection(main.component.Button button) {
    if (selectedButtons.contains(button)) {
        // Deselect - restore to original state
        selectedButtons.remove(button);
        button.setBackground(null); // Reset to default
        button.setOpaque(false); // Make transparent
        button.setContentAreaFilled(false); // Don't fill area
        button.setBorder(null); // Remove any border
    } else {
        // Select - make it bright red with border
        selectedButtons.add(button);
        button.setBackground(new Color(255, 100, 100)); // Bright red background
        button.setOpaque(true); // Make opaque so background shows
        button.setContentAreaFilled(true); // Fill the area
        button.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 2)); // Red border
    }
    
    // Update delete button text based on selection
    updateDeleteButtonText();
    button.repaint(); // Force repaint to show changes
}

// ADDED: Remove single file from the list
private void removeSingleFile(main.component.Button button, File file) {
    int confirm = JOptionPane.showConfirmDialog(this,
        "Remove '" + file.getName() + "' from the list?\n" +
        "(This does NOT delete the actual file)",
        "Remove File",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);
    
    if (confirm == JOptionPane.YES_OPTION) {
        // FIRST: If this button was selected, visually deselect it before removing
        if (selectedButtons.contains(button)) {
            button.setBackground(null);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorder(null);
            button.repaint();
        }
        
        // Remove from panel
        filePanel.remove(button);
        
        // Remove from tracking maps
        buttonFileMap.remove(button);
        selectedButtons.remove(button);
        originalButtonColors.remove(button);
        
        // ONLY update delete button text if we ARE in selection mode
        if (selectionMode) {
            updateDeleteButtonText();
        }
        
        // Refresh display
        filePanel.revalidate();
        filePanel.repaint();
    }
}

private void updateDeleteButtonText() {
    // Only update if we're in selection mode
    if (selectionMode) {
        if (selectedButtons.isEmpty()) {
            deleteButton.setText("Cancel Selection");
        } else {
            deleteButton.setText("Delete Selected (" + selectedButtons.size() + ")");
        }
    }
}

// ADDED: Delete all selected files
private void deleteSelectedFiles() {
    for (main.component.Button button : selectedButtons) {
        File file = buttonFileMap.get(button);
        if (file != null) {
            // Remove from panel
            filePanel.remove(button);
            // Remove from tracking
            buttonFileMap.remove(button);
        }
    }
    
    // Clear selection and exit selection mode
    exitSelectionMode();
    
    // Refresh display
    filePanel.revalidate();
    filePanel.repaint();
    
    JOptionPane.showMessageDialog(this,
        "Selected files removed from list.",
        "Success",
        JOptionPane.INFORMATION_MESSAGE);
}
}
