package dao;

import java.util.List;

import javax.ejb.Remote;

import entities.Utilisateur;

@Remote
public interface UtilisateurRemote {

	Utilisateur addUser(Utilisateur u);
	Utilisateur getUser(int id);
	List<Utilisateur> listUsers();
	boolean updateUser(Utilisateur u);
	boolean deleteUser(Utilisateur u);
}
