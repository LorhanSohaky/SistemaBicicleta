package br.ufscar.dc.dsw.erros;

public class ValidationError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValidationError(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
