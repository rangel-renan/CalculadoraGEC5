package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.util.Idioma;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

@SuppressWarnings("unused")
public class RootLayoutController implements Runnable {
	
	private MainApp mainApp;
	private Stage rootStage;
	
	private ConfigProperties label;
	
	public RootLayoutController() {
		label = ConfigProperties.getInstance(Idioma.Portugues);
	}

	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			public void run() {
				rootStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleSair();
					}
				});
			}
		});
	}
	
	public void show(ConfigProperties label, MainApp mainApp, Stage _rootStage) {
		this.label = label;
		this.rootStage = _rootStage;
		this.mainApp = mainApp;
		
		run();
	}
	
	@FXML
	private void handleSobre() {
		mainApp.ocultarRoot();
		mainApp.initSobre();
	}
	
	@FXML
	private void handleSair() {
		mainApp.exitAplicacao();
	}
	
	@FXML
	private void handleOpcoes() {
		rootStage.close();
		mainApp.initOpcoes();
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	@FXML
	private void handleConversoes() {
		mainApp.ocultarRoot();
		mainApp.initConversoes();
	}
	
	@FXML
	private void handleFracoes() {
		mainApp.ocultarRoot();
		mainApp.initFracoes();
	}
	
	@FXML
	private void handlePorcentagens() {
		mainApp.ocultarRoot();
		mainApp.initPorcentagens();
	}
	
	@FXML
	private void handlePrimos() {
		mainApp.ocultarRoot();
		mainApp.initPrimos();
	}
	
	@FXML
	private void handleRegraTres() {
		mainApp.ocultarRoot();
		mainApp.initRegraTres();
	}
	
	@FXML
	private void handleMatriz() {
		mainApp.ocultarRoot();
		mainApp.initMatriz();
	}
	
	@FXML
	private void handleVetores() {
		mainApp.ocultarRoot();
		mainApp.initVetores();;
	}
	
	@FXML
	private void handleFinanciamento() {
		mainApp.ocultarRoot();
		mainApp.initFinanciamento();
	}
	
	@FXML
	private void handleInvestimento() {
		mainApp.ocultarRoot();
		mainApp.initInvestimento();
	}
	
	@FXML
	private void handleJuros() {
		mainApp.ocultarRoot();
		mainApp.initJuros();
	}
	
	@FXML
	private void handleHipoteca() {
		mainApp.ocultarRoot();
		mainApp.initHipoteca();
	}
	
	@FXML
	private void handleCartaoCredito() {
		mainApp.ocultarRoot();
		mainApp.initCartaoCredito();
	}
	
	@FXML
	private void handlePoupanca() {
		mainApp.ocultarRoot();
		mainApp.initPoupanca();
	}
	
	@FXML
	private void handleConversorMoedas() {
		mainApp.ocultarRoot();
		mainApp.initConversorMoedas();
	}
	
	public void setRootStage(Stage rootStage) {
		this.rootStage = rootStage;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setLabel(ConfigProperties label) {
		this.label = label;
	}

}
