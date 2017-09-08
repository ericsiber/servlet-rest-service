package co.simplon.PoleEmploi.patrimoine.controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.simplon.PoleEmploi.Application;
import co.simplon.PoleEmploi.patrimoine.dao.VilleDao;
import co.simplon.PoleEmploi.patrimoine.dao.VilleJpaDao;
import co.simplon.PoleEmploi.patrimoine.modele.Ville;

import com.fasterxml.jackson.databind.ObjectMapper;

public class VillesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected EntityManager createEntityManager() {
		return Application.EMF.createEntityManager();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String acceptHeader = req.getHeader("accept");
		
		EntityManager em = createEntityManager();
		VilleDao dao = new VilleJpaDao(em);
		List<Ville> villes = dao.findAll(0, 10);

		if ("application/json".equals(acceptHeader)) {
			ObjectMapper objectMapper = new ObjectMapper();
			resp.setContentType("application/json");
			objectMapper.writeValue(resp.getWriter(), villes);
		} else {
			PrintWriter out = resp.getWriter();
			HttpSession session = req.getSession(false);
			String prenom = null;
			if (session != null) {
				prenom = (String) session.getAttribute("prenom");
			}
			out.println("Bienvenue " + (prenom != null ? prenom : "inconnu"));
			out.println("<br><br>");
			out.println("Liste de villes");
			out.println("<ul>");
			for (Ville ville : villes) {
				out.println("<li>");
				out.println("<a href=\"/ville?id=" + ville.getId() + "\">"
						+ ville.getNom() + "</a>");
				out.println("</li>");
			}
			out.println("</ul>");
			out.println("<br>");
			if (session != null) {
				out.println("<a href=\"/aurevoir\">Se déconnecter</a>");
			}
			resp.setContentType("text/html");
		}
		resp.setStatus(HttpServletResponse.SC_OK);

		
	}

}
