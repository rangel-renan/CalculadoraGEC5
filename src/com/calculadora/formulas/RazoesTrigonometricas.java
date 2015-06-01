package com.calculadora.formulas;

import java.util.Scanner;

public class RazoesTrigonometricas {
	public static final int SENO = 1;
	public static final int COSSENO = 2;
	public static final int TANGENTE = 3;
	public static final int VOLTAR = 4;
	Scanner input = new Scanner(System.in);
	
	private double catetoOposto;
	private double catetoAdjacente;
	private double hipotenuza;
	private double resultado;
	
	public double getCatetoOposto() {
		return catetoOposto;
	}
	public void setCatetoOposto(double catetoOposto) {
		this.catetoOposto = catetoOposto;
	}
	public double getCatetoAdjacente() {
		return catetoAdjacente;
	}
	public void setCatetoAdjacente(double catetoAdjacente) {
		this.catetoAdjacente = catetoAdjacente;
	}
	public double getHipotenuza() {
		return hipotenuza;
	}
	public void setHipotenuza(double hipotenuza) {
		this.hipotenuza = hipotenuza;
	}
	public double getResultado() {
		return resultado;
	}
	public void setResultado(double resultado) {
		this.resultado = resultado;
	}
	
	public double leNum(){
		return input.nextDouble();
	}
	
	public void calculaSeno(){
		setResultado(getCatetoOposto() / getHipotenuza());
	}
	
	public void mostraResult(String opcao){
		System.out.println("Resultado do " + opcao + ": " + getResultado());
	}
	
	public void fazSeno(){
		System.out.print("Entre com o Cateto Oposto: ");
		setCatetoOposto(leNum());
		System.out.print("Entre com a Hipotenuza: ");
		setHipotenuza(leNum());
		calculaSeno();
		mostraResult("Seno");
		
	}
	
	public void calculaCosseno(){
		setResultado(getCatetoAdjacente() / getHipotenuza());
	}
	
	public void fazCosseno(){
		System.out.print("Entre com o Cateto Adjacente: ");
		setCatetoAdjacente(leNum());
		System.out.print("Entre com a Hipotenuza: ");
		setHipotenuza(leNum());
		calculaCosseno();
		mostraResult("Cosseno");
	}
	
	public void calculaTangente(){
		setResultado(getCatetoOposto() / getCatetoAdjacente());
	}
	
	public void fazTangente(){
		System.out.print("Entre com o Cateto Oposto: ");
		setCatetoOposto(leNum());
		System.out.print("Entre com o Cateto Adjacente: ");
		setCatetoAdjacente(leNum());
		calculaTangente();
		mostraResult("Tangente");
	}
	
	public void menu(){
		int opcao;
		boolean voltar = false;
		
		while(!voltar){
			System.out.println("-----Sub Menu-----");
			System.out.println("1- Seno");
			System.out.println("2- Cosseno");
			System.out.println("3- Tangente");
			System.out.println("4- Voltar ao menu Anterior");
			System.out.print("Entre com uma opcao: ");
			opcao = input.nextInt();
			switch(opcao){
			case SENO :
				fazSeno();
				voltar = true;
				break;
			case COSSENO:
				fazCosseno();
				voltar = true;
				break;
			case TANGENTE:
				fazTangente();
				voltar = true;
				break;
			case VOLTAR:
				System.out.println("Voltando ao menu Inicial");
				voltar = true;
				break;
			default:
				System.out.println("Entrada incorreta tente novamente!!");
			}
		}
	}
}
