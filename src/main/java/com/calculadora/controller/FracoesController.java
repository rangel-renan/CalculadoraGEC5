package com.calculadora.controller;

import java.math.BigDecimal;

import com.calculadora.MainApp;
import com.calculadora.model.Fracao;
import com.calculadora.service.FracoesService;
import com.calculadora.service.FracoesServiceImpl;
import com.calculadora.util.TipoOperacao;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FracoesController {
	private MainApp mainApp;
	private Stage fracoesStage;
	private FracoesService fracoesService;
	
	@FXML
	private Button btnCalcular;
	
	@FXML
	private ComboBox<TipoOperacao> comboTipoOperacoes;
	
	@FXML
	private TextField labelFirstFracaoNumerador;
	
	@FXML
	private TextField labelFirstFracaoDenominador;
	
	@FXML
	private TextField labelSecondFracaoNumerador;
	
	@FXML
	private TextField labelSecondFracaoDenominador;
	
	@FXML
	private Label labelResultFracaoNumerador;
	
	@FXML
	private Label labelResultFracaoDenominador;
	
	public void show(MainApp mainApp, Stage fracoesStage) {
		this.mainApp = mainApp;
		this.fracoesStage = fracoesStage;
		
		fracoesService = new FracoesServiceImpl();
		
		comboTipoOperacoes.setItems(FXCollections.observableArrayList(TipoOperacao.values()));
		comboTipoOperacoes.getSelectionModel().select(0);
		comboTipoOperacoes.getItems().remove(4);
		
		labelFirstFracaoNumerador.requestFocus();
		btnCalcular.setDisable(true);
		
		setListerners(labelFirstFracaoNumerador);
		setListerners(labelFirstFracaoDenominador);
		setListerners(labelSecondFracaoNumerador);
		setListerners(labelSecondFracaoDenominador);
		
		this.fracoesStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleVoltar();
			}
		});
	}
	
	private void setListerners(TextField label) {
		label.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (labelFirstFracaoNumerador.getText().length() == 0 
		    || labelFirstFracaoDenominador.getText().length() == 0
		    || labelSecondFracaoNumerador.getText().length() == 0
		    || labelSecondFracaoDenominador.getText().length() == 0) {
		    	btnCalcular.setDisable(true);
		    } else {
		    	btnCalcular.setDisable(false);
		    }
		});
	}
	
	@FXML
	private void handleCalcular() {
		Fracao firstFracao = new Fracao(new BigDecimal(labelFirstFracaoNumerador.getText()), new BigDecimal(labelFirstFracaoDenominador.getText()));
		Fracao secondFracao = new Fracao(new BigDecimal(labelSecondFracaoNumerador.getText()), new BigDecimal(labelSecondFracaoDenominador.getText()));;
		
		Fracao resultFracao = fracoesService.calcular(firstFracao, secondFracao, comboTipoOperacoes.getValue());
		
		labelResultFracaoNumerador.setText(resultFracao.getNumerador().toString());
		labelResultFracaoDenominador.setText(resultFracao.getDenominador().toString());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.initRoot();
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
