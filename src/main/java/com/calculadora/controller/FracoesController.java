package com.calculadora.controller;

import java.math.BigDecimal;

import com.calculadora.MainApp;
import com.calculadora.model.Fracao;
import com.calculadora.service.FracoesService;
import com.calculadora.service.FracoesServiceImpl;
import com.calculadora.util.enums.TipoOperacao;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FracoesController implements Runnable {
	private MainApp mainApp;
	private Stage fracoesStage;
	private FracoesService fracoesService;
	
	@FXML
	private Button btnCalcular;
	
	@FXML
	private ComboBox<TipoOperacao> comboTipoOperacoes;
	
	@FXML
	private TextField textFieldFirstFracaoNumerador;
	
	@FXML
	private TextField textFieldFirstFracaoDenominador;
	
	@FXML
	private TextField textFieldSecondFracaoNumerador;
	
	@FXML
	private TextField textFieldSecondFracaoDenominador;
	
	@FXML
	private TextField textFieldResultFracaoNumerador;
	
	@FXML
	private TextField textFieldResultFracaoDenominador;
	
	@Override
	public void run() {
		textFieldFirstFracaoNumerador.requestFocus();
		btnCalcular.setDisable(true);
		
		setListerners(textFieldFirstFracaoNumerador);
		setListerners(textFieldFirstFracaoDenominador);
		setListerners(textFieldSecondFracaoNumerador);
		setListerners(textFieldSecondFracaoDenominador);
		
		Platform.runLater(new Runnable() {
			public void run() {
				fracoesStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage fracoesStage) {
		this.mainApp = _mainApp;
		this.fracoesStage = fracoesStage;
		this.fracoesService = new FracoesServiceImpl();
		
		Platform.runLater(new Runnable() {
			public void run() { 
				comboTipoOperacoes.setItems(FXCollections.observableArrayList(TipoOperacao.values()));
				comboTipoOperacoes.getSelectionModel().select(0);
				comboTipoOperacoes.getItems().remove(4);
			}
		});
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	private void setListerners(TextField textField) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldFirstFracaoNumerador.getText().length() == 0 
				    || textFieldFirstFracaoDenominador.getText().length() == 0
				    || textFieldSecondFracaoNumerador.getText().length() == 0
				    || textFieldSecondFracaoDenominador.getText().length() == 0) {
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
		Fracao firstFracao = new Fracao(new BigDecimal(textFieldFirstFracaoNumerador.getText()), new BigDecimal(textFieldFirstFracaoDenominador.getText()));
		Fracao secondFracao = new Fracao(new BigDecimal(textFieldSecondFracaoNumerador.getText()), new BigDecimal(textFieldSecondFracaoDenominador.getText()));;
		
		Fracao resultFracao = fracoesService.calcular(firstFracao, secondFracao, comboTipoOperacoes.getValue());
		
		textFieldResultFracaoNumerador.setText(resultFracao.getNumerador().toString());
		textFieldResultFracaoDenominador.setText(resultFracao.getDenominador().toString());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		fracoesStage.close();
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setFracoesStage(Stage fracoesStage) {
		this.fracoesStage = fracoesStage;
	}
}
