package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.entity.Company;
import by.epam.webpharmacy.entity.DosageForm;
import by.epam.webpharmacy.entity.Item;
import by.epam.webpharmacy.service.CompanyService;
import by.epam.webpharmacy.service.ItemService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.impl.CompanyServiceImpl;
import by.epam.webpharmacy.service.impl.ItemServiceImpl;
import by.epam.webpharmacy.util.JspPage;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class {@code ViewEditItemCommand} is an admin and manager only implementation of {@see Command}
 * for viewing page, where admin can edit item from the catalog, it also requests requisites from database
 */
public class ViewEditItemCommand implements Command {

    private static ItemService itemService = ItemServiceImpl.getInstance();
    private static CompanyService companyService = CompanyServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long drugId = Long.parseLong(request.getParameter(Parameter.ID.getName()));
        try {
            Item item = itemService.selectItemById(drugId);
            List<DosageForm> dosageForms = itemService.getDosageForms();
            List<String> volumeTypes = itemService.getVolumeTypes();
            List<Company> companies = companyService.getCompanyList();
            request.setAttribute(Parameter.DOSAGE_FORMS.getName(), dosageForms);
            request.setAttribute(Parameter.VOLUME_TYPES.getName(),volumeTypes);
            request.setAttribute(Parameter.COMPANIES.getName(),companies);
            request.setAttribute(Parameter.ITEM.getName(),item);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return ViewPageCommand.VIEW_PAGE + JspPage.EDIT_ITEM;
    }
}
