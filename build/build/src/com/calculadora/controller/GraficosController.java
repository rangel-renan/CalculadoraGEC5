package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.model.Coordenadas;
import com.calculadora.model.Equacao;
import com.calculadora.service.GraficosService;
import com.calculadora.service.GraficosServiceImpl;
import com.calculadora.util.Reta;
import com.calculadora.util.excessoes.SintaxeEquacaoIncorretaException;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GraficosController implements Runnable {
	private MainApp mainApp;
	private Stage graficosStage;
	private BorderPane janelaGraficosLayout;
	
	private ConfigProperties label;
	private GraficosService graficosService;
	
	@FXML
	private Button btnGerar;
	
	@FXML
	private TableView<Coordenadas> tableCoordenadas;
	
	@FXML
	private TableColumn<Coordenadas, Double> colunaX;
	
	@FXML
	private TableColumn<Coordenadas, Double> colunaY;
	
	@FXML
	private TextField textFieldEquacao;
	
	@FXML
	private TextField textFieldXIntMin;
	
	@FXML
	private TextField textFieldXIntMax;
	
	@FXML
	private TextField textFieldYIntMin;
	
	@FXML
	private TextField textFieldYIntMax;
	
	@FXML
	private Label labelError;
	
	@Override
	public void run() {
		btnGerar.setDisable(true);
		
		setListerners(textFieldEquacao);
		setListerners(textFieldXIntMin);
		setListerners(textFieldXIntMax);
		setListerners(textFieldYIntMin);
		setListerners(textFieldYIntMax);
		
		Platform.runLater(new Runnable() {
			public void run() {
				graficosStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	private void setListerners(TextField textField) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldEquacao.getText().length() == 0 
				    || textFieldXIntMin.getText().length() == 0
				    || textFieldXIntMax.getText().length() == 0
				    || textFieldYIntMin.getText().length() == 0
				    || textFieldYIntMax.getText().length() == 0) {
				    	btnGerar.setDisable(true);
				    } else {
				    	btnGerar.setDisable(false);
				    }
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage fracoesStage, BorderPane _janelaGraficosLayout, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.graficosStage = fracoesStage;
		this.janelaGraficosLayout = _janelaGraficosLayout;
		this.label = label;
		this.graficosService = new GraficosServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
		janelaGraficosLayout.setCenter(graficosService.gerarEixos(705, 560, Double.parseDouble(textFieldXIntMin.getText()), Double.parseDouble(textFieldXIntMax.getText()), 
																Double.parseDouble(textFieldYIntMin.getText()), Double.parseDouble(textFieldYIntMax.getText())));
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		graficosStage.close();
		mainApp.clean(graficosStage, this);
	}
	
	@FXML
	private void handleGerar() {
		try {
			labelError.setText("");
			Node reta = graficosService.gerarGrafico(new Equacao(textFieldEquacao.getText()), 705, 560, Double.parseDouble(textFieldXIntMin.getText()), Double.parseDouble(textFieldXIntMax.getText()), 
																Double.parseDouble(textFieldYIntMin.getText()), Double.parseDouble(textFieldYIntMax.getText()));
			preenxerTabela(((Reta) reta).getListCoordenadas());
			janelaGraficosLayout.setCenter(reta);
			
		} catch (SintaxeEquacaoIncorretaException e) {
			labelError.setText(label.getString("error.equacaoIncorreta"));
		}
	}
	
	private void preenxerTabela(ObservableList<Coordenadas> listCoordenadas) {
		colunaX.setCellValueFactory(new PropertyValueFactory<>("XInc"));
		colunaY.setCellValueFactory(new PropertyValueFactory<>("YInc"));
		
		tableCoordenadas.setItems(listCoordenadas);
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public Stage getGraficosStage() {
		return graficosStage;
	}

	public void setGraficosStage(Stage graficosStage) {
		this.graficosStage = graficosStage;
	}

	public ConfigProperties getLabel() {
		return label;
	}

	public void setLabel(ConfigProperties label) {
		this.label = label;
	}

	public MainApp getMainApp() {
		return mainApp;
	}
	
	
	
}
