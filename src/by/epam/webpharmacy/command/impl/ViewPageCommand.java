package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.entity.UserRole;
import by.epam.webpharmacy.util.JspPage;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;

/**
 * Extracts all present messages from the session to the request, checks security rights
 * and defines the page path to forward to
 */
public class ViewPageCommand implements Command {
    /**
     * Checks whether page is allowed to be viewed by a user. If allowed, adds messages to the request
     * and returns a page to view. If not, return path to the index page
     *
     * @param request request from the servlet which may contain messages in the session
     * @return path to the page to view
     */

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = JspPage.ROOT.getPath();
        String requestedPage = request.getParameter(Parameter.PAGE.getName()).replace("-", "_").toUpperCase();
        JspPage jspPage = JspPage.valueOf(requestedPage);
        User user = (User) (request.getSession().getAttribute(Parameter.USER.getName()));
        if (user == null) {
            user = new User();
            user.setRole(UserRole.GUEST);
        }
        if (jspPage.isRoleAllowed(user.getRole())) {
            return jspPage.getPath();
        }
        return result;
    }
}