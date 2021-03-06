package fr.diginamic.Rest01.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="client")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Le nom ne doit être ni vide ni uniquement composé d''espaces")
	private String nom;
	
	@NotBlank(message = "Le prénom ne doit être ni vide ni uniquement composé d''espaces")
	private String prenom;
	
	@OneToMany(mappedBy="clientEmprunteur", fetch = FetchType.EAGER)
	private List<Emprunt> empruntsDuClient;
	
	
	public void addEmprunt(Emprunt emprunt) {
		emprunt.setClientEmprunteur(this);
		this.getEmpruntsDuClient().add(emprunt);
	}
	
	public void removeEmprunt(Emprunt emprunt) {
		emprunt.setClientEmprunteur(null);
		this.getEmpruntsDuClient().remove(emprunt);
	}
	
	
	public Client() {
//		this.empruntsDuClient = new HashSet<Emprunt>();
		this.empruntsDuClient = new ArrayList<Emprunt>();
	}

	public Client(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
//		this.empruntsDuClient = new HashSet<Emprunt>();
		this.empruntsDuClient = new ArrayList<Emprunt>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Emprunt> getEmpruntsDuClient() {
		return empruntsDuClient;
	}
	
	public void setEmpruntsDuClient(List<Emprunt> empruntsDuClient) {
		this.empruntsDuClient = empruntsDuClient;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	
}
