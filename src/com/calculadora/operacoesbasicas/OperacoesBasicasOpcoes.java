/**
 * 
 */
package com.calculadora.operacoesbasicas;

/**
 * @author Tiago
 * 
 */
public enum OperacoesBasicasOpcoes {
	SOMA("Soma", 1), 
	SUBTRACAO("Subtração", 2), 
	MULTIPLICACAO("Multiplicação", 3), 
	DIVISAO("Divisão", 4), 
	SAIR("Sair", 0);
	
	private int opcao;
	private String nomeOperacao;
	
	OperacoesBasicasOpcoes(String nomeOperacao, int opcao) {
		this.opcao = opcao;
		this.nomeOperacao = nomeOperacao;
	}
	
	public String nomeOperacao() {
		return nomeOperacao;
	}
	
	public int getNumOpcao() {
		return opcao;
	}
}
