package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.JspPage;
import by.epam.webpharmacy.entity.Company;
import by.epam.webpharmacy.entity.DosageForm;
import by.epam.webpharmacy.service.company.CompanyService;
import by.epam.webpharmacy.service.company.CompanyServiceFactory;
import by.epam.webpharmacy.service.company.CompanyServiceName;
import by.epam.webpharmacy.service.item.ItemService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.item.ItemServiceFactory;
import by.epam.webpharmacy.service.item.ItemServiceName;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class {@code ViewAddItemCommand} is an admin and manager only implementation of {@see Command}
 * for viewing page, where admin can add new items to the catalog, it also requests requisites from database
 */
public class ViewAddItemCommand implements Command {
    private static final ItemService itemService = ItemServiceFactory.getInstance().getService(ItemServiceName.ITEM_SERVICE);
    private static CompanyService companyService = CompanyServiceFactory.getInstance().getService(CompanyServiceName.COMPANY_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            List<DosageForm> dosageForms = itemService.getDosageForms();
            List<String> volumeTypes = itemService.getVolumeTypes();
            List<Company> companies = companyService.getCompanyList();
            request.setAttribute(Parameter.DOSAGE_FORMS.getName(), dosageForms);
            request.setAttribute(Parameter.VOLUME_TYPES.getName(),volumeTypes);
            request.setAttribute(Parameter.COMPANIES.getName(),companies);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        try {
            request.getRequestDispatcher(JspPage.ADD_ITEM.getPath()).forward(request,response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}
