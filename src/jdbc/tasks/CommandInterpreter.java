package jdbc.tasks;

import jdbc.dao.core.DAOException;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class CommandInterpreter {

    private final TaskDAO dao;

    public CommandInterpreter(TaskDAO dao) {
        this.dao = dao;
    }

    public String interpret(Command command) {
        var params = command.params();
        return switch (command.action()) {
            case LIST -> list();
            case QUIT -> quit();
            case INSERT -> insert(params);
            case DELETE -> delete(params);
            case UPDATE -> update(params);
        };
    }

    private String list() {
        var tasks = dao.list();

        String tasksStr = tasks
            .stream()
            .map(t -> String.format("%-4d%s", t.id(), t.description()))
            .collect(joining("\n"));

        return String.format("%s\n\n%d tasks found.", tasksStr, tasks.size());
    }

    private String quit() {
        System.exit(0);
        return null;
    }

    private String insert(List<String> params) {
        if (params.isEmpty()) {
            return "You must provide a description";
        }

        dao.insert(new Task(params.get(0)));
        return "Task added successfully!";
    }

    private String delete(List<String> params) {
        if (params.isEmpty()) {
            return "You must provide an ID";
        }

        try {
            dao.deleteById(Integer.parseInt(params.get(0)));
        } catch (NumberFormatException e) {
            return "ID is not valid";
        } catch (DAOException e) {
            return "Task does not exist";
        }
        return "Task deleted successfully!";
    }

    private String update(List<String> params) {
        if (params.size() < 2) {
            return "You must provide an ID and a description";
        }

        try {
            dao.update(new Task(Integer.parseInt(params.get(0)), params.get(1)));
        } catch (NumberFormatException e) {
            return "ID is not valid";
        } catch (DAOException e) {
            return "Task does not exist";
        }
        return "Task updated successfully!";
    }
}
