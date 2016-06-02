package by.epam.webpharmacy.command.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * This class helps to operate files that was received from user requests
 */
public class FileHelper {

    private static final FileHelper instance = new FileHelper();
    private static final String SAVE_DIR = "images\\items";


    private FileHelper() {

    }

    public static FileHelper getInstance() {
        return instance;
    }

    public String getImagePath(HttpServletRequest request) throws IOException, ServletException {
        Part part = request.getPart(Parameter.IMAGE_FILE.getName());
        if (part.getSize() != 0) {
            String appPath = request.getServletContext().getRealPath("");
            String savePath = appPath + File.separator + SAVE_DIR;
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
            String fileName = Calendar.getInstance().getTimeInMillis() + extractFileName(part);
            part.write(savePath + File.separator + fileName);
            return File.separator + SAVE_DIR + File.separator + fileName;
        }
        return null;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
