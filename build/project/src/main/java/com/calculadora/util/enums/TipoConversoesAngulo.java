package com.calculadora.util.enums;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesAngulo {
	GRAU("Grau", NonSI.GRADE),
	CENTIR("Centiradiano", NonSI.CENTIRADIAN),
	MINUTO_ARCO("Minuto de Arco", NonSI.MINUTE_ANGLE),
	SEGUNDO_ARCO("Segundo de Arco", NonSI.SECOND_ANGLE),
	RADIANO("Radiano", SI.RADIAN);
	
	private String nome;
	private Unit<?> tipo;
	
	private TipoConversoesAngulo(String nome, Unit<?> tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Unit<?> getTipo() {
		return tipo;
	}
	
	public Unit<?> getTipo(String nome) {
		
		for (TipoConversoesComprimento t : TipoConversoesComprimento.values()) {
			if (t.getNome().equals(nome))
				return t.getTipo();
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
