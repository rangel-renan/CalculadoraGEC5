package com.calculadora.teste.matriz;

import com.calculadora.matriz.Determinante;

public class DeterminanteTeste {

	public static void main(String[] args) {
		
	Determinante determinante = new Determinante();
	
	double det = determinante.determinanteMatrizNxN();
	System.out.println("determinante é: "+det);
	}

}
