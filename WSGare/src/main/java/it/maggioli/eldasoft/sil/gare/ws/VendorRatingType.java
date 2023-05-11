package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;
import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VendorRatingType", propOrder = {
        "dataInizioValidita",
        "dataFineValidita",
        "dataInizioSospensione",
        "dataFineSospensione",
        "indiceVR"
})

public class VendorRatingType implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataInizioValidita;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataFineValidita;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataInizioSospensione;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataFineSospensione;
    protected Double indiceVR;

    public Date getDataInizioValidita() {
        return dataInizioValidita;
    }

    public void setDataInizioValidita(Date dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public Date getDataFineValidita() {
        return dataFineValidita;
    }

    public void setDataFineValidita(Date dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }

    public Date getDataInizioSospensione() {
        return dataInizioSospensione;
    }

    public void setDataInizioSospensione(Date dataInizioSospensione) {
        this.dataInizioSospensione = dataInizioSospensione;
    }

    public Date getDataFineSospensione() {
        return dataFineSospensione;
    }

    public void setDataFineSospensione(Date dataFineSospensione) {
        this.dataFineSospensione = dataFineSospensione;
    }

    public Double getIndiceVR() {
        return indiceVR;
    }

    public void setIndiceVR(Double indiceVR) {
        this.indiceVR = indiceVR;
    }

}

