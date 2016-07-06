package com.calculadora.util.enums;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesVelocidade {
	KILOMETRO_HORA("Kilômetro por Hora", NonSI.KILOMETERS_PER_HOUR),
	MILHAS_HORA("Milhas por Hora", NonSI.MILES_PER_HOUR),
	NO("Nó", NonSI.KNOT),
	VELOCIDADE_LUZ("Velocidade da Luz", NonSI.C),
	NUMERO_MACH("Numero de Mach", NonSI.MACH),
	METROS_SEGUNDO("Metros por Segundo", SI.METERS_PER_SECOND);
	
	private String nome;
	private Unit<?> tipo;
	
	private TipoConversoesVelocidade(String nome, Unit<?> tipo) {
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
		
		for (TipoConversoesVelocidade t : TipoConversoesVelocidade.values()) {
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
