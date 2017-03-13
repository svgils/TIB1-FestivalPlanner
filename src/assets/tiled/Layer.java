package assets.tiled;

import assets.simulation.Drawable;
import assets.simulation.Updatable;

import javax.json.JsonObject;
import java.util.Objects;

/**
 * Created by Michel on 20-2-2017.
 */

public abstract class Layer  implements Updatable, Drawable {
    protected int width;
    protected int height;
    protected String name;
    protected String type;
    protected boolean visible;
    protected int x;
    protected int y;
    protected double opacity;

    private JsonObject layerObject;
    private TileMap map;

    protected boolean forceRedraw;

    public Layer(JsonObject layer, TileMap map)
    {
        //super(layer, map);

        this.layerObject = layer;
        this.map = map;

        this.height = layer.getInt("height");
        this.width = layer.getInt("width");
        this.name = layer.getString("name");
        this.type = layer.getString("type");
        this.visible = layer.getBoolean("visible");
        this.x = layer.getInt("x");
        this.y = layer.getInt("y");
        this.opacity = layer.getJsonNumber("opacity").doubleValue();

        this.forceRedraw = false;
    }

    @Override
    public boolean equals(Object obj) {
        // Reference: https://www.mkyong.com/java/java-how-to-overrides-equals-and-hashcode/
        if (obj == this) return true;
        if (!(obj instanceof Layer)) {
            return false;
        }

        Layer otherLayer = (Layer) obj;
        return this.map == otherLayer.map
                && this.name == otherLayer.name
                && this.height == otherLayer.height
                && this.width == otherLayer.width
                && this.type == otherLayer.type
                && this.visible == otherLayer.visible
                && this.x == otherLayer.x
                && this.y == otherLayer.y
                && this.opacity == otherLayer.opacity
                && this.forceRedraw == otherLayer.forceRedraw;
    }

    @Override
    public int hashCode() {
        return Objects.hash(layerObject, map, height, width, name, type, visible, x, y, opacity, forceRedraw);
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String newName)
    {
        this.name = newName;
        this.forceRedraw = true;
    }

    public double getOpacity()
    {
        return this.opacity;
    }

    public void setOpacity(double newOpacity)
    {
        this.opacity = newOpacity;
        this.forceRedraw = true;
    }

    public boolean getForceRedraw()
    {
        return this.forceRedraw;
    }
}
