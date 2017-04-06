package assets.simulation;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by Michel on 10-3-2017.
 */
public class Texture {
    private BufferedImage image;
    private String name;

    public Texture(String name, BufferedImage image)
    {
        this.image = image;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image){
        this.image = image;
    }
}
