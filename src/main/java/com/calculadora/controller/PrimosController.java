package com.calculadora.controller;

import com.calculadora.MainApp;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PrimosController {
	private MainApp mainApp;
	private Stage primosStage;
	
	@FXML
	private Button btnCalcular;
	
	public void show(MainApp mainApp, Stage primosStage) {
		this.mainApp = mainApp;
		this.primosStage = primosStage;
		
		this.primosStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
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
		primosStage.close();
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setPrimosStage(Stage primosStage) {
		this.primosStage = primosStage;
	}
}
