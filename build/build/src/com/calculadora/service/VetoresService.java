package com.calculadora.service;

import com.calculadora.util.excessoes.VetorTamanhoExcedidoException;
import com.calculadora.util.excessoes.VetoresTamanhosDiferentesException;

public interface VetoresService {
	
	public Double[] calcularSoma(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException;
	public Double[] calcularSubtracao(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException;
	public Double[] multiplicarPor(Double valor, Double[] vetor);
	public Double[] elevarPor(Double valor, Double[] vetor);
	public Double calcularProdutoEscalar(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException;
	public Double[] calcularProdutoVetorial(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException, VetorTamanhoExcedidoException;
	public Double[] calcularProjecao(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException;
	public Double calcularModulo(Double[] vetor);
	public Double calcularCosseno(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException;
}
