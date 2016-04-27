package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Anton on 27.04.2016.
 */
public class ChangeLocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
