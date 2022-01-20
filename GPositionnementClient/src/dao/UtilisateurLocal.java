package dao;

import java.util.List;

import javax.ejb.Local;

import entities.Utilisateur;

@Local
public interface UtilisateurLocal {

	Utilisateur addUser(Utilisateur u);
	Utilisateur getUser(int id);
	List<Utilisateur> listUsers();
	boolean updateUser(Utilisateur u);
	boolean deleteUser(Utilisateur u);
}
