package com.calculadora.controller;

import com.calculadora.MainApp;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ConversoesController {
	private MainApp mainApp;
	private Stage conversoesStage;
	
	@FXML
	private Button btnCalcular;
	
	public void show(MainApp mainApp, Stage conversoesStage) {
		this.mainApp = mainApp;
		this.conversoesStage = conversoesStage;
		
		this.conversoesStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
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
		conversoesStage.close();
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setConversoesStage(Stage conversoesStage) {
		this.conversoesStage = conversoesStage;
	}
}
