package it.prova.dottori.dto;

import java.util.List;
import java.util.stream.Collectors;

import it.prova.dottori.model.Dottore;

public class DottoreDTO {

	private Long id;
	private String nome;
	private String cognome;
	private String codiceDottore;
	private String codFiscalePazienteAttualmenteInVisita;
	private Boolean inVisita;
	private Boolean inServizio;

	public DottoreDTO() {
		super();
	}

	public DottoreDTO(String codiceDottore, String codFiscalePazienteAttualmenteInVisita, Boolean inVisita,
			Boolean inServizio) {
		super();
		this.codiceDottore = codiceDottore;
		this.codFiscalePazienteAttualmenteInVisita = codFiscalePazienteAttualmenteInVisita;
		this.inVisita = inVisita;
		this.inServizio = inServizio;
	}

	public DottoreDTO(Long id, String nome, String cognome, String codiceDottore,
			String codFiscalePazienteAttualmenteInVisita, Boolean inVisita, Boolean inServizio) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDottore = codiceDottore;
		this.codFiscalePazienteAttualmenteInVisita = codFiscalePazienteAttualmenteInVisita;
		this.inVisita = inVisita;
		this.inServizio = inServizio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceDottore() {
		return codiceDottore;
	}

	public void setCodiceDottore(String codiceDottore) {
		this.codiceDottore = codiceDottore;
	}

	public String getCodFiscalePazienteAttualmenteInVisita() {
		return codFiscalePazienteAttualmenteInVisita;
	}

	public void setCodFiscalePazienteAttualmenteInVisita(String codFiscalePazienteAttualmenteInVisita) {
		this.codFiscalePazienteAttualmenteInVisita = codFiscalePazienteAttualmenteInVisita;
	}

	public Boolean getInVisita() {
		return inVisita;
	}

	public void setInVisita(Boolean inVisita) {
		this.inVisita = inVisita;
	}

	public Boolean getInServizio() {
		return inServizio;
	}

	public void setInServizio(Boolean inServizio) {
		this.inServizio = inServizio;
	}

	public Dottore buildDottoreModel() {
		return new Dottore(this.id, this.nome, this.cognome, this.codiceDottore,
				this.codFiscalePazienteAttualmenteInVisita, this.inVisita, this.inServizio);
	}

	public static DottoreDTO buildDTOFromDottoreModel(Dottore model) {
		return new DottoreDTO(model.getId(), model.getNome(), model.getCognome(), model.getCodiceDottore(),
				model.getCodFiscalePazienteAttualmenteInVisita(), model.getInVisita(), model.getInServizio());
	}

	public static DottoreDTO buildDTORidottoFromDottoreModel(Dottore model) {
		return new DottoreDTO(model.getCodiceDottore(), model.getCodFiscalePazienteAttualmenteInVisita(),
				model.getInVisita(), model.getInServizio());
	}

	public static List<DottoreDTO> createDottoreDTOListFromModelList(List<Dottore> modelListInput) {
		return modelListInput.stream().map(d -> DottoreDTO.buildDTOFromDottoreModel(d)).collect(Collectors.toList());
	}
}
