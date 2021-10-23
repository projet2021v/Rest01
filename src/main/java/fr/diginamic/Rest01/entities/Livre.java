package fr.diginamic.Rest01.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="livre")
public class Livre {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "auteur", length = 50, nullable = false)
	@NotBlank(message = "L''auteur ne doit être ni vide ni uniquement composé d''espaces")
	private String auteur;
	
	@Column(name = "titre", length = 255, nullable = false)
	@NotBlank(message = "Le titre ne doit être ni vide ni uniquement composé d''espaces")
	private String titre;
	
	public Livre() {}
		
	public Livre(String auteur, String titre) {
		this.auteur = auteur;
		this.titre = titre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	@Override
	public String toString() {
		return "Livre [id=" + id + ", auteur=" + auteur + ", titre=" + titre + "]";
	}
	
}
