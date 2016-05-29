package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.entity.Company;
import by.epam.webpharmacy.entity.DosageForm;
import by.epam.webpharmacy.service.CompanyService;
import by.epam.webpharmacy.service.ItemService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.impl.CompanyServiceImpl;
import by.epam.webpharmacy.service.impl.ItemServiceImpl;
import by.epam.webpharmacy.util.JspPage;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class {@code ViewAddItemCommand} is an admin-only implementation of {@see Command}
 * for viewing page, where admin can add new items to the catalog, it also requests requisites from database
 */
public class ViewAddItemCommand implements Command {
    private static ItemService itemService = ItemServiceImpl.getInstance();
    private static CompanyService companyService = CompanyServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            HttpSession session = request.getSession();
            List<DosageForm> dosageForms = itemService.getDosageForms();
            List<String> volumeTypes = itemService.getVolumeTypes();
            List<Company> companies = companyService.getCompanyList();
            request.setAttribute(Parameter.DOSAGE_FORMS.getName(), dosageForms);
            request.setAttribute(Parameter.VOLUME_TYPES.getName(),volumeTypes);
            request.setAttribute(Parameter.COMPANIES.getName(),companies);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return JspPage.ADD_ITEM.getPath();
    }
}
