package com.calculadora.util.enums;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesTempo {
	SEGUNDO("Segundos", SI.SECOND),
	MINUTO("Minutos", NonSI.MINUTE),
	HORA("Horas", NonSI.HOUR),
	DIA("Dia", NonSI.DAY),
	DIA_SIDERAL("Dia Sideral", NonSI.DAY_SIDEREAL),
	SEMANA("Semana", NonSI.WEEK),
	MES("Mês", NonSI.MONTH),
	ANO("Ano", NonSI.YEAR),
	ANO_CALENDARIO("Ano-calendário", NonSI.YEAR_CALENDAR),
	ANO_SIDERAL("Ano Sideral", NonSI.YEAR_SIDEREAL),
	NANOS_SEGUNDO("Nanossegundos", SI.NANO(SI.SECOND)),
	MICROS_SEGUNDO("Microssegundos", SI.MICRO(SI.SECOND)),
	MILIS_SEGUNDO("Milissegundos", SI.MILLI(SI.SECOND));
	
	private String nome;
	private Unit<?> tipo;
	
	private TipoConversoesTempo(String nome, Unit<?> tipo) {
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
