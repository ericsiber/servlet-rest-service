package co.simplon.PoleEmploi.patrimoine.endpoint;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import co.simplon.PoleEmploi.patrimoine.dao.VilleDao;
import co.simplon.PoleEmploi.patrimoine.modele.Ville;

@Path("/villes")
@RequestScoped
public class VilleResource {

	@Inject
	private VilleDao villeDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ville> getVilles() {
		List<Ville> villes = villeDao.findAll(0, 10);
		return villes;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getVilleById(@PathParam("id") Long id) {
		Ville ville = villeDao.getVilleById(id);
		if (ville != null)
			return Response.ok(ville).build();
		return Response.status(Status.NOT_FOUND).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVille(Ville villeACreer, @Context UriInfo uriInfo) {
    	Ville ville = villeDao.createVille(villeACreer);
    	if (ville != null) {
			URL url;
			URI uri;
			try {
				url = new URL(uriInfo.getAbsolutePath().toURL().toExternalForm() + "/" + ville.getId());
				uri = url.toURI();
			} catch (MalformedURLException e) {
				return Response.status(Status.BAD_REQUEST).build();
			} catch (URISyntaxException e) {
				return Response.status(Status.BAD_REQUEST).build();
			}
    		return Response.created(uri).build();
    	}
    	return Response.status(Status.BAD_REQUEST).build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateVille(@PathParam("id") Long id, Ville villeAModifier) {
    	villeAModifier.setId(id);
    	villeDao.updateVille(villeAModifier);
    	return Response.ok().build();
    }
    
    @DELETE
    @Path("{id}")
    public void deleteVilleById(@PathParam("id") Long id) {
		villeDao.deleteVilleById(id);
    }
}
