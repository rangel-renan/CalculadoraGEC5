package com.calculadora.util.excessoes;

public class SintaxeEquacaoIncorretaException extends Exception {
	private static final long serialVersionUID = 1L;

	public SintaxeEquacaoIncorretaException() {
        super();
    }

    public SintaxeEquacaoIncorretaException(final String mensagem, final Throwable causa) {
        super(mensagem, causa);
    }

    public SintaxeEquacaoIncorretaException(final String mensagem) {
        super(mensagem);
    }

    public SintaxeEquacaoIncorretaException(final Throwable causa) {
        super(causa);
    }
}
