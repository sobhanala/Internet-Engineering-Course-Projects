package org.example.main;

import Business.models.Author;
import Business.models.Book;
import Business.models.DataLoader;
import Business.models.Person;
import Infrastucure.JsonParser;
import Objects.*;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;


public class CommandRunner {
    private final JsonParser jsonParser;

    private final Map<String, CommandHandler<?>> commandHandlers = Map.of(
            "add_user", new CommandHandler<>(PersonDto.class, Mapper::toPersonObject),
            "add_author", new CommandHandler<>(AuthorDto.class, Mapper::toAuthorObject),
            "add_book", new CommandHandler<>(BookDto.class, Mapper::toBookObject)
    );

    public CommandRunner(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                processCommand(line);
            }
        }
    }

    private void processCommand(String line) {
        int spaceIndex = line.indexOf(" ");
        String command = (spaceIndex == -1) ? line : line.substring(0, spaceIndex);
        String jsonInput = (spaceIndex == -1) ? "{}" : line.substring(spaceIndex + 1);
        handleCommand(command, jsonInput);
    }

    private <T> void handleCommand(String command, String jsonInput) {
        CommandHandler<T> handler = (CommandHandler<T>) commandHandlers.get(command);

        if (handler == null) {
            System.out.println(jsonParser.serialize(new Response(false, "Unknown command: " + command, null)));
            return;
        }
        try {
            T dto = jsonParser.deserialize(jsonInput, handler.dtoClass());
            System.out.println("DTO: " + dto);

            Object mappedObject = handler.mapper.apply(dto);
            System.out.println("Mapped Object: " + mappedObject);

            if (command.equals("add_user")) {
                DataLoader.usersList.add((Person) mappedObject);
            } else if (command.equals("add_author")) {
                DataLoader.authorList.add((Author) mappedObject);
            } else if (command.equals("add_book")) {
                DataLoader.bookList.add((Book) mappedObject);
            }
            DataLoader.usersList.forEach(System.out::println);
            System.out.println(jsonParser.serialize(new Response(true, "Command executed", dto)));

        } catch (Exception e) {
            e.printStackTrace(); // Debugging
            System.out.println(jsonParser.serialize(new Response(false, "Invalid input: " + e.getMessage(), null)));
        }
    }

    private record CommandHandler<T>(Class<T> dtoClass, Function<T, ?> mapper) {}
}
