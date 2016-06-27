package com.calculadora.util.enums;

public enum TipoConversoes {
	COMPRIMENTO("Comprimento"), ARMANEZAMENTO_DADOS("Armanezamento de Dados");
	
	private String nome;
	
	private TipoConversoes(String _nome) {
		this.nome = _nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
