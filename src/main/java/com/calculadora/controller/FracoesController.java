package com.calculadora.controller;

import com.calculadora.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FracoesController {
	private MainApp mainApp;
	private Stage fracoesStage;
	
	@FXML
	private Button btnCalcularPorcentagem;
	
	@FXML
	private Button btnCalcularQuantidade;
	
	@FXML
	private Button btnCalcularTotal;
	
	public void show(MainApp mainApp, Stage fracoesStage) {
		this.mainApp = mainApp;
		this.fracoesStage = fracoesStage;
	}

	@FXML
	private void handleCalcularPorcentagem() {
		
	}
	
	@FXML
	private void handleCalcularQuantidade() {
		
	}
	
	@FXML
	private void handleCalcularTotal() {
		
	}
	
	@FXML
	private void handleVoltar() {
		
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setFracoesStage(Stage fracoesStage) {
		this.fracoesStage = fracoesStage;
	}
}
