package Main;

import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    enum Certo{
        Correto,
        Trocado,
        Errado,
    }

    public static void main(String [] args) {
        MainFrame mainFrame = new MainFrame();
        Scanner s = new Scanner(System.in);
        Certo[] c = new Certo[5];

        reset(c);

//        System.out.println("Insira a palavra que deseja (Com 5 Letras): ");

        String wordToGuess = "bicha";
//        String wordToGuess = s.next();

        if(wordToGuess.length() != 5){
            System.out.println("Palavra não tem 5 letras");
        }

        for(int jogada = 0; jogada < 6; jogada++) {

            System.out.print((jogada + 1) + " - ");
            String guess = s.next();

            for(int x = 0; x<5; x++)  {
                if(guess.charAt(x) == wordToGuess.charAt(x)) {
                    c[x]=Certo.Correto;

                }
            }

//            printState(wordToGuess, guess, c);
//            reset(c);

            Certo[] usado = new Certo[6];
            reset(usado);

            for(int x = 0; x<5; x++){ // Percorrer a guess
                if(c[x] == Certo.Correto){ // Se já está correto ignorar
                    continue;
                }
                for(int y= 0; y<5; y++){ // Percorrer a wordToGuess

                    if(x!=y && guess.charAt(x)==wordToGuess.charAt(y) && c[x]==Certo.Errado && c[x]!= Certo.Trocado){
                        // Quando uma letra esta fora da posição é possivelque outra seja marcada como fora, tendo de
                        // ser marcada como errada

                        char car = guess.charAt(x);
                        car = wordToGuess.charAt(y);
                        Certo w  = c[x];

                        c[x] = Certo.Trocado;

                        Certo k  = c[x];

                        break;
                    }


                }
            }

            printState(wordToGuess, guess, c);
            reset(c);
        }
    }

    public static void printState(String wordToGuess, String guess, Certo[] c){

        for(int i =0; i<c.length; i++){
            switch (c[i]) {
                case Correto -> System.out.print(ANSI_GREEN + guess.charAt(i) + ANSI_RESET + "\t");
                case Trocado -> System.out.print(ANSI_YELLOW + guess.charAt(i) + ANSI_RESET + "\t");
                case Errado -> System.out.print(guess.charAt(i) + "\t");
            }
        }
        System.out.println("\n");
    }

    public static void reset(Certo[] c){

        for (int i = 0; i < 5; i++) { // Instanciar o Array como Errado
            c[i] = Certo.Errado;
        }
    }
}