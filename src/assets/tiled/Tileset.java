package assets.tiled;

import java.awt.image.BufferedImage;

/**
 * Created by Michel on 20-2-2017.
 */
public class Tileset {
    private int columns;
    private int firstgid;
    private String imagePath;
    private int imageheight;
    private int imagewidth;
    private int margin;
    private String name;
    private int spacing;
    //private ArrayList<Terrain> terrains;
    private int tilecount;
    private int tileheight;
    //private Tiles tiles;
    private int tilewidth;
    private BufferedImage tilesetImage;

    public int getColumns() { return this.columns; }

    public void setColumns(int columns) { this.columns = columns; }

    public int getFirstgid() { return this.firstgid; }

    public void setFirstgid(int firstgid) { this.firstgid = firstgid; }

    public String getImagePath() { return this.imagePath; }

    public void setImagePath(String image) { this.imagePath = image; }

    public int getImageHeight() { return this.imageheight; }

    public void setImageHeight(int imageheight) { this.imageheight = imageheight; }

    public int getImageWidth() { return this.imagewidth; }

    public void setImageWidth(int imagewidth) { this.imagewidth = imagewidth; }

    public int getMargin() { return this.margin; }

    public void setMargin(int margin) { this.margin = margin; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public int getSpacing() { return this.spacing; }

    public void setSpacing(int spacing) { this.spacing = spacing; }

    //public ArrayList<Terrain> getTerrains() { return this.terrains; }

    //public void setTerrains(ArrayList<Terrain> terrains) { this.terrains = terrains; }

    public int getTileCount() { return this.tilecount; }

    public void setTileCount(int tilecount) { this.tilecount = tilecount; }

    public int getTileHeight() { return this.tileheight; }

    public void setTileHeight(int tileheight) { this.tileheight = tileheight; }

    //public Tiles getTiles() { return this.tiles; }

    //public void setTiles(Tiles tiles) { this.tiles = tiles; }

    public int getTileWidth() { return this.tilewidth; }

    public void setTileWidth(int tilewidth) { this.tilewidth = tilewidth; }

    public BufferedImage getTilesetImage() {
        return tilesetImage;
    }

    public void setTilesetImage(BufferedImage tilesetImage) {
        this.tilesetImage = tilesetImage;
    }
}
