package by.epam.webpharmacy.command;

import by.epam.webpharmacy.command.impl.*;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * This class based on factory design pattern and provides all possible commands on demand.
 */
public class CommandFactory {

    private static HashMap<CommandName, Command> commands = new HashMap<>();

    private static final CommandFactory instance = new CommandFactory();


    private CommandFactory() {
        commands.put(CommandName.UNKNOWN, new UnknownCommand());
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.REGISTER, new RegisterCommand());
        commands.put(CommandName.BAN_USER, new BanUserCommand());
        commands.put(CommandName.VIEW_ADD_ITEM, new ViewAddItemCommand());
        commands.put(CommandName.ADD_ITEM, new AddItemCommand());
        commands.put(CommandName.DELETE_ITEM, new DeleteItemCommand());
        commands.put(CommandName.VIEW_EDIT_ITEM, new ViewEditItemCommand());
        commands.put(CommandName.VIEW_PAGE, new ViewPageCommand());
        commands.put(CommandName.VIEW_ITEM, new ViewItemCommand());
        commands.put(CommandName.VIEW_CATALOG, new ViewCatalogCommand());
        commands.put(CommandName.SEARCH_ITEM, new SearchItemCommand());
        commands.put(CommandName.VIEW_ORDER, new ViewOrderCommand());
        commands.put(CommandName.VIEW_SHOPPING_CART, new ViewShoppingCartCommand());
        commands.put(CommandName.REMOVE_ITEM_FROM_ORDER, new RemoveItemFromOrderCommand());
        commands.put(CommandName.ADD_ITEM_TO_ORDER, new AddItemToOrderCommand());
        commands.put(CommandName.SUBMIT_ORDER, new SubmitOrderCommand());
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
        Command command = commands.get(CommandName.UNKNOWN);
        String commandRequest = request.getParameter(Parameter.COMMAND.getName());
        if (commandRequest == null || commandRequest.isEmpty()) {
            return command;
        } else {
            CommandName commandName;
            try {
                commandName = CommandName.valueOf(commandRequest.replace("-", "_").toUpperCase());
                if (commands.containsKey(commandName)) {
                    return commands.get(commandName);
                } else {
                    throw new CommandException("No such command in CommandFactory commands map");
                }
            } catch (IllegalArgumentException e) {
                throw new CommandException("command parameter is invalid (can't find it in CommandName enum)",e);
            }
        }
    }

}
