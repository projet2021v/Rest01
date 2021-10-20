package fr.diginamic.Rest01.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.sound.midi.SysexMessage;

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
	
	public void updateClient(Client client, Integer id) {
		Client c = cr.findById(id).get();
		c.setNom(client.getNom());
		c.setPrenom(client.getPrenom());
		c.setEmpruntsDuClient(client.getEmpruntsDuClient());
		cr.save(c);
	}
	
	public void removeClient(Integer id) {
		Client c = cr.findById(id).get();
		
		//clean des emprunts du client
		while(c.getEmpruntsDuClient().size() != 0) {
			ListIterator<Emprunt> it = c.getEmpruntsDuClient().listIterator();
			Emprunt e = it.next();
			es.removeEmprunt(e.getId());
		}

		//suppression du client
		cr.delete(c);
	}
	
	
	
}
