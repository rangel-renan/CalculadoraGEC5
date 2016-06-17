package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

@SuppressWarnings("unused")
public class MatrizController {
	private MainApp mainApp;
	private Stage matrizStage;
	
	private ConfigProperties label;
	
	@FXML
	private TextArea matrizA;
	
	public void show(MainApp mainApp, Stage matrizStage, ConfigProperties label) {
		this.label = label;
		this.mainApp = mainApp;
		this.matrizStage = matrizStage;
		
		this.matrizStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleVoltar();
			}
		});
	}
	
	
	@FXML
	private void handleVoltar() {
		mainApp.initRoot();
		matrizStage.close();
	}
	
	@FXML
	private void handleAjuda() {
		String[] valores = matrizA.getText().split(",");
		String[] linhas = matrizA.getText().split("\n");
		
		Double[][] matriz = new Double[matrizA.getText().split("\n").length][linhas[0].length()];
		int linha = 0;
		int coluna = 0;
		
		while (valores[coluna] != "\0") {
			if (!(valores[coluna] == "\n")) {
				matriz[linha][coluna] = new Double(valores[coluna]);
				coluna++;
			} else {
				linha++;
				coluna = 0;
			}
		}
		
//		
//		Double[][] matriz = new Double[linhas.length][colunas.length];
//		
//		for (int linha = 0; linha < linhas.length; linha++) {
//			for (int coluna = 0; coluna < linhas[linha].length(); coluna++) {
//				matriz[linha][coluna] = new Double(colunas[coluna]);
//			}
//		}
//		
		for (int l = 0; l < linhas.length; l++) {
			for (int c = 0; c < linhas[l].length(); c++) {
				System.out.println(matriz[l][c] + " ");
			}
			System.out.println("\n");
		}
		
	}
	
	public void setLabel(ConfigProperties label) {
		this.label = label;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setMatrizStage(Stage matrizStage) {
		this.matrizStage = matrizStage;
	}
}
