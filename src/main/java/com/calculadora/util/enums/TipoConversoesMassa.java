package com.calculadora.util.enums;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesMassa {
	TONELADAS("Toneladas", NonSI.METRIC_TON),
	QUILOGRAMA("Quilograma", SI.KILOGRAM),
	GRAMA("Grama", SI.GRAM),
	MASSA_ATOMIC("Massa Atômica", NonSI.ATOMIC_MASS),
	ELETRON_MASS("Massa do Elétron", NonSI.ELECTRON_MASS),
	ONCA("Onça", NonSI.OUNCE),
	LIBRA("Libra", NonSI.POUND),
	TONELADAS_UK("Toneladas UK", NonSI.TON_UK),
	TONELADAS_US("Toneladas US", NonSI.TON_US);
	
	private String nome;
	private Unit<?> tipo;
	
	private TipoConversoesMassa(String nome, Unit<?> tipo) {
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
