package com.calculadora.controller;

import java.math.BigDecimal;

import com.calculadora.MainApp;
import com.calculadora.service.OperacoesBasicasService;
import com.calculadora.service.OperacoesBasicasServiceImpl;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RegraTresController implements Runnable {
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
	private TextField textFieldResult;
	
	@FXML
	private Button btnCalcular;
	
	@Override
	public void run() {
		textFieldValorA.requestFocus();
		btnCalcular.setDisable(true);
		
		setListener(textFieldValorA);
		setListener(textFieldValorB);
		setListener(textFieldNovoValor);
		
		Platform.runLater(new Runnable() {
			public void run() {
				regraTresStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage regraTresStage) {
		this.mainApp = _mainApp;
		this.regraTresStage = regraTresStage;
		this.operacoesBasicasService = new OperacoesBasicasServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	private void setListener(TextField textField) {
		Platform.runLater(new Runnable() {
			public void run() {
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
			textFieldResult.setText("");
			return;
		}
		
		textFieldResult.setText(result.toString());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		regraTresStage.close();
		mainApp.clean(regraTresStage, this);
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
