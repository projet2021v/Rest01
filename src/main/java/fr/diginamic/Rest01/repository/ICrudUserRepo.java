package fr.diginamic.Rest01.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.Rest01.entities.User;

public interface ICrudUserRepo extends CrudRepository<User, Integer> {

}
