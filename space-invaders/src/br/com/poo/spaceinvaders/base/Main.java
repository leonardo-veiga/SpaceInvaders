package br.com.poo.spaceinvaders.base;

import br.com.poo.spaceinvaders.scenes.MenuScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Handles window initialization and primary game setup
 * @author Bernardo Copstein, Rafael Copstein
 */

public class Main extends Application {
		
    @Override
    public void start(Stage stage) throws Exception {
    	
        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLE);
 
        Scene sceneMenu = MenuScene.getInstance().getMenuScene();
        
        stage.setScene(sceneMenu);
        
        // Show window
        stage.show();
    }

    public static void main(String args[]) {
        launch();
    }
}
