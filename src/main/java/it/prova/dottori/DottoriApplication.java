package it.prova.dottori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.dottori.model.Dottore;
import it.prova.dottori.service.dottore.DottoreService;

@SpringBootApplication
public class DottoriApplication implements CommandLineRunner {

	@Autowired
	private DottoreService dottoreService;
	
	public static void main(String[] args) {
		SpringApplication.run(DottoriApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Dottore doc1 = new Dottore("Pluto", "Plutotto", "PLUPLU123", false, true);
		dottoreService.inserisciNuovo(doc1);
		
		Dottore doc2 = new Dottore("Micetto", "Miciaccio", "MICMIC123", true, true);
		dottoreService.inserisciNuovo(doc2);
	}

}
