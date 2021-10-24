package fr.diginamic.Rest01.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.diginamic.Rest01.entities.Client;
import fr.diginamic.Rest01.exceptions.ClientException;
import fr.diginamic.Rest01.services.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	ClientService cs;
	
	@GetMapping("/all")
	public String all(Model model) {
		List<Client> listeClients = cs.findAllClients();
		model.addAttribute("listeClients", listeClients);
		return "clients/clients_gestion";
	}
	
	@GetMapping("/create")
	public String add(Model model) {
		model.addAttribute("newClient", new Client());
		return "clients/clients_ajout";
	}
	
	@PostMapping("/create")
	public String add(
			@ModelAttribute("newClient") @Valid Client c, 
			BindingResult result) {
		if(result.hasErrors()) {
			return "clients/clients_ajout";
		}
		cs.addClient(c);
		return "redirect:/clients/all";
	}
	
	@GetMapping("/{id}/update")
	public String update(@PathVariable("id") Integer id, Model model) throws ClientException {
		model.addAttribute("clientToUpdate", cs.findClientById(id));
		return "clients/clients_modification";
	}
	
	@PostMapping("/{id}/update")
	public String update(
			@ModelAttribute("clientToUpdate") @Valid Client c,
			BindingResult result,
			@PathVariable Integer id) throws ClientException {
		if(result.hasErrors()) {
			/*
			 * On ne devrait pas avoir à utiliser de setId().
			 * SpringBoot permet d'updater automatiquement, 
			 * je ne comprends pas pourquoi cela ne fonctionne pas ici.
			 */
			c.setId(id);
			return "clients/clients_modification";
		}
		cs.updateClient(c, id);
		return "redirect:/clients/all";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Integer id) throws ClientException {
		cs.removeClient(id);
		return "redirect:/clients/all";
	}
}
