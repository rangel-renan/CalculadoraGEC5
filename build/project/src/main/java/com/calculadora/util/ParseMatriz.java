package com.calculadora.util;

public class ParseMatriz {
	
	public static Double[][] parse(String stringMatriz) {
		Double[][] matrizDouble = null;
		
		try {
			String[] linhas = stringMatriz.split("\n");
			String[][] colunas = new String[linhas.length][];
			
			for (int l = 0; l < linhas.length; l++)
				colunas[l] = linhas[l].split(",");

			matrizDouble = new Double[linhas.length][colunas[0].length];

			for (int l = 0; l < linhas.length; l++)
				for (int c = 0; c < colunas[0].length; c++)
					matrizDouble[l][c] = new Double(colunas[l][c]);
		} catch (NumberFormatException e) {
			System.out.println("Numero em Formato Inválido.");
			return null;
		}
		
		return matrizDouble;
	}
	
	public static String montarMatriz(Double[][] matriz) {
		String matrizMontada = new String();

		for (int i = 0; i < matriz.length; i++) {
			matrizMontada += "|  ";
			for (int j = 0; j < matriz[0].length; j++) 
				matrizMontada += matriz[i][j] + "  ";
			matrizMontada += "|\n";
		}

		return matrizMontada;
	}
}
