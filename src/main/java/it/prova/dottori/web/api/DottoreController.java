package it.prova.dottori.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.dottori.dto.DottoreDTO;
import it.prova.dottori.model.Dottore;
import it.prova.dottori.service.dottore.DottoreService;
import it.prova.dottori.web.api.exception.DottoreALavoroException;
import it.prova.dottori.web.api.exception.DottoreNotFoundException;

@RestController
@RequestMapping("/api/dottore")
public class DottoreController {

	@Autowired
	private DottoreService dottoreService;

	@GetMapping
	public List<DottoreDTO> listAll() {
		return DottoreDTO.createDottoreDTOListFromModelList(dottoreService.findAll());
	}

	@GetMapping("/{id}")
	public DottoreDTO findById(@PathVariable(name = "id", required = true) Long id) {
		return DottoreDTO.buildDTORidottoFromDottoreModel(dottoreService.findById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DottoreDTO inserisciNuovo(@RequestBody DottoreDTO input) {
		return DottoreDTO.buildDTORidottoFromDottoreModel(dottoreService.inserisciNuovo(input.buildDottoreModel()));
	}

	@PutMapping
	public DottoreDTO aggiorna(@RequestBody DottoreDTO input) {

		Dottore dottoreInstance = dottoreService.findById(input.getId());

		if (dottoreInstance == null) {
			throw new DottoreNotFoundException(
					"Impossibile modificare perche' non e' stato trovato nessun dottore con questo id");
		}

		return DottoreDTO.buildDTORidottoFromDottoreModel(dottoreService.aggiorna(input.buildDottoreModel()));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void rimuovi(@PathVariable(name = "id", required = true) Long id) {

		Dottore dottoreInstance = dottoreService.findById(id);

		if (dottoreInstance == null) {
			throw new DottoreNotFoundException(
					"Impossibile eliminare perche' non e' stato trovato nessun dottore con questo id");
		}

		if (dottoreInstance.getInServizio() || dottoreInstance.getInVisita()) {
			throw new DottoreALavoroException("Non puoi rimuovere un dottore che sta lavorando!");
		}

		dottoreService.rimuovi(id);
	}

	@GetMapping("/findByCodice/{codiceDottore}")
	public DottoreDTO findByCOdiceDottore(@PathVariable(name = "codiceDottore", required = true) String codiceDottore) {
		return DottoreDTO.buildDTORidottoFromDottoreModel(dottoreService.findByCodice(codiceDottore));
	}
	
	@PostMapping("/impostaInVisita/{codiceDottore}")
	public String impostaInVisita(@PathVariable(name = "codiceDottore", required = true) String codiceDottore, @RequestBody String codiceFiscalePaziente) {
		
		Dottore dottoreInstance = dottoreService.findByCodice(codiceDottore);
		if (dottoreInstance.getInVisita() || dottoreInstance.getCodFiscalePazienteAttualmenteInVisita() != null) {
			throw new DottoreALavoroException("Dottore gia' in visita");
		}
		dottoreService.impostaInVisita(dottoreInstance, codiceFiscalePaziente);
		return codiceDottore;
	}
}
