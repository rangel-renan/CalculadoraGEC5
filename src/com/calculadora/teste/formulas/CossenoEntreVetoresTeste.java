package com.calculadora.teste.formulas;

import com.calculadora.formulas.CossenoEntreVetores;

public class CossenoEntreVetoresTeste {

	public static void main(String[] args) {
		
		CossenoEntreVetores cossenoEntreVetores = new CossenoEntreVetores();
		
		final double RESULTADO_ESPERADO = 0.96;
		
		double[] vetor1 = {3, 4, 0};
		double[] vetor2 = {4, 3, 0};
		
		cossenoEntreVetores.setVetor1(vetor1);
		cossenoEntreVetores.setVetor2(vetor2);
		
		if (cossenoEntreVetores.calculaCosseno() == RESULTADO_ESPERADO)
			System.out.println("Teste realizado com sucesso!");
			
		else
			System.out.println("Sinto muito, nao deu certo :'(");
	}

}
