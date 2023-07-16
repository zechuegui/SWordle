package Main;

public class Guess {

	private GuessValue[] guessValues;
	private String word;

	public Guess(GuessValue[] guessValue, String word) {
		this.guessValues = guessValue;
		this.word = word;
	}

	public GuessValue[] getGuessValues() {
		return guessValues;
	}

	public void setGuessValues(GuessValue[] guessValues) {
		this.guessValues = guessValues;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
