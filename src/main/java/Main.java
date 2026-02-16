import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userMessage;

        while (true) {
            System.out.print("$ ");
            userMessage = scanner.nextLine();
            System.out.printf("%s: command not found%n", userMessage);
        }
    }
}
