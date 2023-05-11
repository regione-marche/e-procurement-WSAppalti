package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

public class GetVendorRatingOutType implements Serializable {

    private final static long serialVersionUID = 2L;


    protected VendorRatingType vendorRatingType;
        protected String errore;

        public VendorRatingType getVendorRatingType() {
            return vendorRatingType;
        }

        public void setVendorRatingType(VendorRatingType vendorRatingType) {
            this.vendorRatingType = vendorRatingType;
        }

        public String getErrore() {
            return errore;
        }

        public void setErrore(String errore) {
            this.errore = errore;
        }

    }
