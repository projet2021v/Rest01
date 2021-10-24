//package fr.diginamic.Rest01.securite;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import fr.diginamic.Rest01.entities.User;
//
//public interface UserRepository extends JpaRepository<User, Integer> {
//	
//	@Query("select u from User.u where u.username = :username")
//	User findUserByName(@Param("username") String username);
//	
//}
