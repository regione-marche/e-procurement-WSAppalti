package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;

public class DigoggType implements Serializable {

    private String digogg;

    public String getDigogg() {
        return digogg;
    }
    public void setDigogg(byte[] digogg) {
        this.digogg = new String(digogg);
    }
}
