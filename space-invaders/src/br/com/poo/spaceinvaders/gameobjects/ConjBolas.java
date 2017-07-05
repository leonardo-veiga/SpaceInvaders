package br.com.poo.spaceinvaders.gameobjects;

import java.util.ArrayList;
import java.util.List;
import br.com.poo.spaceinvaders.base.Character;
import br.com.poo.spaceinvaders.base.Game;
import br.com.poo.spaceinvaders.base.BasicElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a set of balls that crosses the screen over and over again
 *
 * @author Bernardo Copstein
 */
public class ConjBolas extends BasicElement {

    private List<BasicElement> elements;

    public ConjBolas(int px, int py) {
        super(px, py);
        elements = new ArrayList<>();
    }

    @Override
    public void start() {
        setDirH(1);
        setLargAlt((32 + 8) * 3, 32 + 8 + 32);

        elements.add(new Ball(getX(), getY()));
        elements.add(new Ball(getX() + 40, getY()));
        elements.add(new Ball(getX() + 80, getY()));

        elements.add(new Ball(getX(), getY() + 40));
        elements.add(new Ball(getX() + 40, getY() + 40));
        elements.add(new Ball(getX() + 80, getY() + 40));
    }

    @Override
    public void testaColisao(Character outro){
        for(int i=0;i<elements.size();i++){
            elements.get(i).testaColisao(outro);
            if (elements.get(i).jaColidiu()){
            	
                Game.getInstance().verifyPoints(elements.get(i));
            	elements.remove(i);
                
                if (elements.size() == 0){
                    deactivate();
                }
            }
        }
    }
    
    @Override
    public void Update() {
        setPosX(getX() + getDirH() * getSpeed());
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).jaColidiu()){
                elements.get(i).deactivate();
            }else{
                int auxX = elements.get(i).getX();
                elements.get(i).setPosX(auxX + getDirH() * getSpeed());
            }
        }
        if (getX() >= getLMaxH()) {
            setPosX(getLMaxH() - 1);
            int aux = getDirH();
            setDirH(aux * -1);
            int auxY = getY();
            setPosY(getY() + 20);
            for (int i = 0; i < elements.size(); i++) {
                auxY = elements.get(i).getY()+20;
                elements.get(i).setPosY(auxY);
            }
        } else if (getX() <= getLMinH()) {
            setPosX(getLMinH() + 1);
            int aux = getDirH();
            setDirH(aux * -1);
        }
    }

        
    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.TRANSPARENT);
        graphicsContext.fillRect(getX(), getY(), getLargura(), getAltura());
        for(int i=0;i<elements.size();i++){
            elements.get(i).Draw(graphicsContext);
        }
    }
}