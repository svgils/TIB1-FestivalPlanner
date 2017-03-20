package assets.simulation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Michel on 7-3-2017.
 */
public class TextureLoader {
    /*
    Simplified version of https://github.com/WhiteHexagon/example-lwjgl3-rift/blob/master/src/main/java/com/sunshineapps/riftexample/thirdparty/TextureLoader.java
    Just the needed parts are extracted.
     */
    private ArrayList<Texture> textures = new ArrayList();

    public boolean LoadTexture(String textureName, String texturePath) throws IOException {
        Texture texture = new Texture(textureName, ImageIO.read(this.getClass().getResourceAsStream(texturePath)));

        return textures.add(texture);
    }

    public Texture getTexture(String textureName)
    {
        for (Texture t : textures) {
            if (t != null && t.getName().equals(textureName)) {
                return t;
            }
        }

        return null;
    }

    public Texture getTexture(int index)
    {
        return textures.get(index);
    }

    public ArrayList<Texture> getTextures()
    {
        return textures;
    }
}
