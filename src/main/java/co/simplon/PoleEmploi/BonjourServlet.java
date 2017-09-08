package co.simplon.PoleEmploi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BonjourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String prenom = request.getParameter("prenom");
		request.getSession(true).setAttribute("prenom", prenom);
		
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = response.getWriter();
		out.println("Bonjour " + prenom);
		out.println("<br>");
		out.println("<a href=\"/villes\">Visualiser la liste des villes</a>");
		response.setContentType("text/html");
	}
}