import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final String DEFAULT_LINE = "$ ";
    private static final String EXIT = "exit";
    private static final String ECHO = "echo";
    private static final String TYPE = "type";
    private static final Set<String> builtins = new HashSet<>(List.of(EXIT, ECHO, TYPE));
    private static final String SEPARATOR = " ";
    private static final String PATH = "PATH";

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

            switch (command) {
                case ECHO -> System.out.println(operand);
                case TYPE -> handleType(operand);
                case EXIT -> System.exit(0);
                default -> System.out.printf("%s: command not found%n", commandLine);
            }
        }
    }

    private static void handleType(String operand) {
        String pathEnv = System.getenv(PATH);
        String[] directories = pathEnv.split(File.pathSeparator);

        if (builtins.contains(operand)) {
            System.out.printf("%s is a shell builtin%n", operand);
            return;
        }

        Path filePath;
        for (String directory : directories) {
            filePath = Path.of(directory, operand);

            if (Files.exists(filePath) && Files.isExecutable(filePath)) {
                System.out.printf("%s is %s%n", operand, filePath);
                return;
            }
        }
        System.out.printf("%s: not found%n", operand);
    }
}
