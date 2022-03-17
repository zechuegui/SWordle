package Main;

import Exceptions.WrongInputException;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

	JButton word_submit_button;
	JLabel[][] word_matrix;


	public MainFrame() {
		setup();
	}

	public void setup() {

		JFrame frame = new JFrame("SWordle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //Main Frame
		frame.setSize(700, 900);
		frame.setLayout(null);
		frame.setResizable(false);

		word_matrix = new JLabel[7][6];

		for(int y = 0; y < 6; y++) {
			for(int x = 0; x < 5; x++) {

				JLabel wordLabel = new JLabel("");
				wordLabel.setFont(new Font("Courier New", Font.BOLD, 40));

				wordLabel.setVisible(true);
				wordLabel.setBounds(x*100 + 140,y*100+ 10,100,100);
				frame.add(wordLabel);
				word_matrix[x][y] = wordLabel;

			}
		}

		JTextField word_guess_box = new JTextField();
		word_guess_box.setVisible(true);
		word_guess_box.setBounds(250,650,200,55);
		word_guess_box.setFont(new Font("Courier New", Font.BOLD, 30));
		frame.add(word_guess_box);

		word_submit_button = new JButton("ADIVINHAR");
		word_submit_button.setVisible(true);
		word_submit_button.setBounds(290, 720, 120,50);
		word_submit_button.setFont(new Font("Courier New", Font.BOLD, 15));
		frame.add(word_submit_button);


		frame.setVisible(true);

		word_submit_button.addActionListener(e -> {
			try {

				String word = word_guess_box.getText(); // Get the word input

				checkWord(word); // Check if the given word is valid

				word_guess_box.setText(""); // Reset the box
			}
			catch (WrongInputException ex) {
				System.out.println(ex);
			}

		});
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
 * Look for colors (Bumblebee yellow) Green 88D453
 *
 * Ser possível jogar contra o pc
 *
 */