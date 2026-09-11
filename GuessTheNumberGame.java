import java.awt.*;
import java.util.Random;
import javax.swing.*;

final class Game {
    public int computerInput;
    public int noOfGuesses = 0;
    public int userInput;

    Game() {
        resetGame();
    }

    public void resetGame() {
        Random rd = new Random();
        this.computerInput = rd.nextInt(100) + 1;
        this.noOfGuesses = 0;
        this.userInput = 0;
    }

    public void setNoOfGuesses(int noOfGuesses) {
        this.noOfGuesses = noOfGuesses;
    }

    public int getNoOfGuesses() {
        return noOfGuesses;
    }

    public void takeUserInput(int userInput) {
        this.userInput = userInput;
    }

    public String isCorrectNumber() {
        noOfGuesses++;

        if (userInput == computerInput) {
            return String.format("HURRAY!! You guessed the right number, it was %d. You guessed it in %d attempt(s).",
                    computerInput, noOfGuesses);
        } else if (userInput < computerInput) {
            return "Guess higher!";
        } else {
            return "Guess lower!";
        }
    }
}

public class GuessTheNumberGame extends JFrame {
    private final Game game;
    private final JTextField guessField;
    private final JLabel statusLabel;
    private final JLabel attemptsLabel;
    private final JButton guessButton;
    private final JButton newGameButton;

    public GuessTheNumberGame() {
        super("Guess The Number by SK");
        this.game = new Game();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 320);
        setMinimumSize(new Dimension(520, 320));
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(237, 242, 255));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        mainPanel.setBackground(new Color(237, 242, 255));

        JLabel titleLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(30, 41, 59));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setOpaque(false);

        JLabel guessLabel = new JLabel("Your Guess:");
        guessLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        guessLabel.setForeground(new Color(30, 41, 59));

        guessField = new JTextField(10);
        guessField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        guessField.setPreferredSize(new Dimension(120, 38));
        guessField.setBackground(Color.WHITE);
        guessField.setForeground(new Color(15, 23, 42));
        guessField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(148, 163, 184), 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));

        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        guessButton.setBackground(new Color(79, 70, 229));
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);
        guessButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(79, 70, 229), 1),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));

        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        newGameButton.setBackground(new Color(22, 163, 74));
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setFocusPainted(false);
        newGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newGameButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(21, 128, 61), 1),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));

        inputPanel.add(guessLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        inputPanel.add(newGameButton);

        statusLabel = new JLabel("Enter a number to start playing!", SwingConstants.CENTER);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        statusLabel.setForeground(new Color(51, 65, 85));
        statusLabel.setOpaque(true);
        statusLabel.setBackground(new Color(219, 234, 254));
        statusLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(191, 219, 254), 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        attemptsLabel = new JLabel("Attempts: 0", SwingConstants.CENTER);
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        attemptsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        attemptsLabel.setForeground(new Color(71, 85, 105));

        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(18));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(18));
        mainPanel.add(statusLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(attemptsLabel);

        add(mainPanel, BorderLayout.CENTER);

        guessButton.addActionListener(e -> handleGuess());
        newGameButton.addActionListener(e -> startNewGame());
        guessField.addActionListener(e -> handleGuess());

        startNewGame();
    }

    private void handleGuess() {
        String input = guessField.getText().trim();

        if (input.isEmpty()) {
            statusLabel.setText("Please enter a number first.");
            return;
        }

        try {
            int guessedNumber = Integer.parseInt(input);

            if (guessedNumber < 1 || guessedNumber > 100) {
                statusLabel.setText("Please choose a number between 1 and 100.");
                guessField.setText("");
                guessField.requestFocusInWindow();
                return;
            }

            game.takeUserInput(guessedNumber);
            String result = game.isCorrectNumber();
            attemptsLabel.setText("Attempts: " + game.getNoOfGuesses());

            if (result.contains("HURRAY")) {
                statusLabel.setText(result);
                guessButton.setEnabled(false);
                guessField.setEnabled(false);
                return;
            }

            statusLabel.setText(result);
            guessField.setText("");
            guessField.requestFocusInWindow();
        } catch (NumberFormatException ex) {
            statusLabel.setText("Please enter a valid whole number.");
            guessField.setText("");
            guessField.requestFocusInWindow();
        }
    }

    private void startNewGame() {
        game.resetGame();
        statusLabel.setText("New game started! Enter a number to begin.");
        attemptsLabel.setText("Attempts: 0");
        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
        guessField.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessTheNumberGame frame = new GuessTheNumberGame();
            frame.setVisible(true);
        });
    }
}