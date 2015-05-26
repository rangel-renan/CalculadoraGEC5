package com.calculadora.teste.formulas;

import com.calculadora.formulas.Bhaskara;

public class BhaskaraTest {

	public static void main(String[] args) {

		Bhaskara bhaskara = new Bhaskara();

		bhaskara.setA(1);
		bhaskara.setB(-8);
		bhaskara.setC(7);
		bhaskara.fazDelta();
		bhaskara.acharResultPositivo();
		bhaskara.acharResultNegativo();
		if((bhaskara.getResultPositivo() == 1 && bhaskara.getResultNegativo() == 7) || (bhaskara.getResultPositivo() == 7 && bhaskara.getResultNegativo() == 1)){
			System.out.println("Teste executado com sucesso");
		} else {
			System.out.println("Teste executado sem sucesso");
		}
	}

}
