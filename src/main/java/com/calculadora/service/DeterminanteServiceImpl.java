package com.calculadora.service;

public class DeterminanteServiceImpl implements DeterminanteService {
	private int iDF = 1;
	
	public Double calcularDeterminante(Double[][] matriz) {
		return Determinant(matriz);
	}
	
	public Double Determinant(Double[][] matrix) {
		int tms = matrix.length;

		Double det = 1.0;

		matrix = UpperTriangle(matrix);

		for (int i = 0; i < tms; i++) {
			det = det * matrix[i][i];
		} // multiply down diagonal

		det = det * iDF; // adjust w/ determinant factor

		return det;
	}
	
	public Double[][] UpperTriangle(Double[][] m) {
		Double f1 = 0.0;
		Double temp = 0.0;
		int tms = m.length; // get This Matrix Size (could be smaller than
							// global)
		int v = 1;

		iDF = 1;

		for (int col = 0; col < tms - 1; col++) {
			for (int row = col + 1; row < tms; row++) {
				v = 1;

				outahere: while (m[col][col] == 0) // check if 0 in diagonal
				{ // if so switch until not
					if (col + v >= tms) // check if switched all rows
					{
						iDF = 0;
						break outahere;
					} else {
						for (int c = 0; c < tms; c++) {
							temp = m[col][c];
							m[col][c] = m[col + v][c]; // switch rows
							m[col + v][c] = temp;
						}
						v++; // count row switchs
						iDF = iDF * -1; // each switch changes determinant
										// factor
					}
				}

				if (m[col][col] != 0) {
					
					try {
						f1 = (-1) * m[row][col] / m[col][col];
						for (int i = col; i < tms; i++) {
							m[row][i] = f1 * m[col][i] + m[row][i];
						}
					} catch (Exception e) {
						System.out.println("Still Here!!!");
					}

				}

			}
		}

		return m;
	}
}
