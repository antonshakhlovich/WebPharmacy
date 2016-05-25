package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.util.JspPage;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code UnknownCommand} will be used when {@see CommandFactory} can't return
 * properly command on demand.
 */
public class UnknownCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return JspPage.ROOT.getPath();
    }
}
