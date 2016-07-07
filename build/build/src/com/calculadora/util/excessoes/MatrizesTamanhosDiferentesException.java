package com.calculadora.util.excessoes;

public class MatrizesTamanhosDiferentesException extends Exception {
	private static final long serialVersionUID = 1L;

	public MatrizesTamanhosDiferentesException() {
        super();
    }

    public MatrizesTamanhosDiferentesException(final String mensagem, final Throwable causa) {
        super(mensagem, causa);
    }

    public MatrizesTamanhosDiferentesException(final String mensagem) {
        super(mensagem);
    }

    public MatrizesTamanhosDiferentesException(final Throwable causa) {
        super(causa);
    }
}
