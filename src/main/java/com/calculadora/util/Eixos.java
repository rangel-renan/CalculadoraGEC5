package com.calculadora.util;

import javafx.beans.binding.Bindings;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;

public class Eixos extends Pane {
	private NumberAxis eixoX;
	private NumberAxis eixoY;

	public Eixos(int largura, int altura, double xLow, double xHi, double xTickUnit, double yLow, double yHi,
			double yTickUnit) {
		setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
		setPrefSize(largura, altura);
		setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

		eixoX = new NumberAxis(xLow, xHi, xTickUnit);
		eixoX.setSide(Side.BOTTOM);
		eixoX.setMinorTickVisible(false);
		eixoX.setPrefWidth(largura);
		eixoX.setLayoutY(altura / 2);

		eixoY = new NumberAxis(yLow, yHi, yTickUnit);
		eixoY.setSide(Side.LEFT);
		eixoY.setMinorTickVisible(false);
		eixoY.setPrefHeight(altura);
		eixoY.layoutXProperty().bind(Bindings.subtract((largura / 2) + 1, eixoY.widthProperty()));

		getChildren().setAll(eixoX, eixoY);
	}
	
	public NumberAxis getEixoX() {
		return eixoX;
	}
	
	public NumberAxis getEixoY() {
		return eixoY;
	}
}
