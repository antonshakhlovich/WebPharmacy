package by.epam.webpharmacy.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Class {@code Prescription} represents an prescription that some drugs are required in
 */
public class Prescription implements Serializable {
    private long id;
    private Date timestamp;
    private Date validFrom;
    private Date validUntil;
    private long drugId;
    private long userId;
    private long doctorId;
    private boolean isRequested;
    private boolean isCanceled;

    public Prescription() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public boolean isRequested() {
        return isRequested;
    }

    public void setRequested(boolean requested) {
        isRequested = requested;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prescription that = (Prescription) o;

        if (id != that.id) return false;
        if (drugId != that.drugId) return false;
        if (userId != that.userId) return false;
        if (doctorId != that.doctorId) return false;
        if (isRequested != that.isRequested) return false;
        if (isCanceled != that.isCanceled) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (validFrom != null ? !validFrom.equals(that.validFrom) : that.validFrom != null) return false;
        return validUntil != null ? validUntil.equals(that.validUntil) : that.validUntil == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (validFrom != null ? validFrom.hashCode() : 0);
        result = 31 * result + (validUntil != null ? validUntil.hashCode() : 0);
        result = 31 * result + (int) (drugId ^ (drugId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (doctorId ^ (doctorId >>> 32));
        result = 31 * result + (isRequested ? 1 : 0);
        result = 31 * result + (isCanceled ? 1 : 0);
        return result;
    }
}
