package by.epam.webpharmacy.managers;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    EN("en-US"), RU("ru-RU");
    private ResourceBundle resourceBundle;

    MessageManager(String localeString) {
        resourceBundle = ResourceBundle.getBundle("resources.local", Locale.forLanguageTag(localeString));
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
