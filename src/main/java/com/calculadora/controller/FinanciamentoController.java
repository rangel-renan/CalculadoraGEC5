package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.FinanciamentoService;
import com.calculadora.service.FinanciamentoServiceImpl;
import com.calculadora.util.enums.TipoMoedas;
import com.calculadora.util.enums.TipoPeriodos;
import com.calculadora.util.enums.TipoPrestacao;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FinanciamentoController implements Runnable {
	private MainApp mainApp;
	private Stage financiamentoStage;

	private ConfigProperties label;
	private FinanciamentoService financiamentoService;

	@FXML
	private ComboBox<TipoMoedas> comboMoedas;
	
	@FXML
	private ComboBox<TipoPrestacao> comboTipoPrestacao;
	
	@FXML
	private ComboBox<TipoPeriodos> comboTipoPeridos;
	
	@FXML
	private TextField textFieldSimboloMoeda;
	
	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			public void run() {
				financiamentoStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
		
		Platform.runLater(new Runnable() {
			public void run() { 
				comboMoedas.setItems(FXCollections.observableArrayList(TipoMoedas.values())); 
				comboMoedas.getSelectionModel().select(0);
			}
		});
		
		Platform.runLater(new Runnable() {
			public void run() { 
				comboTipoPrestacao.setItems(FXCollections.observableArrayList(TipoPrestacao.values())); 
				comboTipoPrestacao.getSelectionModel().select(0);
			}
		});
		
		Platform.runLater(new Runnable() {
			public void run() { 
				comboTipoPeridos.setItems(FXCollections.observableArrayList(TipoPeriodos.values())); 
				comboTipoPeridos.getSelectionModel().select(0);
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage _financiamentoStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.financiamentoStage = _financiamentoStage;
		this.label = label;
		this.financiamentoService = new FinanciamentoServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	@FXML
	private void hiddenMoeda() {
		textFieldSimboloMoeda.setText(comboMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		financiamentoStage.close();
		mainApp.clean(financiamentoStage, this);
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
	
	public FinanciamentoService getFinanciamentoService() {
		return financiamentoService;
	}
	
	public void setFinanciamentoService(FinanciamentoService financiamentoService) {
		this.financiamentoService = financiamentoService;
	}
	
	public void setFinanciamentoStage(Stage financiamentoStage) {
		this.financiamentoStage = financiamentoStage;
	}
	
	public Stage getFinanciamentoStage() {
		return financiamentoStage;
	}
}
