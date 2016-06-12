package com.calculadora.controller;

import java.math.BigDecimal;

import com.calculadora.MainApp;
import com.calculadora.service.PorcentagemService;
import com.calculadora.service.PorcentagemServiceImpl;
import com.calculadora.util.TipoCalPorcentagem;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PorcentagensController {
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
	private Label labelResultQuantidade;
	
	@FXML
	private Label labelResultTotal;
	
	@FXML
	private Label labelResultPorcentagem;
	
	@FXML
	private Button btnCalcularQuantidade;
	
	@FXML
	private Button btnCalcularTotal;
	
	@FXML
	private Button btnCalcularPorcentagem;
	
	
	public void show(MainApp mainApp, Stage porcentagensStage) {
		this.mainApp = mainApp;
		this.porcentagensStage = porcentagensStage;
		
		porcentagemService = new PorcentagemServiceImpl();
		
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
		
		this.porcentagensStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleVoltar();
			}
		});
	}
	
	private void setListeners(TextField label, TextField labelPorcentagem, TextField labelValor, Button btnCalcular) {
		label.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (labelPorcentagem.getText().length() == 0 || 
		    	labelValor.getText().length() == 0) {
		    	btnCalcular.setDisable(true);
		    } else {
		    	btnCalcular.setDisable(false);
		    }
		});
	}
	
	@FXML
	private void handleCalcularPorcentagem() {
		calcular(textFieldPorcValor, textFieldPorcValorTotal, labelResultPorcentagem, TipoCalPorcentagem.PORCENTAGEM);
	}
	
	@FXML
	private void handleCalcularQuantidade() {
		calcular(textFieldQuantValor, textFieldQuantPorcent, labelResultQuantidade, TipoCalPorcentagem.QUANTIDADE);
	}
	
	@FXML
	private void handleCalcularTotal() {
		calcular(textFieldTotalValor, textFieldTotalPorcent, labelResultTotal, TipoCalPorcentagem.TOTAL);
	}
	
	private void calcular(TextField valor, TextField porcentagem, Label labelResult, TipoCalPorcentagem tipoCalculo) {
		BigDecimal result = null;
		
		try {
			result = porcentagemService.calcular(new BigDecimal(valor.getText()), 
					porcentagem.getText(), tipoCalculo);
		} catch (NumberFormatException e) {
			valor.setText("");
			porcentagem.setText("");
			valor.requestFocus();
			labelResult.setText("");
			return;
		}
		
		labelResult.setText(result.toString());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.initRoot();
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
