package br.ufscar.dc.dsw.erros;

public class SemanticError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SemanticError(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
