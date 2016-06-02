package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@code UnknownCommand} will be used when {@see CommandFactory} can't return
 * properly command on demand.
 */
public class UnknownCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            response.sendRedirect(request.getContextPath());
        } catch (IOException e) {
            throw new CommandException(e);
        }
    }
}
