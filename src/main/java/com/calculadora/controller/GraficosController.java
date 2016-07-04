package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.GraficosService;
import com.calculadora.service.GraficosServiceImpl;
import com.calculadora.util.equacao.Equacao;
import com.calculadora.util.excessoes.SintaxeEquacaoIncorretaException;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GraficosController implements Runnable {
	private MainApp mainApp;
	private Stage graficosStage;
	private BorderPane janelaGraficosLayout;
	
	private ConfigProperties label;
	private GraficosService graficosService;
	
	@FXML
	private TextField textFieldEquacao;
	
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
	
	public void show(MainApp _mainApp, Stage fracoesStage, BorderPane _janelaGraficosLayout, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.graficosStage = fracoesStage;
		this.janelaGraficosLayout = _janelaGraficosLayout;
		this.label = label;
		this.graficosService = new GraficosServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
		janelaGraficosLayout.setCenter(graficosService.gerarEixos(600, 400, -12, 12, -7, 7));
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		graficosStage.close();
		mainApp.clean(graficosStage, this);
	}
	
	@FXML
	private void handleGerar() {
		try {
			janelaGraficosLayout.setCenter(graficosService.gerarGrafico(new Equacao(textFieldEquacao.getText()), 600, 400, -12, 12, -7, 7));
		} catch (SintaxeEquacaoIncorretaException e) {
			e.printStackTrace();
		}
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
