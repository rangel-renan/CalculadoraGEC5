package com.calculadora.util.excessoes;

public class DeltaNegativoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DeltaNegativoException() {
        super();
    }

    public DeltaNegativoException(final String menssagem, final Throwable causa) {
        super(menssagem, causa);
    }

    public DeltaNegativoException(final String menssagem) {
        super(menssagem);
    }

    public DeltaNegativoException(final Throwable causa) {
        super(causa);
    }
}
