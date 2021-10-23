package fr.diginamic.Rest01.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.Rest01.entities.Livre;
import fr.diginamic.Rest01.exceptions.LivreException;
import fr.diginamic.Rest01.services.LivreService;

@Controller
@RequestMapping("/livres")
public class LivreController {
	
	@Autowired
	LivreService ls;
	
	@GetMapping("/all")
	public String all(Model model) {
		List<Livre> listeLivres = ls.findAllLivres();
		model.addAttribute("listeLivres", listeLivres);
		return "livres/livres_gestion";
	}
	
	@GetMapping("/create")
	public String add(Model model) {
		model.addAttribute("newLivre", new Livre());
		return "livres/livres_ajout";
	}
	
	@PostMapping("/create")
	public String add(Model model, @ModelAttribute("newLivre") @Valid Livre l, BindingResult result) {
		if(result.hasErrors()) {
			return "livres/livres_ajout";
		}
		ls.addLivre(l);
		return "redirect:/livres/all";
	}
	
	@GetMapping("/{id}/update")
	public String update(Model model, @PathVariable Integer id) throws LivreException {
		model.addAttribute("livreToUpdate", ls.findLivreById(id));
		return "livres/livres_modification";
	}
	
	@PostMapping("/{id}/update")
	public String update(
			@PathVariable Integer id,
			@ModelAttribute("livreToUpdate") @Valid Livre l, 
			BindingResult result) throws LivreException {
		if(result.hasErrors()) {
			l.setId(id);
			return "livres/livres_modification";
		}
		ls.updateLivre(l, id);
		return "redirect:/livres/all";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Integer id) throws LivreException {
		ls.removeLivre(id);
		return "redirect:/livres/all";
	}
}
