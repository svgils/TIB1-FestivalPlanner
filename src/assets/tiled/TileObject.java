package assets.tiled;

import assets.simulation.SimulationObject;
import com.oracle.deploy.update.UpdateCheckListener;

import javax.json.JsonObject;

/**
 * Created by Michel on 13-3-2017.
 */
public class TileObject {
    protected int width;
    protected int height;
    protected String name;
    protected String type;
    protected boolean visible;
    protected int x;
    protected int y;

    private JsonObject layerObject;
    private TileMap map;

    protected boolean forceRedraw;

    public TileObject(JsonObject layer, TileMap map) {
        this.layerObject = layer;
        this.map = map;

        this.height = layer.getInt("height");
        this.width = layer.getInt("width");
        this.name = layer.getString("name");
        this.type = layer.getString("type");
        this.visible = layer.getBoolean("visible");
        this.x = layer.getInt("x");
        this.y = layer.getInt("y");
    }
}
