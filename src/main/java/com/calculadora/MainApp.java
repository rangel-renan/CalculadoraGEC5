package com.calculadora;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

import com.calculadora.config.ConfigProperties;
import com.calculadora.controller.CalculadoraController;
import com.calculadora.controller.ConversoesController;
import com.calculadora.controller.FracoesController;
import com.calculadora.controller.MatrizController;
import com.calculadora.controller.OpcoesController;
import com.calculadora.controller.PorcentagensController;
import com.calculadora.controller.PrimosController;
import com.calculadora.controller.RegraTresController;
import com.calculadora.controller.RootLayoutController;
import com.calculadora.controller.SobreController;
import com.calculadora.controller.VetoresController;
import com.calculadora.util.Idioma;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
	public static final String CAMINHO_ICONE_APLICACAO = "/images/logo.png";

	private ConfigProperties label;
	private int currentSegundos = 0;
	
	private Stage rootStage;
	private Stage opcoesStage;
	private Stage sobreStage;
	private Stage conversoesStage;
	private Stage fracoesStage;
	private Stage porcentagensStage;
	private Stage primosStage;
	private Stage regraTresStage;
	private Stage matrizStage;
	private Stage vetoresStage;
	private BorderPane rootLayout;
	
	private RootLayoutController rootLayoutController;
	private CalculadoraController calculadoraController;
	private SobreController sobreController;
	private ConversoesController conversoesController;
	private OpcoesController opcoesController;
	private FracoesController fracoesController;
	private PorcentagensController porcentagensController;
	private PrimosController primosController;
	private RegraTresController regraTresController;
	private MatrizController matrizController;
	private VetoresController vetoresController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		label = ConfigProperties.getInstance(Idioma.Portugues);
		initWelcome(primaryStage);
	}
	
	private void initWelcome(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader();
		AnchorPane welcome = (AnchorPane) getLayout(loader, "/views/welcome.fxml");
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(new Image(CAMINHO_ICONE_APLICACAO));
		primaryStage.setScene(new Scene(welcome));
		primaryStage.show();
		
		new Timer().schedule(new TimerTask() {
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						currentSegundos++;

						if (currentSegundos == 3) {
							primaryStage.close();
							initRoot();
						}
					}
				});
			}
		}, 0, 1000);
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
			return null;
		}
	}
	
	public Stage getStage(Parent pageLayout, String nomeAplicacao, String icone) {
		
		Stage novoStage = new Stage();
		novoStage.initModality(Modality.WINDOW_MODAL);
		novoStage.setResizable(false);
		novoStage.setTitle(nomeAplicacao);
		novoStage.getIcons().add(new Image(icone));
		novoStage.setScene(new Scene(pageLayout));
		novoStage.show();
		
		return novoStage;
	}
	
	public void initRoot() {
		
		FXMLLoader loader = getLoader();
		rootLayout = (BorderPane) getLayout(loader, "/views/RootLayout.fxml");
		rootStage = getStage(rootLayout, label.getString("root.titulo"), CAMINHO_ICONE_APLICACAO);

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
		opcoesStage = getStage((AnchorPane) getLayout(loader, "/views/Opcoes.fxml"), label.getString("opcoes.titulo"), CAMINHO_ICONE_APLICACAO);
		opcoesController = loader.getController();
		opcoesController.show(this, opcoesStage);
	}
	
	public void initSobre() {
		
		FXMLLoader loader = getLoader();
		sobreStage = getStage((AnchorPane) getLayout(loader, "/views/Sobre.fxml"), label.getString("sobre.tituloJanela"), CAMINHO_ICONE_APLICACAO);
		sobreController = loader.getController();
		sobreController.show(rootStage, sobreStage);
	}
	
	public void initConversoes() {
		
		FXMLLoader loader = getLoader();
		conversoesStage = getStage((AnchorPane) getLayout(loader, "/views/outrasOperacoes/Conversoes.fxml"), label.getString("root.tab.arquivo.conversoes.titulo"), CAMINHO_ICONE_APLICACAO);
		conversoesController = loader.getController();
		conversoesController.show(this, conversoesStage);
	}
	
	public void initFracoes() {
		
		FXMLLoader loader = getLoader();
		fracoesStage = getStage((AnchorPane) getLayout(loader, "/views/outrasOperacoes/Fracoes.fxml"), label.getString("root.tab.arquivo.fracoes.titulo"), CAMINHO_ICONE_APLICACAO);
		fracoesController = loader.getController();
		fracoesController.show(this, fracoesStage);
	}
	
	public void initPorcentagens() {
		
		FXMLLoader loader = getLoader();
		porcentagensStage = getStage((AnchorPane) getLayout(loader, "/views/outrasOperacoes/Porcentagens.fxml"), label.getString("root.tab.arquivo.porcentagem.titulo"), CAMINHO_ICONE_APLICACAO);
		porcentagensController = loader.getController();
		porcentagensController.show(this, porcentagensStage);
	}
	
	public void initPrimos() {
		
		FXMLLoader loader = getLoader();
		primosStage = getStage((AnchorPane) getLayout(loader, "/views/outrasOperacoes/Primos.fxml"), label.getString("root.tab.arquivo.primo.titulo"), CAMINHO_ICONE_APLICACAO);
		primosController = loader.getController();
		primosController.show(this, primosStage, label);
	}
	
	public void initRegraTres() {
		
		FXMLLoader loader = getLoader();
		regraTresStage = getStage((AnchorPane) getLayout(loader, "/views/outrasOperacoes/RegraTres.fxml"), label.getString("root.tab.arquivo.regratres.titulo"), CAMINHO_ICONE_APLICACAO);
		regraTresController = loader.getController();
		regraTresController.show(this, regraTresStage);
	}
	
	public void initMatriz() {
		FXMLLoader loader = getLoader();
		matrizStage = getStage((AnchorPane) getLayout(loader, "/views/outrasOperacoes/MatrizNxN.fxml"), label.getString("root.tab.matrizEquacao.matrizTitulo"), CAMINHO_ICONE_APLICACAO);
		matrizController = loader.getController();
		matrizController.show(this, matrizStage, label);
	}
	
	public void initVetores() {
		FXMLLoader loader = getLoader();
		vetoresStage = getStage((AnchorPane) getLayout(loader, "/views/outrasOperacoes/Vetores.fxml"), label.getString("root.tab.vetor.tituloJanela"), CAMINHO_ICONE_APLICACAO);
		vetoresController = loader.getController();
		vetoresController.show(this, vetoresStage, label);
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
