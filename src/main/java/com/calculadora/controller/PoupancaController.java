package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.PoupancaService;
import com.calculadora.service.PoupancaServiceImpl;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PoupancaController implements Runnable {
	private MainApp mainApp;
	private Stage poupancaStage;

	private ConfigProperties label;
	private PoupancaService poupancaService;
	
	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			public void run() {
				poupancaStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage _poupancaStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.poupancaStage = _poupancaStage;
		this.label = label;
		this.poupancaService = new PoupancaServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		poupancaStage.close();
	}

	@FXML
	private void handleAjuda() {
		
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public ConfigProperties getLabel() {
		return label;
	}

	public void setLabel(ConfigProperties label) {
		this.label = label;
	}
	
	public PoupancaService getPoupancaService() {
		return poupancaService;
	}
	
	public void setPoupancaService(PoupancaService poupancaService) {
		this.poupancaService = poupancaService;
	}
	
	public Stage getPoupancaStage() {
		return poupancaStage;
	}
	
	public void setPoupancaStage(Stage poupancaStage) {
		this.poupancaStage = poupancaStage;
	}
}
