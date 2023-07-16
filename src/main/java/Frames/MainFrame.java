package Frames;

import Exceptions.WrongInputException;
import Main.Game;
import Main.Guess;
import Main.GuessValue;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

	private JLabel[][] word_matrix;
	private final Color colorGreen = new Color(136, 212, 83);
	private final Color colorYellow = new Color(252, 226, 5);
	private final String fontName = "Courier New";
	private Game game;
	private JFrame frame;
	private JTextField word_guess_box;

	public MainFrame(Game game) {
		this.game = game;
		setup();
	}

	public void setup() {

		frame = new JFrame("SWordle");

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       //Main Frame
		frame.setSize(700, 900);
		frame.setLayout(null);
		frame.setResizable(false);

		word_matrix = new JLabel[7][6];

		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 5; x++) {

				JLabel wordLabel = new JLabel("");
				wordLabel.setFont(new Font(fontName, Font.BOLD, 40));

				wordLabel.setVisible(true);
				wordLabel.setBounds(x * 100 + 140, y * 100 + 10, 100, 100);
				frame.add(wordLabel);

				word_matrix[x][y] = wordLabel;

			}
		}

		word_guess_box = new JTextField();
		word_guess_box.setVisible(true);
		word_guess_box.setBounds(250, 650, 200, 55);
		word_guess_box.setFont(new Font(fontName, Font.BOLD, 30));
		frame.add(word_guess_box);

		JButton word_submit_button = new JButton("GUESS");
		word_submit_button.setVisible(true);
		word_submit_button.setBounds(290, 720, 120, 50);
		word_submit_button.setFont(new Font(fontName, Font.BOLD, 15));
		frame.add(word_submit_button);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		word_guess_box.addActionListener(e -> buttonOrEnterPressed());
		word_submit_button.addActionListener(e -> buttonOrEnterPressed());
	}

	public void updateBoard(int guessNumber, Guess guess) {

		String wordGuessed = guess.getWord();
		GuessValue[] guessValue = guess.getGuessValues();

		for (int i = 0; i < guess.getWord().length(); i++) {

			JLabel currentLabel = word_matrix[i][guessNumber];

			currentLabel.setText(String.valueOf(wordGuessed.charAt(i)));

			switch (guessValue[i]) {
				case Correct -> currentLabel.setForeground(colorGreen);
				case Missplaced -> currentLabel.setForeground(colorYellow);
			}
		}
	}

	private void checkWord(String word) throws WrongInputException {

		if (word.length() != 5) {
			throw new WrongInputException("Input must have 5 letters");
		}
	}

	public void hide() {
		if (frame != null) {
			frame.dispose();
		}

	}

	private void buttonOrEnterPressed() {
		try {

			String word = word_guess_box.getText().toUpperCase(); // Get the word input

			checkWord(word); // Check if the given word is valid

			word_guess_box.setText(""); // Reset the box

			game.nextRound(word);

		} catch (WrongInputException ex) {
			System.out.println(ex);
		}
	}
}

/**
 * TODO
 * <p>
 * !! Permitir apenas letras, não simbolos
 * <p>
 * <p>
 * Ser possível jogar contra o pc
 * <p>
 * Guardar vitórias j1 vs j2 (colocando a possibilidade de no início escolhar quem joga 1º e à melhor de quanto)
 */