package com.calculadora.util;

public enum TipoPrestacao {
	FIXAS("Fixas (Tabela Price)"), DECRESCENTE("Decrescente(Tabela SAC");
	
	private String nome;
	
	private TipoPrestacao(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
