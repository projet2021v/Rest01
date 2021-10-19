package fr.diginamic.Rest01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fr.diginamic.Rest01.entities.Client;
import fr.diginamic.Rest01.services.ClientService;

@RestController
@RequestMapping("/clients")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
public class ClientRest {
	
	@Autowired
	ClientService cs;
	
	@GetMapping("/all")
	public Iterable<Client> findAll() {
		return cs.findAllClients();
	}
	
	@GetMapping("/{id}")
	public Client findOneById(@PathVariable Integer id) {
		return cs.findClientById(id);
	}
	
	@DeleteMapping("/{id}/delete")
	public void delete(@PathVariable Integer id) {
		cs.removeClient(id);
	}
	
	

}
