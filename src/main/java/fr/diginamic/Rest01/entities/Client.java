package fr.diginamic.Rest01.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="client")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String nom;
		
	private String prenom;
	
	@OneToMany(mappedBy="clientE", orphanRemoval = true)
	private Set<Emprunt> emprunts;
	
	
	public void addEmprunt(Emprunt emprunt) {
		this.getEmprunts().add(emprunt);
		emprunt.setClientE(this);
	}
	
	public void removeEmprunt(Emprunt emprunt) {
		this.getEmprunts().remove(emprunt);
		emprunt.setClientE(null);
	}
	
	
	public Client() {
		this.emprunts = new HashSet<Emprunt>();
	}

	public Client(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.emprunts = new HashSet<Emprunt>();
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

	public Set<Emprunt> getEmprunts() {
		return emprunts;
	}
	
	public void setEmprunts(Set<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	
}
