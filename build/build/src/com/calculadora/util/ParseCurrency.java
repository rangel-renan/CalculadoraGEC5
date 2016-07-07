package com.calculadora.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class ParseCurrency {
	public static BigDecimal parseCurrency(String value) throws ParseException {
        
        NumberFormat fmt = NumberFormat.getNumberInstance();

        ( (DecimalFormat) fmt).setParseBigDecimal(true);

        return (BigDecimal) fmt.parse(value);
	}
}
