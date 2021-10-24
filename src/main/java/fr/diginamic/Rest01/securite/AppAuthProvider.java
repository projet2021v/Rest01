//package fr.diginamic.Rest01.securite;
//
//import javax.naming.AuthenticationException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//public class AppAuthProvider extends DaoAuthenticationProvider {
//	
//	@Autowired
//	UserService userDetailsService;
//	
//	@Override
//	public Authentication authenticate(Authentication authentication) {
//		
//		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
//		String name =  auth.getName();
//		String password = auth.getCredentials().toString();
//		
//		UserDetails user = userDetailsService.loadUserByUsername(name);
//		if(user == null) {
//			throw new BadCredentialsException("Username/Password doesn't match for " + auth.getPrincipal());
//		}
//		
//		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//	}
//	
//	public boolean support(Class<?> authentication) {
//		return true;
//	}
//
//}
