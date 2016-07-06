package com.calculadora.controller;

import java.math.BigDecimal;

import javax.measure.converter.ConversionException;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.ConversaoService;
import com.calculadora.service.ConversaoServiceImpl;
import com.calculadora.util.enums.TipoConversoes;
import com.calculadora.util.enums.TipoConversoesAngulo;
import com.calculadora.util.enums.TipoConversoesArea;
import com.calculadora.util.enums.TipoConversoesArmaDados;
import com.calculadora.util.enums.TipoConversoesComprimento;
import com.calculadora.util.enums.TipoConversoesEletricidade;
import com.calculadora.util.enums.TipoConversoesFrequencia;
import com.calculadora.util.enums.TipoConversoesMassa;
import com.calculadora.util.enums.TipoConversoesPressao;
import com.calculadora.util.enums.TipoConversoesTempo;
import com.calculadora.util.enums.TipoConversoesVelocidade;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ConversoesController implements Runnable {
	private MainApp mainApp;
	private Stage conversoesStage;
	
	private ConfigProperties label;
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
	
	@FXML
	private Label labelError;
	
	@Override
	public void run() {
		setListeners(textFieldInput);
		
		Platform.runLater(new Runnable() {
			public void run() {
				conversoesStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage conversoesStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.conversoesStage = conversoesStage;
		this.label = label;
		this.conversaoService = new ConversaoServiceImpl();
		
		preenxerCombo();
		btnCalcular.setDisable(true);
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	private void preenxerCombo() {
		Platform.runLater(new Runnable() {
			public void run() { 
				comboTipoConversoes.setItems(FXCollections.observableArrayList(TipoConversoes.values())); 
				comboTipoConversoes.getSelectionModel().select(0);
			}
		});
		Platform.runLater(new Runnable() {
			public void run() { 
				comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesComprimento.values())); 
				comboFistTipo.getSelectionModel().select(0);
			}
		});
		Platform.runLater(new Runnable() {
			public void run() { 
				comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesComprimento.values())); 
				comboSecondTipo.getSelectionModel().select(0);
			}
		});
	}
	
	private void setListeners(TextField textField) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldInput.getText().length() == 0) {
				    	btnCalcular.setDisable(true);
				    } else {
				    	btnCalcular.setDisable(false);
				    }
				});
			}
		});
	}
	
	@FXML
	private void hiddenTipoConversao() {
		
		switch (comboTipoConversoes.getValue()) {
			case COMPRIMENTO:
				Platform.runLater(new Runnable() {
					public void run() { 
						comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesComprimento.values())); 
						comboFistTipo.getSelectionModel().select(0);
					}
				});
				
				Platform.runLater(new Runnable() {
					public void run() { 
						comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesComprimento.values())); 
						comboSecondTipo.getSelectionModel().select(0);
					}
				});
				break;
			case ARMANEZAMENTO_DADOS:
				Platform.runLater(new Runnable() {
					public void run() { 
						comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesArmaDados.values()));
						comboFistTipo.getSelectionModel().select(0);
					}
				});
				
				Platform.runLater(new Runnable() {
					public void run() { 
						comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesArmaDados.values())); 
						comboSecondTipo.getSelectionModel().select(0);
					}
				});
				break;
			case VELOCIDADE:
				Platform.runLater(new Runnable() {
					public void run() { 
						comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesVelocidade.values()));
						comboFistTipo.getSelectionModel().select(0);
					}
				});
				
				Platform.runLater(new Runnable() {
					public void run() { 
						comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesVelocidade.values())); 
						comboSecondTipo.getSelectionModel().select(0);
					}
				});
				break;
			case AREA:
				Platform.runLater(new Runnable() {
					public void run() { 
						comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesArea.values())); 
						comboFistTipo.getSelectionModel().select(0);
					}
				});
				
				Platform.runLater(new Runnable() {
					public void run() { 
						comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesArea.values())); 
						comboSecondTipo.getSelectionModel().select(0);
				}
				});
				break;
			case PRESSAO:
				Platform.runLater(new Runnable() {
					public void run() { 
						comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesPressao.values())); 
						comboFistTipo.getSelectionModel().select(0);
					}
				});
				
				Platform.runLater(new Runnable() {
					public void run() { 
						comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesPressao.values()));
						comboSecondTipo.getSelectionModel().select(0);
					}
				});
				break;
			case TEMPO:
				Platform.runLater(new Runnable() {
					public void run() { 
						comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesTempo.values())); 
						comboFistTipo.getSelectionModel().select(0);
					}
				});
				
				Platform.runLater(new Runnable() {
					public void run() { 
						comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesTempo.values()));
						comboSecondTipo.getSelectionModel().select(0);
					}
				});
				break;
			case MASSA:
				Platform.runLater(new Runnable() {
					public void run() { 
						comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesMassa.values())); 
						comboFistTipo.getSelectionModel().select(0);
					}
				});
				
				Platform.runLater(new Runnable() {
					public void run() { 
						comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesMassa.values()));
						comboSecondTipo.getSelectionModel().select(0);
					}
				});
				break;
			case FREQUENCIA:
				Platform.runLater(new Runnable() {
					public void run() { 
						comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesFrequencia.values()));
						comboFistTipo.getSelectionModel().select(0);
					}
				});
				
				Platform.runLater(new Runnable() {
					public void run() { 
						comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesFrequencia.values()));
						comboSecondTipo.getSelectionModel().select(0);
					}
				});
				break;
			case ANGULO:
				Platform.runLater(new Runnable() {
					public void run() { 
						comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesAngulo.values())); 
						comboFistTipo.getSelectionModel().select(0);
					}
				});
				
				Platform.runLater(new Runnable() {
					public void run() { 
						comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesAngulo.values())); 
						comboSecondTipo.getSelectionModel().select(0);
					}
				});
				break;
			case ELETRICIDADE:
				Platform.runLater(new Runnable() {
					public void run() { 
						comboFistTipo.setItems(FXCollections.observableArrayList(TipoConversoesEletricidade.values())); 
						comboFistTipo.getSelectionModel().select(0);
					}
				});
				
				Platform.runLater(new Runnable() {
					public void run() { 
						comboSecondTipo.setItems(FXCollections.observableArrayList(TipoConversoesEletricidade.values())); 
						comboSecondTipo.getSelectionModel().select(0);
					}
				});
				break;
		}
		textFieldInput.setText("");
		textFieldResult.setText("");
		
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
				case ANGULO:
					result = conversaoService.converter(new BigDecimal(textFieldInput.getText()), 
							((TipoConversoesAngulo) comboFistTipo.getValue()).getTipo(), 
							((TipoConversoesAngulo) comboSecondTipo.getValue()).getTipo());
					break;
				case AREA:
					result = conversaoService.converter(new BigDecimal(textFieldInput.getText()), 
							((TipoConversoesArea) comboFistTipo.getValue()).getTipo(), 
							((TipoConversoesArea) comboSecondTipo.getValue()).getTipo());
					break;
				case ELETRICIDADE:
					result = conversaoService.converter(new BigDecimal(textFieldInput.getText()), 
							((TipoConversoesEletricidade) comboFistTipo.getValue()).getTipo(), 
							((TipoConversoesEletricidade) comboSecondTipo.getValue()).getTipo());
					break;
				case FREQUENCIA:
					result = conversaoService.converter(new BigDecimal(textFieldInput.getText()), 
							((TipoConversoesFrequencia) comboFistTipo.getValue()).getTipo(), 
							((TipoConversoesFrequencia) comboSecondTipo.getValue()).getTipo());
					break;
				case MASSA:
					result = conversaoService.converter(new BigDecimal(textFieldInput.getText()), 
							((TipoConversoesMassa) comboFistTipo.getValue()).getTipo(), 
							((TipoConversoesMassa) comboSecondTipo.getValue()).getTipo());
					break;
				case PRESSAO:
					result = conversaoService.converter(new BigDecimal(textFieldInput.getText()), 
							((TipoConversoesPressao) comboFistTipo.getValue()).getTipo(), 
							((TipoConversoesPressao) comboSecondTipo.getValue()).getTipo());
					break;
				case TEMPO:
					result = conversaoService.converter(new BigDecimal(textFieldInput.getText()), 
							((TipoConversoesTempo) comboFistTipo.getValue()).getTipo(), 
							((TipoConversoesTempo) comboSecondTipo.getValue()).getTipo());
					break;
				case VELOCIDADE:
					result = conversaoService.converter(new BigDecimal(textFieldInput.getText()), 
							((TipoConversoesVelocidade) comboFistTipo.getValue()).getTipo(), 
							((TipoConversoesVelocidade) comboSecondTipo.getValue()).getTipo());
					break;
			}
			
			textFieldResult.setText(result.toString());
			} catch (NumberFormatException e) {
				textFieldInput.setText("");
				textFieldResult.setText("");
			} catch (ConversionException e) {
				labelError.setText(label.getString("root.tab.arquivo.conversoes.error"));
			}
		
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		conversoesStage.close();
		mainApp.clean(conversoesStage, this);
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
