package Frames;

import Exceptions.WrongInputException;
import Main.Game;

import javax.swing.*;
import java.awt.*;

public class InitialFrame {


	private final Font textFont = new Font("Courier New", Font.BOLD, 15);
	private final Game game;
	private JFrame initialFrame;
	private JTextField wordBox;

	public InitialFrame(Game game){
		this.game = game;
		setup();
	}

	public void setup(){

		initialFrame = new JFrame();
		initialFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       //Main Frame setup
		initialFrame.setSize(200, 150);
		initialFrame.setLayout(null);
		initialFrame.setResizable(false);


		JRadioButton playerOption = new JRadioButton("2 Players",true); // Creating the Radio Buttons
		playerOption.setFont(textFont);
		playerOption.setBounds(40,0, 200,50);

		JRadioButton computerOption = new JRadioButton("Computer");
		computerOption.setFont(textFont);
		computerOption.setBounds(40,35, 200,  50);

		ButtonGroup buttonGroup = new ButtonGroup();    // Creating the button Group
		buttonGroup.add(playerOption);
		buttonGroup.add(computerOption);


		JButton startButton = new JButton("START"); // Creting the start button
		startButton.setBounds(60,80,80,40);
		startButton.setFont(textFont);


		initialFrame.add(playerOption);
		initialFrame.add(computerOption);
		initialFrame.add(startButton);

		initialFrame.setLocationRelativeTo(null);
		initialFrame.setVisible(true);

		startButton.addActionListener(e -> {

			boolean is2PGame = playerOption.isSelected();

			if(is2PGame){ // Ask the player for a word, that the 2nd player has to guess

				playerOption.setVisible(false);
				computerOption.setVisible(false);
				startButton.setVisible(false);

				wordBox = new JTextField();
				wordBox.setVisible(true);
				wordBox.setBounds(10,10,180,55);
				wordBox.setFont(new Font("Courier New", Font.BOLD, 30));

				JButton startGameButton = new JButton("START"); // Creting the start button
				startGameButton.setBounds(60,80,80,40);
				startGameButton.setFont(textFont);

				initialFrame.add(wordBox);
				initialFrame.add(startGameButton);

				startGameButton.addActionListener(w -> buttonOrEnterPressed());
				wordBox.addActionListener(w -> buttonOrEnterPressed());

			}
			else{ // Choose the word
				// TODO
			}


		});

	}

	private void checkWord(String word) throws WrongInputException {

		if(word.length()!=5){
			throw new WrongInputException("Input must have 5 letters");
		}
	}

	private void buttonOrEnterPressed(){

		try {

			String wordToGuess = wordBox.getText().toUpperCase();
			checkWord(wordToGuess);

			game.startGame(wordToGuess);

			initialFrame.setVisible(false);
			initialFrame.dispose();

		}
		catch (WrongInputException ex){
			System.out.println(ex);
		}
	}
}
