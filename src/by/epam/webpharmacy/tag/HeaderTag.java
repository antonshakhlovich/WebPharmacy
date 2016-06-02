package by.epam.webpharmacy.tag;

import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.entity.UserRole;
import by.epam.webpharmacy.command.util.JspPage;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Jstl custom tag class for retrieving page header in accordance with user role
 */

public class HeaderTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try {
            User currentUser = (User) pageContext.getSession().getAttribute(Parameter.USER.getName());
            if (currentUser != null) {
                if (UserRole.USER == currentUser.getRole()) {
                    pageContext.include(JspPage.USER_HEADER.getPath());
                } else if (UserRole.ADMIN == currentUser.getRole()) {
                    pageContext.include(JspPage.ADMIN_HEADER.getPath());
                } else if (UserRole.DOCTOR == currentUser.getRole()) {
                    pageContext.include(JspPage.DOCTOR_HEADER.getPath());
                } else if (UserRole.MANAGER == currentUser.getRole()) {
                    pageContext.include(JspPage.MANAGER_HEADER.getPath());
                }
            } else {
                pageContext.include(JspPage.GUEST_HEADER.getPath());
            }
        } catch (ServletException | IOException e) {
            throw new JspTagException(e);
        }
        return SKIP_BODY;
    }
}
