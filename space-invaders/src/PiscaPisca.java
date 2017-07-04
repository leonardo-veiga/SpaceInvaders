
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 *
 * @author Bernardo
 */
public class PiscaPisca extends BasicElement implements Enemy {
    private boolean olhoAberto;
    private int pisca;
    private int atira;
    private int points;

    public PiscaPisca(int px, int py) {
        super(px, py);
        points = 4;
    }

    @Override
    public void start() {
        setDirH(1);
        setSpeed(3);
        pisca = 0;
        olhoAberto = true;
        atira = 0;
    }

    @Override
    public void Update() {
        if (jaColidiu()) {
            deactivate();
        } else {
            // Logica dos olhos
            pisca++;
            if (pisca == 30){
                olhoAberto = !olhoAberto;
                pisca = 0;
            }
            // Logica da posicao
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX() >= getLMaxH()) {
                // Reposiciona no lado esquerdo e ...
                setPosX(getLMaxH() - 1);
                setDirH(-1);
                int oldY = getY();
                setPosY(oldY + 20);
            } else if (getX() <= getLMinH()) {
                setPosX(getLMinH() + 1);
                setDirH(1);
                int oldY = getY();
                setPosY(oldY + 20);
            }
            // Logica do tiro
            atira++;
            if (atira == 100){
                Game.getInstance().addChar(new ShotDown(getX()+16,getY()+40));            
                atira = 0;
            }
        }
    }

    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Paint.valueOf("#0000FF"));
        graphicsContext.fillRect(getX(), getY() + 16, 32, 16);
        graphicsContext.setFill(Paint.valueOf("#FF00FF"));
        graphicsContext.fillOval(getX(), getY(), 16, 16);
        graphicsContext.fillOval(getX() + 16, getY(), 16, 16);
        if (olhoAberto) {
            graphicsContext.setFill(Paint.valueOf("#000000"));
            graphicsContext.fillOval(getX(), getY(), 8, 8);
            graphicsContext.fillOval(getX() + 16, getY(), 8, 8);
        }
    }
    
    @Override
    public int getPoints() {
    	return this.points;
    }
}
