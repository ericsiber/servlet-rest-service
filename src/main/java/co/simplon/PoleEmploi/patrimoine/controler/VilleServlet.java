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
				out.println("<head><meta charset=\"UTF-8\"><script src=\"https://code.jquery.com/jquery-3.2.1.min.js\" integrity=\"sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=\" crossorigin=\"anonymous\"></script></head>");
				out.println("Détail d'une ville :");
				out.println("<br>");
				out.println("<form action=\"./ville?id=" + ville.getId()
						+ "\" method=\"post\">");

				out.println("Id : " + ville.getId());
				out.println("<br>");
				out.println("Nom : <input type=\"text\" name=\"nom\" value=\""
						+ ville.getNom() + "\" />");
				out.println("<br>");
				out.println("Latitude : <input type=\"text\" name=\"latitude\" value=\""
						+ ville.getLatitude() + "\" />");
				out.println("<br>");
				out.println("Longitude : <input type=\"text\" name=\"longitude\" value=\""
						+ ville.getLongitude() + "\" />");
				out.println("<br>");
				out.println("<input type=\"submit\" value=\"Modifier\"/>");
				out.println("</form>");
				out.println("<script>$(document).ready(function(){$(\'#DeleteButton\').click(function(){");
				out.println("$.ajax({ url: \'./ville?id=" + id
						+ "\', method: \'DELETE\' }).done(function( data ) { window.location.href = \"./villes\"; });");
				out.println("}); });");
				out.println("</script>");
				out.println("<input type=\"button\" value=\"Supprimer\" id=\"DeleteButton\" >");
				out.println("<br>");
				out.println("<a href=\"./villes\">Retour à la liste</a>");
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/html");

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
		String idParam = req.getParameter("id");

		if (idParam != null) {
			EntityManager em = null;

			try {
				Long id = Long.parseLong(idParam);
				String nomParam = req.getParameter("nom");
				String latitudeParam = req.getParameter("latitude");
				String longitudeParam = req.getParameter("longitude");

				em = createEntityManager();
				VilleDao dao = new VilleJpaDao(em);
				Ville ville = dao.getVilleById(id);
				ville.setNom(nomParam);
				ville.setLatitude(Double.parseDouble(latitudeParam));
				ville.setLongitude(Double.parseDouble(longitudeParam));
				dao.updateVille(ville);

				resp.sendRedirect("./ville?id=" + idParam);
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
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idParam = req.getParameter("id");

		if (idParam != null) {
			EntityManager em = null;
			try {
				Long id = Long.parseLong(idParam);
				em = createEntityManager();
				VilleDao dao = new VilleJpaDao(em);
				dao.deleteVilleById(id);
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

}
