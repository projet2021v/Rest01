package fr.diginamic.Rest01.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="emprunt")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Emprunt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut")
	private Date datedebut;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin")
	private Date datefin;
	
	private int delai;
	
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client clientEmprunteur;
	
	@ManyToMany
	@JoinTable(name="livre_emprunt",
	joinColumns= @JoinColumn(name="id_emp", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="id_liv", referencedColumnName="id"))
	private Set<Livre> livresEmpruntes;
	
	public void addLivreEmprunte(Livre livre) {
		this.getLivresEmpruntes().add(livre);
//		livre.getEmpruntLivres().add(this);
	}
	
	public void removeLivreEmprunte(Livre livre) {
		this.getLivresEmpruntes().remove(livre);
//		livre.getEmpruntLivres().remove(this);
	}

	public Emprunt() {
		this.livresEmpruntes = new HashSet<Livre>();
	}

	public Emprunt(Date datedebut, Date datefin, int delai) {
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.delai = delai;
		this.livresEmpruntes = new HashSet<Livre>();
	}

	public int getId() {
		return id;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public int getDelai() {
		return delai;
	}

	public void setDelai(int delai) {
		this.delai = delai;
	}

	public Client getClientEmprunteur() {
		return clientEmprunteur;
	}

	public void setClientEmprunteur(Client clientEmprunteur) {
		this.clientEmprunteur = clientEmprunteur;
	}

	public Set<Livre> getLivresEmpruntes() {
		return livresEmpruntes;
	}
	
	public void setLivresEmpruntes(Set<Livre> livresEmpruntes) {
		this.livresEmpruntes = livresEmpruntes;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Emprunt [id=");
		builder.append(id);
		builder.append(", datedebut=");
		builder.append(datedebut.getDay() + " " + datedebut.getMonth() + " " + datedebut.getYear());
		builder.append(", datefin=");
		builder.append(datefin.getDay() + " " + datefin.getMonth() + " " + datefin.getYear());
		builder.append(", delai=");
		builder.append(delai);
		builder.append("]");
		return builder.toString();
	}
}
