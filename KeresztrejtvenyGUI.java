import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class KeresztrejtvenyGUI {
    private JFrame frame;
    private JPanel gridPanel;
    private JComboBox<Integer> rowsDropdown, colsDropdown, fileIndexDropdown;
    private JButton createButton, saveButton;
    private JTextField[][] textFields;
    
    public KeresztrejtvenyGUI() {
        frame = new JFrame("Keresztrejtvény készítő");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        
        JPanel controlPanel = new JPanel();
        rowsDropdown = createDropdown(6, 15, 15);
        colsDropdown = createDropdown(6, 15, 15);
        fileIndexDropdown = createDropdown(1, 10, 3);
        createButton = new JButton("Keresztrejtvény létrehozása");
        saveButton = new JButton("Keresztrejtvény mentése");
        
        controlPanel.add(new JLabel("Sorok: "));
        controlPanel.add(rowsDropdown);
        controlPanel.add(new JLabel("Oszlopok: "));
        controlPanel.add(colsDropdown);
        controlPanel.add(createButton);
        controlPanel.add(new JLabel("Fájl index: "));
        controlPanel.add(fileIndexDropdown);
        controlPanel.add(saveButton);
        
        gridPanel = new JPanel();
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);
        
        createButton.addActionListener(e -> createGrid());
        saveButton.addActionListener(e -> saveGrid());
        
        frame.setVisible(true);
    }
    
    private JComboBox<Integer> createDropdown(int min, int max, int defaultValue) {
        JComboBox<Integer> dropdown = new JComboBox<>();
        for (int i = min; i <= max; i++) {
            dropdown.addItem(i);
        }
        dropdown.setSelectedItem(defaultValue);
        return dropdown;
    }
    
    private void createGrid() {
        int rows = (int) rowsDropdown.getSelectedItem();
        int cols = (int) colsDropdown.getSelectedItem();
        
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(rows, cols));
        textFields = new JTextField[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JTextField textField = new JTextField("-");
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Arial", Font.BOLD, 18));
                textField.setDocument(new JTextFieldLimit(1));
                textField.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            textField.setText(textField.getText().equals("#") ? "-" : "#");
                        }
                    }
                });
                textFields[i][j] = textField;
                gridPanel.add(textField);
            }
        }
        
        gridPanel.revalidate();
        gridPanel.repaint();
    }
    
    private void saveGrid() {
        int index = (int) fileIndexDropdown.getSelectedItem();
        String fileName = "kr" + index + ".txt";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (JTextField[] row : textFields) {
                for (JTextField cell : row) {
                    String text = cell.getText();
                    if (text.isEmpty()) {
                        text = "-";
                    }
                    writer.print(text);
                }
                writer.println();
            }
            JOptionPane.showMessageDialog(frame, "Sikeres mentés: " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Hiba mentéskor: " + e.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    class JTextFieldLimit extends PlainDocument {
        private int limit;
        JTextFieldLimit(int limit) {
            this.limit = limit;
        }
        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null || (getLength() + str.length() > limit)) return;
            super.insertString(offset, str, attr);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(KeresztrejtvenyGUI::new);
    }
}

