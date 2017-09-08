package co.simplon.PoleEmploi.patrimoine.controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.simplon.PoleEmploi.Application;
import co.simplon.PoleEmploi.patrimoine.dao.VilleDao;
import co.simplon.PoleEmploi.patrimoine.dao.VilleJpaDao;
import co.simplon.PoleEmploi.patrimoine.modele.Ville;

public class VillesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected EntityManager createEntityManager() {
		return Application.EMF.createEntityManager();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EntityManager em = createEntityManager();
		VilleDao dao = new VilleJpaDao(em);
		List<Ville> villes = dao.findAll(0, 10);
		PrintWriter out = resp.getWriter();
		out.println("Liste de villes");
		out.println("<ul>");
		for (Ville ville : villes) {
			out.println("<li>");
			out.println("<a href=\"/ville?id=" + ville.getId() + "\">"
					+ ville.getNom() + "</a>");
			out.println("</li>");
		}
		out.println("</ul>");
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html");
	}

}
