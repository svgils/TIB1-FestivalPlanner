package assets.tiled;

import java.awt.image.BufferedImage;

/**
 * Created by Michel on 20-2-2017.
 */
public class Tile {
    private int width;
    private int height;
    private BufferedImage image;
    private TileLayer layer;

    public Tile(int width, int height, BufferedImage tileImage)
    {
        this.width = width;
        this.height = height;
        this.image = tileImage;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public BufferedImage getTileImage()
    {
        return this.image;
    }

    //public TileLayer getTileLayer() { return this.layer; }
}
