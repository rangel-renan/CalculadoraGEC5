package com.calculadora.util;

public enum TipoOperacao {
	SOMA("+"),
	SUBTRACAO("-"),
	MULTIPLICACAO("*"),
	DIVISAO("/"),
	RAIZ_QUADRADA("RAIZ");
	
	private String sinalOperacao;
	
	private TipoOperacao(String _sinalOperacao) {
		sinalOperacao = _sinalOperacao;
	}
	
	public String getSinalOperacao() {
		return sinalOperacao;
	}
	
	public static TipoOperacao getOperacao(String sinalOperacao) {
		
		for (TipoOperacao tipo : values()) {
			if (sinalOperacao.equals(tipo.getSinalOperacao()))
				return tipo;
		}
		
		return null;
	}
	
	
}
