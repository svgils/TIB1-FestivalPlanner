package mapviewer;

import assets.mapviewer.Camera;
import assets.tiled.TileMap;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by Michel on 20-2-2017.
 */
public class MapViewer extends JPanel {
    private TileMap map;
    private Camera camera;

    private int linesV;
    private int linesH;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Map Viewer");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH | JFrame.NORMAL);
        frame.setContentPane(new MapViewer());
        frame.pack();
        frame.setVisible(true);
    }

    public MapViewer() {
        this.map = new TileMap("./resources/festivalmap.json");
        this.camera = new Camera(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        this.drawCrosshair(g2d);

        AffineTransform oldTransform = g2d.getTransform();

        // KEEP THIS ORDER - DO NOT EDIT UNLESS FUCKERY IS WANTED
        g2d.setTransform(this.camera.getTransform(getWidth(), getHeight()));

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

        // Camera transfom is needed for the map to draw and to keep correct ratio's
        this.map.draw(g2d);

        g2d.setTransform(oldTransform);

        // Reset camera transform
        //   g2d.scale(1 / this.camera.getZoom() , 1 / this.camera.getZoom());
         //  g2d.translate(-(this.getWidth() / 2) - this.camera.getCenterPoint().getX() * this.camera.getZoom(), -(this.getHeight() / 2) - this.camera.getCenterPoint().getY() * this.camera.getZoom());
        // Done resetting camera transform
        // YOU CAN EDIT BEYOND THIS POINT AGAIN!

        this.drawGrid(g2d);


        this.drawStats(g2d);

        g2d.setColor(Color.blue);

        // Top Vertical line
        g2d.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, 0);

        // Down Vertical line
        g2d.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, this.getHeight());

        // Left Horizontal line
        g2d.drawLine(this.getWidth() / 2, this.getHeight() / 2, 0, this.getHeight() / 2);

        // Right Horizontal line
        g2d.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
    }

    private void drawStats(Graphics2D g2d)
    {
        int statCount = 12;
        int statHeight = 14;

        g2d.setColor(Color.red);
        g2d.fillOval((this.getWidth() / 2) + (int)(this.camera.getCenterPoint().getX() * this.camera.getZoom()) - 5, (this.getHeight() / 2) + (int)(this.camera.getCenterPoint().getY() * this.camera.getZoom()) - 5, 10,10);
        g2d.setColor(Color.blue);
        g2d.fillOval(this.getWidth() / 2 - 5, this.getHeight() / 2 - 5, 10,10);
        g2d.setColor(Color.gray);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d.fillRect(0, 0, 350, statCount * statHeight);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2d.setColor(Color.white);
        g2d.translate(0, statHeight);
        g2d.drawString("Zoom Factor: " + this.camera.getZoom(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Max Zoom:" + this.camera.getMaxZoom(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Min Zoom:" + this.camera.getMinZoom(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Map Origin Point: X: " + (int)this.camera.getCenterPoint().getX() + "|Y: " + (int)this.camera.getCenterPoint().getY(), 0,0);
        g2d.translate(0, statHeight);
        g2d.drawString("World Position: ", 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Mouse Point: X:" + (int)MouseInfo.getPointerInfo().getLocation().getX() + "|Y: " + (int)MouseInfo.getPointerInfo().getLocation().getY(), 0,0);
        g2d.translate(0, statHeight);
        g2d.drawString("Grid Lines Vertical:" + this.linesV, 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Grid Lines Horizontal:" + this.linesH, 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Map Width:" + this.map.getWidth(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Map Height:" + this.map.getHeight(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Tile Width:" + this.map.getTileWidth(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Tile Height:" + this.map.getTileHeight(), 0, 0);
        g2d.translate(0, -(statCount * statHeight));
    }

    private void drawCrosshair(Graphics2D g2d)
    {
        g2d.setColor(Color.red);
        int centerX = (int) ((this.getWidth() / 2) + (this.camera.getCenterPoint().getX() * this.camera.getZoom()));
        int centerY = (int) ((this.getHeight() / 2) + (this.camera.getCenterPoint().getY() * this.camera.getZoom()));

        // Top Vertical line
        g2d.drawLine(centerX, centerY, centerX, 0);

        // Down Vertical line
        g2d.drawLine(centerX, centerY, centerX, this.getHeight());

        // Left Horizontal line
        g2d.drawLine(centerX, centerY, 0, centerY);

        // Right Horizontal line
        g2d.drawLine(centerX, centerY, this.getWidth(), centerY);
    }

    private void drawGrid(Graphics2D g2d)
    {
        int stepSize = 32;

        int lV = 0;
        int lH = 0;

        g2d.setColor(Color.lightGray);
        int centerX = (int) ((this.getWidth() / 2) + (this.camera.getCenterPoint().getX() * this.camera.getZoom()));
        int centerY = (int) ((this.getHeight() / 2) + (this.camera.getCenterPoint().getY() * this.camera.getZoom()));

        // Draw rows
        for(int y = 0; y < this.getHeight(); y+=(stepSize * this.camera.getZoom()))
        {
            g2d.drawLine(0, centerY + y, this.getWidth(), centerY + y);
            g2d.drawLine(0, centerY - y, this.getWidth(), centerY - y);
            lH+=2;
        }

        // Draw columns
        for(int x = 0; x < this.getWidth(); x+=(stepSize * this.camera.getZoom()))
        {
            g2d.drawLine(centerX + x, 0, centerX + x, this.getHeight());
            g2d.drawLine(centerX - x, 0, centerX - x, this.getHeight());
            lV+=2;
        }

        this.linesH = lH;
        this.linesV = lV;
    }
}
