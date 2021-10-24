package fr.diginamic.Rest01.securite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.diginamic.Rest01.entities.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = ur.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(username + "NOT FOUND !");
		}
		return new CustomUserDetails(user);
	}

}
