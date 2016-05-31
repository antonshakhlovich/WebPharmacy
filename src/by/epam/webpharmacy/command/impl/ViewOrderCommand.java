package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;

import javax.servlet.http.HttpServletRequest;


/**
 * Class {@code ViewOrderCommand} is a non-guest implementation of {@see Command}
 * for viewing given order
 */

public class ViewOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
