import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    static Scanner scanner = new Scanner(System.in);
    static String[] questions = {
            "1. What is the capital of France?\nA. Berlin\nB. Madrid\nC. Paris\nD. Rome",
            "2. Who wrote 'Hamlet'?\nA. Charles Dickens\nB. William Shakespeare\nC. Mark Twain\nD. Leo Tolstoy",
            "3. What is the largest planet in our solar system?\nA. Earth\nB. Jupiter\nC. Mars\nD. Venus"
    };
    static char[] correctAnswers = {'C', 'B', 'B'};
    static char[] userAnswers = new char[questions.length];
    static int score = 0;
    static int timeLimit = 10;  // Time limit per question in seconds
    static boolean timeout = false;

    public static void main(String[] args) {
        startQuiz();
        displayResult();
    }

    // Start the quiz
    public static void startQuiz() {
        for (int i = 0; i < questions.length; i++) {
            timeout = false;
            System.out.println(questions[i]);
            System.out.println("You have " + timeLimit + " seconds to answer.");
            char answer = getUserAnswerWithinTime();

            if (!timeout && answer == correctAnswers[i]) {
                score++;
                userAnswers[i] = answer;
            } else {
                userAnswers[i] = timeout ? 'X' : answer; // 'X' indicates timeout
            }
        }
    }

    // Get user answer within the given time limit
    public static char getUserAnswerWithinTime() {
        final char[] answer = {' '};
        Timer timer = new Timer();
        
        // Timer to force timeout
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up!");
                timeout = true;
            }
        };
        
        timer.schedule(task, timeLimit * 1000);

        // Thread for user input
        Thread inputThread = new Thread(() -> {
            System.out.print("Enter your answer (A/B/C/D): ");
            answer[0] = scanner.next().toUpperCase().charAt(0);
            timer.cancel();  // Cancel timer if user answers in time
        });
        
        inputThread.start();

        // Wait for the user answer or timeout
        try {
            inputThread.join(timeLimit * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return answer[0];
    }

    // Display the final result
    public static void displayResult() {
        System.out.println("\nQuiz Result:");
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.println("Correct Answer: " + correctAnswers[i]);
            System.out.println("Your Answer: " + (userAnswers[i] == 'X' ? "Timeout" : userAnswers[i]));
            System.out.println();
        }
        System.out.println("Your final score: " + score + " out of " + questions.length);
    }
}

