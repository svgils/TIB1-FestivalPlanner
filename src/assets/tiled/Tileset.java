package assets.tiled;

/**
 * Created by Michel on 20-2-2017.
 */
public class Tileset {
    private int columns;
    private int firstgid;
    private String image;
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

    public int getColumns() { return this.columns; }

    public void setColumns(int columns) { this.columns = columns; }

    public int getFirstgid() { return this.firstgid; }

    public void setFirstgid(int firstgid) { this.firstgid = firstgid; }

    public String getImage() { return this.image; }

    public void setImage(String image) { this.image = image; }

    public int getImageheight() { return this.imageheight; }

    public void setImageheight(int imageheight) { this.imageheight = imageheight; }

    public int getImagewidth() { return this.imagewidth; }

    public void setImagewidth(int imagewidth) { this.imagewidth = imagewidth; }

    public int getMargin() { return this.margin; }

    public void setMargin(int margin) { this.margin = margin; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public int getSpacing() { return this.spacing; }

    public void setSpacing(int spacing) { this.spacing = spacing; }

    //public ArrayList<Terrain> getTerrains() { return this.terrains; }

    //public void setTerrains(ArrayList<Terrain> terrains) { this.terrains = terrains; }

    public int getTilecount() { return this.tilecount; }

    public void setTilecount(int tilecount) { this.tilecount = tilecount; }

    public int getTileheight() { return this.tileheight; }

    public void setTileheight(int tileheight) { this.tileheight = tileheight; }

    //public Tiles getTiles() { return this.tiles; }

    //public void setTiles(Tiles tiles) { this.tiles = tiles; }

    public int getTilewidth() { return this.tilewidth; }

    public void setTilewidth(int tilewidth) { this.tilewidth = tilewidth; }
}
