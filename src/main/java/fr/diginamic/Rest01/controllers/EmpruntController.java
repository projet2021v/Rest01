package fr.diginamic.Rest01.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.diginamic.Rest01.entities.Client;
import fr.diginamic.Rest01.entities.Emprunt;
import fr.diginamic.Rest01.services.ClientService;
import fr.diginamic.Rest01.services.EmpruntService;
import fr.diginamic.Rest01.services.LivreService;

@Controller
@RequestMapping("/emprunts")
public class EmpruntController {
	
	@Autowired
	EmpruntService es;
	
	@Autowired
	ClientService cs;
	
	@Autowired
	LivreService ls;
	
	@GetMapping("/all")
	public String all(Model model) {
		model.addAttribute("listeEmprunts", es.findAllEmprunts());
		return "emprunts/emprunts_gestion";
	}
	
	@GetMapping("/create")
	public String add(Model model) {
		model.addAttribute("newEmprunt", new Emprunt());
		model.addAttribute("listeLivres", ls.findAllLivres());
		model.addAttribute("listeClients", cs.findAllClients());
		model.addAttribute("auj", new Date());
		return "emprunts/emprunts_ajout";
	}
	
	@PostMapping("/create")
	public String add(Model model, @ModelAttribute("newEmprunt") @Valid Emprunt e, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("listeLivres", ls.findAllLivres());
			model.addAttribute("listeClients", cs.findAllClients());
			model.addAttribute("auj", new Date());
			return "emprunts/emprunts_ajout";
		}
		es.addEmprunt(e);
		return "redirect:/emprunts/all";
	}
	
	
	
	
	
	
	@GetMapping("/{id}/update")
	public String update(@PathVariable Integer id, Model model) {
		model.addAttribute("empruntToUpdate", es.findEmpruntById(id));
		model.addAttribute("listeClients", cs.findAllClients());
		model.addAttribute("listeLivres", ls.findAllLivres());
		return "emprunts/emprunts_modification";
	}
	
	@PostMapping("/{id}/update")
	public String update(
			@ModelAttribute("empruntToUpdate") @Valid Emprunt e,
			BindingResult result,
			@PathVariable Integer id,
			Model model) {
		model.addAttribute("listeClients", cs.findAllClients());
		model.addAttribute("listeLivres", ls.findAllLivres());
		if(result.hasErrors()) {
			e.setId(id);
			return "emprunts/emprunts_modification";
		}
		es.updateEmprunt(e, id);
		return "redirect:/emprunts/all";
	}
	
	
	
	
	
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Integer id) {
		es.removeEmprunt(id);
		return "redirect:/emprunts/all";
	}
	
	
}
