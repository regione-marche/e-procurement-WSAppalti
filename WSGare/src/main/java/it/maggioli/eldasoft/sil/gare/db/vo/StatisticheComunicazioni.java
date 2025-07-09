package it.maggioli.eldasoft.sil.gare.db.vo;

import java.io.Serializable;

public class StatisticheComunicazioni implements Serializable {

	protected int count;
	protected int dal;
	protected int al;
	protected int daLeggere;
	protected int soccorsoNo;
	protected int soccorsoSi;
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getDal() {
		return dal;
	}
	
	public void setDal(int dal) {
		this.dal = dal;
	}
	
	public int getAl() {
		return al;
	}
	
	public void setAl(int al) {
		this.al = al;
	}
	
	public int getDaLeggere() {
		return daLeggere;
	}
	
	public void setDaLeggere(int daLeggere) {
		this.daLeggere = daLeggere;
	}
	
	public int getSoccorsoNo() {
		return soccorsoNo;
	}
	
	public void setSoccorsoNo(int soccorsoNo) {
		this.soccorsoNo = soccorsoNo;
	}
	
	public int getSoccorsoSi() {
		return soccorsoSi;
	}
	
	public void setSoccorsoSi(int soccorsoSi) {
		this.soccorsoSi = soccorsoSi;
	}
	
}