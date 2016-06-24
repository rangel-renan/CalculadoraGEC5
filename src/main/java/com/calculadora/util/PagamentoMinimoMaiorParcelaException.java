package com.calculadora.util;

public class PagamentoMinimoMaiorParcelaException extends Exception {
	private static final long serialVersionUID = 1L;

	public PagamentoMinimoMaiorParcelaException() {
        super();
    }

    public PagamentoMinimoMaiorParcelaException(final String mensagem, final Throwable causa) {
        super(mensagem, causa);
    }

    public PagamentoMinimoMaiorParcelaException(final String mensagem) {
        super(mensagem);
    }

    public PagamentoMinimoMaiorParcelaException(final Throwable causa) {
        super(causa);
    }
}
