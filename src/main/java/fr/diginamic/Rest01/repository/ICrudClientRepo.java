package fr.diginamic.Rest01.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.Rest01.entities.Client;

public interface ICrudClientRepo extends CrudRepository<Client, Integer> {
	
}
