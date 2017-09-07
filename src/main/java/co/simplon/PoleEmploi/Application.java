package co.simplon.PoleEmploi;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Application {

	public static void main(String args[]) throws Exception {

		Server server = new Server(9092);
		
		final ServletContextHandler context = new ServletContextHandler();
		context.addServlet(DefaultServlet.class, "/*");
		
		String[] welcomeFiles = { "index.html" };
		context.setWelcomeFiles(welcomeFiles);
		context.setResourceBase("./src/main/resources/");
		context.addServlet(DemoServlet.class, "/dynamic/*");
		
		server.setHandler(context);
		server.setStopAtShutdown(true);
		server.start();
		server.join();
	}

}
