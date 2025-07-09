package it.maggioli.eldasoft.sil.gare.ws.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CategoryForOperator")
//@XmlType(name = "CategoryForOperator", propOrder = {
//        "classifica",
//        "category"
//})
public class CategoryForOperator implements Serializable {

    private String classifica;
    private String category;

    public void setCaisim(String caisim) {
        this.category = caisim;
    }
    public void setDescat(String descat) {
        this.category += " - " + descat;
    }
    public String getClassifica() {
        return classifica;
    }
    public void setClassifica(String classifica) {
        if (classifica != null)
            this.classifica = classifica.trim();
        else
            this.classifica = null;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

}
