package com.calculadora.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.calculadora.config.ConfigProperties;
import com.calculadora.service.OperacoesBasicasService;
import com.calculadora.service.OperacoesBasicasServiceImpl;
import com.calculadora.service.RazaoTrigonometricaService;
import com.calculadora.service.RazaoTrigonometricaServiceImpl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings("unused")
public class CalculadoraController {
	private AnchorPane rootLayout;
	private ConfigProperties label;
	
	private OperacoesBasicasService operacoesBasicasService;
	private RazaoTrigonometricaService razaoTrigonometricaService;
	
	private BigDecimal currentNumber;
	private BigDecimal isStartCurrentNumber;
	private String operador = "";
	private boolean isResultado;
	
	private String memory;

	@FXML
	private Label mLabel;

	@FXML
	private Label displayField;

	@FXML
	private Label displaySubField;
	
	@FXML
	private void initialize() {
	}

	public void show(ConfigProperties label, AnchorPane rootLayout) {
		this.rootLayout = rootLayout;
		this.label = label;
		isResultado = false;
		
		operacoesBasicasService = new OperacoesBasicasServiceImpl();
		razaoTrigonometricaService = new RazaoTrigonometricaServiceImpl();
		
		displayField.requestFocus();
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

		isStartCurrentNumber = null;
		if (displayField.getText() != null || !displayField.getText().isEmpty()) {
			displayField.setText("");
			displaySubField.setText("");
		}
	}

	@FXML
	public void apagarUltimaLetra() {
		if (!displayField.getText().isEmpty() && displayField.getText().length() > 1) {
			displayField.setText(displayField.getText().substring(0, displayField.getText().length() - 1));
		} else if (displayField.getText().length() == 1) {
			displayField.setText("");
		} else {
			displayField.setText("");
		}
	}

	public BigDecimal calcular(BigDecimal valor1, BigDecimal valor2, String operador) {
		try {
			return operacoesBasicasService.calcular(valor1, valor2, operador);
		} catch (NumberFormatException e) {
			displayField.setText("NaN");
		}
		
		return null;
	}

	@FXML
	private void handleMc() {
		memory = null;
		mLabel.setVisible(false);
	}

	@FXML
	private void handleMr() {
		if (memory != null)
			mLabel.setText(memory);
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

		if (displayField.getText() != null && !displayField.getText().isEmpty()) {
			if (displayField.getText().charAt(displayField.getText().length() - 1) != ')')
				displayField.setText(displayField.getText() + "(");
		} else {
			displayField.setText(displayField.getText() + "(");
		}

	}

	@FXML
	private void handleLastParenteses() {

		if (displayField.getText() != null && !displayField.getText().isEmpty()) {
			if (displayField.getText().charAt(displayField.getText().length() - 1) == '(')
				displayField.setText(displayField.getText() + "0)");
			else
				displayField.setText(displayField.getText() + ")");
		}
	}

	@FXML
	private void handlePonto() {

		if (displayField.getText().indexOf(".") == -1) {
			if (displayField.getText().length() > 0)
				displayField.setText(displayField.getText() + ".");
			else
				displayField.setText(displayField.getText() + "0.");
		}
	}

	@FXML
	private void handlePI() {
		displayField.setText(operacoesBasicasService.calcularPI().toString());
	}

	@FXML
	private void handleMudarSinal() {

		if (!displayField.getText().isEmpty()) {
			BigDecimal novoValor = operacoesBasicasService.changeSinal(new BigDecimal(displayField.getText()));
			displayField.setText(novoValor.toString());
		}

	}

	@FXML
	private void handleEuler() {
		displayField.setText(operacoesBasicasService.valorEuler().toString());
	}
	
	@FXML
	private void handleElevadoAoQuadrado() {
		if (!displayField.getText().isEmpty())
			displayField.setText(operacoesBasicasService.calcularElevadoAoQuadrado(
										new BigDecimal(displayField.getText())).toString());
	}
	
	@FXML
	private void handleElevadoAoCubo() {
		if (!displayField.getText().isEmpty())
			displayField.setText(operacoesBasicasService.calcularElevadoAoCubo(
										new BigDecimal(displayField.getText())).toString());
	}
	
	@FXML
	private void handeRaizQuadrada() {
		if (!displayField.getText().isEmpty())
			displayField.setText(operacoesBasicasService.calcularRaizQuadrada(
										new BigDecimal(displayField.getText())).toString());
	}
	
	@FXML
	private void handleDms() {
		if (!displayField.getText().isEmpty()) {
            displayField.setText(operacoesBasicasService.calcularDms(new BigDecimal(displayField.getText())).toString());
        }
	}
	
