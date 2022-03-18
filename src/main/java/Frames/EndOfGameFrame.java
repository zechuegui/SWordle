package Frames;

import Main.Game;

import javax.swing.*;
import java.awt.*;

public class EndOfGameFrame {

	private final String fontName = "Courier New";
	private final JFrame gameOverFrame;
	private JLabel informationLabel;
	private final Game game;

	public EndOfGameFrame(Game game){

		this.game = game;

		gameOverFrame = new JFrame("Game Over");
		gameOverFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       //Main Frame setup
		gameOverFrame.setSize(200, 250);
		gameOverFrame.setLayout(null);
		gameOverFrame.setResizable(false);

		informationLabel = new JLabel();
		informationLabel.setFont(new Font(fontName, Font.BOLD, 20));
		informationLabel.setBounds(20,0,200,150);
		informationLabel.setVisible(true);

		JButton tryAgainButton = new JButton("Try Again");
		tryAgainButton.setFont(new Font(fontName, Font.BOLD, 15));
		tryAgainButton.setBounds(70,180,100,20);
		tryAgainButton.setVisible(true);

		gameOverFrame.add(informationLabel);
		gameOverFrame.add(tryAgainButton);


		gameOverFrame.setLocationRelativeTo(null);

		tryAgainButton.addActionListener(e -> game.newGame());
	}


	public void gameOver(String wordToGuess) {

		informationLabel.setText("<html><div style=\"text-align: center;\">You loose, <br> the word was: "+
				"\n" + wordToGuess + "<br><br> Try again?</div></html>");

		gameOverFrame.setVisible(true);
	}

	public void gameWon() {
		informationLabel.setText("<html><div style=\"text-align: center;\"> You Won!<br><br> Try again?</div></html>");

		gameOverFrame.setVisible(true);
	}

	public void hide() {
		if(gameOverFrame!=null) {
			gameOverFrame.dispose();
		}
	}
}
