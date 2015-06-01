package com.calculadora.execucao;

/**
 * @author Tiago
 *
 */

public enum MenuOpcoes {
	CALCULADORA("Calculadora", 1),
	BHASKARA("Bhaskara", 2),
	PRODUTO_ESCALAR("Produto Escalar", 3),
	MODULO_VETOR("Modulo de um Vetor", 4),
	COSSENO_ENTRE_VETORES("Cosseno Entre Vetores", 5),
	RAIZ_QUADRADA("Raiz Quadrada", 6),
	POTENCIACAO("Potenciação", 7),
	DETERMINANTE("Determinante", 8),
	PRODUTO_NUM_VETOR("Produto de um Numero com um Vetor", 9),
	PRODUTO_VETORIAL("Produto Vetorial", 10),
	PROJECAO_VETOR("Projeção de um Vetor sobre Outro", 11),
	PITAGORAS("Pitágoras", 12),
	RAZOES_TRIGONOMETRICAS("Razões Trigonométricas", 13),
	SAIR("Sair", 0);
	
	private String nomeOpcao;
	private int numOpcao;
	
	MenuOpcoes(String nomeOpcao, int numOpcao) {
		this.nomeOpcao = nomeOpcao;
		this.numOpcao = numOpcao;
	}
	
	public int getNumOpcao() {
		return numOpcao;
	}
	
	public String getNomeOpcao() {
		return nomeOpcao;
	}
}
