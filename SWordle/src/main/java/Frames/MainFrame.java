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
	private final Color colorYellow = new Color(252,226,5);
	private final String fontName = "Courier New";
	private Game game;


	public MainFrame(Game game) {
		this.game = game;
		setup();
	}

	public void setup() {

		JFrame frame = new JFrame("SWordle");

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       //Main Frame
		frame.setSize(700, 900);
		frame.setLayout(null);
		frame.setResizable(false);

		word_matrix = new JLabel[7][6];

		for(int y = 0; y < 6; y++) {
			for(int x = 0; x < 5; x++) {

				JLabel wordLabel = new JLabel("");
				wordLabel.setFont(new Font(fontName, Font.BOLD, 40));

				wordLabel.setVisible(true);
				wordLabel.setBounds(x*100 + 140,y*100+ 10,100,100);
				frame.add(wordLabel);

				word_matrix[x][y] = wordLabel;

			}
		}

		JTextField word_guess_box = new JTextField();
		word_guess_box.setVisible(true);
		word_guess_box.setBounds(250,650,200,55);
		word_guess_box.setFont(new Font(fontName, Font.BOLD, 30));
		frame.add(word_guess_box);

		JButton word_submit_button = new JButton("GUESS");
		word_submit_button.setVisible(true);
		word_submit_button.setBounds(290, 720, 120,50);
		word_submit_button.setFont(new Font(fontName, Font.BOLD, 15));
		frame.add(word_submit_button);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		word_submit_button.addActionListener(e -> {
			try {

				String word = word_guess_box.getText(); // Get the word input

				checkWord(word); // Check if the given word is valid

				word_guess_box.setText(""); // Reset the box

				game.nextRound(word);

			}
			catch (WrongInputException ex) {
				System.out.println(ex);
			}

		});
	}

	public void updateBoard(int guessNumber, Guess guess){
		for(int i = 0; i<guess.getWord().length(); i++) {

			String wordGuessed = guess.getWord();
			GuessValue[] guessValue = guess.getGuessValues();
			JLabel currentLabel = word_matrix[i][guessNumber];

			currentLabel.setText(String.valueOf(wordGuessed.charAt(i)));

			switch (guessValue[i]) {
				case Correct -> currentLabel.setForeground(colorGreen);
				case Missplaced -> currentLabel.setForeground(colorYellow);
			}
		}
	}

	public void gameOver(String wordToGuess){
		JFrame gameOverPanel = new JFrame("Game Over");
		gameOverPanel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       //Main Frame setup
		gameOverPanel.setSize(200, 250);
		gameOverPanel.setLayout(null);
		gameOverPanel.setResizable(false);

		JLabel informationLabel = new JLabel("<html><div style=\"text-align: center;\">You loose, <br> the word was: "+ "\n" + wordToGuess + "<br><br> Try again?</div></html>");
		informationLabel.setFont(new Font(fontName, Font.BOLD, 20));
		informationLabel.setBounds(20,0,200,150);
		informationLabel.setVisible(true);

		JButton tryAgainButton = new JButton("Try Again");
		tryAgainButton.setFont(new Font(fontName, Font.BOLD, 15));
		tryAgainButton.setBounds(70,180,100,20);
		tryAgainButton.setVisible(true);

		gameOverPanel.add(informationLabel);
		gameOverPanel.add(tryAgainButton);


		gameOverPanel.setLocationRelativeTo(null);
		gameOverPanel.setVisible(true);
	}

	public void gameWon(){

	}

	public void checkWord(String word) throws WrongInputException {

		if(word.length()!=5){
			throw new WrongInputException("Input must have 5 letters");
		}
	}

}

/**
 *
 *  TODO
 *
 *
 * Ser possível jogar contra o pc
 *
 */