package com.calculadora.util;

import javafx.beans.binding.Bindings;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;

public class Eixos extends Pane {
	private NumberAxis eixoX;
	private NumberAxis eixoY;
	private double xLow;
	private double xHi;

	public Eixos(int largura, int altura, double xLow, double xHi, double xTickUnit, double yLow, double yHi,
			double yTickUnit) {
		this.xLow = xLow;
		this.xHi = xHi;
		
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

	public double getxLow() {
		return xLow;
	}

	public void setxLow(double xLow) {
		this.xLow = xLow;
	}

	public double getxHi() {
		return xHi;
	}

	public void setxHi(double xHi) {
		this.xHi = xHi;
	}
	
	
}
