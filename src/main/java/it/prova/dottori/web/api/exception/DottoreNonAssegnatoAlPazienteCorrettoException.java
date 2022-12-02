package it.prova.dottori.web.api.exception;

public class DottoreNonAssegnatoAlPazienteCorrettoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DottoreNonAssegnatoAlPazienteCorrettoException(String message) {
		super(message);
	}
}
