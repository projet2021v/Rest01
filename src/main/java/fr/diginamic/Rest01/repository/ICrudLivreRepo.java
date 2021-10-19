package fr.diginamic.Rest01.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.Rest01.entities.Livre;

public interface ICrudLivreRepo extends CrudRepository<Livre, Integer> {

}
