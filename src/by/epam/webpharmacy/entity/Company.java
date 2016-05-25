package by.epam.webpharmacy.entity;

import java.io.Serializable;

/**
 * Class {@code Company} represents any company that can be used in the pharmacy-app
 * such as manufacturer or vendor and etc
 */
public class Company implements Serializable {

    private long id;
    private String type;
    private String fullName;
    private String shortName;
    private String country;
    private String website;

    public Company() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (id != company.id) return false;
        if (type != null ? !type.equals(company.type) : company.type != null) return false;
        if (fullName != null ? !fullName.equals(company.fullName) : company.fullName != null) return false;
        if (shortName != null ? !shortName.equals(company.shortName) : company.shortName != null) return false;
        if (country != null ? !country.equals(company.country) : company.country != null) return false;
        return website != null ? website.equals(company.website) : company.website == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        return result;
    }
}
