package co.simplon.PoleEmploi.patrimoine.dao;

import java.util.List;

import co.simplon.PoleEmploi.patrimoine.modele.Ville;

public interface VilleDao {
	List<Ville> findAll(int first, int size);
	
	Ville getVilleById(Long id);

	void deleteVilleById(Long id);

	Ville createVille(Ville ville);
	
	Ville updateVille(Ville ville);
}
