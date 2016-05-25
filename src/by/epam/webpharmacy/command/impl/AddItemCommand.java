package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.entity.Item;
import by.epam.webpharmacy.service.ItemService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.impl.ItemServiceImpl;
import by.epam.webpharmacy.util.JspPage;
import by.epam.webpharmacy.util.Parameter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * Class {@code AddItemCommand} is an admin-only implementation of {@see Command}
 * for adding new item to catalog
 */
public class AddItemCommand implements Command {

    private static final String SAVE_DIR = "images\\items";
    private static final ItemService itemService = ItemServiceImpl.getInstance();
    private static final String VIEW_ADD_ITEM = "/Controller?command=view_add_item";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Item item = new Item();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new CommandException(e);
        }
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
            Part part = request.getPart(Parameter.IMAGE_FILE.getName());
            if (part.getSize() != 0) {
                String appPath = request.getServletContext().getRealPath("");
                String savePath = appPath + File.separator + SAVE_DIR;
                File fileSaveDir = new File(savePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdir();
                }
                String fileName = extractFileName(part);
                part.write(savePath + File.separator + fileName);
                item.setImagePath(File.separator + SAVE_DIR + File.separator + fileName);
            }


        } catch (ServletException | IOException e) {
            throw new CommandException("Failed to process image file part", e);
        }
        try {
            if (itemService.addItem(item)) {
                request.getSession().setAttribute(Parameter.SUCCESS_MESSAGE.getName(), Boolean.TRUE);
            } else {
                request.getSession().setAttribute(Parameter.ERROR_MESSAGE.getName(), Boolean.TRUE);
            }
        } catch (ServiceException e) {
            throw new CommandException("Failed to add new item to database", e);
        }

        return VIEW_ADD_ITEM;
    }
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
