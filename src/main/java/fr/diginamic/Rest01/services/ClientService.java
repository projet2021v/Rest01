package fr.diginamic.Rest01.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.Rest01.entities.Client;
import fr.diginamic.Rest01.entities.Emprunt;
import fr.diginamic.Rest01.exceptions.ClientException;
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
	
	public Client findClientById(Integer id) throws ClientException {
		Optional<Client> cOpt = cr.findById(id);
		if(cOpt.isEmpty()) {
			throw new ClientException("Client inexistant");
		}
		return cOpt.get();
	}
	
	public void updateClient(Client client, Integer id) throws ClientException {
		Optional<Client> cOpt = cr.findById(id);
		if(cOpt.isEmpty()) {
			throw new ClientException("Client inexistant");
		}
		Client c = cOpt.get();
		c.setNom(client.getNom());
		c.setPrenom(client.getPrenom());
		c.setEmpruntsDuClient(client.getEmpruntsDuClient());
		cr.save(c);	
	}
	
	public void removeClient(Integer id) throws ClientException {
		Optional<Client> cOpt = cr.findById(id);
		if(cOpt.isEmpty()) {
			throw new ClientException("Client inexistant");
		}
		Client c = cOpt.get();
		
		//clean des emprunts du client
		/*
		 * Ici bricolage:
		 * ListIterator est un type "FailFast", 
		 * c-à-d qu'il ne permet la suppression à la volée d'un de ses éléments que si c'est lui-même qui la réalise (it.remove()).
		 * Ici la suppression est déléguée au service (EmpruntService), et ListIterator lance donc une exception.
		 * => j'ai opté pour la construction d'un ListIterator pour chaque emprunt, ce qui n'est pas élégant.
		 */
		while(c.getEmpruntsDuClient().size() != 0) {
			ListIterator<Emprunt> it = c.getEmpruntsDuClient().listIterator();
			Emprunt e = it.next();
			es.removeEmprunt(e.getId());
		}

		//suppression du client
		cr.delete(c);
	}
	
	
	
}
