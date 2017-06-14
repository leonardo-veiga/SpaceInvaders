import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Inimigo1 extends BasicElement {
	
	private boolean olhoAberto;
	private int pisca;
	private int atira;
	
	public Inimigo1(int px,int py){
        super(px,py);
    }
    
    @Override
    public void start(){
        setDirH(1);
        pisca = 0;
        olhoAberto = true;
        atira = 0;
    }
        
    @Override
    public void Update(){
        if (jaColidiu()){
            deactivate();
        }else{
        	// Logica dos olhos
        	pisca++;
        	if (pisca == 30) {
        		olhoAberto = !olhoAberto;
        		pisca = 0;
        	}
        	
        	// Logica da posição
            setPosX(getX() + getDirH() * getSpeed());
            
            // Se chegou no lado direito da tela ...
            if (getX() >= getLMaxH()){
                setPosX(getX() - 1);
                setDirH(-1);
                setPosY(getY() + 20);
                setSpeed(Params.getInstance().nextInt(5)+1);
            } else if (getX() <= getLMinH()) {
            	setPosX(getX() + 1);
            	setDirH(1);
            	setPosY(getY() + 20);
            	setSpeed(Params.getInstance().nextInt(5)+1);
            }
            
            // Logica tiro
        	atira++;
        	if (atira == 100) {
        		atira = 0;
        		Game.getInstance().addChar(new ShotDown(getX()+16, getY()+40));
        	}
        }
    }    
    
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(Paint.valueOf("#FF0000"));
        graphicsContext.fillRect(getX(), getY()+16, 32, 16);
        graphicsContext.setFill(Paint.valueOf("#FF0000"));
        graphicsContext.fillOval(getX(), getY(), 16, 16);
        graphicsContext.fillOval(getX()+16, getY(), 16, 16);
        
        if (olhoAberto) {
        	graphicsContext.setFill(Paint.valueOf("#000000"));
        	graphicsContext.fillOval(getX(), getY(), 8, 8);
        	graphicsContext.fillOval(getX()+16, getY(), 8, 8);
        	 
        }
        
    }    
}
