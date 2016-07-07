package com.calculadora.util.enums;

public enum TipoPeriodos {
	DIAS("Dias"), MESES("Meses"), ANOS("Anos");
	
	private String nome;
	
	private TipoPeriodos(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
