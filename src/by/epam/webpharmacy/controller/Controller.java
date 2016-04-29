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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = null;
        try {
            Command command = CommandFactory.getInstance().getCommand(request);
            LOG.debug("executing " + command);
            page = command.execute(request);
        } catch (CommandException e) {
            LOG.error("Command execution failed", e);
        }
        if (page != null) {
            getServletContext().getRequestDispatcher(page).forward(request,response);

        } else {
            page = "/index.jsp";
        }
    }
}
