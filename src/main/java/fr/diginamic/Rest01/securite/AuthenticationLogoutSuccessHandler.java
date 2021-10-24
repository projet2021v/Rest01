//package fr.diginamic.Rest01.securite;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
//
//public class AuthenticationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
//	
//	@Autowired
//	UserDetailsService userService;
//	
//	@Override
//	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//		response.setStatus(HttpServletResponse.SC_OK);
//	}
//	
//	@Bean
//	public AuthenticationProvider getProvider() {
//		AppAuthProvider provider = new AppAuthProvider();
//		provider.setUserDetailsService(userService);
//		return provider;
//	}
//	
//}
