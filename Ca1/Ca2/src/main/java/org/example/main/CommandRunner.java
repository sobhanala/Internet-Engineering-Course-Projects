package org.example.main;

import Business.BookService;
import Business.ICommand;
import Business.ShopService;
import Business.UserService;
import Business.commands.*;
import Infrastucure.JsonParser;
import Objects.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandRunner {
    private final JsonParser jsonParser;
    private final Map<String, ICommand> commandMap;

    public CommandRunner(JsonParser jsonParser, ShopService shopService,BookService bookService,UserService userService) {
        this.jsonParser = jsonParser;
        this.commandMap = initializeCommands(shopService,bookService,userService);
    }

    private Map<String, ICommand> initializeCommands(ShopService shopService, BookService bookService, UserService userService) {
        Map<String, ICommand> commands = new HashMap<>();
        commands.put("add_user", new AddUserCommand(userService));
        commands.put("add_author", new AddAuthorCommand(userService));
        commands.put("add_book", new AddBookCommand(bookService));
        commands.put("add_cart", new AddCartCommand(shopService));
        commands.put("remove_cart", new RemoveCartCommand(shopService));
        commands.put("add_credit", new AddCreditCommand(shopService));
        commands.put("purchase_cart", new PurchaseCartCommand(shopService));


        return commands;
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
        String commandName = (spaceIndex == -1) ? line : line.substring(0, spaceIndex);
        String jsonInput = (spaceIndex == -1) ? "{}" : line.substring(spaceIndex + 1);
        executeCommand(commandName, jsonInput);
    }

    private void executeCommand(String commandName, String jsonInput) {
        ICommand command = commandMap.get(commandName);
        if (command == null) {
            return;
        }

        try {
            Object dto = jsonParser.deserialize(jsonInput, command.getDtoClass());
            Response result = command.execute(dto);
            respond(result);
        } catch (Exception e) {
            respond(new Response(false, e.getMessage(), null));//TODO
        }
    }

    private void respond(Response response) {
        System.out.println(jsonParser.serialize(response));
    }
}