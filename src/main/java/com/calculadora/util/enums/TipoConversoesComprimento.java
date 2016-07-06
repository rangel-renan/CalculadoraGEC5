package com.calculadora.util.enums;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesComprimento {
	QUILOMETRO("Quilômetro", SI.KILOMETER),
	HECTOMETRO("Hectômetro", SI.HECTO(SI.METER)),
	DECAMETRO("Decâmetro", SI.DEKA(SI.METER)),
	METRO("Metro", SI.METER),
	DECIMETRO("Decímetro", SI.DECI(SI.METER)),
	CENTIMETRO("Centímetro", SI.METER),
	MILIMETRO("Milímetro", SI.METER),
	ANGSTROM("Angstrom", NonSI.ANGSTROM),
	UNID_ASTRONOMICA("Unidade Astronômica", NonSI.ASTRONOMICAL_UNIT),
	PONSTOS_COMPUTADOR("Pontos de Computador", NonSI.COMPUTER_POINT),
	PES("Pés", NonSI.FOOT),
	POLEGADA("Polegada", NonSI.INCH),
	ANO_LUZ("Ano-Luz", NonSI.LIGHT_YEAR),
	MILHAS("Milhas", NonSI.MILE),
	MILHA_NAUTICA("Milhas Náuticas", NonSI.NAUTICAL_MILE),
	PARSEC("Parsec", NonSI.PARSEC),
	PIXEL("Pixel", NonSI.PIXEL),
	PONTO("Ponto", NonSI.POINT),
	JARDA("Jarda", NonSI.YARD);
	
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
