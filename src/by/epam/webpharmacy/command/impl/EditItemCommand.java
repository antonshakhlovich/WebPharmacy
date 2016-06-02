package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.FileHelper;
import by.epam.webpharmacy.entity.Item;
import by.epam.webpharmacy.service.item.ItemService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.item.ItemServiceFactory;
import by.epam.webpharmacy.service.item.ItemServiceName;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * Class {@code EditItemCommand} is an admin and manager only implementation of {@see Command}
 * for editing given item from catalog
 */
public class EditItemCommand implements Command {

    private static FileHelper fileHelper = FileHelper.getInstance();
    private static final ItemService itemService = ItemServiceFactory.getInstance().getService(ItemServiceName.ITEM_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Item item = new Item();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new CommandException(e);
        }
        item.setId(Long.parseLong(request.getParameter(Parameter.ID.getName())));
        item.setLabel(request.getParameter(Parameter.LABEL.getName()));
        item.setDosageFormId(Long.parseLong(request.getParameter(Parameter.DOSAGE_FORM_ID.getName())));
        item.setDosage(request.getParameter(Parameter.DOSAGE.getName()));
        item.setVolume(Double.parseDouble(request.getParameter(Parameter.VOLUME.getName())));
        item.setVolumeType(request.getParameter(Parameter.VOLUME_TYPE.getName()));
        item.setManufacturerId(Long.parseLong(request.getParameter(Parameter.MANUFACTURER_ID.getName())));
        item.setPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter(Parameter.PRICE.getName()))));
        item.setByPrescription(Boolean.parseBoolean(request.getParameter(Parameter.BY_PRESCRIPTION.getName())));
        item.setDescription(request.getParameter(Parameter.DESCRIPTION.getName()));
        try {
            item.setImagePath(fileHelper.getImagePath(request));
        } catch (ServletException | IOException e) {
            throw new CommandException("Failed to process image file part", e);
        }

        HttpSession session = request.getSession();
        try {
            if (itemService.editItem(item)) {
                session.setAttribute(Parameter.SUCCESS_MESSAGE.getName(), Boolean.TRUE);
                session.setAttribute(Parameter.ITEM.getName(), itemService.selectItemById(item.getId()));
            } else {
                session.setAttribute(Parameter.ERROR_MESSAGE.getName(), Boolean.TRUE);
            }
        } catch (ServiceException e) {
            throw new CommandException("Failed to add new item to database", e);
        }
        try {
            response.sendRedirect(request.getHeader(Parameter.REFERER.getName()));
        } catch (IOException e) {
            throw new CommandException("Can't get referer header from request", e);
        }

    }

}
