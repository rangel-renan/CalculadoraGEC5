package com.calculadora.controller;

import java.math.BigDecimal;

import com.calculadora.service.OperacoesBasicasService;
import com.calculadora.service.OperacoesBasicasServiceImpl;
import com.calculadora.util.Idioma;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class CalculadoraController {
	private String operador = "";
	private OperacoesBasicasService operacoesBasicasService;

	private AnchorPane rootLayout;
	private Idioma idioma;
	private BigDecimal currentNumber;
	
	private String memory;
	
	@FXML
	private Label mLabel;
	
	@FXML
	private Label displayField;

	@FXML
	private Label displayAnteriorField;
	
	@FXML
	private Button firstParenteses;
	
	@FXML
	private Button lastParenteses;
	
	@FXML
	private void initialize() {
	}

	public void show(Idioma idioma, AnchorPane rootLayout) {
		this.rootLayout = rootLayout;
		this.idioma = idioma;
		operacoesBasicasService = new OperacoesBasicasServiceImpl();
		
		firstParenteses.setDisable(true);
		lastParenteses.setDisable(true);
		mLabel.setVisible(false);
		
		rootLayout.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {
				handleKeys(keyEvent);
			}
		});
	}

	private void handleKeys(KeyEvent keyEvent) {
		switch (keyEvent.getCode()) {
			case DIGIT1:
			case NUMPAD1:
				handleNumPad("1");
				break;
			case DIGIT2:
			case NUMPAD2:
				handleNumPad("2");
				break;
			case DIGIT3:
			case NUMPAD3:
				handleNumPad("3");
				break;
			case DIGIT4:
			case NUMPAD4:
				handleNumPad("4");
				break;
			case DIGIT5:
			case NUMPAD5:
				handleNumPad("5");
				break;
			case DIGIT6:
			case NUMPAD6:
				handleNumPad("6");
				break;
			case DIGIT7:
			case NUMPAD7:
				handleNumPad("7");
				break;
			case DIGIT8:
			case NUMPAD8:
				handleNumPad("8");
				break;
			case DIGIT9:
			case NUMPAD9:
				handleNumPad("9");
				break;
			case DIGIT0:
			case NUMPAD0:
				handleNumPad("0");
				break;
			case SUBTRACT:
				handleOperador("-");
				break;
			case ADD:
				handleOperador("+");
				break;
			case DIVIDE:
				handleOperador("/");
				break;
			case MULTIPLY:
				handleOperador("*");
				break;
			case DECIMAL:
				handlePonto();
				break;
			case ENTER:
				handleIgual();
				break;
			case DELETE:
				apagarTudo();
				break;
			case BACK_SPACE:
				apagarUltimaLetra();
				break;
			default:
				break;
		}
	}

	@FXML
	public void apagarTudo() {
		
		if (displayField.getText() != null || !displayField.getText().equals("")) {
			displayField.setText("");
			displayAnteriorField.setText("");
		}
	}

	@FXML
	public void apagarUltimaLetra() {
		
		if (displayField.getText() != null && !displayField.getText().equals("")) {
			displayField.setText(displayField.getText().substring(0, displayField.getText().length() - 1));
			displayAnteriorField.setText(displayField.getText().substring(0, displayField.getText().length() - 1));
		}
	}

	@FXML
	private void handleIgual() {

		if (displayField.getText() != null) {
			String resultado = calcular(currentNumber, new BigDecimal(displayField.getText()), operador).toString();
			displayField.setText(resultado);
			displayAnteriorField.setText(resultado);
		}
	}
	
	public BigDecimal calcular(BigDecimal valor1, BigDecimal valor2, String operador) {
		return operacoesBasicasService.calcular(valor1, valor2, operador);
	}
	
	@FXML
	private void handleMc() {
		memory = null;
		mLabel.setVisible(false);
	}
	
	@FXML
	private void handleMr() {
		if (memory != null) {
			mLabel.setText(memory);
        }
	}
	
	@FXML
	private void handleMs() {
		if (!displayField.getText().isEmpty()) {
            memory = displayField.getText();
            mLabel.setVisible(true);
        }
	}
	
	@FXML
	private void handleMplus() {
		if (!displayField.getText().isEmpty()) {
            memory = displayField.getText();
            mLabel.setVisible(true);
        }
	}
	
	@FXML
	private void handleMmin() {
		if (!displayField.getText().isEmpty()) {
            memory = "-" + displayField.getText();
            mLabel.setVisible(true);
        }
	}
	
	@FXML
	private void handleFirstParenteses() {
		
		if (displayField.getText() != null && !displayField.getText().equals("")) {
			if (displayField.getText().charAt(displayField.getText().length() - 1) != ')')
				displayField.setText(displayField.getText() + "(");
		} else {
			displayField.setText(displayField.getText() + "(");
		}
		
	}
	
	@FXML
	private void handleLastParenteses() {
		
		if (displayField.getText() != null && !displayField.getText().equals("")) {
			if (displayField.getText().charAt(displayField.getText().length() - 1) == '(')
				displayField.setText(displayField.getText() + "0)");
			else
				displayField.setText(displayField.getText() + ")");
		}
	}
	
	@FXML
	private void handlePonto() {
		if (displayField.getText().indexOf(".") != -1 || displayField.getText().length() > 0)
			displayField.setText(displayField.getText() + ".");
	}
	
	@FXML
	private void handlePI() {
		displayField.setText(operacoesBasicasService.calcularPI().toString());
	}
	
	@FXML
	private void handleMudarSinal() {
		
		if (displayField.getText() != "") {
			BigDecimal novoValor = operacoesBasicasService.changeSinal(new BigDecimal(displayField.getText()));
			displayField.setText(novoValor.toString());
		}

	}
	
	@FXML
	private void handleEuler() {
		displayField.setText(operacoesBasicasService.valorEuler().toString());
	}
	
	@FXML
	private void handleOperador(ActionEvent actionEvent) {
		handleOperador(((Button) actionEvent.getSource()).getText());
	}

	private void handleOperador(String operacaoAtual) {
		String currentText = displayField.getText();

		if (!currentText.isEmpty()) {
			currentNumber = new BigDecimal(currentText);
			displayField.setText("");
			operador = operacaoAtual;
		}

		displayAnteriorField.setText(currentText + " " + operador + " ");
	}
	
	@FXML
	private void handleNumPad(ActionEvent actionEvent) {
		handleNumPad(((Button) actionEvent.getSource()).getText());
	}

	private void handleNumPad(String novoValor) {
		displayField.setText(displayField.getText() + novoValor);
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public void setRootLayout(AnchorPane rootLayout) {
		this.rootLayout = rootLayout;
	}
}
