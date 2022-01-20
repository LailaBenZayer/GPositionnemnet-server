package entities;

import java.io.Serializable;
import java.lang.String;

/**
 * Entity implementation class for Entity: SmartPhone
 *
 */
public class SmartPhone implements Serializable {

	private int id;
	private String imei;
	private Utilisateur utilisateur;
	private static final long serialVersionUID = -558553967080513790L;

	public SmartPhone() {
		super();
	}   
	
	
	public SmartPhone(String imei, Utilisateur utilisateur) {
		super();
		this.imei = imei;
		this.utilisateur = utilisateur;
	}


	public SmartPhone(int id, String imei, Utilisateur utilisateur) {
		super();
		this.id = id;
		this.imei = imei;
		this.utilisateur = utilisateur;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
   
}
