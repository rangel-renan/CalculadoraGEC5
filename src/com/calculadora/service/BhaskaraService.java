package com.calculadora.service;

public interface BhaskaraService {

	public <T extends Number> Double calcularValorPositivo(T valA, T valB, T valC);
	public <T extends Number> Double calcularValorNegativo(T valA, T valB, T valC);

}