package br.com.poo.spaceinvaders.base;

import java.util.LinkedList;
import java.util.List;

import br.com.poo.spaceinvaders.gameobjects.Ball;
import br.com.poo.spaceinvaders.gameobjects.Canhao;
import br.com.poo.spaceinvaders.gameobjects.ConjBolas;
import br.com.poo.spaceinvaders.gameobjects.Enemy;
import br.com.poo.spaceinvaders.gameobjects.PiscaPisca;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

/**
 * Handles the game lifecycle and behavior
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Game {
    private static Game game = null;
    private Canhao canhao;
    private List<Character> activeChars;
    private LongProperty totalPoints;
    
    private Game(){
    	this.totalPoints = new SimpleLongProperty(0);
    }
    
    public static Game getInstance(){
        if (game == null){
            game = new Game();
        }
        return(game);
    }
    
    public void addChar(Character c){
        activeChars.add(c);
        c.start();
    }
    
    public void eliminate(Character c){
        activeChars.remove(c);
        if (c == canhao){
            canhao = null;
            System.out.println("+++++++++++++++++");
            System.out.println(getTotalPoints().get());
            System.out.println("+++++++++++++++++");
        } else {
        	if (c instanceof Enemy) {
        		Enemy e = (Enemy) c;
        		addPoints(e.getPoints());
        	}
        }
        
        if (noEnemyRemaining()) {
        	System.out.println("GAME OVER!");
        }
    }   

    public void Start() {
        // Repositório de personagens
        activeChars = new LinkedList();
        
        // Adiciona o canhao
        canhao = new Canhao(400,550);
        activeChars.add(canhao);
        
        for(int i=0; i<5; i++){
            activeChars.add(new Ball(100+(i*60),60+i*40));
        }
        
        activeChars.add(new PiscaPisca(120,380));
        
        activeChars.add(new ConjBolas(120,300));
        
        for(Character c:activeChars){
            c.start();
        }
    }
    
    public void Update(long currentTime, long deltaTime) {
        for(int i=0;i<activeChars.size();i++){
            Character este = activeChars.get(i);
            este.Update();
            for(int j =0; j<activeChars.size();j++){
                Character outro = activeChars.get(j);
                if ( este != outro){
                    este.testaColisao(outro);
                }
            }
        }
    }
    
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        if (canhao != null){
            canhao.OnInput(keyCode, isPressed);
        }
    }
    
    public void Draw(GraphicsContext graphicsContext) {
        for(Character c:activeChars){
            c.Draw(graphicsContext);
        }
    }
    
    public void addPoints(long points) {
    	this.totalPoints.set(this.totalPoints.get() + points);
    }
    
    public LongProperty getTotalPoints() {
    	return this.totalPoints;
    }
    
    public boolean noEnemyRemaining() {
    	for (Character character : activeChars) {
			if(character instanceof Enemy) {
				return false;
			}
		}
    	return true;
    }
}
