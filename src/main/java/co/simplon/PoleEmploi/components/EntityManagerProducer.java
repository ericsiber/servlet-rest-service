package co.simplon.PoleEmploi.components;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {

	private static final EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory("BasePatrimoineLocale");

	@Produces
	@RequestScoped
	public EntityManager em() {
		return EMF.createEntityManager();
	}

	public void dispose(@Disposes EntityManager em) {
		em.close();
	}

}