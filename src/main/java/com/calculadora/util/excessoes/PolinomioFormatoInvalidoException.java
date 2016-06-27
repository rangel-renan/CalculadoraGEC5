package com.calculadora.util.excessoes;

public class PolinomioFormatoInvalidoException extends Exception {
	private static final long serialVersionUID = 1L;

	public PolinomioFormatoInvalidoException() {
        super();
    }

    public PolinomioFormatoInvalidoException(final String mensagem, final Throwable causa) {
        super(mensagem, causa);
    }

    public PolinomioFormatoInvalidoException(final String mensagem) {
        super(mensagem);
    }

    public PolinomioFormatoInvalidoException(final Throwable causa) {
        super(causa);
    }
}
