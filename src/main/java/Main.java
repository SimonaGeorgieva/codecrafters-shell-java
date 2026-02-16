import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        System.out.print("$ ");

        Scanner scanner = new Scanner(System.in);
        String userMessage = scanner.nextLine();

        System.out.printf("%s: command not found%n", userMessage);
    }
}
