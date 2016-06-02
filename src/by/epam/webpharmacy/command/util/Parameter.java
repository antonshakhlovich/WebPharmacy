package by.epam.webpharmacy.command.util;

/**
 * Contains names of all possible http parameters
 */
public enum Parameter {
    ACCESS_DENIED,
    ADDRESS,
    AMOUNT,
    BAN_STATUS,
    BY_PRESCRIPTION,
    CITY,
    CHANGE_QUANTITY,
    COMMAND,
    COMMAND_TO,
    COMPANY_ID,
    COMPANIES,
    COUNTRY,
    CUSTOMER_ID,
    DATE,
    DESCRIPTION,
    DOSAGE,
    DOSAGE_FORM_ID,
    DOSAGE_FORM_NAME,
    DOSAGE_FORMS,
    DRUG_ID,
    EMAIL,
    ERROR_MESSAGE,
    ERROR_MESSAGE_DELETE,
    FIRST_NAME,
    FROM,
    ID,
    IS_CANCELED,
    IMAGE_FILE,
    IMAGE_PATH,
    ITEM,
    ITEM_ID,
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
    OLD_QUANTITY,
    ORDER,
    ORDER_ID,
    ORDERS,
    PAGE,
    PAGE_NUMBER,
    PASSWORD,
    PASSWORD_MD5,
    PHONE_NUMBER,
    QUANTITY,
    PRICE,
    REFERER,
    ROLE,
    SALT,
    SEARCH,
    SHORT_NAME,
    SHOPPING_CART,
    STATUS,
    SUCCESS_MESSAGE,
    SUCCESS_MESSAGE_DELETE,
    TIMESTAMP,
    TYPE,
    USER,
    USER_ID,
    USER_NAME,
    VOLUME,
    VOLUME_TYPE,
    VOLUME_TYPES,
    WEBSITE;

    public String getName() {
        return name().toLowerCase();
    }

}