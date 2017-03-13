package assets.simulation;

import java.util.ArrayList;

/**
 * Created by Michel on 7-3-2017.
 */
public class TextureLoader {
    /*
    Simplified version of https://github.com/WhiteHexagon/example-lwjgl3-rift/blob/master/src/main/java/com/sunshineapps/riftexample/thirdparty/TextureLoader.java
    Just the needed parts are extracted.
     */
    private static ArrayList<Texture> textures = new ArrayList();

    public static boolean LoadTextures(String texturePath)
    {
        //ImageIO.read(new FileReader(texturePath));
        return false;
    }

    public static Texture getTexture(String textureName)
    {
        Texture texture = null;

        for (Texture t : textures) {
            if (t != null && t.getName() == textureName) {
                texture = t;
                break;
            }
        }

        return texture;
    }
}
