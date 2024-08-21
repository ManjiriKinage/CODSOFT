import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentResultsCalculator extends JFrame {
    private JTextField[] marksInputs;
    private JLabel totalMarksLabel, averageLabel, gradeLabel;
    private JButton calculateButton;

    public StudentResultsCalculator() {
        super("Student Results Calculator");

        initializeUI();
    }

    private void initializeUI() {
        // Frame settings
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));

        // Panel for input fields
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.CENTER);

        // Panel for result display
        JPanel resultPanel = createResultPanel();
        add(resultPanel, BorderLayout.SOUTH);

        // Calculate button setup
        setupCalculateButton(inputPanel);

        // Center the frame and make it visible
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Subjects array
        String[] subjects = {"Java", "C", "Python", "AI", "PHP", "JavaScript"};
        marksInputs = new JTextField[subjects.length];

        // Adding input fields for each subject
        for (int i = 0; i < subjects.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            inputPanel.add(new JLabel("Marks for " + subjects[i] + " (0-100):"), gbc);

            gbc.gridx = 1;
            marksInputs[i] = new JTextField(10);
            marksInputs[i].setFont(new Font("Arial", Font.PLAIN, 18));
            inputPanel.add(marksInputs[i], gbc);
        }

        return inputPanel;
    }

    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        totalMarksLabel = new JLabel("Total Marks: ");
        totalMarksLabel.setFont(new Font("Arial", Font.BOLD, 18));
        averageLabel = new JLabel("Average Percentage: ");
        averageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gradeLabel = new JLabel("Grade: ");
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        resultPanel.add(totalMarksLabel);
        resultPanel.add(averageLabel);
        resultPanel.add(gradeLabel);

        return resultPanel;
    }

    private void setupCalculateButton(JPanel inputPanel) {
        calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 18));
        calculateButton.setBackground(new Color(39, 55, 77)); // Dark blue background
        calculateButton.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = marksInputs.length;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        inputPanel.add(calculateButton, gbc);

        // Adding action listener to handle button click
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computeResults();
            }
        });
    }

    private void computeResults() {
        try {
            int totalMarks = 0;
            int numSubjects = marksInputs.length;

            // Calculate total marks
            for (JTextField field : marksInputs) {
                int marks = Integer.parseInt(field.getText());
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                totalMarks += marks;
            }

            // Calculate average percentage
            double averagePercent = (double) totalMarks / numSubjects;

            // Determine grade based on average percentage
            String grade = determineGrade(averagePercent);

            // Update result labels
            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercent) + "%");
            gradeLabel.setText("Grade: " + grade);

            // Define colors
            Color passColor = new Color(0, 100, 0); // Dark green
            Color failColor = Color.RED;

            // Color the labels based on pass/fail
            if (averagePercent >= 60) {  // Assuming 60% is the passing percentage
                totalMarksLabel.setForeground(passColor);
                averageLabel.setForeground(passColor);
                gradeLabel.setForeground(passColor); // Dark green for passing grades
            } else {
                totalMarksLabel.setForeground(failColor);
                averageLabel.setForeground(failColor);
                gradeLabel.setForeground(failColor); // Red for failing grade
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String determineGrade(double avgPercent) {
        if (avgPercent >= 90) return "A";
        if (avgPercent >= 80) return "B";
        if (avgPercent >= 70) return "C";
        if (avgPercent >= 60) return "D";
        return "F";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentResultsCalculator());
    }
}
