package com.calculadora.util;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/*
 * quilômetro (km), hectômetro (hm) e decâmetro (dam) e os submúltiplos são decímetro (dm), centímetro (cm) e milímetro
 */
public enum TipoConversoesComprimento {
	QUILOMETRO("Quilômetro", SI.KILOMETER),
	HECTOMETRO("Hectômetro", SI.HECTO(SI.METER)),
	DECAMETRO("Decâmetro", SI.DEKA(SI.METER)),
	METRO("Metro", SI.METER),
	DECIMETRO("Decímetro", SI.DECI(SI.METER)),
	CENTIMETRO("Centímetro", SI.CENTIMETER),
	MILIMETRO("Milímetro", SI.MILLIMETER);
	
	private String nome;
	private Unit<?> tipo;
	
	private TipoConversoesComprimento(String nome, Unit<?> tipo) {
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
