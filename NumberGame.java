import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalScore = 0;
        boolean playAgain;

        do {
            int attempts = 0;
            int maxAttempts = 7;  // Limiting number of attempts
            int numberToGuess = random.nextInt(100) + 1;  // Generating a random number between 1 and 100
            boolean hasWon = false;

            System.out.println("Guess the number between 1 and 100:");

            // Game loop for guesses
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    hasWon = true;
                    totalScore += maxAttempts - attempts + 1; // Higher score for fewer attempts
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Too low!");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (!hasWon) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + numberToGuess + ".");
            }

            System.out.println("Your total score: " + totalScore);
            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thank you for playing! Your final score: " + totalScore);
        scanner.close();
    }
}
