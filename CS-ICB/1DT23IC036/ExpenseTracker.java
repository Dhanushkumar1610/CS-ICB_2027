import javax.swing.*;                   // For Swing components like JFrame, JButton, etc.
import javax.swing.table.DefaultTableModel; // For table model handling
import java.awt.*;                       // For AWT components
import java.awt.event.*;                 // For event handling
import java.io.*;                        // For file I/O
import java.util.Vector;                 // For Vector class
import javax.swing.JOptionPane;          // For displaying message dialogs
import java.io.File;                     // For File I/O (to use JFileChooser)

public class ExpenseTracker extends JFrame {
    private JTextField amountField, categoryField;
    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel totalLabel;
    private double total = 0;

    public ExpenseTracker() {
        setTitle("Personal Expense Tracker");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));

        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        JButton addButton = new JButton("Add Expense");
        inputPanel.add(addButton);

        JButton saveButton = new JButton("Save to File");
        inputPanel.add(saveButton);

        tableModel = new DefaultTableModel(new Object[]{"Amount", "Category"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        totalLabel = new JLabel("Total: Rs. 0.0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(totalLabel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addExpense());
        saveButton.addActionListener(e -> saveToFile());
    }

    private void addExpense() {
        try {
            String amountText = amountField.getText();
            if (amountText.isEmpty()) {
                throw new Exception("Amount cannot be empty");
            }

            double amount = Double.parseDouble(amountText);
            String category = categoryField.getText();
            if (category.isEmpty()) {
                throw new Exception("Category cannot be empty");
            }

            tableModel.addRow(new Object[]{amount, category});
            total += amount;
            totalLabel.setText("Total: Rs. " + total);

            amountField.setText("");
            categoryField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Expenses");
        fileChooser.setSelectedFile(new File("expenses.csv"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    Vector<?> row = tableModel.getDataVector().elementAt(i);
                    writer.write(row.get(0).toString() + "," + row.get(1).toString());
                    writer.newLine();
                }
                writer.write("Total,Rs. " + total);
                JOptionPane.showMessageDialog(this, "Expenses saved to " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTracker().setVisible(true));
    }
}

