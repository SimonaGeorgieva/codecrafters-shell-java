import java.io.File;
import java.io.IOException;
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

    static void main(String[] args) throws IOException, InterruptedException {
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

            if (findExecutable(command) != null) {
                ProcessBuilder processBuilder = new ProcessBuilder(commandLine.split(SEPARATOR));
                processBuilder.inheritIO();
                Process process = processBuilder.start();
                process.waitFor();
                continue;
            }

            switch (command) {
                case ECHO -> System.out.println(operand);
                case TYPE -> handleType(operand);
                case EXIT -> System.exit(0);
                default -> System.out.printf("%s: command not found%n", commandLine);
            }
        }
    }

    private static void handleType(String operand) {
        if (builtins.contains(operand)) {
            System.out.printf("%s is a shell builtin%n", operand);
            return;
        }
        Path filePath = findExecutable(operand);
        if (filePath != null) {
            System.out.printf("%s is %s%n", operand, filePath);
        } else {
            System.out.printf("%s: not found%n", operand);
        }
    }

    private static Path findExecutable(String command) {
        String pathEnv = System.getenv(PATH);
        if (pathEnv == null) {
            return null;
        }
        Path filePath;
        for (String directory : pathEnv.split(File.pathSeparator)) {
            filePath = Path.of(directory, command);
            if (Files.exists(filePath) && Files.isExecutable(filePath)) {
                return filePath;
            }
        }
        return null;
    }
}
