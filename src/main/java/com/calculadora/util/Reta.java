package com.calculadora.util;

import com.calculadora.model.Coordenadas;
import com.calculadora.model.Equacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

public class Reta extends Pane {
	private ObservableList<Coordenadas> listCoordenadas;
	
	public Reta(Equacao equacao, double xMin, double xMax, double xInc, Eixos eixos) {
		listCoordenadas = FXCollections.observableArrayList();
		
		Path path = new Path();
		path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
		path.setStrokeWidth(2);

		path.setClip(new Rectangle(0, 0, eixos.getPrefWidth(), eixos.getPrefHeight()));

		double x = xMin;

		double y = equacao.evaluate(x);

		path.getElements().add(new MoveTo(mapX(x, eixos), mapY(y, eixos)));

		x += xInc;
		while (x < xMax) {
			y = equacao.evaluate(x);
			path.getElements().add(new LineTo(mapX(x, eixos), mapY(y, eixos)));

			listCoordenadas.add(new Coordenadas(x, y));
			
			x += xInc;
		}

		setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
		setPrefSize(eixos.getPrefWidth(), eixos.getPrefHeight());
		setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

		getChildren().setAll(eixos, path);
	}
	
	private double mapX(double x, Eixos axes) {
		double tx = axes.getPrefWidth() / 2;
		double sx = axes.getPrefWidth() / (axes.getEixoX().getUpperBound() - axes.getEixoX().getLowerBound());

		return x * sx + tx;
	}

	private double mapY(double y, Eixos axes) {
		double ty = axes.getPrefHeight() / 2;
		double sy = axes.getPrefHeight() / (axes.getEixoY().getUpperBound() - axes.getEixoY().getLowerBound());

		return -y * sy + ty;
	}

	public ObservableList<Coordenadas> getListCoordenadas() {
		return listCoordenadas;
	}

	public void setListCoordenadas(ObservableList<Coordenadas> listCoordenadas) {
		this.listCoordenadas = listCoordenadas;
	}
	
}
