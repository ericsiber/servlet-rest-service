package co.simplon.PoleEmploi;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import co.simplon.PoleEmploi.patrimoine.controler.VilleServlet;
import co.simplon.PoleEmploi.patrimoine.controler.VillesServlet;

public class Application {

	public static final EntityManagerFactory EMF = getEntityManagerFactory();

	public static void main(String args[]) throws Exception {

		Server server = new Server(9093);

		final ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.addServlet(DefaultServlet.class, "/*");

		String[] welcomeFiles = { "index.html" };
		context.setWelcomeFiles(welcomeFiles);
		context.setResourceBase("./src/main/webapp/");
		context.addServlet(DemoServlet.class, "/dynamic/*");
		context.addServlet(VillesServlet.class, "/villes");
		context.addServlet(VilleServlet.class, "/ville");
		context.addServlet(BonjourServlet.class, "/bonjour");
		context.addServlet(AurevoirServlet.class, "/aurevoir");

		server.setHandler(context);
		server.setStopAtShutdown(true);

		server.start();
		server.join();
	}

	private static EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("BasePatrimoineLocale");
	}
}
