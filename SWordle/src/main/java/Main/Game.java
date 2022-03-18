package Main;

import Frames.EndOfGameFrame;
import Frames.InitialFrame;
import Frames.MainFrame;

public class Game {


	private final Calculate calculate;
	private String wordToGuess;
	private int round = 0;
	private MainFrame mainFrame;
	private InitialFrame initialFrame;
	private EndOfGameFrame endOfGameFrame;

	public Game(){
		calculate = new Calculate();
	}

	public void newGame() {

		if(mainFrame != null){

			mainFrame.hide();
		}
		if(endOfGameFrame != null) {
			endOfGameFrame.hide();
		}

		round = 0;
		initialFrame = new InitialFrame(this);
	}

	public void startGame(String wordToGuess){

		this.wordToGuess = wordToGuess;

		mainFrame = new MainFrame(this);
	}

	public void nextRound(String guessedWord){

		GuessValue[] guessValues = calculate.calculate(wordToGuess, guessedWord);

		Guess guess = new Guess(guessValues, guessedWord);

		mainFrame.updateBoard(round, guess);
		round++;

		checkStatus(guessValues);
	}

	public void checkStatus(GuessValue[] guessValues) {
		if(round == 6){
			endOfGameFrame = new EndOfGameFrame(this);
			endOfGameFrame.gameOver(wordToGuess);
		}

		for(GuessValue value : guessValues){

			if(value != GuessValue.Correct) {
				return;
			}
		}

		endOfGameFrame = new EndOfGameFrame(this);
		endOfGameFrame.gameWon();
	}

}
