package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GraficosController implements Runnable {
	private MainApp mainApp;
	private Stage graficosStage;
	
	private ConfigProperties label;
	
	@Override
	public void run() {
		
		Platform.runLater(new Runnable() {
			public void run() {
				graficosStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage fracoesStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.graficosStage = fracoesStage;
		this.label = label;
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		graficosStage.close();
		mainApp.clean(graficosStage, this);
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public Stage getGraficosStage() {
		return graficosStage;
	}

	public void setGraficosStage(Stage graficosStage) {
		this.graficosStage = graficosStage;
	}

	public ConfigProperties getLabel() {
		return label;
	}

	public void setLabel(ConfigProperties label) {
		this.label = label;
	}

	public MainApp getMainApp() {
		return mainApp;
	}
	
	
	
}
