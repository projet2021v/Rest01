package fr.diginamic.Rest01.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.Rest01.entities.Client;
import fr.diginamic.Rest01.entities.Emprunt;
import fr.diginamic.Rest01.repository.ICrudClientRepo;

@Service
public class ClientService {
	
	@Autowired
	ICrudClientRepo cr;
	
	@Autowired
	EmpruntService es;

	public Client addClient(Client client) {
		Client c = cr.save(client);
		return c;
	}
	
	public List<Client> findAllClients() {
		List<Client> liste = new ArrayList<Client>();
		for(Client c : cr.findAll()) {
			liste.add(c);
		}
		return liste;
	}
	
	public Client findClientById(Integer id) {
		return cr.findById(id).get();
	}
	
	public void updateClient(Client client) {
		Client c = cr.findById(client.getId()).get();
		c.setNom(client.getNom());
		c.setPrenom(client.getPrenom());
		c.setEmprunts(client.getEmprunts());
		cr.save(c);
	}
	
	public void removeClient(Integer id) {
		Client c = cr.findById(id).get();
		
		//clean des emprunts du client
//		for(Emprunt e : c.getEmprunts()) {
//			es.removeEmprunt(e.getId());
//		}
		
		//clean des emprunts du client
		Iterator<Emprunt> it = c.getEmprunts().iterator();
		while(it.hasNext()) {
			es.removeEmprunt(it.next().getId());
		}
		
		//suppression du client
		cr.delete(c);
		
	}
	
	
	
}
