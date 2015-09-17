package graphics;

import Utils.ResourceLoader;

import java.awt.image.BufferedImage;

/**
 * Created by ilnurgazizov on 17.09.15.
 */
public class TextureAtlas {

    BufferedImage image;

    public TextureAtlas(String imageName){
        image = ResourceLoader.loadImage(imageName);
    }

    // Вырезаем кусок из image
    public BufferedImage cut(int x, int y, int w, int h){
        return image.getSubimage(x, y, w, h);
    }

}
