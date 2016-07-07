package com.calculadora.util.enums;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesVolume {
	POLEGADA_CUB("Polegada Cúbica", NonSI.CUBIC_INCH),
	GALAO_UK("Galões britânicos", NonSI.GALLON_UK),
	GALAO_SECO_US("Galões americanos (Seco)", NonSI.GALLON_DRY_US),
	GALAO_LIQUIDO_US("Galões americanos (Líquido)", NonSI.GALLON_LIQUID_US),
	LITRO("Litro", NonSI.LITER),
	ONCA_LIQUIDA_UK("Onça Líquida Britânica", NonSI.OUNCE_LIQUID_UK),
	ONCA_LIQUIDA_US("Onça Líquida Americana", NonSI.OUNCE_LIQUID_US),
	METRO_CUBICO("Metro Cúbico", SI.CUBIC_METRE);
	
	private String nome;
	private Unit<?> tipo;
	
	private TipoConversoesVolume(String nome, Unit<?> tipo) {
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
