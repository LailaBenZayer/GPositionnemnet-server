package dao;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LookUpUtilisateur {

	public static dao.UtilisateurRemote lookUpUtilisateurRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();

		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (UtilisateurRemote) context.lookup("ejb:/GPosionnementServer/UtilisateurService!dao.UtilisateurRemote");

	}
}
