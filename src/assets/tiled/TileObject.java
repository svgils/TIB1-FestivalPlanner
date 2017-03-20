package assets.tiled;

import assets.simulation.Drawable;
import assets.simulation.Updatable;

import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Michel on 13-3-2017.
 */
public class TileObject implements Updatable, Drawable {
    private int id;
    private int width;
    private int height;
    private String name;
    private String type;
    private boolean visible;
    private int x;
    private int y;

    private JsonObject layerObject;
    private TileMap map;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point2D getCenterPoint()
    {
        return new Point2D.Double(x + (width / 2), y + (height / 2));
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update() {

    }
}
