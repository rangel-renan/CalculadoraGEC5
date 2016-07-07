package com.calculadora.util.excessoes;

public class VetorTamanhoExcedidoException extends Exception {
	private static final long serialVersionUID = 1L;

	public VetorTamanhoExcedidoException() {
        super();
    }

    public VetorTamanhoExcedidoException(final String mensagem, final Throwable causa) {
        super(mensagem, causa);
    }

    public VetorTamanhoExcedidoException(final String mensagem) {
        super(mensagem);
    }

    public VetorTamanhoExcedidoException(final Throwable causa) {
        super(causa);
    }

}
