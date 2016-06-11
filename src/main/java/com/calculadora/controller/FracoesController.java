package com.calculadora.controller;

import com.calculadora.MainApp;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
		
		this.fracoesStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleVoltar();
			}
		});
	}

	@FXML
	private void handleCalcular() {
		
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.initRoot();
		fracoesStage.close();
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
