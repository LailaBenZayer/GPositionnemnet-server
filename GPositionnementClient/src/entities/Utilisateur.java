package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

/**
 * Entity implementation class for Entity: Utilisateur
 *
 */
public class Utilisateur implements Serializable {

	   
	private int id;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private Date dateNaissance;
	private static final long serialVersionUID = -558553967080513790L;

	public Utilisateur() {
		super();
	}   
	
	
	public Utilisateur(String nom, String prenom, String telephone, String email, Date dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		this.dateNaissance = dateNaissance;
	}
	
	public Utilisateur(int id, String nom, String prenom, String telephone, String email, Date dateNaissance) {
		super();
		this.id=id;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		this.dateNaissance = dateNaissance;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}   
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}   
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	@Override
	public String toString() {
		return nom + " " + prenom;
	}
   
	
}
