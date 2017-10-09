package co.simplon.PoleEmploi.patrimoine.endpoint;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.simplon.PoleEmploi.Application;
import co.simplon.PoleEmploi.patrimoine.dao.VilleDao;
import co.simplon.PoleEmploi.patrimoine.dao.VilleJpaDao;
import co.simplon.PoleEmploi.patrimoine.modele.Ville;

@Path("/villes")
public class VilleResource {

	protected EntityManager createEntityManager() {
		return Application.EMF.createEntityManager();
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ville> getVilles() {
		EntityManager em = createEntityManager();
		VilleDao dao = new VilleJpaDao(em);
		List<Ville> villes = dao.findAll(0, 10);
		return villes;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Ville getVilleById(@PathParam("id") Long id) {
		EntityManager em = createEntityManager();
		VilleDao dao = new VilleJpaDao(em);
		return dao.getVilleById(id);	
    }
}
