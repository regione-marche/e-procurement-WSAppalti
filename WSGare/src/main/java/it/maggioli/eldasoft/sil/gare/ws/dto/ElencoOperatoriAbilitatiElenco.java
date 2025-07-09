package it.maggioli.eldasoft.sil.gare.ws.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ElencoOperatoriAbilitatiElenco")
//@XmlType(name = "ElencoOperatoriAbilitatiElenco", propOrder = {
//        "fiscalCode",
//        "city",
//        "province",
//        "businessName",
//        "rating",
//        "stato",
//        "abilitazione",
//        "categories",
//        "classification",
//        "codElenco"
//})
public class ElencoOperatoriAbilitatiElenco implements Serializable {

    private String fiscalCode;
    private String city;
    private String province;
    private String businessName;
    private String rating;
    private Boolean stato;
    private Integer abilitazione;
    private String codElenco;
    private List<CategoryForOperator> categories;

    public String getFiscalCode() {
        return fiscalCode;
    }
    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }
    public String getCity() {
        return city;
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
        return businessName;
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
    public List<CategoryForOperator> getCategories() {
        return categories;
    }
    public void setCategories(List<CategoryForOperator> categories) {
        this.categories = categories;
    }
    public String getCodElenco() {
        return codElenco;
    }
    public void setCodElenco(String codElenco) {
        this.codElenco = codElenco;
    }
    public Boolean getStato() {
        return stato;
    }
    public void setStato(Integer stato) {
        if (stato != null)
            this.stato = stato == 1;
    }
	public Integer getAbilitazione() {
		return abilitazione;
	}
	public void setAbilitazione(Integer abilitazione) {
		this.abilitazione = abilitazione;
	}

}
