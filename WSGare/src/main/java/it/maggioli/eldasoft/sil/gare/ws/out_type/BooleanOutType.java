package it.maggioli.eldasoft.sil.gare.ws.out_type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * Oggetto ritornato dal wsdl, può ritornare:
 * - true
 * - false
 * - String di errore.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BooleanOutType implements Serializable {

    protected Boolean result;
    protected String errore;

    public BooleanOutType(Boolean result, String errore) {
        this.result = result;
        this.errore = errore;
    }

    public static BooleanOutType result(boolean result) {
        return new BooleanOutType(result, null);
    }
    public static BooleanOutType error(String errore) {
        return new BooleanOutType(null, errore);
    }

    public boolean isResult() {
        return result;
    }
    public void setResult(Boolean result) {
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
