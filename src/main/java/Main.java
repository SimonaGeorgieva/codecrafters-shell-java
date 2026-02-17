import java.util.Scanner;

public class Main {

    public static final String DEFAULT_LINE = "$ ";
    public static final String EXIT = "exit";
    public static final String ECHO = "echo";
    public static final String SEPARATOR = " ";

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String commandLine;
        String command;

        while (true) {
            System.out.print(DEFAULT_LINE);
            commandLine = scanner.nextLine();

            if (commandLine == null || commandLine.isBlank()) {
                continue;
            }
            commandLine = commandLine.trim();
            command = commandLine.split(SEPARATOR)[0];

            if (ECHO.equals(command)) {
                System.out.println(commandLine.substring(command.length()).trim());
                continue;
            }

            if (EXIT.equals(command)) {
                break;
            }
            System.out.printf("%s: command not found%n", commandLine);
        }
    }
}
