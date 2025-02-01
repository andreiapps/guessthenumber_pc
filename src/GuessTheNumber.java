import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GuessTheNumber {
    JPanel MainWindow;
    JLabel welcomeLabel;
    JLabel correct;
    JTextField guess;
    JButton guessButton;
    JLabel hint;
    JButton resetButton;
    public int randomNumber = 0;
    public int prevNum = 0;
    public int prevInput = 0;
    Random randomizer = new Random();
    public GuessTheNumber() {
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				// Generate a random number that was not previously generated and also isn't the previous input
                while (randomNumber == 0 || randomNumber == prevNum || randomNumber == prevInput) {
                    randomNumber = randomizer.nextInt(11);
                }
                prevNum = randomNumber;
                guess.setEnabled(false);
                resetButton.setEnabled(true);
                guessButton.setEnabled(false);
                String input_str = guess.getText();
				// Check if number is correct
                try {
                    int input = Integer.parseInt(input_str);
                    if (input > 10 || input < 1) correct.setText("Number is not in range between 1 and 10");
                    else if (input != randomNumber) correct.setText("Wrong answer. The number was " + randomNumber);
                    else if (input == randomNumber) correct.setText("You win!");
                    prevInput = input;

                }
                catch (NumberFormatException error) {
                    correct.setText("Text is not a number.");
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guess.setEnabled(true);
                guessButton.setEnabled(true);
                guess.setText("");
                correct.setText(" ");
                resetButton.setEnabled(false);
            }
        });
        guess.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
				// Limit input to only digits(can be bypassed by pasting, I don't think that can be fixed, maybe disable Ctrl-V in some way???
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) e.consume();
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Guess The Number");
        frame.setContentPane(new GuessTheNumber().MainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
