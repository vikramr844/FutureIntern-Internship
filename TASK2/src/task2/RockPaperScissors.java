package task2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class RockPaperScissors {

    private static final String[] CHOICES = {"Rock", "Paper", "Scissors"};
    private static int scoreHuman = 0;
    private static int scoreComputer = 0;
    private static int scoreTies = 0;

    private static JFrame frame;
    private static JLabel lblScore, lblResult, lblComputerChoice;
    private static Color primaryColor = new Color(52, 152, 219); // Custom theme color

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            initializeGame();
            showInstructions();
        });
    }

    public static void initializeGame() {
        frame = new JFrame("Rock Paper Scissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);

        // Main container panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(primaryColor);

        // Title section
        JLabel lblTitle = new JLabel("Rock Paper Scissors", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Game buttons panel
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1, 3, 20, 20));
        panelButtons.setOpaque(false);

        JButton btnRock = createGameButton("rock-icon.png", 0, new Color(139, 69, 19)); // Brown for Rock
        JButton btnPaper = createGameButton("paper-icon.png", 1, Color.YELLOW); // Yellow for Paper
        JButton btnScissors = createGameButton("scissors-icon.png", 2, Color.BLUE); // Blue for Scissors
        
        panelButtons.add(btnRock);
        panelButtons.add(btnPaper);
        panelButtons.add(btnScissors);

        mainPanel.add(panelButtons, BorderLayout.CENTER);

        // Results and Score section
        JPanel panelResults = new JPanel(new GridLayout(3, 1));
        panelResults.setOpaque(false);
        lblComputerChoice = new JLabel("Computer's Choice: ?", SwingConstants.CENTER);
        lblResult = new JLabel("Result: ", SwingConstants.CENTER);
        lblScore = new JLabel("Score - Human: 0 | Computer: 0 | Ties: 0", SwingConstants.CENTER);

        styleLabel(lblComputerChoice);
        styleLabel(lblResult);
        styleLabel(lblScore);

        panelResults.add(lblComputerChoice);
        panelResults.add(lblResult);
        panelResults.add(lblScore);

        mainPanel.add(panelResults, BorderLayout.SOUTH);

        // Add hover effects for buttons
        addHoverEffect(btnRock);
        addHoverEffect(btnPaper);
        addHoverEffect(btnScissors);

        // Toggle for dark/light mode
        JToggleButton toggleMode = new JToggleButton("Light Mode");
        toggleMode.addItemListener(e -> toggleMode(frame, e));
        mainPanel.add(toggleMode, BorderLayout.WEST);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    // Show game instructions
    public static void showInstructions() {
        String instructions = "Welcome to Rock, Paper, Scissors!\n\n"
                + "1. Click on Rock, Paper, or Scissors to make your move.\n"
                + "2. The computer will randomly select a choice.\n"
                + "3. The winner will be determined as per the game rules.\n\n"
                + "Good luck!";
        JOptionPane.showMessageDialog(frame, instructions, "How to Play", JOptionPane.INFORMATION_MESSAGE);
    }

    // Create buttons for the game
    private static JButton createGameButton(String imageName, int choice, Color backgroundColor) {
        ImageIcon icon = new ImageIcon(imageName);
        JButton button = new JButton(icon);
        button.setBackground(backgroundColor); // Set custom background color
        button.setOpaque(true); // Ensure the background color is visible
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> playRound(choice));
        return button;
    }

    // Play a round and update the UI with results
    private static void playRound(int playerChoice) {
        int computerChoice = new Random().nextInt(3);
        String result = determineWinner(playerChoice, computerChoice);

        lblComputerChoice.setText("Computer's Choice: " + CHOICES[computerChoice]);
        lblResult.setText(result);
        updateScore();
    }

    // Determine winner and return result string
    private static String determineWinner(int playerChoice, int computerChoice) {
        if (playerChoice == computerChoice) {
            scoreTies++;
            return "It's a tie!";
        } else if ((playerChoice == 0 && computerChoice == 2) || 
                   (playerChoice == 1 && computerChoice == 0) || 
                   (playerChoice == 2 && computerChoice == 1)) {
            scoreHuman++;
            return "You win!";
        } else {
            scoreComputer++;
            return "Computer wins!";
        }
    }

    // Update the scoreboard
    private static void updateScore() {
        lblScore.setText(String.format("Score - Human: %d | Computer: %d | Ties: %d", scoreHuman, scoreComputer, scoreTies));
    }

    // Style labels for consistency
    private static void styleLabel(JLabel label) {
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
    }

    // Toggle between dark mode and light mode
    private static void toggleMode(JFrame frame, ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            frame.getContentPane().setBackground(Color.WHITE);
            lblComputerChoice.setForeground(Color.BLACK);
            lblResult.setForeground(Color.BLACK);
            lblScore.setForeground(Color.BLACK);
            ((JToggleButton) e.getItem()).setText("Dark Mode");
        } else {
            frame.getContentPane().setBackground(primaryColor);
            lblComputerChoice.setForeground(Color.WHITE);
            lblResult.setForeground(Color.WHITE);
            lblScore.setForeground(Color.WHITE);
            ((JToggleButton) e.getItem()).setText("Light Mode");
        }
    }

    // Add hover effects for buttons
    private static void addHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBorderPainted(true);
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBorderPainted(false);
            }
        });
    }
}
