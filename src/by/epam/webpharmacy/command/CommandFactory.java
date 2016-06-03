package by.epam.webpharmacy.command;

import by.epam.webpharmacy.command.impl.*;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * This class based on factory design pattern and provides all possible commands on demand.
 */
public class CommandFactory {

    private static HashMap<CommandName, Command> commandMap = new HashMap<>();

    private static final CommandFactory instance = new CommandFactory();


    private CommandFactory() {
        commandMap.put(CommandName.UNKNOWN, new UnknownCommand());
        commandMap.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        commandMap.put(CommandName.LOGIN, new LoginCommand());
        commandMap.put(CommandName.LOGOUT, new LogoutCommand());
        commandMap.put(CommandName.REGISTER, new RegisterCommand());
        commandMap.put(CommandName.BAN_USER, new BanUserCommand());
        commandMap.put(CommandName.VIEW_ADD_ITEM, new ViewAddItemCommand());
        commandMap.put(CommandName.ADD_ITEM, new AddItemCommand());
        commandMap.put(CommandName.DELETE_ITEM, new DeleteItemCommand());
        commandMap.put(CommandName.VIEW_EDIT_ITEM, new ViewEditItemCommand());
        commandMap.put(CommandName.VIEW_ITEM, new ViewItemCommand());
        commandMap.put(CommandName.VIEW_CATALOG, new ViewCatalogCommand());
        commandMap.put(CommandName.SEARCH_ITEM, new SearchItemCommand());
        commandMap.put(CommandName.VIEW_ORDER, new ViewOrderCommand());
        commandMap.put(CommandName.VIEW_ORDERS, new ViewOrdersCommand());
        commandMap.put(CommandName.VIEW_ALL_ORDERS, new ViewAllOrdersCommand());
        commandMap.put(CommandName.VIEW_SHOPPING_CART, new ViewShoppingCartCommand());
        commandMap.put(CommandName.REMOVE_ITEM_FROM_ORDER, new RemoveItemFromOrderCommand());
        commandMap.put(CommandName.ADD_ITEM_TO_ORDER, new AddItemToOrderCommand());
        commandMap.put(CommandName.SUBMIT_ORDER, new SubmitOrderCommand());
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    /**
     * Returns a command for corresponding command parameter in a http-request
     *
     * @param request http request from the servlet
     * @return corresponding action command or an empty command in case command is not specified
     * @throws CommandException if command parameter is invalid
     */
    public Command getCommand(HttpServletRequest request) throws CommandException {
        Command command = commandMap.get(CommandName.UNKNOWN);
        String commandRequest = request.getParameter(Parameter.COMMAND.getName());
        if (commandRequest == null || commandRequest.isEmpty()) {
            return command;
        } else {
            CommandName commandName;
            try {
                commandName = CommandName.valueOf(commandRequest.replace("-", "_").toUpperCase());
                if (commandMap.containsKey(commandName)) {
                    return commandMap.get(commandName);
                } else {
                    throw new CommandException("No such command in CommandFactory commands map");
                }
            } catch (IllegalArgumentException e) {
                throw new CommandException("command parameter is invalid (can't find it in CommandName enum)",e);
            }
        }
    }

}
