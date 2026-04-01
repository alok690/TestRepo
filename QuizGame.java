import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuizGame {
    private static class Question {
        private final String prompt;
        private final List<String> choices;
        private final int correctIndex;

        Question(String prompt, List<String> choices, int correctIndex) {
            this.prompt = prompt;
            this.choices = choices;
            this.correctIndex = correctIndex;
        }
    }

    public static void main(String[] args) {
        List<Question> questions = buildQuestions();
        Collections.shuffle(questions);

        Scanner scanner = new Scanner(System.in);
        int score = 0;

        System.out.println("===========================");
        System.out.println(" Welcome to Java Quiz Game ");
        System.out.println("===========================\n");

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            askQuestion(i + 1, question);
            int answer = readAnswer(scanner, question.choices.size());

            if (answer - 1 == question.correctIndex) {
                System.out.println("Correct!\n");
                score++;
            } else {
                String correctChoice = question.choices.get(question.correctIndex);
                System.out.println("Wrong! Correct answer: " + correctChoice + "\n");
            }
        }

        int total = questions.size();
        double percentage = (score * 100.0) / total;
        System.out.println("Quiz complete!");
        System.out.println("Score: " + score + " / " + total);
        System.out.printf("Percentage: %.2f%%\n", percentage);

        if (percentage == 100.0) {
            System.out.println("Outstanding! Perfect score!");
        } else if (percentage >= 70.0) {
            System.out.println("Great job! You passed.");
        } else {
            System.out.println("Keep practicing and try again!");
        }

        scanner.close();
    }

    private static void askQuestion(int number, Question question) {
        System.out.println("Question " + number + ": " + question.prompt);
        for (int i = 0; i < question.choices.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + question.choices.get(i));
        }
    }

    private static int readAnswer(Scanner scanner, int maxOption) {
        while (true) {
            System.out.print("Your answer (1-" + maxOption + "): ");
            String input = scanner.nextLine().trim();

            try {
                int answer = Integer.parseInt(input);
                if (answer >= 1 && answer <= maxOption) {
                    return answer;
                }
            } catch (NumberFormatException ignored) {
                // handled below
            }

            System.out.println("Invalid choice. Please enter a number between 1 and " + maxOption + ".");
        }
    }

    private static List<Question> buildQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
            "Which keyword is used to inherit a class in Java?",
            Arrays.asList("implements", "extends", "inherits", "super"),
            1
        ));

        questions.add(new Question(
            "What is the size of int in Java?",
            Arrays.asList("8 bits", "16 bits", "32 bits", "64 bits"),
            2
        ));

        questions.add(new Question(
            "Which method is the entry point of a Java program?",
            Arrays.asList("start()", "run()", "main()", "init()"),
            2
        ));

        questions.add(new Question(
            "Which collection does NOT allow duplicate elements?",
            Arrays.asList("List", "Queue", "Map", "Set"),
            3
        ));

        questions.add(new Question(
            "Which exception is unchecked?",
            Arrays.asList("IOException", "SQLException", "ClassNotFoundException", "NullPointerException"),
            3
        ));

        return questions;
    }
}
