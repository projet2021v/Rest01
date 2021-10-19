package fr.diginamic.Rest01.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.Rest01.entities.Client;
import fr.diginamic.Rest01.entities.Emprunt;
import fr.diginamic.Rest01.entities.Livre;
import fr.diginamic.Rest01.repository.ICrudEmpruntRepo;

@Service
public class EmpruntService {
	
	@Autowired
	ICrudEmpruntRepo er;
	
	@Autowired
	LivreService ls;
	
	public Emprunt addEmprunt(Emprunt emprunt) {
		Emprunt e = er.save(emprunt);
		return e;
	}
	
	public List<Emprunt> findAllEmprunts() {
		List<Emprunt> liste = new ArrayList<Emprunt>();
		for(Emprunt e : er.findAll()) {
			liste.add(e);
		}
		return liste;
	}
	
	public Emprunt findEmpruntById(Integer id) {
		return er.findById(id).get();
	}
	
	public void updateEmprunt(Emprunt emprunt) {
		Emprunt e = er.findById(emprunt.getId()).get();
		e.setDatedebut(emprunt.getDatedebut());
		e.setDatefin(emprunt.getDatefin());
		e.setDelai(emprunt.getDelai());
		e.setClientE(emprunt.getClientE());
		e.setLivresE(emprunt.getLivresE());
		er.save(e);
	}
	
	public void removeEmprunt(Integer id) {
		Emprunt e = er.findById(id).get();
		
		//clean du client emprunteur
		e.getClientE().removeEmprunt(e);
		
		//clean des livres empruntés
//		for(Livre l : e.getLivresE()) {
//			l.removeEmprunt(e);
//		}
		
		//clean des livres empruntés
		Iterator<Livre> it = e.getLivresE().iterator();
		while(it.hasNext()) {
			e.removeLivre(it.next());
		}
		
		//suppression de l'emprunt
		er.delete(e);
	}
	
}
