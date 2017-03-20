package assets.tiled;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Created by Michel on 20-2-2017.
 */
public class Tile {
    private int width;
    private int height;
    private BufferedImage image;
    private int x;
    private int y;


    public Tile(int width, int height, BufferedImage tileImage, int x, int y)
    {
        this.width = width;
        this.height = height;
        this.image = tileImage;
        this.x = x;
        this.y = x;
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

    public Point2D getCenterPoint()
    {
        return new Point2D.Double(x + (width / 2), y + (height / 2));
    }
}
