package fr.diginamic.Rest01.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.Rest01.entities.Emprunt;
import fr.diginamic.Rest01.entities.Livre;
import fr.diginamic.Rest01.repository.ICrudLivreRepo;

@Service
public class LivreService {
	
	@Autowired
	ICrudLivreRepo lr;
	
	@Autowired
	EmpruntService es;
	
	public Livre addLivre(Livre livre) {
		Livre l = lr.save(livre);
		return l;
	}
	
	public List<Livre> findAllLivres() {
		List<Livre> liste = new ArrayList<Livre>();
		for(Livre l : lr.findAll()) {
			liste.add(l);
		}
		return liste;
	}
	
	public Livre findLivreById(Integer id) {
		return lr.findById(id).get();
	}
	
	public void updateLivre(Livre livre, Integer id) {
		Livre l = lr.findById(id).get();
		l.setAuteur(livre.getAuteur());
		l.setTitre(livre.getTitre());
		lr.save(l);
	}
	
	public void removeLivre(Integer id) {
		Livre l = lr.findById(id).get();
		
		ListIterator<Emprunt> it = es.findAllEmprunts().listIterator();
		while(it.hasNext()) {
			Emprunt e = it.next();
			if(e.getLivresEmpruntes().contains(l)) {
				e.removeLivreEmprunte(l);
			}
		}
		
//		for(Emprunt e : l.getEmpruntLivres()) {
//			e.removeLivre(l);
//		}
		
		lr.delete(l);
	}

}
