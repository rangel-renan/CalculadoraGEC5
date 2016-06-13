package com.calculadora.service;

import java.math.BigDecimal;

import com.calculadora.util.TipoOperacaoTrigono;

/**
 * Classe de Implementação da Interface RazoesTrigonometricasService.
 * 
 * Classe <code>RazoesTrigonometricasServiceImpl</code>
 * 
 * @author TiagoHenrique
 * @author YasminRomi
 * @version 1.0(23/05/2016)
 * @version 1.2 (25/05/2016)
 */
public class RazaoTrigonometricaServiceImpl implements RazaoTrigonometricaService {
	
	@Override
	public BigDecimal calcular(BigDecimal valor, String nomeOperacao) throws NumberFormatException {
		TipoOperacaoTrigono operacao = TipoOperacaoTrigono.getOperacao(nomeOperacao);
		
		switch (operacao) {
			case COSSENO:
				return calcularCosseno(valor.doubleValue());
			case SENO:
				return calcularSeno(valor.doubleValue());
			case TANGENTE:
				return calcularTangente(valor.doubleValue());
			case ARCO_COSSENO:
				return calcularArcoCosseno(valor.doubleValue());
			case ARCO_SENO:
				return calcularArcoSeno(valor.doubleValue());
			case ARCO_TANGENTE:
				return calcularArcoTangente(valor.doubleValue());
			case COSSECANTE:
				return calcularCossecante(valor.doubleValue());
			case COTANGENTE:	
				return calcularCotangente(valor.doubleValue());
			case SECANTE:
				return calcularSecante(valor.doubleValue());
		}
		
		return null;
	}
	
	/**
	 * Calcula o cosseno a partir de um valor em radiano.
	 * 
	 * @param valor
	 *  			valor
	 * @return cosseno
	 */
	private BigDecimal calcularCosseno(Double valor) throws NumberFormatException {
		return new BigDecimal(Math.cos(valor));
	}
	
	/**
	 * Calcula a função inversa do cosseno a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return arco-cosseno
	 */
	private BigDecimal calcularArcoCosseno(Double valor) throws NumberFormatException {
		return new BigDecimal(Math.acos(valor));
	}
	
	/**
	 * Calcula a secante a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return secante
	 */
	private BigDecimal calcularSecante(Double valor) throws NumberFormatException {
		return new BigDecimal((1 / Math.cos(valor)));
	}

	/**
	 * Calcula o seno a partir de um valor em radiano.
	 * 
	 * @param valor
	 *  			valor
	 * @return seno
	 */
	private BigDecimal calcularSeno(Double valor) throws NumberFormatException {
		return new BigDecimal(Math.sin(valor));
	}

	/**
	 * Calcula o arcosseno a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return arco-seno
	 */
	private BigDecimal calcularArcoSeno(Double valor) throws NumberFormatException {
		return new BigDecimal(Math.asin(valor));
	}
	
	/**
	 * Calcula a cossecante a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return cossecante
	 */
	private BigDecimal calcularCossecante(Double valor) throws NumberFormatException {
		return new BigDecimal((1 / Math.sin(valor)));
	}

	/**
	 * Calcula a tangente a partir a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return tangente
	 */
	private BigDecimal calcularTangente(Double valor) throws NumberFormatException {
		return new BigDecimal(Math.tan(valor));
	}
	
	/**
	 * Calcula a função inversa da tangente a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return arco-tangente
	 */
	private BigDecimal calcularArcoTangente(Double valor) throws NumberFormatException {
		return new BigDecimal(Math.atan(valor));
	}

	/**
	 * Calcula a cotangente a partir da tangente
	 * 
	 * @param valor
	 *  			valor
	 * @return cotangente
	 */
	private BigDecimal calcularCotangente(Double valor) throws NumberFormatException {
		return new BigDecimal((1 / Math.tan(valor)));
	}

}
