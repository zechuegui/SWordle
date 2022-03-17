package Main;

import Frames.InitialFrame;
import Frames.MainFrame;

public class Game {

	private final InitialFrame initialFrame;
	private final Calculate calculate;
	private String wordToGuess;
	private int round = 0;
	MainFrame mainFrame;

	public Game(){
		initialFrame = new InitialFrame(this);
		calculate = new Calculate();
	}

	public void start(String wordToGuess){

		this.wordToGuess = wordToGuess;

		mainFrame = new MainFrame(this);
	}

	public void nextRound(String guessedWord){

		GuessValue[] guessValues = calculate.calculate(wordToGuess, guessedWord);

		Guess guess = new Guess(guessValues, guessedWord);

		mainFrame.updateBoard(round, guess);
		round++;
	}


}
