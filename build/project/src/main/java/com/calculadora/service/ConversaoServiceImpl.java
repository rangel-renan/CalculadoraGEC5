package com.calculadora.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.measure.converter.UnitConverter;
import javax.measure.unit.Unit;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.calculadora.util.excessoes.ImpossivelConverterException;

import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ConversaoServiceImpl implements ConversaoService {
	private String resultado;

	@Override
	public BigDecimal converter(BigDecimal valor, Unit<?> tipoInicial, Unit<?> tipoFinal) throws NumberFormatException {
		UnitConverter toConverter = tipoInicial.getConverterTo(tipoFinal);
		return new BigDecimal(toConverter.convert(valor.doubleValue()));
	}

	public BigDecimal converterMoeda(BigDecimal valor, String firstMoeda, String moedaResultante)
			throws NumberFormatException, MalformedURLException, IOException, ImpossivelConverterException, ConnectException {
		String query = "https://www.google.com/finance/converter?a=" + valor.doubleValue() + "&from=" + firstMoeda
				+ "&to=" + moedaResultante;
		URL url = new URL(query);
		InputStreamReader stream = new InputStreamReader(url.openStream());
		BufferedReader in = new BufferedReader(stream);
		StringBuilder str = new StringBuilder();
		String temp = "";

		while ((temp = in.readLine()) != null && (in.readLine().contains("select") || in.readLine().contains("option"))) {
			str.append(temp);
		}

		WebView view = new WebView();
		WebEngine engine = view.getEngine();
		engine.loadContent(str.toString());
		engine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
			if (newState == State.SUCCEEDED) {
				Document doc = engine.getDocument();
				Element el = doc.getElementById("currency_converter_result");
				resultado = el.getTextContent();
			}
		});
		
		if (resultado.equals("Could not convert.")) 
			throw new ImpossivelConverterException("Impossível de se Converter.");
		
		return new BigDecimal(resultado.split("= ")[1].split(" " + moedaResultante)[0]);
	}
	

}
