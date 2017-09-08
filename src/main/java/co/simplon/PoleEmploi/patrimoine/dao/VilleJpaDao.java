package co.simplon.PoleEmploi.patrimoine.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.simplon.PoleEmploi.patrimoine.modele.Ville;

public class VilleJpaDao implements VilleDao {

	private EntityManager entityManager;

	public VilleJpaDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Ville getVilleById(Long id) {
		return entityManager.find(Ville.class, id);
	}

	@Override
	public void deleteVilleById(Long id) {
		entityManager.getTransaction().begin();
		entityManager.createNamedQuery("Ville.deleteById")
				.setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
	}

	@Override
	public Ville createVille(Ville ville) {
		entityManager.getTransaction().begin();
		entityManager.persist(ville);
		entityManager.getTransaction().commit();
		return ville;
	}

	@Override
	public Ville updateVille(Ville ville) {
		entityManager.getTransaction().begin();
		ville = entityManager.merge(ville);
		entityManager.getTransaction().commit();
		return ville;
	}

	@Override
	public List<Ville> findAll(int first, int size) {
		return entityManager.createNamedQuery("Ville.findAll", Ville.class)
				.setFirstResult(first).setMaxResults(size).getResultList();
	}

}
