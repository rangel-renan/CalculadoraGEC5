package com.calculadora.util.enums;

public enum TipoOperacaoTrigono {
	SENO("Sin"), COSSENO("Cos"), TANGENTE("Tan"), 
	ARCO_COSSENO("Cos^-1"), ARCO_SENO("Sin^-1"), ARCO_TANGENTE("Tan^-1"),
	SECANTE("Sec^-1"), COSSECANTE("Coss^-1"), COTANGENTE("Cot^-1");
	
	private String nome;

	private TipoOperacaoTrigono(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public static TipoOperacaoTrigono getOperacao(String nome) {
		
		for(TipoOperacaoTrigono op : TipoOperacaoTrigono.values()) {
			if (op.getNome().equals(nome))
				return op;
		}
		
		return null;
	}
}
