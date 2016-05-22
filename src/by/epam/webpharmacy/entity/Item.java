package by.epam.webpharmacy.entity;

import java.math.BigDecimal;

/**
 * Class {@code Item} represents an item in the pharmacy's catalog.
 */
public class Item {
    private long id;
    private String label;
    private String dosageForm;
    private String dosage;
    private double volume;
    private String volumeType;
    private Company manufacturer;
    private BigDecimal price;
    private boolean byPrescription;
    private String description;
    private String imagePath;




}
