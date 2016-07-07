package com.calculadora.service;

import java.math.BigDecimal;

/**
 * Interface que define os serviços para a classe RazoesTrigonometricas.
 * 
 * Interface <code>RazoesTrigonometricasService</code>
 * 
 * @author TiagoHenrique
 * @author YasminRomi
 * @version 1.0 (23/05/2016)
 * @version 1.2 (25/05/2016)
 *
 */
public interface RazaoTrigonometricaService {
	
	public BigDecimal calcular(BigDecimal valor, String nomeOperacao) throws NumberFormatException;
}
