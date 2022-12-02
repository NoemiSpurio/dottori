package it.prova.dottori.service.dottore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.dottori.model.Dottore;
import it.prova.dottori.repository.DottoreRepository;
import it.prova.dottori.web.api.exception.IdNotNullForInsertException;

@Service
@Transactional
public class DottoreServiceImpl implements DottoreService {

	@Autowired
	private DottoreRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Dottore> findAll() {
		return (List<Dottore>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Dottore findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Dottore inserisciNuovo(Dottore input) {
		
		if (input.getId() != null) {
			throw new IdNotNullForInsertException("Non puoi inserire un dottore con un id associato.");
		}
		
		return repository.save(input);
	}

	@Override
	public Dottore aggiorna(Dottore input) {		
		return repository.save(input);
	}

	@Override
	public void rimuovi(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Dottore findByCodice(String codice) {
		return repository.findByCodiceDottore(codice);
	}

	@Override
	public void impostaInVisita(Dottore input, String codiceFiscalePaziente) {
		input.setInVisita(true);
		input.setCodFiscalePazienteAttualmenteInVisita(codiceFiscalePaziente);
		repository.save(input);
	}

}
