package com.calculadora.controller;

import com.calculadora.MainApp;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PorcentagensController {
	private MainApp mainApp;
	private Stage porcentagensStage;
	
	public void show(MainApp mainApp, Stage porcentagensStage) {
		this.mainApp = mainApp;
		this.porcentagensStage = porcentagensStage;
		
		this.porcentagensStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleVoltar();
			}
		});
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
		mainApp.initRoot();
		porcentagensStage.close();
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setPorcentagensStage(Stage porcentagensStage) {
		this.porcentagensStage = porcentagensStage;
	}
	
}
