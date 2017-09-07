package co.simplon.PoleEmploi.patrimoine.controler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.simplon.PoleEmploi.Application;
import co.simplon.PoleEmploi.patrimoine.dao.VilleDao;
import co.simplon.PoleEmploi.patrimoine.dao.VilleJpaDao;
import co.simplon.PoleEmploi.patrimoine.modele.Ville;

public class VilleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected EntityManager createEntityManager() {
		return Application.EMF.createEntityManager();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idParam = req.getParameter("id");

		if (idParam != null) {
			EntityManager em = null;

			try {
				Long id = Long.parseLong(idParam);

				em = createEntityManager();
				VilleDao dao = new VilleJpaDao(em);
				Ville ville = dao.getVilleById(id);

				PrintWriter out = resp.getWriter();
				out.println("Détail d'une ville :");
				out.println();
				out.println("Id = " + ville.getId());
				out.println("Nom = " + ville.getNom());
				out.println("Latitude = " + ville.getLatitude());
				out.println("Longitude = " + ville.getLongitude());
				resp.setStatus(HttpServletResponse.SC_OK);

			} catch (NumberFormatException e) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			} finally {
				if (em != null) {
					em.close();
				}
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

}
