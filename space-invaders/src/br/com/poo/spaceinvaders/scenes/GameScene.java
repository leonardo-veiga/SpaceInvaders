package br.com.poo.spaceinvaders.scenes;

import br.com.poo.spaceinvaders.base.Game;
import br.com.poo.spaceinvaders.base.Params;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameScene {
	
	private static GameScene gameScene = null;
	
	private AnimationTimer animationTimer;
	
	private GameScene() {
		
	}
	
	public static GameScene getInstance(){
        if (gameScene == null){
        	gameScene = new GameScene();
        }
        return(gameScene);
    }
	
	public Scene getGameScene() {
		Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        
        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
        root.getChildren().add(canvas);
        
        Label label = new Label();
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Courier New", 16));
        label.textProperty().bind(Bindings.createStringBinding(() -> ("Score: " + Game.getInstance().getTotalPoints().get()), Game.getInstance().getTotalPoints()));
        root.getChildren().add(label);
        
        Game.getInstance().Start();
        
        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });
        
        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });
        
        // Register Game Loop       
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        this.animationTimer = new AnimationTimer() {
            long lastNanoTime = System.nanoTime();
            
            @Override
            public void handle(long currentNanoTime)
            {
                long deltaTime = currentNanoTime - lastNanoTime;
                
                Game.getInstance().Update(currentNanoTime, deltaTime);
                gc.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                Game.getInstance().Draw(gc);
                
                lastNanoTime = currentNanoTime;
            }
            
        };
        
        this.animationTimer.start();

        return scene;
	}
	
	public void stop() {
		this.animationTimer.stop();
	}
	
	public void start() {
		this.animationTimer.start();
	}
	
}
