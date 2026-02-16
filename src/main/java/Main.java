import java.util.Scanner;

public class Main {

    public static final String EXIT = "exit";

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userMessage;

        while (true) {
            System.out.print("$ ");
            userMessage = scanner.nextLine();

            if (userMessage != null && EXIT.equals(userMessage.trim())) {
                break;
            }
            System.out.printf("%s: command not found%n", userMessage);
        }
    }
}
