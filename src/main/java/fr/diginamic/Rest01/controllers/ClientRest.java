package fr.diginamic.Rest01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.Rest01.entities.Client;
import fr.diginamic.Rest01.repository.ICrudClientRepo;
import fr.diginamic.Rest01.services.ClientService;

@RestController
@RequestMapping("/clients")
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
