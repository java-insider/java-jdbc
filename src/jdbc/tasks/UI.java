package jdbc.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.isNull;

public final class UI {

    private UI() {}

    public static Command nextCommand() throws InvalidCommandException {
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);

        try {
            String line = scanner.nextLine().trim();
            List<String> params = new ArrayList<>();

            int endPos = line.indexOf(' ');

            if (endPos < 0) {
                endPos = line.length();
            } else {
                params = Arrays.asList(line.substring(endPos + 1).split(","));
            }

            Action action = Action.fromString(line.substring(0, endPos));

            return new Command(action, params);
        } catch (Exception e) {
            throw new InvalidCommandException();
        }
    }

    public static void show(String text) {
        if (!isNull(text)) {
            System.out.println(text);
            System.out.println();
        }
    }
}
