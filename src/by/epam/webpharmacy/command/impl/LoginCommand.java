package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code LoginCommand} is a guest-only implementation of {@see Command}
 * for signing in a user with given credentials
 */
public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return "/login";
    }
}
