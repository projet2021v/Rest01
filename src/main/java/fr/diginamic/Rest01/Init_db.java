package fr.diginamic.Rest01;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.diginamic.Rest01.entities.Client;
import fr.diginamic.Rest01.entities.Emprunt;
import fr.diginamic.Rest01.entities.Livre;
import fr.diginamic.Rest01.entities.User;
import fr.diginamic.Rest01.repository.ICrudClientRepo;
import fr.diginamic.Rest01.repository.ICrudEmpruntRepo;
import fr.diginamic.Rest01.repository.ICrudLivreRepo;
import fr.diginamic.Rest01.repository.ICrudUserRepo;

@Component
public class Init_db {
	
	@Autowired
	ICrudClientRepo cr;
	
	@Autowired
	ICrudEmpruntRepo er;
	
	@Autowired
	ICrudLivreRepo lr;
	
	@Autowired
	ICrudUserRepo ur;
	
//	@Autowired
//	UserRepository urr;
	
	@PostConstruct
	public void init() {
		
		User u1 = new User("admin", "admin");
		ur.save(u1);
//		urr.save(u1);
		
		Client c1 = new Client();
		c1.setNom("LB");
		c1.setPrenom("Michel");
		
		Client c2 = new Client();
		c2.setNom("LB");
		c2.setPrenom("Jean");
		
		
		Livre l1 = new Livre();
		l1.setAuteur("Jules Verne");
		l1.setTitre("Voyage au centre de la Terre");
			
		Livre l2 = new Livre();
		l2.setAuteur("William Gibson");
		l2.setTitre("Neuromancien");
		
		Livre l3 = new Livre();
		l3.setAuteur("Bernard Clavel");
		l3.setTitre("Amarok");
			
		Livre l4 = new Livre();
		l4.setAuteur("Simenon");
		l4.setTitre("Les frères Rico");
		

		l1 = lr.save(l1);
		l2 = lr.save(l2);
		l3 = lr.save(l3);
		l4 = lr.save(l4);
		
		c1 = cr.save(c1);
		c2 = cr.save(c2);
		
		
		//un 1er emprunt (c1 emprunte)
		Emprunt e1 = new Emprunt();
		e1.setDatedebut(new Date());
		e1.setDatefin(new Date());
		e1.setDelai(5);
		e1 = er.save(e1);
		
		e1.getLivresEmpruntes().add(l1);
//		l1.getEmpruntLivres().add(e1);
		l1 = lr.save(l1);
		
		e1.getLivresEmpruntes().add(l2);
//		l2.getEmpruntLivres().add(e1);
		l2 = lr.save(l2);
		
		e1.getLivresEmpruntes().add(l3);
//		l3.getEmpruntLivres().add(e1);
		l3 = lr.save(l3);
		
		e1 = er.save(e1);
		
		c1.getEmpruntsDuClient().add(e1);
		e1.setClientEmprunteur(c1);
		c1 = cr.save(c1);
		e1 = er.save(e1);
		
		
		//un 2ème emprunt (c2 emprunte)
		Emprunt e2 = new Emprunt();
		e2.setDatedebut(new Date());
		e2.setDatefin(new Date());
		e2.setDelai(5);
		e2 = er.save(e2);
		
		e2.getLivresEmpruntes().add(l3);
//		l3.getEmpruntLivres().add(e2);
		l3 = lr.save(l3);
		
		e2 = er.save(e2);
		
		c2.getEmpruntsDuClient().add(e2);
		e2.setClientEmprunteur(c2);
		c2 = cr.save(c2);
		e2 = er.save(e2);
		
		
		//un 3ème emprunt (c1 emprunte à nouveau)
		Emprunt e3 = new Emprunt();
		e3.setDatedebut(new Date());
		e3.setDatefin(new Date());
		e3.setDelai(5);
		e3 = er.save(e3);
		
		e3.getLivresEmpruntes().add(l2);
//		l2.getEmpruntLivres().add(e3);
		l2 = lr.save(l2);
		
		e3.getLivresEmpruntes().add(l4);
//		l4.getEmpruntLivres().add(e3);
		l4 = lr.save(l4);
		
		e3 = er.save(e3);
		
		c1.getEmpruntsDuClient().add(e3);
		e3.setClientEmprunteur(c1);
		c1 = cr.save(c1);
		e3 = er.save(e3);
	}

}
