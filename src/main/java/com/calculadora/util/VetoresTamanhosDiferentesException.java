package com.calculadora.util;

public class VetoresTamanhosDiferentesException extends Exception {
	private static final long serialVersionUID = 1L;

	public VetoresTamanhosDiferentesException() {
        super();
    }

    public VetoresTamanhosDiferentesException(final String mensagem, final Throwable causa) {
        super(mensagem, causa);
    }

    public VetoresTamanhosDiferentesException(final String mensagem) {
        super(mensagem);
    }

    public VetoresTamanhosDiferentesException(final Throwable causa) {
        super(causa);
    }
}
