package jdbc.tasks;

public class InvalidCommandException extends Exception {

    public InvalidCommandException() {
        super("Invalid command");
    }
}
