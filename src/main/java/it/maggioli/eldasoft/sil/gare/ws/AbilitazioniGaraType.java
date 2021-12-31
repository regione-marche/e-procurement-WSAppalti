
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per AbilitazioniGaraType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbilitazioniGaraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="richPartecipazione" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="richInvioOfferta" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="richComprovaRequisiti" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbilitazioniGaraType", propOrder = {
    "richPartecipazione",
    "richInvioOfferta",
    "richComprovaRequisiti"
})
public class AbilitazioniGaraType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected boolean richPartecipazione;
    protected boolean richInvioOfferta;
    protected boolean richComprovaRequisiti;

    /**
     * Gets the richPartecipazione value for this AbilitazioniGaraType.
     * 
     * @return richPartecipazione
     */
    public boolean isRichPartecipazione() {
        return richPartecipazione;
    }

    /**
     * Sets the richPartecipazione value for this AbilitazioniGaraType.
     * 
     * @param richPartecipazione
     */
    public void setRichPartecipazione(boolean value) {
        this.richPartecipazione = value;
    }

    /**
     * Gets the richInvioOfferta value for this AbilitazioniGaraType.
     * 
     * @return richInvioOfferta
     */
    public boolean isRichInvioOfferta() {
        return richInvioOfferta;
    }

    /**
     * Sets the richInvioOfferta value for this AbilitazioniGaraType.
     * 
     * @param richInvioOfferta
     */
    public void setRichInvioOfferta(boolean value) {
        this.richInvioOfferta = value;
    }

    /**
     * Gets the richComprovaRequisiti value for this AbilitazioniGaraType.
     * 
     * @return richComprovaRequisiti
     */
    public boolean isRichComprovaRequisiti() {
        return richComprovaRequisiti;
    }

    /**
     * Sets the richComprovaRequisiti value for this AbilitazioniGaraType.
     * 
     */
    public void setRichComprovaRequisiti(boolean value) {
        this.richComprovaRequisiti = value;
    }

}
