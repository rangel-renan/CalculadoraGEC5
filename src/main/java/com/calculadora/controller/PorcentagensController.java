package com.calculadora.controller;

import java.math.BigDecimal;

import com.calculadora.MainApp;
import com.calculadora.service.PorcentagemService;
import com.calculadora.service.PorcentagemServiceImpl;
import com.calculadora.util.enums.TipoCalPorcentagem;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PorcentagensController implements Runnable {
	private MainApp mainApp;
	private Stage porcentagensStage;
	private PorcentagemService porcentagemService;
	
	@FXML
	private TextField textFieldQuantPorcent;
	
	@FXML
	private TextField textFieldQuantValor;
	
	@FXML
	private TextField textFieldTotalPorcent;
	
	@FXML
	private TextField textFieldTotalValor;
	
	@FXML
	private TextField textFieldPorcValorTotal;
	
	@FXML
	private TextField textFieldPorcValor;
	
	@FXML
	private TextField textFieldResultQuantidade;
	
	@FXML
	private TextField textFieldResultTotal;
	
	@FXML
	private TextField textFieldResultPorcentagem;
	
	@FXML
	private Button btnCalcularQuantidade;
	
	@FXML
	private Button btnCalcularTotal;
	
	@FXML
	private Button btnCalcularPorcentagem;
	
	@Override
	public void run() {
		btnCalcularQuantidade.setDisable(true);
		btnCalcularTotal.setDisable(true);
		btnCalcularPorcentagem.setDisable(true);
		textFieldQuantPorcent.requestFocus();
		
		setListeners(textFieldQuantPorcent, textFieldQuantPorcent, textFieldQuantValor, btnCalcularQuantidade);
		setListeners(textFieldQuantValor, textFieldQuantPorcent, textFieldQuantValor, btnCalcularQuantidade);
		setListeners(textFieldTotalPorcent, textFieldTotalPorcent, textFieldTotalValor, btnCalcularTotal);
		setListeners(textFieldTotalValor, textFieldTotalPorcent, textFieldTotalValor, btnCalcularTotal);
		setListeners(textFieldPorcValorTotal, textFieldPorcValorTotal, textFieldPorcValor, btnCalcularPorcentagem);
		setListeners(textFieldPorcValor, textFieldPorcValorTotal, textFieldPorcValor, btnCalcularPorcentagem);
		
		Platform.runLater(new Runnable() {
			public void run() {
				porcentagensStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage porcentagensStage) {
		this.mainApp = _mainApp;
		this.porcentagensStage = porcentagensStage;
		this.porcentagemService = new PorcentagemServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	private void setListeners(TextField textField, TextField labelPorcentagem, TextField labelValor, Button btnCalcular) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (labelPorcentagem.getText().length() == 0 || 
				    	labelValor.getText().length() == 0) {
				    	btnCalcular.setDisable(true);
				    } else {
				    	btnCalcular.setDisable(false);
				    }
				});
			}
		});
	}
	
	@FXML
	private void handleCalcularPorcentagem() {
		calcular(textFieldPorcValor, textFieldPorcValorTotal, textFieldResultPorcentagem, TipoCalPorcentagem.PORCENTAGEM);
	}
	
	@FXML
	private void handleCalcularQuantidade() {
		calcular(textFieldQuantValor, textFieldQuantPorcent, textFieldResultQuantidade, TipoCalPorcentagem.QUANTIDADE);
	}
	
	@FXML
	private void handleCalcularTotal() {
		calcular(textFieldTotalValor, textFieldTotalPorcent, textFieldResultTotal, TipoCalPorcentagem.TOTAL);
	}
	
	private void calcular(TextField valor, TextField porcentagem, TextField textFieldResult, TipoCalPorcentagem tipoCalculo) {
		BigDecimal result = null;
		
		try {
			result = porcentagemService.calcular(new BigDecimal(valor.getText()), 
					porcentagem.getText(), tipoCalculo);
		} catch (NumberFormatException e) {
			valor.setText("");
			porcentagem.setText("");
			valor.requestFocus();
			textFieldResult.setText("");
			return;
		}
		
		textFieldResult.setText(result.toString());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		porcentagensStage.close();
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setPorcentagensStage(Stage porcentagensStage) {
		this.porcentagensStage = porcentagensStage;
	}
	
}
