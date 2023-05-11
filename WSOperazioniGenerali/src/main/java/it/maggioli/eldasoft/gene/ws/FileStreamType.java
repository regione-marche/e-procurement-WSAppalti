/**
 * Classe per la gestione dei file (AllegatoComunicazioneType.file, FileType.file)
 * esternamente alle buste xml o agli allegati delle comunicazioni   
 * 
 */
package it.maggioli.eldasoft.gene.ws;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "FileStreamType")
public class FileStreamType {
	
	protected byte[] file;

	/**
	 * @return the file
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}
		
}
