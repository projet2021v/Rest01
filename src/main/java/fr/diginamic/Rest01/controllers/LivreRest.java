package fr.diginamic.Rest01.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fr.diginamic.Rest01.entities.Emprunt;
import fr.diginamic.Rest01.entities.Livre;
import fr.diginamic.Rest01.repository.ICrudEmpruntRepo;
import fr.diginamic.Rest01.repository.ICrudLivreRepo;
import fr.diginamic.Rest01.services.LivreService;

@RestController
@RequestMapping("/livres")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
public class LivreRest {
	
	@Autowired
	LivreService ls; 
	
	@GetMapping("/all")
	public List<Livre> findAll() {
		return ls.findAllLivres();
	}
	
	@GetMapping("/{id}")
	public Livre findOneById(@PathVariable Integer id) {
		return ls.findLivreById(id);
	}
	
	@DeleteMapping("/{id}/delete")
	public void delete(@PathVariable Integer id) {
		ls.removeLivre(id);
	}
}
