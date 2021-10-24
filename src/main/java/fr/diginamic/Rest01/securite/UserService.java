//package fr.diginamic.Rest01.securite;
//
//import java.util.Objects;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import fr.diginamic.Rest01.entities.User;
//
//@Service
//public class UserService implements UserDetailsService {
//	
//	private final UserRepository ur;
//	
//	@Autowired
//	public UserService(UserRepository ur) {
//		this.ur = ur;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		Objects.requireNonNull(username);
//		try {
//			User user = ur.findUserByName(username);
//			return user;
//		} catch (UsernameNotFoundException e) {
//			throw new UsernameNotFoundException("User not found");
//		}
//		
////		User user = ur.findUserByName(username)
////		.orElseThrow(
////				() -> new UsernameNotFoundException("User not found")
////		);
//		
//		
//	}
//
//}
