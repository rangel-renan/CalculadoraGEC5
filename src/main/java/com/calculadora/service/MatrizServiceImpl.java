package com.calculadora.service;

import com.calculadora.util.MatrizesTamanhosDiferentesException;
import com.calculadora.util.TipoOperacao;

public class MatrizServiceImpl implements MatrizService {
	private int iDF = 1;
	
	@Override
	public Double[][] operacaoMatrizes(Double[][] firstMatriz, Double[][] secondMatriz, TipoOperacao tipoOperacao) throws MatrizesTamanhosDiferentesException {
		
		switch (tipoOperacao) {
			case SOMA:
				return somarMatrizes(firstMatriz, secondMatriz);
			case SUBTRACAO:
				return subtrairMatrizes(firstMatriz, secondMatriz);
			case MULTIPLICACAO:
				return multiplicarMatrizes(firstMatriz, secondMatriz);
			default:
				return null;
		}
	}

	
	private Double[][] somarMatrizes(Double[][] firstMatriz, Double[][] secondMatriz) throws MatrizesTamanhosDiferentesException {
		int tamanhoLinhaFirstMatriz = firstMatriz.length;
		int tamanhoColunaFirstMatriz = firstMatriz[0].length;
		int tamanhoLinhaSecondMatriz = secondMatriz.length;
		int tamanhoColunaSecondMatriz = secondMatriz[0].length;
		
		if (tamanhoLinhaFirstMatriz != tamanhoLinhaSecondMatriz
			|| tamanhoColunaFirstMatriz != tamanhoColunaSecondMatriz) 
			throw new MatrizesTamanhosDiferentesException("Matrizes de Tamanho Diferentes.");

		Double matrizResultante[][] = new Double[tamanhoLinhaFirstMatriz][tamanhoColunaFirstMatriz];

		for (int coluna = 0; coluna < tamanhoLinhaFirstMatriz; coluna++)
			for (int linha = 0; linha < tamanhoColunaFirstMatriz; linha++) {
				matrizResultante[coluna][linha] = firstMatriz[coluna][linha] + secondMatriz[coluna][linha];
			}

		return matrizResultante;
	}
	
	private Double[][] subtrairMatrizes(Double[][] firstMatriz, Double[][] secondMatriz) throws MatrizesTamanhosDiferentesException {
		int tamanhoLinhaFirstMatriz = firstMatriz.length;
		int tamanhoColunaFirstMatriz = firstMatriz[0].length;
		int tamanhoLinhaSecondMatriz = secondMatriz.length;
		int tamanhoColunaSecondMatriz = secondMatriz[0].length;
		
		if (tamanhoLinhaFirstMatriz != tamanhoLinhaSecondMatriz
			|| tamanhoColunaFirstMatriz != tamanhoColunaSecondMatriz) 
			throw new MatrizesTamanhosDiferentesException("Matrizes de Tamanho Diferentes.");
		
		Double matrizResultante[][] = new Double[tamanhoLinhaFirstMatriz][tamanhoColunaFirstMatriz];

		for (int coluna = 0; coluna < tamanhoLinhaFirstMatriz; coluna++)
			for (int linha = 0; linha < tamanhoColunaFirstMatriz; linha++) {
				matrizResultante[coluna][linha] = firstMatriz[coluna][linha] - secondMatriz[coluna][linha];
				System.out.print(matrizResultante[coluna][linha] + " ");
			}

		return matrizResultante;
	}
	
	private Double[][] multiplicarMatrizes(Double[][] firstMatriz, Double[][] secondMatriz) throws MatrizesTamanhosDiferentesException {
		int tamanhoLinhaFirstMatriz = firstMatriz.length;
		int tamanhoColunaFirstMatriz = firstMatriz[0].length;
		int tamanhoLinhaSecondMatriz = secondMatriz.length;
		int tamanhoColunaSecondMatriz = secondMatriz[0].length;
		
		if (tamanhoLinhaFirstMatriz != tamanhoLinhaSecondMatriz
			|| tamanhoColunaFirstMatriz != tamanhoColunaSecondMatriz) {
			throw new MatrizesTamanhosDiferentesException("Matrizes de Tamanho Diferentes.");
		}

		Double matrizResultante[][] = new Double[tamanhoLinhaFirstMatriz][tamanhoColunaFirstMatriz];

		for (int coluna = 0; coluna < tamanhoLinhaFirstMatriz; coluna++)
			for (int linha = 0; linha < tamanhoColunaFirstMatriz; linha++)
				matrizResultante[coluna][linha] = 0.0;

		for (int coluna = 0; coluna < tamanhoLinhaFirstMatriz; coluna++)
			for (int linha = 0; linha < tamanhoColunaSecondMatriz; linha++) {
				for (int p = 0; p < tamanhoLinhaFirstMatriz; p++)
					matrizResultante[coluna][linha] += firstMatriz[coluna][p] * secondMatriz[p][linha];
			}

		return matrizResultante;
	}
	
