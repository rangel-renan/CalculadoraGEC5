package com.calculadora.controller;

import java.math.BigInteger;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.OperacoesBasicasService;
import com.calculadora.service.OperacoesBasicasServiceImpl;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PrimosController {
	private MainApp mainApp;
	private ConfigProperties label;
	private Stage primosStage;
	private OperacoesBasicasService operacoesBasicasService;
	
	@FXML
	private Button btnCalcular;
	
	@FXML
	private TextField textFieldInputPrimo;
	
	@FXML
	private Label labelIsPrimo;
	
	@FXML
	private Label labelExplicacao;
	
	public void show(MainApp mainApp, Stage primosStage, ConfigProperties label) {
		this.mainApp = mainApp;
		this.primosStage = primosStage;
		this.label = label;
		
		operacoesBasicasService = new OperacoesBasicasServiceImpl();
		
		btnCalcular.setDisable(true);
		textFieldInputPrimo.requestFocus();
		setListerners(textFieldInputPrimo);
		
		this.primosStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleVoltar();
			}
		});
	}
	
	private void setListerners(TextField label) {
		label.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (textFieldInputPrimo.getText().length() == 0) {
		    	btnCalcular.setDisable(true);
		    	labelIsPrimo.setText("");
		    	labelExplicacao.setText("");
		    } else {
		    	btnCalcular.setDisable(false);
		    }
		});
	}
	
	@FXML
	private void handleCalcular() {
		BigInteger numPrimo = new BigInteger(textFieldInputPrimo.getText());
		if (operacoesBasicasService.isPrimo(numPrimo)) {
			showIsPrimo();
		} else {
			showNotIsPrimo(numPrimo);
		}
	}
	
	private void showIsPrimo() {
		labelIsPrimo.setText(label.getString("root.tab.arquivo.primo.isPrimoFristPt") + 
				textFieldInputPrimo.getText() + 
				label.getString("root.tab.arquivo.primo.isPrimoSecondPt"));
	}
	
	private void showNotIsPrimo(BigInteger numPrimo) {
		labelIsPrimo.setText(label.getString("root.tab.arquivo.primo.isPrimoFristPt") + 
							textFieldInputPrimo.getText() + 
							label.getString("root.tab.arquivo.primo.notPrimoSecondPt"));

		if (numPrimo.compareTo(new BigInteger("1")) == 0) {
			labelExplicacao.setText(label.getString("root.tab.arquivo.primo.isPrimoFristPt") + 
									textFieldInputPrimo.getText() + 
									label.getString("root.tab.arquivo.primo.notPrimeExplicOneSecond"));
			return;
		} else if (numPrimo.remainder(new BigInteger("2")).compareTo(new BigInteger("0")) == 0) 
			labelExplicacao.setText(label.getString("root.tab.arquivo.primo.isPrimoFristPt") + 
									textFieldInputPrimo.getText() + 
									label.getString("root.tab.arquivo.primo.notPrimeExplicPar"));
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
