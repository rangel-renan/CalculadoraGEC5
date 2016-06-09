package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.util.Idioma;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class OpcoesController {
	
	private MainApp mainApp;
	private Stage opcoesStage;
	
	@FXML
	private Button btnOk;
	
	@FXML
	private ComboBox<Idioma> comboIdiomas;
	
	public void show(MainApp mainApp, Stage opcoesStage) {
		this.mainApp = mainApp;
		this.opcoesStage = opcoesStage;
		
		btnOk.setDisable(true);
		comboIdiomas.setItems(FXCollections.observableArrayList(Idioma.values()));
	}
	
	@FXML
	private void handleOk() {
		if (comboIdiomas.getValue() != null) {
			mainApp.setIdioma(comboIdiomas.getValue());
			mainApp.initRoot();
			opcoesStage.close();
		}
	}
	
	@FXML
	private void handleCancelar() {
		mainApp.initRoot();
		opcoesStage.close();
	}
	
	@FXML
	private void hiddenBtnComboIdiomas() {
		btnOk.setDisable(false);
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setOpcoesStage(Stage opcoesStage) {
		this.opcoesStage = opcoesStage;
	}
}