	@Override
	public Double calcularDeterminante(Double[][] matriz) {
		int tamanhoMatriz = matriz.length;
		Double determinante = 1.0;

		matriz = calcularMatrizTriangular(matriz);

		for (int cont = 0; cont < tamanhoMatriz; cont++) {
			determinante = determinante * matriz[cont][cont];
		} // multiply down diagonal

		determinante = determinante * iDF; // adjust w/ determinant factor

		return determinante;
	}

	public Double[][] calcularMatrizTriangular(Double[][] matriz) {
		Double f1 = 0.0;
		Double temp = 0.0;
		int tms = matriz.length; // get This Matrix Size (could be smaller than
		// global)
		int v = 1;

		iDF = 1;

		for (int col = 0; col < tms - 1; col++) {
			for (int row = col + 1; row < tms; row++) {
				v = 1;

				outahere: while (matriz[col][col] == 0) // check if 0 in
														// diagonal
				{ // if so switch until not
					if (col + v >= tms) // check if switched all rows
					{
						iDF = 0;
						break outahere;
					} else {
						for (int c = 0; c < tms; c++) {
							temp = matriz[col][c];
							matriz[col][c] = matriz[col + v][c]; // switch rows
							matriz[col + v][c] = temp;
						}
						v++; // count row switchs
						iDF = iDF * -1; // each switch changes determinant
										// factor
					}
				}

				if (matriz[col][col] != 0) {

					try {
						f1 = (-1) * matriz[row][col] / matriz[col][col];
						for (int i = col; i < tms; i++) {
							matriz[row][i] = f1 * matriz[col][i] + matriz[row][i];
						}
					} catch (Exception e) {
						System.out.println("Still Here!!!");
					}

				}

			}
		}

		return matriz;
	}

	@Override
	public Double[][] calcularTransposta(Double[][] matriz) {
		int tamanhoMatriz = matriz.length;

		Double matrizTranposta[][] = new Double[tamanhoMatriz][tamanhoMatriz];

		for (int coluna = 0; coluna < tamanhoMatriz; coluna++)
			for (int linha = 0; linha < tamanhoMatriz; linha++) {
				matrizTranposta[coluna][linha] = matriz[linha][coluna];
			}

		return matrizTranposta;
	}

	@Override
	public Double[][] calcularMatrizInversa(Double[][] matriz) {
		// Formula used to Calculate Inverse:
		// inv(A) = 1/det(A) * adj(A)

		int tamanhoMatriz = matriz.length;

		Double matrizInversa[][] = new Double[tamanhoMatriz][tamanhoMatriz];
		Double matrizAdjunta[][] = calcularMatrizAdjunta(matriz);

		Double determinante = calcularDeterminante(matriz);
		Double dd = 0.0;

		if (determinante == 0) {
			System.out.println("Determinant Equals 0, Not Invertible.");
		} else {
			dd = 1 / determinante;
		}

		for (int i = 0; i < tamanhoMatriz; i++)
			for (int j = 0; j < tamanhoMatriz; j++) {
				matrizInversa[i][j] = dd * matrizAdjunta[i][j];
			}

		return matrizInversa;
	}

	@Override
	public Double[][] calcularMatrizAdjunta(Double[][] matriz) {
		int tamanhoMatriz = matriz.length;

		Double matrizAdjunta[][] = new Double[tamanhoMatriz][matriz[0].length];

		int ii, jj, ia, ja;
		Double determinante;

		for (int coluna = 0; coluna < tamanhoMatriz; coluna++)
			for (int linha = 0; linha < matriz[0].length; linha++) {
				ia = ja = 0;

				Double ap[][] = new Double[tamanhoMatriz - 1][tamanhoMatriz - 1];

				for (ii = 0; ii < tamanhoMatriz; ii++) {
					for (jj = 0; jj < matriz[0].length; jj++) {

						if ((ii != coluna) && (jj != linha)) {
							ap[ia][ja] = matriz[ii][jj];
							ja++;
						}

					}
					if ((ii != coluna) && (jj != linha)) {
						ia++;
					}
					ja = 0;
				}

				determinante = calcularDeterminante(ap);
				matrizAdjunta[coluna][linha] = (Double) Math.pow(-1, coluna + linha) * determinante;
			}

		matrizAdjunta = calcularTransposta(matrizAdjunta);

		return matrizAdjunta;
	}


	@Override
	public Double[][] multiplicarPor(Double[][] matriz, Double valor) {
		int tamanhoMatriz = matriz.length;
		
		Double matrizResultante[][] = new Double[tamanhoMatriz][matriz[0].length];
		
		for (int coluna = 0; coluna < tamanhoMatriz; coluna++)
			for (int linha = 0; linha < matriz[0].length; linha++) {
				matrizResultante[coluna][linha] = matriz[coluna][linha] * valor;
			}
		
		return matrizResultante;
	}


	@Override
	public Double[][] elevarPor(Double[][] matriz, Double valor) {
		int tamanhoMatriz = matriz.length;
		Double matrizResultante[][] = new Double[matriz.length][matriz[0].length];
		
		for (int coluna = 0; coluna < tamanhoMatriz; coluna++)
			for (int linha = 0; linha < matriz[0].length; linha++) {
				matrizResultante[coluna][linha] = Math.pow(matriz[coluna][linha], valor);
			}
		
		return matrizResultante;
	}

}
