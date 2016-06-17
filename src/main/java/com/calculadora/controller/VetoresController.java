package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

@SuppressWarnings("unused")
public class VetoresController {
	private MainApp mainApp;
	private Stage vetorStage;
	
	private ConfigProperties label;
	
	public void show(MainApp mainApp, Stage vetorStage, ConfigProperties label) {
		this.label = label;
		this.mainApp = mainApp;
		this.vetorStage = vetorStage;
		
		this.vetorStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleVoltar();
			}
		});
	}
	@FXML
	private void handleVoltar() {
		mainApp.initRoot();
		vetorStage.close();
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setLabel(ConfigProperties label) {
		this.label = label;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setVetorStage(Stage vetorStage) {
		this.vetorStage = vetorStage;
	}
}
