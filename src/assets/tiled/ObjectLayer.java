package assets.tiled;

import javax.json.JsonObject;
import java.awt.*;

/**
 * Created by Michel on 13-3-2017.
 */
public class ObjectLayer extends Layer {
    public ObjectLayer(JsonObject layer, TileMap map)
    {
        super(layer, map);
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update() {

    }
}
