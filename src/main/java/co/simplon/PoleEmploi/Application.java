package co.simplon.PoleEmploi;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Application {

	public static final EntityManagerFactory EMF = getEntityManagerFactory();

	public static void main(String args[]) throws Exception {

		Server server = new Server(9093);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		webAppContext.setResourceBase("src/main/webapp/");
		webAppContext.setContextPath("/servlet-rest-service");
		server.setHandler(webAppContext);
			
		server.start();
		server.join();
	}

	private static EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("BasePatrimoineLocale");
	}
}
