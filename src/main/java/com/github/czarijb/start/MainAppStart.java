package com.github.czarijb.start;

import com.github.czarijb.controllers.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aleksandr on 04.02.17.
 */
public class MainAppStart extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainAppStart.class);
    private final MainWindowController controller = new MainWindowController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        log.info("Starting cashflow money manager application");

        String fxmlFile = "/fxml/mainWindow.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();

        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        MainWindowController mainWindowController = loader.getController();
        mainWindowController.setMainStage(stage);

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Cashflow money manager");
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setOnCloseRequest(e -> controller.closeAllThred());
        stage.setScene(scene);
        stage.show();
    }
}
