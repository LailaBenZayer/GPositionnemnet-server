package test;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.IDaoRemote;
import dao.UtilisateurRemote;
import entities.Utilisateur;

public class testUserClient {

	public static dao.IDaoRemote<Utilisateur> lookUpUtilisateurRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();

		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);
		return (IDaoRemote<Utilisateur>) context.lookup("ejb:/GPosionnementServer/UtilisateurService!dao.IDaoRemote");

	}

	
	public static void main(String[] args) {
		try {
			IDaoRemote<Utilisateur> u = lookUpUtilisateurRemote();
			u.Create(new Utilisateur("nom","prenom","email","tele", new Date()));

			for(Utilisateur us :u.findAll()) {
				
				System.out.println("Shoooooooooooooow"+us.getNom());
			}
			
			/*
			 * for(Compte c : o.getAll()) System.out.println(c);
			 * 
			 * //o.delete(o.getByCode(1)); o.retirer(o.getByCode(1), 4000);
			 * 
			 * System.out.println("apres : "); for(Compte c : o.getAll())
			 * System.out.println(c);
			 */
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
