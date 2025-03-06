package org.example.main;

import Business.ICommand;
import Business.ShopService;
import Business.WalletService;
import Business.commands.*;
import Infrastucure.JsonParser;
import Objects.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandRunner {
    private final JsonParser jsonParser;
    private final Map<String, ICommand> commandMap;

    public CommandRunner(JsonParser jsonParser, ShopService shopService,WalletService walletService) {
        this.jsonParser = jsonParser;
        this.commandMap = initializeCommands(shopService,walletService);
    }

    private Map<String, ICommand> initializeCommands(ShopService shopService, WalletService walletService) {
        Map<String, ICommand> commands = new HashMap<>();
        commands.put("add_user", new AddUserCommand(shopService));
        commands.put("add_author", new AddAuthorCommand(shopService));
        commands.put("add_book", new AddBookCommand(shopService));
        commands.put("add_cart", new AddCartCommand(shopService));
        commands.put("remove_cart", new RemoveCartCommand(shopService));
        commands.put("add_credit", new AddCreditCommand(walletService));

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
            respond(false, "Unknown command: " + commandName, null);
            return;
        }

        try {
            Object dto = jsonParser.deserialize(jsonInput, command.getDtoClass());
            String result = command.execute(dto); // Command handles mapping and calls ShopService
            respond(true, "Command executed successfully", result);
        } catch (Exception e) {
            respond(false, "Invalid input: " + e.getMessage(), null);
        }
    }

    private void respond(boolean success, String message, String data) {
        System.out.println(jsonParser.serialize(new Response(success, message, data)));
    }
}