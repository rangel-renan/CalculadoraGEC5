package com.calculadora;

import java.util.Optional;

import com.calculadora.config.ConfigProperties;
import com.calculadora.config.RootLayoutController;
import com.calculadora.model.Idioma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

public class MainApp extends Application {
	private FXMLLoader loader;
	
	private Stage homeStage;
	
	private RootLayoutController rootLayoutController;
	
	private ConfigProperties label;
	private Idioma idioma;
	
	@Override
	public void start(Stage _homeStage) throws Exception {
		idioma = showEscolherIdioma();
		label = ConfigProperties.getInstance(idioma);
		homeStage = _homeStage;
		
		FXMLLoader carregarFXML = new FXMLLoader();
		carregarFXML.setResources(label.getBundle());
		
		initHome();
	}
	
	private Idioma showEscolherIdioma() {
		ChoiceDialog<Idioma> dialog = new ChoiceDialog<Idioma>(Idioma.Ingles, Idioma.values());
		dialog.setTitle("Escolher Idioma");
		dialog.setHeaderText("Escolher o Idioma Padrão da Aplicação.");
		dialog.setContentText("Escolha o Idioma Desejado: ");

		// Verifica no ComboBox qual Opção o Usuário Escolheu.
		Optional<Idioma> idiomaEscolhido = dialog.showAndWait();
		if (idiomaEscolhido.isPresent()) {
			return idiomaEscolhido.get();
		} else {
			System.exit(0);
		}

		return null;
	}
	
	private void initHome() {
		
		rootLayoutController = loader.getController();
		rootLayoutController.setMainApp(this);
		rootLayoutController.setRootLayout(homeStage);
		rootLayoutController.initRootLayout();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
