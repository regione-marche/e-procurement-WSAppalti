
package it.maggioli.eldasoft.gene.ws;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>Java class for AllegatoComunicazioneType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AllegatoComunicazioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeFile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="file" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllegatoComunicazioneType", propOrder = {
    "id",
    "tipo",
    "nomeFile",
    "descrizione",
    "file",
    "uuid",
    "modificato",
    "dimensione",
    "firmacheck",
    "firmacheckts",
    "cifrato",			// digkey4 per le rettifiche
    "bucket_uuid",
    "bucket_ref"
})
public class AllegatoComunicazioneType
    implements Serializable
{
	private final static long serialVersionUID = 2L;
    protected Long id;
    protected String tipo;
    @XmlElement(required = true)
    protected String nomeFile;
    protected String descrizione;
    @XmlElement(required = false)
    protected byte[] file;
//    @XmlJavaTypeAdapter(DataHandlerAdapter.class)
//    @XmlMimeType("application/octet-stream")
//    protected DataHandler file;
    protected String uuid;
    protected int modificato;
    protected long dimensione;
    protected String firmacheck;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date firmacheckts;
    protected String cifrato;
    protected String bucket_ref;
    protected String bucket_uuid;

}
