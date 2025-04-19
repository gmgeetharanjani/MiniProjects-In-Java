package com.example.Splitwise.command;

public class RegisterUser implements Command {
    @Override
    public boolean matches(String input) {
        return input.startsWith("register");
    }

    @Override
    public void execute(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            System.out.println("Invalid command. Usage: register <name> <email>");
            return;
        }
        String name = parts[1];
        String email = parts[2];
        // Logic to register the user
        System.out.println("User registered with name: " + name + " and email: " + email);
    }
}
