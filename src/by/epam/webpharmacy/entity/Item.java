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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (dosageFormId != item.dosageFormId) return false;
        if (Double.compare(item.volume, volume) != 0) return false;
        if (manufacturerId != item.manufacturerId) return false;
        if (byPrescription != item.byPrescription) return false;
        if (label != null ? !label.equals(item.label) : item.label != null) return false;
        if (dosage != null ? !dosage.equals(item.dosage) : item.dosage != null) return false;
        if (volumeType != null ? !volumeType.equals(item.volumeType) : item.volumeType != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        return imagePath != null ? imagePath.equals(item.imagePath) : item.imagePath == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (int) (dosageFormId ^ (dosageFormId >>> 32));
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (volumeType != null ? volumeType.hashCode() : 0);
        result = 31 * result + (int) (manufacturerId ^ (manufacturerId >>> 32));
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (byPrescription ? 1 : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
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
