package fr.diginamic.Rest01.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.Rest01.entities.Livre;
import fr.diginamic.Rest01.services.LivreService;

@RestController
@RequestMapping("/livres")
public class LivreRest {
	
	@Autowired
	LivreService ls;
	
	@PostMapping("/create")
	public Livre add(@RequestBody Livre l) {
		return ls.addLivre(l);
	}
	
	@GetMapping("/all")
	public List<Livre> findAll() {
		return ls.findAllLivres();
	}
	
	@GetMapping("/{id}")
	public Livre findOneById(@PathVariable Integer id) {
		return ls.findLivreById(id);
	}
	
	@PutMapping("/{id}/update")
	public void update(@RequestBody Livre l, @PathVariable Integer id) {
		ls.updateLivre(l, id);
	}
	
	@DeleteMapping("/{id}/delete")
	public void delete(@PathVariable Integer id) {
		ls.removeLivre(id);
	}
}
