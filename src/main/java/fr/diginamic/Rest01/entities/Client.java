package fr.diginamic.Rest01.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String nom;
		
	private String prenom;
	
	@OneToMany(mappedBy="clientEmprunteur")
	private Set<Emprunt> empruntsDuClient;
	
	
	public void addEmprunt(Emprunt emprunt) {
		this.getEmpruntsDuClient().add(emprunt);
		emprunt.setClientEmprunteur(this);
	}
	
	public void removeEmprunt(Emprunt emprunt) {
		this.getEmpruntsDuClient().remove(emprunt);
		emprunt.setClientEmprunteur(null);
	}
	
	
	public Client() {
		this.empruntsDuClient = new HashSet<Emprunt>();
	}

	public Client(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.empruntsDuClient = new HashSet<Emprunt>();
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Set<Emprunt> getEmpruntsDuClient() {
		return empruntsDuClient;
	}
	
	public void setEmpruntsDuClient(Set<Emprunt> empruntsDuClient) {
		this.empruntsDuClient = empruntsDuClient;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	
}
