package colby;

import java.io.IOException;
import java.util.Scanner;

public class Colby {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Colby(String file) {
        ui = new Ui();
        storage = new Storage(file);
        taskList = new TaskList();
    }

        public String getResponse(String input) throws IOException {
            String command = Parser.parseCommand(input);

            if (command.equalsIgnoreCase("hello")) {
                return ui.showWelcomeMessage();
            } else if (command.equalsIgnoreCase("bye")) {
                return ui.showGoodbyeMessage();
            } else if (command.equalsIgnoreCase("list")) {
                return "Here's all the tasks you have to do: \n" + storage.returnFileContents();
            } else if (command.startsWith("mark")) {
                int index = Parser.parseIndex(input);
                taskList.markTaskAsDone(index);
                storage.writeToFile(taskList.getTasks());
                return ui.showTaskMarked(taskList.getTask(index));
            } else if (command.startsWith("unmark")) {
                int index = Parser.parseIndex(input);
                taskList.markTaskAsUndone(index);
                storage.writeToFile(taskList.getTasks());
                return ui.showTaskUnmarked(taskList.getTask(index));
            } else if (command.startsWith("todo")) {
                Task task = Parser.parseToDoTask(input);
                taskList.addTask(task);
                storage.appendToFile(task.toString() + "\n");
                return ui.showTaskAdded(task, taskList.size());
            } else if (command.startsWith("deadline")) {
                Task task = Parser.parseDeadlineTask(input);
                taskList.addTask(task);
                storage.appendToFile(task.toString() + "\n");
                return ui.showTaskAdded(task, taskList.size());
            } else if (command.startsWith("event")) {
                Task task = Parser.parseEventTask(input);
                taskList.addTask(task);
                storage.appendToFile(task.toString() + "\n");
                return ui.showTaskAdded(task, taskList.size());
            } else if (command.startsWith("delete")) {
                int index = Parser.parseIndex(input);
                Task task = taskList.deleteTask(index);
                storage.writeToFile(taskList.getTasks());
                return ui.showTaskDeleted(task);
            } else if (command.startsWith("find")) {
                return taskList.printMatchingTasks(input.split(" ", 2)[1]);
            }
            return "";
        }
}