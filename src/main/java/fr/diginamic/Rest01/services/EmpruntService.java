package fr.diginamic.Rest01.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.Rest01.entities.Emprunt;
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
	
	public void removeEmprunt(Integer id) {
		Emprunt e = er.findById(id).get();
		
		//clean des livres empruntés
		e.getLivresEmpruntes().clear();
		
		//clean du client emprunteur
		e.getClientEmprunteur().removeEmprunt(e);
		
		//suppression de l'emprunt
		er.delete(e);
	}
	
}
