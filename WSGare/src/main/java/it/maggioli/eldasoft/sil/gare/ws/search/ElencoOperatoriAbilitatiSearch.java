package it.maggioli.eldasoft.sil.gare.ws.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElencoOperatoriAbilitatiSearch", propOrder = {
        "fiscalCode",
        "city",
        "province",
        "businessName",
        "rating",
        "category",
        "classification",
        "codElenco",
        "stato",
        "indicePrimoRecord",
        "maxNumRecord"
})
public class ElencoOperatoriAbilitatiSearch implements Serializable {

    private String fiscalCode;
    private String city;
    private String province;
    private String businessName;
    private String rating;
    private String codElenco;
    private String category;
    private String classification;
    private Boolean stato;
    private int indicePrimoRecord;
    private int maxNumRecord;

    public String getFiscalCode() {
        return fiscalCode == null || fiscalCode.isEmpty() ? "" : "%" + fiscalCode.toUpperCase() +  "%";
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getCity() {
        return city == null || city.isEmpty() ? "" : "%" + city.toUpperCase() +  "%";
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getBusinessName() {
        return businessName == null || businessName.isEmpty() ? "" : "%" + businessName.toUpperCase() +  "%";
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category != null && !category.isEmpty() ? "%" + category.toUpperCase() + "%" : category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
    public String getCodElenco() {
        return codElenco != null && !codElenco.isEmpty() ? "%" + codElenco.toUpperCase() + "%" : codElenco;
    }
    public void setCodElenco(String codElenco) {
        this.codElenco = codElenco;
    }
    public Boolean getStato() {
        return stato;
    }

    public void setStato(Boolean stato) {
        this.stato = stato;
    }

    public int getIndicePrimoRecord() {
        return indicePrimoRecord;
    }

    public void setIndicePrimoRecord(int indicePrimoRecord) {
        this.indicePrimoRecord = indicePrimoRecord;
    }

    public int getMaxNumRecord() {
        return maxNumRecord;
    }

    public void setMaxNumRecord(int maxNumRecord) {
        this.maxNumRecord = maxNumRecord;
    }

    @Override
    public String toString() {
        return "ElencoOperatoriAbilitatiSearch{" +
                "fiscalCode='" + fiscalCode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", businessName='" + businessName + '\'' +
                ", rating='" + rating + '\'' +
                ", category='" + category + '\'' +
                ", classification='" + classification + '\'' +
                ", stato=" + stato +
                ", indicePrimoRecord=" + indicePrimoRecord +
                ", maxNumRecord=" + maxNumRecord +
                '}';
    }
}
