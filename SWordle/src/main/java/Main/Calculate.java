package Main;

public class Calculate {

	private GuessValue[] currentGuess; // Guess values associated with the current Guess
 	private GuessValue[] used; // Used letters, to help with Missplaced chars

	public Calculate(){
		currentGuess  = new GuessValue[5];
		used = new GuessValue[5];

		reset(currentGuess);
		reset(used);
	}

	public GuessValue[] calculate(String wordToGuess, String guess){

		reset(currentGuess);
		reset(used);

		for(int x = 0; x<5; x++)  {
			if(guess.charAt(x) == wordToGuess.charAt(x)) {
				currentGuess[x] = GuessValue.Corret;
			}
		}

		for(int x = 0; x<5; x++){ // Iterate the guess

			if(currentGuess[x] == GuessValue.Corret){ // If the number is already Correct, continue
				continue;
			}

			for(int y= 0; y<5; y++){ // Iterate the wordToGuess

				if(x!=y && guess.charAt(x)==wordToGuess.charAt(y) && currentGuess[x] == GuessValue.Incorrect &&
						used[y]!= GuessValue.Missplaced){

					currentGuess[x] = GuessValue.Missplaced;
					used[y] = GuessValue.Missplaced;
					break;
				}


			}
		}

		return currentGuess;
	}

	public static void reset(GuessValue[] guessArray){

		for (int i = 0; i < 5; i++) { // Reset the guessArray to Incorrect
			guessArray[i] = GuessValue.Incorrect;
		}
	}
}
