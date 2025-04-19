package com.example.Splitwise.command;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    private final List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommand(String input) {
        for (Command command : commands) {
            if (command.matches(input)) {
                command.execute(input);
                return;
            }
        }
        System.out.println("Invalid command");
    }

    public void removeCommand(Command command) {
        commands.remove(command);
    }
}
