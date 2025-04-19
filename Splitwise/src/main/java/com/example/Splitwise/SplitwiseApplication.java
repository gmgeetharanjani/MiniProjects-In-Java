package com.example.Splitwise;

import com.example.Splitwise.command.CommandExecutor;
import com.example.Splitwise.command.RegisterUser;
import com.example.Splitwise.command.SettleUp;
import com.example.Splitwise.controller.SettleUpController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {

	Scanner scanner = new Scanner(System.in);
	CommandExecutor commandExecutor = new CommandExecutor();

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Welcome to Splitwise!");
		System.out.println("Please enter your command:");

		// Register commands
		commandExecutor.addCommand(new RegisterUser());
		SettleUpController settleUpController = new SettleUpController();
		commandExecutor.addCommand(new SettleUp(settleUpController));

		while (true) {
			String input = scanner.next();
			if (input.equalsIgnoreCase("exit")) {
				break;
			}
			commandExecutor.executeCommand(input);
		}
		System.out.println("Exiting Splitwise. Goodbye!");
	}
}
