package by.epam.webpharmacy.util;

/**
 * Contains names of all possible http parameters
 */
public enum Parameter {
    ADDRESS,
    AMOUNT,
    BAN_STATUS,
    BY_PRESCRIPTION,
    CITY,
    COMMAND,
    COMPANY_ID,
    COMPANIES,
    COUNTRY,
    CUSTOMER_ID,
    DESCRIPTION,
    DOSAGE,
    DOSAGE_FORM_ID,
    DOSAGE_FORM_NAME,
    DOSAGE_FORMS,
    DRUG_ID,
    EMAIL,
    ERROR_MESSAGE,
    FIRST_NAME,
    FROM,
    ID,
    IS_CANCELED,
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
    QUANTITY,
    PRICE,
    ROLE,
    SALT,
    SEARCH,
    SHORT_NAME,
    STATUS,
    SUCCESS_MESSAGE,
    TIMESTAMP,
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
