package main.component;

/**
 *
 * @author Cyrus Wilson
 */
public class CalendarNav extends javax.swing.JPanel {
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton todayButton;
    
    // Your custom variables
    private java.time.YearMonth currentMonth;
    private java.util.List<MonthChangeListener> listeners;
    
    public interface MonthChangeListener {
        void onMonthChanged(java.time.YearMonth newMonth);
    }

    /** Creates new form CalendarNav */
    public CalendarNav() {
        initComponents();
        initCustomComponents();
    }
    
        private void initCustomComponents() {
        currentMonth = java.time.YearMonth.now();
        listeners = new java.util.ArrayList<>();
        updateDisplay();

        // Add action listeners
        prevButton.addActionListener(evt -> prevButtonActionPerformed());
        nextButton.addActionListener(evt -> nextButtonActionPerformed());
    }

    private void prevButtonActionPerformed() {
        currentMonth = currentMonth.minusMonths(1);
        updateDisplay();
        fireMonthChanged();
    }

    private void nextButtonActionPerformed() {
        currentMonth = currentMonth.plusMonths(1);
        updateDisplay();
        fireMonthChanged();
    }

    private void updateDisplay() {
        monthLabel.setText(currentMonth.getMonth() + " " + currentMonth.getYear());
    }

    public void addMonthChangeListener(MonthChangeListener listener) {
        listeners.add(listener);
    }

    private void fireMonthChanged() {
        for (MonthChangeListener listener : listeners) {
            listener.onMonthChanged(currentMonth);
        }
    }

    public java.time.YearMonth getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(java.time.YearMonth month) {
        this.currentMonth = month;
        updateDisplay();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        prevButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        monthLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        prevButton.setText("◀");
        prevButton.addActionListener(this::prevButtonActionPerformed);

        nextButton.setText("▶");

        monthLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        monthLabel.setText("Month");
        monthLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        monthLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(prevButton)
                .addGap(73, 73, 73)
                .addComponent(monthLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(nextButton)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nextButton)
                    .addComponent(prevButton)
                    .addComponent(monthLabel))
                .addGap(29, 29, 29))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        currentMonth = currentMonth.minusMonths(1);
        updateDisplay();
        fireMonthChanged();
    }//GEN-LAST:event_prevButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel monthLabel;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton prevButton;
    // End of variables declaration//GEN-END:variables

}
