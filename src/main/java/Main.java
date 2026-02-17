import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static final String DEFAULT_LINE = "$ ";
    public static final String EXIT = "exit";
    public static final String ECHO = "echo";
    public static final String TYPE = "type";
    public static final Set<String> builtins = new HashSet<>(List.of(EXIT, ECHO, TYPE));
    public static final String SEPARATOR = " ";

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String commandLine;
        String command;
        String operand;

        while (true) {
            System.out.print(DEFAULT_LINE);
            commandLine = scanner.nextLine();

            if (commandLine == null || commandLine.isBlank()) {
                continue;
            }
            commandLine = commandLine.trim();
            command = commandLine.split(SEPARATOR)[0];
            operand = commandLine.substring(command.length()).trim();

            if (ECHO.equals(command)) {
                System.out.println(operand);
                continue;
            }

            if (TYPE.equals(command)) {
                if (operand.isBlank()) {
                    continue;
                }
                System.out.printf(builtins.contains(operand) ? "%s is a shell builtin%n" : "%s: not found%n", operand);
                continue;
            }

            if (EXIT.equals(command)) {
                break;
            }
            System.out.printf("%s: command not found%n", commandLine);
        }
    }
}
