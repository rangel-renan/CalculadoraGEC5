package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.FinanceiraService;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class JurosController {
	private MainApp mainApp;
	private Stage jurosStage;

	private ConfigProperties label;
	private FinanceiraService financeiraService;
	
	public void show(MainApp mainApp, Stage jurosStage, ConfigProperties label,
			FinanceiraService financeiraService) {
		this.mainApp = mainApp;
		this.jurosStage = jurosStage;
		this.label = label;
		this.financeiraService = financeiraService;
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.initRoot();
		jurosStage.close();
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

	public FinanceiraService getFinanceiraService() {
		return financeiraService;
	}

	public void setFinanceiraService(FinanceiraService financeiraService) {
		this.financeiraService = financeiraService;
	}
	
	public void setJurosStage(Stage jurosStage) {
		this.jurosStage = jurosStage;
	}
	
	public Stage getJurosStage() {
		return jurosStage;
	}
}
