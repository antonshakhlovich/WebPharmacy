package by.epam.webpharmacy.controller;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles all requests, using command pattern.
 */
@WebServlet("/controller")

public class Controller extends HttpServlet {
    private final static Logger LOG = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = processRequest(request, response);
        if (page != null) {
            LOG.debug("Command was requested from " +  request.getParameter("from"));
            getServletContext().getRequestDispatcher(page).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = processRequest(request, response);
        if (page != null) {
            LOG.debug("Command was requested from " +  request.getParameter("from"));
            response.sendRedirect(request.getParameter("from") + page);
        }
    }

    private String processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Command command = CommandFactory.getInstance().getCommand(request);
            LOG.debug("executing " + command);
            return command.execute(request);
        } catch (CommandException e) {
            LOG.error("Command execution failed", e);
            response.sendError(500);
            return null;
        }
    }
}
