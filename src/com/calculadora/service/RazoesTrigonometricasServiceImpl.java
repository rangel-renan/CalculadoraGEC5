package com.calculadora.service;

import com.calculadora.model.RazoesTrigonometricas;

public class RazoesTrigonometricasServiceImpl implements RazoesTrigonometricasService {
	private RazoesTrigonometricas razoesTrigonometricas;
	
	public RazoesTrigonometricasServiceImpl(RazoesTrigonometricas _razoesTrigonometricas) {
		razoesTrigonometricas = _razoesTrigonometricas;
	}
	
	@Override
	public Double calcularCosseno() {
		razoesTrigonometricas.setResultado(razoesTrigonometricas.getCatetoAdjacente() / razoesTrigonometricas.getHipotenuza());
		return razoesTrigonometricas.getResultado();
	}

	@Override
	public Double calcularSeno() {
		razoesTrigonometricas.setResultado(razoesTrigonometricas.getCatetoOposto() / razoesTrigonometricas.getHipotenuza());
		return razoesTrigonometricas.getResultado();
	}

	@Override
	public Double calcularTangente() {
		razoesTrigonometricas.setResultado(razoesTrigonometricas.getCatetoOposto() / razoesTrigonometricas.getCatetoAdjacente());
		return razoesTrigonometricas.getResultado();
	}
	
//	public void mostraResult(String opcao){
//		System.out.println("Resultado do " + opcao + ": " + getResultado());
//	}
//	
//	
//	public void fazCosseno(){
//		System.out.print("Entre com o Cateto Adjacente: ");
//		setCatetoAdjacente(leNum());
//		System.out.print("Entre com a Hipotenuza: ");
//		setHipotenuza(leNum());
//		calculaCosseno();
//		mostraResult("Cosseno");
//	}
//	public void fazSeno(){
//		System.out.print("Entre com o Cateto Oposto: ");
//		setCatetoOposto(leNum());
//		System.out.print("Entre com a Hipotenuza: ");
//		setHipotenuza(leNum());
//		calculaSeno();
//		mostraResult("Seno");
//		
//	}
//	public void fazTangente(){
//		System.out.print("Entre com o Cateto Oposto: ");
//		setCatetoOposto(leNum());
//		System.out.print("Entre com o Cateto Adjacente: ");
//		setCatetoAdjacente(leNum());
//		calculaTangente();
//		mostraResult("Tangente");
//	}
//	
//	public void menu(){
//		int opcao;
//		boolean voltar = false;
//		
//		while(!voltar){
//			System.out.println("-----Sub Menu-----");
//			System.out.println("1- Seno");
//			System.out.println("2- Cosseno");
//			System.out.println("3- Tangente");
//			System.out.println("4- Voltar ao menu Anterior");
//			System.out.print("Entre com uma opcao: ");
//			opcao = input.nextInt();
//			switch(opcao){
//			case SENO :
//				fazSeno();
//				voltar = true;
//				break;
//			case COSSENO:
//				fazCosseno();
//				voltar = true;
//				break;
//			case TANGENTE:
//				fazTangente();
//				voltar = true;
//				break;
//			case VOLTAR:
//				System.out.println("Voltando ao menu Inicial");
//				voltar = true;
//				break;
//			default:
//				System.out.println("Entrada incorreta tente novamente!!");
//			}
//		}
//	}

	
}
