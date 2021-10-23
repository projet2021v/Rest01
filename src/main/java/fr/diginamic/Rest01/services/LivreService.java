package fr.diginamic.Rest01.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.Rest01.entities.Emprunt;
import fr.diginamic.Rest01.entities.Livre;
import fr.diginamic.Rest01.exceptions.LivreException;
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
	
	public Livre findLivreById(Integer id) throws LivreException {
		Optional<Livre> lOpt = lr.findById(id);
		if(lOpt.isEmpty()) {
			throw new LivreException("Livre inexistant");
		}
		return lOpt.get();
	}
	
	public void updateLivre(Livre livre, Integer id) throws LivreException {
		Optional<Livre> lOpt = lr.findById(id);
		if(lOpt.isEmpty()) {
			throw new LivreException("Livre inexistant");
		}
		Livre l = lOpt.get();
		l.setAuteur(livre.getAuteur());
		l.setTitre(livre.getTitre());
		lr.save(l);
	}
	
	public void removeLivre(Integer id) throws LivreException {
		Optional<Livre> lOpt = lr.findById(id);
		if(lOpt.isEmpty()) {
			throw new LivreException("Livre inexistant");
		}
		Livre l = lOpt.get();
		
		ListIterator<Emprunt> it = es.findAllEmprunts().listIterator();
		while(it.hasNext()) {
			Emprunt e = it.next();
			if(e.getLivresEmpruntes().contains(l)) {
				e.removeLivreEmprunte(l);
			}
		}

		lr.delete(l);
	}

}
