package com.calculadora.controller;

import java.math.BigDecimal;

import com.calculadora.MainApp;
import com.calculadora.service.OperacoesBasicasService;
import com.calculadora.service.OperacoesBasicasServiceImpl;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RegraTresController {
	private MainApp mainApp;
	private Stage regraTresStage;
	private OperacoesBasicasService operacoesBasicasService;
	
	@FXML
	private TextField textFieldValorA;
	
	@FXML
	private TextField textFieldValorB;
	
	@FXML
	private TextField textFieldNovoValor;
	
	@FXML
	private Label labelResult;
	
	@FXML
	private Button btnCalcular;
	
	public void show(MainApp mainApp, Stage regraTresStage) {
		this.mainApp = mainApp;
		this.regraTresStage = regraTresStage;
		
		operacoesBasicasService = new OperacoesBasicasServiceImpl();
		
		textFieldValorA.requestFocus();
		btnCalcular.setDisable(true);
		
		setListener(textFieldValorA);
		setListener(textFieldValorB);
		setListener(textFieldNovoValor);
		
		this.regraTresStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleVoltar();
			}
		});
	}
	
	private void setListener(TextField textField) {
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (textFieldValorA.getText().length() == 0 
		    || textFieldValorB.getText().length() == 0
		    || textFieldNovoValor.getText().length() == 0) {
		    	btnCalcular.setDisable(true);
		    } else {
		    	btnCalcular.setDisable(false);
		    }
		});
	}
	
	@FXML
	private void handleCalcular() {
		BigDecimal result;
		
		try {
		result = operacoesBasicasService.calcularRegraTres(new BigDecimal(textFieldValorA.getText()), 
												new BigDecimal(textFieldValorB.getText()), 
												new BigDecimal(textFieldNovoValor.getText()));
		} catch (NumberFormatException e) {
			textFieldValorA.setText("");
			textFieldValorB.setText("");
			textFieldNovoValor.setText("");
			labelResult.setText("");
			return;
		}
		
		labelResult.setText(result.toString());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.initRoot();
		regraTresStage.close();
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setRegraTresStage(Stage regraTresStage) {
		this.regraTresStage = regraTresStage;
	}
}
