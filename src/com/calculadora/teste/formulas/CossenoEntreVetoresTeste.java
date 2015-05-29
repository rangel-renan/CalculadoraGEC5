// CossenoEntreVetoresTeste.java
// Testa a classe CossenoEntreVetores
package com.calculadora.teste.formulas;

// Para o teste, importa-se a classe CossenoEntreVetores
import com.calculadora.formulas.CossenoEntreVetores;

public class CossenoEntreVetoresTeste {

	public static void main(String[] args) {
		
		double[] vetor1 = {3, 4, 0};
		double[] vetor2 = {4, 3, 0};
		
		// Cria um objeto do tipo CossenoEntreVetores
		// Adiciona os valores de vetor1 e vetor2 como parâmetro do objeto
		CossenoEntreVetores cossenoEntreVetores = new CossenoEntreVetores(vetor1, vetor2);

		// o termo final é utilizado pois é uma constante
		// representa o resultado do cosseno entre o vetor 1 e 2
		final double RESULTADO_ESPERADO = 0.96;
		
		// testa e printa se o teste ocorreu com sucesso ou não
			System.out.println(cossenoEntreVetores.calculaCosseno() == RESULTADO_ESPERADO ? "Teste realizado com sucesso!" : "Sinto muito, nao deu certo.");
	}

} // Término da classe
