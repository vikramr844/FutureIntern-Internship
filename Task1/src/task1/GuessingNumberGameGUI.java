package task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingNumberGameGUI {
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel label;
    private JTextField textField;
    private JButton submitButton;
    private int targetNumber;
    private int attempts;
    private int maxAttempts;
    private int score;

    public GuessingNumberGameGUI() {
        frame = new JFrame("Number Game");
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        // Set GridBagLayout for more flexible resizing
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        titleLabel = new JLabel("Welcome to the Number Guessing Game!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(70, 130, 180));

        label = new JLabel("Enter your guess (1-100):");
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        textField = new JTextField(10);

        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(70, 130, 180));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);

        targetNumber = generateRandomNumber();
        maxAttempts = 5;
        attempts = 0;
        score = 0;

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        // Set constraints and add components
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(titleLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(textField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(submitButton, gbc);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setMinimumSize(new Dimension(400, 200));  // Set a minimum size
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void checkGuess() {
        int userGuess;
        try {
            userGuess = Integer.parseInt(textField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            return;
        }

        attempts++;

        if (userGuess == targetNumber) {
            JOptionPane.showMessageDialog(frame, "Congratulations! You guessed the correct number.");
            score++;
            showScore();
        } else if (attempts < maxAttempts) {
            String message = (userGuess < targetNumber) ? "Too low! Try a higher number." : "Too high! Try a lower number.";
            JOptionPane.showMessageDialog(frame, message);
        } else {
            JOptionPane.showMessageDialog(frame, "Sorry! You've used all your attempts. The correct number was " + targetNumber);
            showScore();
        }

        if (attempts == maxAttempts || userGuess == targetNumber) {
            resetGame();
        }
    }

    private void showScore() {
        JOptionPane.showMessageDialog(frame, "Your final score is: " + score);
        int option = JOptionPane.showConfirmDialog(frame, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.NO_OPTION) {
            frame.dispose();
        }
    }

    private void resetGame() {
        targetNumber = generateRandomNumber();
        attempts = 0;
        textField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuessingNumberGameGUI();
            }
        });
    }
}
