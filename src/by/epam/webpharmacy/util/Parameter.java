package by.epam.webpharmacy.util;

/**
 * Contains names of all possible http parameters
 */
public enum Parameter {
    ADDRESS,
    BAN_STATUS,
    BY_PRESCRIPTION,
    CITY,
    COMMAND,
    COMPANY_ID,
    COMPANIES,
    COUNTRY,
    DESCRIPTION,
    DOSAGE,
    DOSAGE_FORM_ID,
    DOSAGE_FORM_NAME,
    DOSAGE_FORMS,
    EMAIL,
    ERROR_MESSAGE,
    FIRST_NAME,
    FROM,
    ID,
    IMAGE_FILE,
    IMAGE_PATH,
    ITEM,
    ITEMS,
    LABEL,
    LAST_NAME,
    LIMIT,
    LOCALE,
    LOGIN,
    LOGIN_FAILED,
    MANUFACTURER_NAME,
    MANUFACTURER_ID,
    NAME,
    NUMBER_OF_ITEMS,
    PAGE,
    PAGE_NUMBER,
    PASSWORD,
    PASSWORD_MD5,
    PHONE_NUMBER,
    PRICE,
    ROLE,
    SALT,
    SHORT_NAME,
    SUCCESS_MESSAGE,
    TYPE,
    USER,
    VOLUME,
    VOLUME_TYPE,
    VOLUME_TYPES,
    WEBSITE;

    public String getName() {
        return name().toLowerCase();
    }

}
