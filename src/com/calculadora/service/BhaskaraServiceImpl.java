package com.calculadora.service;

import com.calculadora.model.Bhaskara;
import com.calculadora.util.DeltaNegativoException;

public class BhaskaraServiceImpl implements BhaskaraService {
	private Bhaskara bhaskara;
	
	public BhaskaraServiceImpl(Bhaskara _bhaskara) {
		bhaskara = _bhaskara;
	}
	
	@Override
	public Double calcularValorPositivo() {
		
		try {
			bhaskara.setDelta(calcularDelta());
		} catch (DeltaNegativoException e) {
			e.printStackTrace();
		}
		
		return acharResultPositivo();
	}

	@Override
	public  Double calcularValorNegativo() {
		
		try {
			bhaskara.setDelta(calcularDelta());
		} catch (DeltaNegativoException e) {
			e.printStackTrace();
		}
		
		return acharResultNegativo();
	}

	public <T> Double calcularDelta() throws DeltaNegativoException {
		
		Double quadradoB = bhaskara.getB() * bhaskara.getB();
		Double delta = bhaskara.getA() * bhaskara.getC();
		delta *= 4;
		delta = quadradoB - delta;
		
		if (delta < 0)
			throw new DeltaNegativoException("Delta com valor negativo: " + delta, new Throwable("Não existe valor de Delta negativo, impossível continuar a operação."));
		
		return delta;
	}
	
	public Double acharResultPositivo(){
		
		double raizQuadrada = Math.sqrt(bhaskara.getDelta());
		double result = raizQuadrada - bhaskara.getB();
		result /= 2 * bhaskara.getA();

		return result;
	}
	
	public Double acharResultNegativo(){
		
		double raizQuadrada = Math.sqrt(bhaskara.getDelta());
		double result = -raizQuadrada - bhaskara.getB();
		result /= 2 * bhaskara.getA();
		
		return result;
	}
	
//	public void MostraResultados(){
//		Scanner input = new Scanner(System.in);
//		System.out.println("x1 = " + getResultPositivo());
//		System.out.println("x2 = " + getResultNegativo());
//	}
//	
//	public void formulaBhaskara(){
//		System.out.println("****Equação de BHASKARA****");
//		System.out.println("Equação do segundo grau: ax^2 + bx + c = 0");
//		this.levalores();
//		this.calcularDelta();
//		if(calcularDelta() < 0){
//			System.out.println("Delta com valor " + calcularDelta() + "Impossivel continuar a expressao");
//		} else {
//			this.acharResultPositivo();
//			this.acharResultNegativo();
//			this.MostraResultados();
//		}
//	}
//	
//	public void levalores(){
//		Scanner input = new Scanner(System.in);
//		
//		System.out.print("Entre com o valor de a: ");
//		this.setA(input.nextDouble());
//		System.out.print("Entre com o valor de b: ");
//		this.setB(input.nextDouble());
//		System.out.print("Entre com o valor de c: ");
//		this.setC(input.nextDouble());
//	}

}
