package com.calculadora.util.excessoes;

public class ImpossivelConverterException extends Exception {
	private static final long serialVersionUID = 1L;

	public ImpossivelConverterException() {
        super();
    }

    public ImpossivelConverterException(final String mensagem, final Throwable causa) {
        super(mensagem, causa);
    }

    public ImpossivelConverterException(final String mensagem) {
        super(mensagem);
    }

    public ImpossivelConverterException(final Throwable causa) {
        super(causa);
    }
}
