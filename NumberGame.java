import javax.swing.*;
import javax.swing.border.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;

public class NumberGame extends JFrame {
    private int attempts, maxAttempts = 10, totalScore = 0, randomNumber;
    private JLabel msg;
    private JTextField inputField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JButton resetButton;
    private JPanel panel;

    public NumberGame(String title) {
        super(title);
        reset();

        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize components
        msg = new JLabel("Guess a number between 1 and 100:");
        inputField = new JTextField(15);
        guessButton = new JButton("Guess");
        feedbackLabel = new JLabel();
        resetButton = new JButton("Play Again");
        panel = new JPanel(new GridBagLayout());

        // Set component fonts
        Font font = new Font("SansSerif", Font.PLAIN, 18);
        msg.setFont(font);
        inputField.setFont(font);
        guessButton.setFont(font);
        feedbackLabel.setFont(font);
        resetButton.setFont(font);

        // Apply color palette
        panel.setBackground(Color.decode("#DDE6ED")); // Very Light Blue background
        msg.setForeground(Color.decode("#526D82")); // Medium Blue text
        feedbackLabel.setForeground(Color.decode("#27374D")); // Dark Navy Blue feedback text
// Dark background for buttons and white text for contrast
// Dark background for buttons and white text for contrast
guessButton.setBackground(Color.decode("#27374D")); // Dark Navy Blue background
guessButton.setForeground(Color.BLACK); // White text
resetButton.setBackground(Color.decode("#27374D")); // Dark Navy Blue background
resetButton.setForeground(Color.WHITE); // White text
        inputField.setBorder(BorderFactory.createLineBorder(Color.decode("#9DB2BF"))); // Light Blue border
        feedbackLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Adjusted padding

        // Set up layout and constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Margins for components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(msg, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(inputField, gbc);

        gbc.gridx = 1;
        panel.add(guessButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(feedbackLabel, gbc);

        gbc.gridy = 3;
        panel.add(resetButton, gbc);

        // Set initial state of resetButton
        resetButton.setEnabled(false);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);

        // Set frame properties
        setSize(600, 400); // Increased size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Add action listeners
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int n = Integer.parseInt(inputField.getText());
                    attempts++;
                    if (n > randomNumber) {
                        feedbackLabel.setText("Too high! Try again\n" + (maxAttempts - attempts) + " attempts left");
                    } else if (n < randomNumber) {
                        feedbackLabel.setText("Too low! Try again\n" + (maxAttempts - attempts) + " attempts left");
                    } else {
                        totalScore += (maxAttempts - attempts + 1);
                        feedbackLabel.setText("Correct! Guessed it in " + attempts + " attempts\nTotal Score: " + totalScore);
                        guessButton.setEnabled(false);
                        resetButton.setEnabled(true);
                    }

                    if (attempts >= maxAttempts) {
                        if (n != randomNumber) {
                            feedbackLabel.setText("Out of attempts! The number was " + randomNumber + ".\nTotal Score: " + totalScore);
                            guessButton.setEnabled(false);
                            resetButton.setEnabled(true);
                        }
                    }
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Please enter a valid number.");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
                guessButton.setEnabled(true);
                resetButton.setEnabled(false);
                inputField.setText("");
                feedbackLabel.setText("");
            }
        });
    }

    private void reset() {
        Random r = new Random();
        randomNumber = r.nextInt(100) + 1;
        attempts = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGame("Guessing Game"));
    }
}
