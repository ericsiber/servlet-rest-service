package co.simplon.PoleEmploi.patrimoine.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "CITIES")
@NamedQueries({
		@NamedQuery(name = "Ville.findAll", query = "SELECT v FROM Ville v"),
		@NamedQuery(name = "Ville.deleteById", query = "DELETE FROM Ville v WHERE v.id = :id") })
public class Ville {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String nom;

	@Column(name = "LATITUDE")
	private double latitude;

	@Column(name = "LONGITUDE")
	private double longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
