package by.epam.webpharmacy.controller;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.CommandFactory;
import by.epam.webpharmacy.command.CommandName;
import by.epam.webpharmacy.dao.util.ConnectionPool;
import by.epam.webpharmacy.dao.util.ConnectionPoolException;
import by.epam.webpharmacy.util.Parameter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles all requests, using command pattern.
 */

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class Controller extends HttpServlet {
    private final static Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = processRequest(request, response);
        if (page != null) {
            getServletContext().getRequestDispatcher(page).forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = processRequest(request, response);
        if (page != null) {
            response.sendRedirect(page);
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

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().closePool();
        } catch (ConnectionPoolException e) {
            LOG.error("Connection pool wasn't closed");
        }
    }
}
