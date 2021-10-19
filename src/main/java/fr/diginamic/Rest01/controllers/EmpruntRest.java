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
import fr.diginamic.Rest01.services.EmpruntService;

@RestController
@RequestMapping("/emprunts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
public class EmpruntRest {
	
	@Autowired
	EmpruntService es; 
	
	@GetMapping("/all")
	public List<Emprunt> findAll() {
		return es.findAllEmprunts();
	}
	
	@GetMapping("/{id}")
	public Emprunt findOneById(@PathVariable Integer id) {
		return es.findEmpruntById(id);
	}
	
	@DeleteMapping("/{id}/delete")
	public void delete(@PathVariable Integer id) {
		es.removeEmprunt(id);
	}
}
