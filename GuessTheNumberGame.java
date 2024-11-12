/*
Create a class game which allows a user to play "guess the number" game once.
Game should have follwing methods :
1. constructor to generate the random number
2. takeuserinput() to take a user input of number
3. iscorrectnumber() to detect whether the number entered is correct or not
4. getter and setter for number of guess
5. use properties such as no._of_guess(int) , etc to get the task done
 */


import java .util.Scanner;
import java.util.Random;

class Game {
    public int computerInput;
    public int noOfGuesses = 0;
    public int userInput;

    Game() {
        Random rd = new Random();
        this.computerInput = rd.nextInt(100);
    }

    public void setNoOfGuesses(int noOfGuesses) {
        this.noOfGuesses = noOfGuesses;
    }

    public int getNoOfGuesses() {
        return noOfGuesses;
    }

    void takeUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Guess a number : ");
        this.userInput = sc.nextInt();
    }

    boolean isCorrectNumber() {
        noOfGuesses++;
        if (userInput == computerInput) {
           System.out.format(" HURRAY!! You guess the right, it was %d " , computerInput);
            System.out.format("\n You guessed it in % d attempts  ",noOfGuesses );
            return true;
        }
        else if (userInput < computerInput) {
            System.out.println("Guess higher !");
        }
        else {
            System.out.println("Guess lower ! ");
        }
        return false;
    }


}

public class GuessTheNumberGame {
    public static void main(String[] args) {

        Game g = new Game();
        boolean b = false;

        while(!b) {
            g.takeUserInput();
            b = g.isCorrectNumber();
        }

    }
}