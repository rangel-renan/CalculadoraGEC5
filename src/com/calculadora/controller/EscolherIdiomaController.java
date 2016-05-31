package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.util.Idioma;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class EscolherIdiomaController {
	
	@FXML
	private ComboBox<Idioma> comboIdiomas;
	
	@FXML
	private Button btnOk;
	
	@FXML
	private Button btnCancelar;
	
	private MainApp mainApp;
	private Stage escolherIdiomaStage;
	
	@FXML
	private void initialize() {
	}
	
	public void show() {
		btnOk.setDisable(true);
		comboIdiomas.setItems(FXCollections.observableArrayList(Idioma.values()));
	}
	
	@FXML
	private void handleBtnOk() {
		if (comboIdiomas.getValue() != null) {
			mainApp.initRoot(comboIdiomas.getValue());
			escolherIdiomaStage.close();
		}
	}
	
	@FXML
	private void handleBtnCancelar() {
		System.exit(0);
	}
	
	@FXML
	private void hiddenBtnComboIdiomas() {
		btnOk.setDisable(false);
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setEscolherIdiomaStage(Stage escolherIdiomaStage) {
		this.escolherIdiomaStage = escolherIdiomaStage;
	}
}
