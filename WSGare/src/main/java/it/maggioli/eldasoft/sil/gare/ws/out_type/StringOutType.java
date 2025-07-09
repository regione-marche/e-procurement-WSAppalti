package it.maggioli.eldasoft.sil.gare.ws.out_type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * Oggetto ritornato dal wsdl, può ritornare:
 * - null
 * - Una qualsiasi stringa
 * - String di errore.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class StringOutType implements Serializable {

    protected String result;
    protected String errore;

    public StringOutType(String result, String errore) {
        this.result = result;
        this.errore = errore;
    }

    public static StringOutType result(String result) {
        return new StringOutType(result, null);
    }
    public static StringOutType error(String errore) {
        return new StringOutType(null, errore);
    }

    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    @Override
    public String toString() {
        return "BooleanOutType{" +
                "result=" + result +
                ", errore='" + errore + '\'' +
                '}';
    }

}
