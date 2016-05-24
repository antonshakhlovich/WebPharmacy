package by.epam.webpharmacy.command;

import by.epam.webpharmacy.command.impl.*;
import sun.plugin.com.event.COMEventHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class based on factory design pattern and provides all possible commands on demand.
 */
public class CommandFactory {

    private static CommandFactory instance;
    private static AtomicBoolean isNull = new AtomicBoolean(true);
    private static ReentrantLock lock = new ReentrantLock();
    private static HashMap<CommandName, Command> commands = new HashMap<>();


    private CommandFactory() {
        commands.put(CommandName.UNKNOWN, new UnknownCommand());
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.REGISTER, new RegisterCommand());
        commands.put(CommandName.BAN_USER, new BanUserCommand());
        commands.put(CommandName.VIEW_ADD_ITEM, new ViewAddItemCommand());
    }

    public static CommandFactory getInstance() {
        if (isNull.get()) {
            lock.lock();
            if (isNull.get()) {
                instance = new CommandFactory();
                isNull.set(false);
            }
            lock.unlock();
        }
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
        String commandRequest = request.getParameter("command");
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
                throw new CommandException("command parameter is invalid (can't find it in CommandName enum).");
            }
        }
    }

}
