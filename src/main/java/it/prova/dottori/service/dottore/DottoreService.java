package it.prova.dottori.service.dottore;

import java.util.List;

import it.prova.dottori.model.Dottore;

public interface DottoreService {

	List<Dottore> findAll();

	Dottore findById(Long id);

	Dottore inserisciNuovo(Dottore input);

	Dottore aggiorna(Dottore input);

	void rimuovi(Long id);

	Dottore findByCodice(String codice);
	
	void impostaInVisita(Dottore input, String codiceFiscalePaziente);
	
	void terminaVisita(Dottore input);
}
