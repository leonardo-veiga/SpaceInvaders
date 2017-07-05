package br.com.poo.spaceinvaders.gameobjects;

/**
 *
 * @author Bernardo
 */
public class ShotDown extends Shot{
    public ShotDown(int px, int py) {
        super(px, py);
    }
    
    @Override
    public void start(){
        setDirV(1);
        setSpeed(2);
    }
    
    @Override
    public void Update(){
        if (jaColidiu()){
            deactivate();
//            System.out.println("+++++++++++++++++");
//            System.out.println(Game.getInstance().getTotalPoints().get());
//            System.out.println("+++++++++++++++++");
//            Platform.exit();
        }else{
            setPosY(getY() + getDirV() * getSpeed());
            // Se chegou na parte inferior da tela ...
            if (getY() >= getLMaxV()){
                // Desaparece
                deactivate();
            }
        }
    }    


    
}
