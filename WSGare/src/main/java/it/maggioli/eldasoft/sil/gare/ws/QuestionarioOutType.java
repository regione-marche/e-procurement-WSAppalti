package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuestionarioOutType", propOrder = {
    "questionari",
    "errore"
})
public class QuestionarioOutType implements Serializable {    
	/**
	 * UID 
	 */
	private static final long serialVersionUID = 668618949733854714L;
	
	protected List<QuestionarioType> questionari;
    protected String errore;
		
	public List<QuestionarioType> getQuestionari() {
		return questionari;
	}

	public void setQuestionari(List<QuestionarioType> questionari) {
		this.questionari = questionari;
	}

	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}

}
