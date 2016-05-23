package com.calculadora.service;

import java.util.List;

public interface ICossenoEntreVotores {

	public <T extends Number> Double calcularCosseno(List<T> primeiroVetor, List<T> segundoVetor);

}