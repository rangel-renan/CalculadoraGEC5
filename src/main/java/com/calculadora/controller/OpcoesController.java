package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.util.enums.Idioma;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class OpcoesController implements Runnable {
	
	private MainApp mainApp;
	private Stage opcoesStage;
	
	@FXML
	private Button btnOk;
	
	@FXML
	private ComboBox<Idioma> comboIdiomas;
	
	@Override
	public void run() {
		btnOk.setDisable(true);
		
		Platform.runLater(new Runnable() {
			public void run() { 
				comboIdiomas.setItems(FXCollections.observableArrayList(Idioma.values()));
				comboIdiomas.getSelectionModel().select(0);
			}
		});
		
		Platform.runLater(new Runnable() {
			public void run() {
				opcoesStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleCancelar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage opcoesStage) {
		this.mainApp = _mainApp;
		this.opcoesStage = opcoesStage;
		
		run();
		mainApp.addThread(new Thread(this));
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
		mainApp.clean(opcoesStage, this);
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
