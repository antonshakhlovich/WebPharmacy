package by.epam.webpharmacy.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class {@code Item} represents an item in the pharmacy's catalog.
 */
public class Item implements Serializable {

    private long id;
    private String label;
    private long dosageFormId;
    private String dosage;
    private double volume;
    private String volumeType;
    private long manufacturerId;
    private BigDecimal price;
    private boolean byPrescription;
    private String description;
    private String imagePath;

    public Item () {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getDosageFormId() {
        return dosageFormId;
    }

    public void setDosageFormId(long dosageFormId) {
        this.dosageFormId = dosageFormId;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getVolumeType() {
        return volumeType;
    }

    public void setVolumeType(String volumeType) {
        this.volumeType = volumeType;
    }

    public long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isByPrescription() {
        return byPrescription;
    }

    public void setByPrescription(boolean byPrescription) {
        this.byPrescription = byPrescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", dosageFormId=" + dosageFormId +
                ", dosage='" + dosage + '\'' +
                ", volume=" + volume +
                ", volumeType='" + volumeType + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", price=" + price +
                ", byPrescription=" + byPrescription +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
