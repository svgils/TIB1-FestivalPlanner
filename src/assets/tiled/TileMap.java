package assets.tiled;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Created by Michel on 20-2-2017.
 */
public class TileMap {



    private ArrayList<Tileset> tilesets = new ArrayList<>();
    private ArrayList<TileLayer> layers = new ArrayList();
    private ArrayList<BufferedImage> tiles = new ArrayList();

    private int width;
    private int height;
    private int nextObjectId;
    private String orientation;
    private int tileWidth;
    private int tileHeight;
    private int version;
    private  String renderOrder;
    private int rows;
    private int columns;

    public TileMap(String resourcePath)
    {
        try(JsonReader reader = Json.createReader(new FileReader(resourcePath)))
        {
            JsonObject o = (JsonObject)reader.read();

            // Save all data of the root object (TileMap)
            this.height = o.getInt("height");
            this.width = o.getInt("width");
            this.nextObjectId = o.getInt("nextobjectid");
            this.orientation = o.getString("orientation");
            this.renderOrder = o.getString("renderorder");
            this.tileWidth = o.getInt("tilewidth");
            this.tileHeight = o.getInt("tileheight");
            this.version = o.getInt("version");

            // Parse tilesets
            JsonArray tilesetsArray = o.getJsonArray("tilesets");

            for(int t = 0; t < tilesetsArray.size(); t++)
            {
                JsonObject tilesetObject = tilesetsArray.getJsonObject(t);

                Tileset tileset = new Tileset();
                tileset.setColumns(tilesetObject.getInt("columns"));
                tileset.setFirstgid(tilesetObject.getInt("firstgid"));

                // Replace Tiled path format with Java path format, replace slashes
                tileset.setImagePath(tilesetObject.getString("image").replaceAll("\\.\\./", "/"));
                tileset.setImageHeight(tilesetObject.getInt("imageheight"));
                tileset.setImageWidth(tilesetObject.getInt("imagewidth"));
                tileset.setMargin(tilesetObject.getInt("margin"));
                tileset.setName(tilesetObject.getString("name"));
                tileset.setSpacing(tilesetObject.getInt("spacing"));
                tileset.setTileCount(tilesetObject.getInt("tilecount"));
                tileset.setTileHeight(tilesetObject.getInt("tileheight"));
                tileset.setTileWidth(tilesetObject.getInt("tilewidth"));
                tileset.setTilesetImage(ImageIO.read(this.getClass().getResourceAsStream(tileset.getImagePath())));

                tilesets.add(tileset);

                // TODO: Add comments regarding what this code does
                int index = tileset.getFirstgid();
                while(this.tiles.size() < index + tileset.getTileCount())
                {
                    this.tiles.add(null);
                }

                System.out.println("#"+t+" | Parse Tiles");
                // Parse tiles
                // TODO: Add comments regarding what this code does
                for (int y = 0; y + tileset.getTileHeight() <= tileset.getImageHeight(); y += tileset.getTileHeight()) {
                    for (int x = 0; x + tileset.getTileWidth() <= tileset.getImageWidth(); x += tileset.getTileWidth()) {
                        Tile tile = new Tile(tileset.getTileWidth(), tileset.getTileHeight(), tileset.getTilesetImage().getSubimage(x, y, tileset.getTileWidth(), tileset.getTileHeight()));
                        this.tiles.set(index, tile.getTileImage());
                        index++;
                    }
                }
            }

            // Parse layers
            // TODO: Add comments regarding what this code does
            JsonArray jsonLayers = o.getJsonArray("layers");
            for (int i = 0; i < jsonLayers.size(); i++) {
                TileLayer layer = new TileLayer(jsonLayers.getJsonObject(i), this);
                if(jsonLayers.getJsonObject(i).getString("name").equals("Path"))
                    continue;
                this.layers.add(layer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



//        JsonReader reader = null;
//        try {
//            reader = Json.createReader(new FileReader(resourcePath));
//            JsonObject o = (JsonObject) reader.read();
//
//            this.height = o.getInt("height");
//            this.width = o.getInt("width");
//
//            JsonArray tilesets = o.getJsonArray("tilesets");
//
//            for (int i = 0; i < tilesets.size(); i++) {
//                JsonObject tileset = tilesets.getJsonObject(i);
//                String tileFile = tileset.getString("image");
//                tileFile = tileFile.replaceAll("\\.\\./", "/");
//                BufferedImage img = ImageIO.read(getClass().getResource(tileFile));
//
//                int tilesetWidth = tileset.getInt("imagewidth");
//                int tilesetHeight = tileset.getInt("imageheight");
//                int tileWidth = tileset.getInt("tilewidth");
//                int tileHeight = tileset.getInt("tileheight");
//
//                int index = tileset.getInt("firstgid");
//                while (this.tiles.size() < index + tileset.getInt("tilecount")) {
//                    this.tiles.add(null);
//                }
//
//                for (int y = 0; y + tileHeight <= tilesetHeight; y += tileHeight) {
//                    for (int x = 0; x + tileWidth <= tilesetWidth; x += tileWidth) {
//                        this.tiles.set(index, img.getSubimage(x, y, tileWidth, tileHeight));
//                        index++;
//                    }
//                }
//            }
//
//            JsonArray jsonLayers = o.getJsonArray("layers");
//            for (int i = 0; i < jsonLayers.size(); i++) {
//                JsonObject layer = jsonLayers.getJsonObject(i);
//                if(layer.getString("name").equals("Signs"))
//                    continue;
//                this.layers.add(new TileLayer(jsonLayers.getJsonObject(i), this));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }printStackTrace
    }

    public void draw(Graphics2D g2) {
        for (TileLayer l : this.layers) {
            g2.drawImage(l.getImage(), null, null);
        }
    }

    public BufferedImage[] getTiles() {
        return this.tiles.toArray(new BufferedImage[tiles.size()]);
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
}
