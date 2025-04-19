package com.example.Splitwise.command;

public interface Command {
    public boolean matches(String input);
    public void execute(String input);
}
