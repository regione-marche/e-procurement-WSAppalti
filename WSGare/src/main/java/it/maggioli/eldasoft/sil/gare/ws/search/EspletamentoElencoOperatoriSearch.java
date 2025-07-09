package it.maggioli.eldasoft.sil.gare.ws.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "EspletamentoElencoOperatoriSearch")
@XmlAccessorType(XmlAccessType.FIELD)
public class EspletamentoElencoOperatoriSearch implements Serializable {

    private String codice;
    private String codiceLotto;
    /**
     * Codice dell'operatore
     */
    private String codiceOperatore;
    /**
     * Se impostato a true, il campo "tokenRichiedente" verrà usato anche come filtro.
     * Questo campo è necessario da PORTAPPALT-1078 per i concorsi di progettazione
     * che obbliga a passare il tokenRichiedente.
     */
    private String username;

    public String getCodice() {
        return codice;
    }
    public void setCodice(String codice) {
        this.codice = codice;
    }
    public String getCodiceLotto() {
        return codiceLotto;
    }
    public void setCodiceLotto(String codiceLotto) {
        this.codiceLotto = codiceLotto;
    }
    public String getCodiceOperatore() {
        return codiceOperatore;
    }
    public void setCodiceOperatore(String codiceOperatore) {
        this.codiceOperatore = codiceOperatore;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}
