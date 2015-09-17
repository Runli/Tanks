package Game;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by ilnurgazizov on 17.09.15.
 */
// Хранит ту инфу которая нам нужна будет для анимации
public class Sprite {

    private SpriteSheet sheet;
    private float scale; // на сколько большим хотим рисовать спрайт на экране

    public Sprite(SpriteSheet sheet, float scale){
        this.sheet = sheet;
        this.scale = scale;
    }

    public void render(Graphics2D g, float x, float y){

        BufferedImage image = sheet.getSprite(0);
        g.drawImage(image, (int)(x), (int)(y), (int)(image.getWidth() * scale), (int) (image.getHeight() * scale), null);

    }

}
