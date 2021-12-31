
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per StatisticheComunicazioniPersonaliType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="StatisticheComunicazioniPersonaliType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numComunicazioniRicevute" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numComunicazioniRicevuteDaLeggere" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numComunicazioniArchiviate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numComunicazioniArchiviateDaLeggere" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numComunicazioniInviate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatisticheComunicazioniPersonaliType", propOrder = {
    "numComunicazioniRicevute",
    "numComunicazioniRicevuteDaLeggere",
    "numComunicazioniArchiviate",
    "numComunicazioniArchiviateDaLeggere",
    "numSoccorsiIstruttori",
    "numSoccorsiIstruttoriDaLeggere",
    "numComunicazioniInviate"
})
public class StatisticheComunicazioniPersonaliType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected int numComunicazioniRicevute;
    protected int numComunicazioniRicevuteDaLeggere;
    protected int numComunicazioniArchiviate;
    protected int numComunicazioniArchiviateDaLeggere;
    protected int numSoccorsiIstruttori;
    protected int numSoccorsiIstruttoriDaLeggere;
    protected int numComunicazioniInviate;

    public int getNumComunicazioniRicevute() {
        return numComunicazioniRicevute;
    }

    public void setNumComunicazioniRicevute(int value) {
        this.numComunicazioniRicevute = value;
    }

    public int getNumComunicazioniRicevuteDaLeggere() {
        return numComunicazioniRicevuteDaLeggere;
    }

    public void setNumComunicazioniRicevuteDaLeggere(int value) {
        this.numComunicazioniRicevuteDaLeggere = value;
    }

    public int getNumComunicazioniArchiviate() {
        return numComunicazioniArchiviate;
    }

    public void setNumComunicazioniArchiviate(int value) {
        this.numComunicazioniArchiviate = value;
    }

    public int getNumComunicazioniArchiviateDaLeggere() {
        return numComunicazioniArchiviateDaLeggere;
    }

    public void setNumComunicazioniArchiviateDaLeggere(int value) {
        this.numComunicazioniArchiviateDaLeggere = value;
    }

    public int getNumSoccorsiIstruttori() {
		return numSoccorsiIstruttori;
	}

	public void setNumSoccorsiIstruttori(int numSoccorsiIstruttori) {
		this.numSoccorsiIstruttori = numSoccorsiIstruttori;
	}
	
	public int getNumSoccorsiIstruttoriDaLeggere() {
		return numSoccorsiIstruttoriDaLeggere;
	}

	public void setNumSoccorsiIstruttoriDaLeggere(int numSoccorsiIstruttoriDaLeggere) {
		this.numSoccorsiIstruttoriDaLeggere = numSoccorsiIstruttoriDaLeggere;
	}

	public int getNumComunicazioniInviate() {
        return numComunicazioniInviate;
    }

    public void setNumComunicazioniInviate(int value) {
        this.numComunicazioniInviate = value;
    }

}
