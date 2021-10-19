package fr.diginamic.Rest01.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.Rest01.entities.Emprunt;

public interface ICrudEmpruntRepo extends CrudRepository<Emprunt, Integer> {

}
