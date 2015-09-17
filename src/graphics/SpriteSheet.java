package graphics;

import java.awt.image.BufferedImage;

/**
 * Created by ilnurgazizov on 17.09.15.
 */
// Для более мелких картинок, отвечающих за анимацию
public class SpriteSheet {

    private BufferedImage sheet;
    private int spriteCount; // количество спрайтов
    private int scale; // размер одного спрайта в пикселях
    private int spritesInWidth; // количество спрайтов в ширину

    public SpriteSheet(BufferedImage sheet, int spriteCount, int scale) {
        this.sheet = sheet;
        this.spriteCount = spriteCount;
        this.scale = scale;
        this.spritesInWidth = sheet.getWidth() / scale;
    }

    public BufferedImage getSprite(int index){

        index = index % spriteCount; // для того чтобы мы могли выбирать танки

        int x = index % spritesInWidth * scale;
        int y = index / spritesInWidth * scale;

        return sheet.getSubimage(x, y, scale, scale);

    }


}
