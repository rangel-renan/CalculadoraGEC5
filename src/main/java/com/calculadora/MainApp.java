package com.calculadora;

import java.io.IOException;
import java.net.MalformedURLException;

import com.calculadora.config.ConfigProperties;
import com.calculadora.controller.CalculadoraController;
import com.calculadora.controller.ConversoesController;
import com.calculadora.controller.EscolherIdiomaController;
import com.calculadora.controller.OpcoesController;
import com.calculadora.controller.RootLayoutController;
import com.calculadora.controller.SobreController;
import com.calculadora.util.Idioma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	public static final String CAMINHO_ICONE_APLICACAO = "/images/logo.png";

	private ConfigProperties label;

	private Stage escolherIdiomaStage;
	private Stage rootStage;
	private Stage opcoesStage;
	private Stage sobreStage;
	private Stage conversoesStage;

	private AnchorPane escolherIdiomaLayout;
	private BorderPane rootLayout;
	
	private RootLayoutController rootLayoutController;
	private CalculadoraController calculadoraController;
	private EscolherIdiomaController escolherIdiomaController;
	private OpcoesController opcoesController;
	private SobreController sobreController;
	private ConversoesController conversoesController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.escolherIdiomaStage = primaryStage;
		this.escolherIdiomaStage.setTitle("Escolher Idioma");

		initEscolherIdioma();
	}
	
	public FXMLLoader getLoader() {
		FXMLLoader loader = new FXMLLoader();
		loader.setResources(label.getBundle());
		
		return loader;
	}
	
	public Object getLayout(FXMLLoader loader, String pathFXML) {

		try {
			loader.setLocation(MainApp.class.getResource(pathFXML));
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Stage getStage(FXMLLoader loader, Parent pageLayout, String nomeAplicacao, String icone) {
		
		Stage tempStage = new Stage();
		tempStage.initModality(Modality.WINDOW_MODAL);
		tempStage.setResizable(false);
		tempStage.setTitle(nomeAplicacao);
		tempStage.getIcons().add(new Image(icone));
		tempStage.setScene(new Scene(pageLayout));
		
		return tempStage;
	}

	public void initEscolherIdioma() {

		FXMLLoader loader = new FXMLLoader();
		escolherIdiomaLayout = (AnchorPane) getLayout(loader, "/views/EscolherIdioma.fxml");

		escolherIdiomaStage.setScene(new Scene(escolherIdiomaLayout));
		escolherIdiomaStage.getIcons().add(new Image(CAMINHO_ICONE_APLICACAO));
		escolherIdiomaStage.show();

		escolherIdiomaController = loader.getController();
		escolherIdiomaController.show(escolherIdiomaStage, this);
	}
	
	public void initRoot() {
		
		FXMLLoader loader = getLoader();
		rootLayout = (BorderPane) getLayout(loader, "/views/RootLayout.fxml");
		rootStage = getStage(loader, rootLayout, label.getString("root.titulo"), CAMINHO_ICONE_APLICACAO);
		rootStage.show();
		
		rootLayoutController = loader.getController();
		rootLayoutController.show(label, this, rootStage);
		
		FXMLLoader loaderCalculadora = getLoader();
		AnchorPane calculadoraLayout = (AnchorPane) getLayout(loaderCalculadora, "/views/Calculadora.fxml");
		
		rootLayout.setBottom(calculadoraLayout);
		
		calculadoraController = loaderCalculadora.getController();
		calculadoraController.show(label, calculadoraLayout);
	}
	
	public void initOpcoes() {
		
		FXMLLoader loader = getLoader();
		opcoesStage = getStage(loader, (AnchorPane) getLayout(loader, "/views/Opcoes.fxml"), label.getString("opcoes.titulo"), CAMINHO_ICONE_APLICACAO);
		opcoesStage.show();
		
		opcoesController = loader.getController();
		opcoesController.show(this, opcoesStage);
	}
	
	public void initSobre() {
		
		FXMLLoader loader = getLoader();
		sobreStage = getStage(loader, (AnchorPane) getLayout(loader, "/views/Sobre.fxml"), label.getString("sobre.tituloJanela"), CAMINHO_ICONE_APLICACAO);
		sobreStage.show();
		
		sobreController = loader.getController();
		sobreController.show(rootStage, sobreStage);
	}
	
	public void initConversoes() {
		
		FXMLLoader loader = getLoader();
		conversoesStage = getStage(loader, (AnchorPane) getLayout(loader, "/views/outrasOperacoes/Conversoes.fxml"), label.getString("root.tab.arquivo.conversoes.titulo"), CAMINHO_ICONE_APLICACAO);
		conversoesStage.show();
		
		conversoesController = loader.getController();
		conversoesController.show(this, conversoesStage);
	}
	
	public void setIdioma(Idioma idioma) {
		
		if (label == null)
			label = ConfigProperties.getInstance(idioma);
		else {
			try {
				label.setConfigJanelas(idioma);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