	@FXML
	private void handleInv() {
		if (!displayField.getText().isEmpty()) {
			displayField.setText(new BigDecimal(displayField.getText()).setScale(0, RoundingMode.FLOOR).toString());
		}
	}
	
	@FXML
	private void handleFatorial() {
		if (!displayField.getText().isEmpty()) {
			BigDecimal valor = new BigDecimal(displayField.getText());
			displayField.setText((operacoesBasicasService.calcularFatorial(valor, valor)).toString());
		}
	}
	
	@FXML
	private void handleLog() {
		if (!displayField.getText().isEmpty()) {
            displayField.setText(operacoesBasicasService.calcularLog(new BigDecimal(displayField.getText())).toString());
        }
	}
	
	@FXML
	private void handleLn() {
		if (!displayField.getText().isEmpty()) {
            displayField.setText(operacoesBasicasService.calcularLn(new BigDecimal(displayField.getText())).toString());
        }
	}
	
	@FXML
	private void handleDezElevadoX() {
		if (!displayField.getText().isEmpty()) {
            displayField.setText(operacoesBasicasService.calcularDezElevadoX(new BigDecimal(displayField.getText())).toString());
        }
	}
	
	@FXML
	private void handleUnder() {
		if (!displayField.getText().isEmpty()) {
            displayField.setText(operacoesBasicasService.calcularUnder(new BigDecimal(displayField.getText())).toString());
        }
	}
	
	@FXML
	private void handleIgual() {

		if (displayField.getText() != null) {
			isStartCurrentNumber = calcular(isStartCurrentNumber, new BigDecimal(displayField.getText()), operador);
			displayField.setText(isStartCurrentNumber.toString());
			displaySubField.setText("");
			isStartCurrentNumber = null;
		}
	}
	
	@FXML
	private void handleExp() {
		if (displayField.getText().contains(".")) {
			displayField.setText(displayField.getText() + "E+0");
        } else {
        	displayField.setText(displayField.getText() + ".E+0");
        }
	}
	
	@FXML
	private void handleOperadorTrigonometrico(ActionEvent actionEvent) {
		BigDecimal result = null;
		
		try {
			result = razaoTrigonometricaService.calcular(new BigDecimal(displayField.getText()), 
					((Button) actionEvent.getSource()).getText());
		} catch (NumberFormatException e) {
			displayField.setText("NaN");
			return;
		}
		
		displayField.setText(result.toString());
	}
	
	@FXML
	private void handleOperador(ActionEvent actionEvent) {
		handleOperador(((Button) actionEvent.getSource()).getText());
	}

	private void handleOperador(String operacaoAtual) {
		String displayAtual = displayField.getText();
		String displaySub = displaySubField.getText();
		
		if (operacaoAtual.equals("Mod")) operacaoAtual = "%";
		if (operacaoAtual.equals("x^y")) operacaoAtual = "^";
		if (operacaoAtual.equals("y √x")) operacaoAtual = "yroot";
		
		if (!displayAtual.isEmpty()) {
			if (isStartCurrentNumber == null) 
				preenxer(displayAtual, displaySub, operacaoAtual);
			else 
				realizarOperacaoAndPreenxer(displayAtual, operacaoAtual);
		} else 
			displaySubField.setText(displayAtual + " " + operador + " ");
	}
	
	private void preenxer(String displayAtual, String displaySub, String operacaoAtual) {
		isStartCurrentNumber = new BigDecimal(displayAtual);
		displayField.setText("");
		if (displaySub.isEmpty())
			displaySubField.setText(isStartCurrentNumber.toString() + " " + operacaoAtual + " ");
		else
			displaySubField.setText(displaySub + " " + operacaoAtual + " ");
		operador = operacaoAtual;
	}
	
	private void realizarOperacaoAndPreenxer(String displayAtual, String operacaoAtual) {
		String displayAnterior = displaySubField.getText();
		displaySubField.setText(displayAnterior + " " + displayAtual + " " + operacaoAtual);
		isStartCurrentNumber = calcular(isStartCurrentNumber, new BigDecimal(displayField.getText()), operador);
		displayField.setText(isStartCurrentNumber.toString());
		isResultado = true;
	}

	@FXML
	private void handleNumPad(ActionEvent actionEvent) {
		handleNumPad(((Button) actionEvent.getSource()).getText());
	}

	private void handleNumPad(String valorAtual) {
		
		if (isResultado) {
			displayField.setText(valorAtual);
			isResultado = false;
		} else {
			String oldValor = displayField.getText();
			String novoValor = oldValor + valorAtual;
			displayField.setText(novoValor); 
		}
	}

	public void setLabel(ConfigProperties label) {
		this.label = label;
	}

	public void setRootLayout(AnchorPane rootLayout) {
		this.rootLayout = rootLayout;
	}
}
