package mapviewer;

import assets.mapviewer.Camera;
import assets.tiled.TileMap;

import javax.swing.*;
import java.awt.*;

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
        this.map = new TileMap("./map.json");
        this.camera = new Camera(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        this.drawGrid(g2d);
        this.drawCrosshair(g2d);

        // KEEP THIS ORDER - DO NOT EDIT UNLESS FUCKERY IS WANTED
        g2d.setTransform(this.camera.getTransform(getWidth(), getHeight()));

        // Camera transfom is needed for the map to draw and to keep correct ratio's
        this.map.draw(g2d);

        // Reset camera transform
        g2d.scale(1 / this.camera.getZoom() , 1 / this.camera.getZoom());
        g2d.translate(-(this.getWidth() / 2) - this.camera.getCenterPoint().getX() * this.camera.getZoom(), -(this.getHeight() / 2) - this.camera.getCenterPoint().getY() * this.camera.getZoom());
        // Done resetting camera transform
        // YOU CAN EDIT BEYOND THIS POINT AGAIN!

        this.drawStats(g2d);
    }

    private void drawStats(Graphics2D g2d)
    {
        g2d.setColor(Color.gray);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d.fillRect(0, 0, 300, 100);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2d.setColor(Color.white);
        g2d.translate(0, 12);
        g2d.drawString("Zoom Factor: " + this.camera.getZoom(), 0, 0);
        g2d.translate(0, 12);
        g2d.drawString("Max Zoom:" + this.camera.getMaxZoom(), 0, 0);
        g2d.translate(0, 12);
        g2d.drawString("Min Zoom:" + this.camera.getMinZoom(), 0, 0);
        g2d.translate(0, 12);
        g2d.drawString("Center Point: X: " + (int)this.camera.getCenterPoint().getX() + "|Y: " + (int)this.camera.getCenterPoint().getY(), 0,0);
        g2d.translate(0, 12);
        g2d.drawString("World Position: ", 0, 0);
        g2d.translate(0, 12);
        g2d.drawString("Mouse Point: X:" + (int)MouseInfo.getPointerInfo().getLocation().getX() + "|Y: " + (int)MouseInfo.getPointerInfo().getLocation().getY(), 0,0);
        g2d.translate(0, 12);
        g2d.drawString("Lines Vertical:" + this.linesV, 0, 0);
        g2d.translate(0, 12);
        g2d.drawString("Lines Horizontal:" + this.linesH, 0, 0);
    }

    private void drawCrosshair(Graphics2D g2d)
    {
        g2d.setColor(Color.gray);
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
