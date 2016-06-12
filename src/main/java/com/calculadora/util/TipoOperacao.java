package com.calculadora.util;

public enum TipoOperacao {
	SOMA("+", "Soma"),
	SUBTRACAO("-", "Subtração"),
	MULTIPLICACAO("*", "Multiplicação"),
	DIVISAO("/", "Divisão"),
	RESTO("%", "Resto");
	
	private String sinalOperacao;
	private String nomeOperacao;
	
	private TipoOperacao(String _sinalOperacao, String _nomeOperacao) {
		sinalOperacao = _sinalOperacao;
		nomeOperacao = _nomeOperacao;
	}
	
	public String getSinalOperacao() {
		return sinalOperacao;
	}
	
	public String getNomeOperacao() {
		return nomeOperacao;
	}
	
	public static TipoOperacao getOperacao(String sinalOperacao) {
		
		for (TipoOperacao tipo : values()) {
			if (sinalOperacao.equals(tipo.getSinalOperacao()))
				return tipo;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return getSinalOperacao();
	}
}
