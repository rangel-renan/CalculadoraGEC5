package com.calculadora.service;

public interface RazoesTrigonometricasService {
	
	public <T extends Number> T calcularCosseno(T catetoAdjacente, T hipotenusa);
	public <T extends Number> T calcularSeno(T catetoOposto, T hipotenusa);
	public <T extends Number> T calcularTangente(T catetoOposto, T catetoAdjacente);
}
