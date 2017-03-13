package assets.tiled;

import javax.json.JsonObject;
import java.awt.*;

/**
 * Created by Michel on 20-2-2017.
 */
public class ObjectLayer extends Layer {
    private String drawOrder;

    public ObjectLayer(JsonObject layer, TileMap map)
    {
        super(layer, map);

        this.drawOrder = layer.getString("draworder");
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

    }
}
