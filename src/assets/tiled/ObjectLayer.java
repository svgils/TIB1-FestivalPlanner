package assets.tiled;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Michel on 13-3-2017.
 */
public class ObjectLayer extends Layer {
    protected ArrayList<TileObject> tileObjects;

    public ObjectLayer(JsonObject layer, TileMap map)
    {
        super(layer, map);

        this.tileObjects = new ArrayList<>();

        JsonArray array = layer.getJsonArray("objects");
        System.out.println(array);

        for(int i = 0; i < array.size(); i++)
        {
            TileObject to = new TileObject(layer, map);
            to.setId(array.getJsonObject(i).getInt("id"));
            to.setHeight(array.getJsonObject(i).getInt("height"));
            to.setWidth(array.getJsonObject(i).getInt("width"));
            to.setName(array.getJsonObject(i).getString("name"));
            to.setType(array.getJsonObject(i).getString("type"));
            to.setVisible(array.getJsonObject(i).getBoolean("visible"));
            to.setX(array.getJsonObject(i).getInt("x"));
            to.setY(array.getJsonObject(i).getInt("y"));

            tileObjects.add(to);
        }
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update() {

    }

    public ArrayList<TileObject> getTileObjects()
    {
        return this.tileObjects;
    }
}
