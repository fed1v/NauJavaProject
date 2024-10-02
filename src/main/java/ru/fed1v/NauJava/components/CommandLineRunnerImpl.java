package ru.fed1v.NauJava.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.fed1v.NauJava.controller.CommandProcessor;

import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CommandProcessor commandProcessor;

    @Autowired
    public CommandLineRunnerImpl(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Override
    public void run(String... args) {
        System.out.println("Run");

        System.out.println("Syntax: \n" +
                "> add foodId foodName foodKcal\n" +
                "> get id\n" +
                "> getAll\n" +
                "> update id foodName foodKcal\n" +
                "> delete id\n"
        );
        
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите команду. 'exit' для выхода.");
            while (true) {
                
                System.out.print("> ");

                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input.trim())) {
                    System.out.println("Выход из программы...");
                    break;
                }

                commandProcessor.processCommand(input);
            }
        }
    }
}
