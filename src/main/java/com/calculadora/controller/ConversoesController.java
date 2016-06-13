package com.calculadora.controller;

import java.math.BigDecimal;

import com.calculadora.MainApp;
import com.calculadora.service.ConversaoService;
import com.calculadora.service.ConversaoServiceImpl;
import com.calculadora.util.TipoConversoes;
import com.calculadora.util.TipoConversoesArmaDados;
import com.calculadora.util.TipoConversoesComprimento;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ConversoesController {
	private MainApp mainApp;
	private Stage conversoesStage;
	private ConversaoService conversaoService;
	
	@FXML
	private ComboBox<TipoConversoes> comboTipoConversoes;
	
	@FXML
	private ComboBox<Object> comboFistTipo;
	
	@FXML
	private ComboBox<Object> comboSecondTipo;
	
	@FXML
	private TextField textFieldInput;
	
	@FXML
	private TextField textFieldResult;
	
	@FXML
	private Button btnCalcular;
	
	public void show(MainApp mainApp, Stage conversoesStage) {
		this.mainApp = mainApp;
		this.conversoesStage = conversoesStage;
		
		conversaoService = new ConversaoServiceImpl();
		
		comboTipoConversoes.setItems(FXCollections.observableArrayList(TipoConversoes.values()));
		comboTipoConversoes.getSelectionModel().select(0);
		comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesComprimento.values()));
		comboFistTipo.getSelectionModel().select(0);
		comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesComprimento.values()));
		comboSecondTipo.getSelectionModel().select(0);
		btnCalcular.setDisable(true);
		
		setListeners(textFieldInput);
		
		this.conversoesStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleVoltar();
			}
		});
	}
	
	private void setListeners(TextField textField) {
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (textFieldInput.getText().length() == 0) {
		    	btnCalcular.setDisable(true);
		    } else {
		    	btnCalcular.setDisable(false);
		    }
		});
	}
	
	@FXML
	private void hiddenTipoConversao() {
		
		switch (comboTipoConversoes.getValue()) {
			case COMPRIMENTO:
				comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesComprimento.values()));
				comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesComprimento.values()));
				break;
			case ARMANEZAMENTO_DADOS:
				comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesArmaDados.values()));
				comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesArmaDados.values()));
				break;
		}
		textFieldInput.setText("");
		textFieldResult.setText("");
		comboFistTipo.getSelectionModel().select(0);
		comboSecondTipo.getSelectionModel().select(0);
	}
	
	@FXML
	private void handleCalcular() {
		BigDecimal result = null;
		
		try {
			switch (comboTipoConversoes.getValue()) {
				case COMPRIMENTO:
					result = conversaoService.converter(new BigDecimal(textFieldInput.getText()), 
							((TipoConversoesComprimento) comboFistTipo.getValue()).getTipo(), 
							((TipoConversoesComprimento) comboSecondTipo.getValue()).getTipo());
					break;
				case ARMANEZAMENTO_DADOS:
					result = conversaoService.converter(new BigDecimal(textFieldInput.getText()), 
							((TipoConversoesArmaDados) comboFistTipo.getValue()).getTipo(), 
							((TipoConversoesArmaDados) comboSecondTipo.getValue()).getTipo());
					break;
			}
			} catch (NumberFormatException e) {
				textFieldInput.setText("");
				textFieldResult.setText("");
				return;
			}
		
		textFieldResult.setText(result.toString());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.initRoot();
		conversoesStage.close();
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setConversoesStage(Stage conversoesStage) {
		this.conversoesStage = conversoesStage;
	}
}
