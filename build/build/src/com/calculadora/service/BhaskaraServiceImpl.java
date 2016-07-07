package com.calculadora.service;

import com.calculadora.util.excessoes.DeltaNegativoException;

public class BhaskaraServiceImpl implements BhaskaraService {
	
	@Override
	public Double calcularValorPositivo(Double a, Double b, Double c) {
		Double delta = 0.0;
		
		try {
			delta = calcularDelta(a, b, c);
		} catch (DeltaNegativoException e) {
			e.printStackTrace();
		}
		
		return acharResultPositivo(a, b, c, delta);
	}

	@Override
	public  Double calcularValorNegativo(Double a, Double b, Double c) {
		Double delta = 0.0;
		
		try {
			delta = calcularDelta(a, b, c);
		} catch (DeltaNegativoException e) {
			e.printStackTrace();
		}
		
		return acharResultNegativo(a, b, c, delta);
	}
	
	public <T> Double calcularDelta(Double a, Double b, Double c) throws DeltaNegativoException {
		
		Double quadradoB = a * b;
		Double delta = a * c;
		delta *= 4;
		delta = quadradoB - delta;
		
		if (delta < 0)
			throw new DeltaNegativoException("Delta com valor negativo: " + delta, new Throwable("Não existe valor de Delta negativo, impossível continuar a operação."));
		
		return delta;
	}
	
	public Double acharResultPositivo(Double a, Double b, Double c, Double delta){
		
		double raizQuadrada = Math.sqrt(delta);
		double result = raizQuadrada - b;
		result /= 2 * a;

		return result;
	}
	
	public Double acharResultNegativo(Double a, Double b, Double c, Double delta) {
		
		double raizQuadrada = Math.sqrt(delta);
		double result = -raizQuadrada - b;
		result /= 2 * a;
		
		return result;
	}
	
}
