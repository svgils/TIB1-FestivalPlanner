package mapviewer;

import assets.Visitor;
import assets.mapviewer.Camera;
import assets.simulation.Updatable;
import assets.tiled.Layer;
import assets.tiled.TileMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Michel on 20-2-2017.
 */
public class MapViewer extends JPanel implements ActionListener{
    private TileMap map;
    public Camera camera;

    private AffineTransform cameraTransform;

    private int linesV;
    private int linesH;

    private Random rng = new Random();

    public  ArrayList<Visitor> visitors;

    JCheckBox[] layerCheckboxes;

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

        this.map = new TileMap("./resources/maps/festivalmap.json");
        this.camera = new Camera(this);

        this.visitors = new ArrayList<>();

        int max = this.map.getWidth() * this.map.getTileWidth() - this.map.getTileWidth();
        int min = this.map.getTileWidth();
        int range = max - min + 1;

        while(visitors.size() < 2)
        {
            Point2D newPosition = new Point2D.Double(
                    rng.nextInt(range) + min,
                    rng.nextInt(range) + min
            );
            //if(canSpawn(newPosition))
                visitors.add(new Visitor("Henk", "m", newPosition));
        }

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                AffineTransform cameraTransform = camera.getTransform(getWidth(), getHeight());

                try {
                    Point2D worldMousePosition = cameraTransform.inverseTransform(e.getPoint(), null);
                    for(Visitor v : visitors)
                        v.destination = worldMousePosition;


                } catch (NoninvertibleTransformException e1) {
                    e1.printStackTrace();
                }
            }
        });

        this.layerCheckboxes = new JCheckBox[this.map.getLayers().size()];
        // Add Checkboxes
        for(int i = 0; i < this.map.getLayers().size(); i++)
        {
            boolean isSelected = this.map.getLayers().get(i).getOpacity() > 0 ? true : false;
            add(this.layerCheckboxes[i] = new JCheckBox(this.map.getLayers().get(i).getName(), isSelected));
            int finalI = i;
            this.layerCheckboxes[i].addItemListener(new ItemListener()
            {
                public void itemStateChanged(ItemEvent e)
                {
                    for(int i = 0; i < layerCheckboxes.length; i++) {
                        if (layerCheckboxes[i].isSelected() && !map.getLayers().get(i).getForceRedraw()) {
                            if(map.getLayers().get(i).getName().equals("Path"))
                            {
                                map.getLayers().get(i).setOpacity(0.4);
                            }
                            else
                            {
                                map.getLayers().get(i).setOpacity(1.0);

                            }
                        } else {
                            map.getLayers().get(i).setOpacity(0.0);
                        }
                    }
                }
            });
        }

        new Timer(1000/60,this).start();

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform oldTransform = g2d.getTransform();

        cameraTransform = this.camera.getTransform(getWidth(), getHeight());

        // KEEP THIS ORDER - DO NOT EDIT UNLESS FUCKERY IS WANTED
        g2d.setTransform(cameraTransform);

        // Camera transfom is needed for the map to draw and to keep correct ratio's
        this.map.draw(g2d);
        //this.drawGrid(g2d); // BUGGY NEEDS FIXING

        for(Visitor v : visitors)
            v.draw(g2d);


        g2d.setTransform(oldTransform);
        // YOU CAN EDIT BEYOND THIS POINT AGAIN!




        //g2d.setTransform(this.camera.getTransform(getWidth(), getHeight()));
        //g2d.setTransform(oldTransform);


        //this.drawCrosshair(g2d); // BUGGY NEEDS FIXING
        //this.drawStats(g2d);
    }

    private void drawStats(Graphics2D g2d)
    {
        int statCount = 12;
        int statHeight = 14;

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
        AffineTransform oldTransform = g2d.getTransform();

        g2d.setTransform(cameraTransform);

        int centerX = (int) (this.camera.getCenterPoint().getX());
        //int centerX = (int) ((this.getWidth() / 2) - (this.camera.getCenterPoint().getX()));
        int centerY = (int) (this.camera.getCenterPoint().getY());
        //int centerY = (int) ((this.getHeight() / 2) - (this.camera.getCenterPoint().getY()));

        centerX = 32*(centerX/32);
        centerY = 32*(centerY/32);

        g2d.setColor(Color.DARK_GRAY);

        // Top Vertical line
        g2d.drawLine(centerX, centerY, centerX, 0);

        // Down Vertical line
        g2d.drawLine(centerX, centerY, centerX, this.getHeight());

        // Left Horizontal line
        g2d.drawLine(centerX, centerY, 0, centerY);

        // Right Horizontal line
        g2d.drawLine(centerX, centerY, this.getWidth(), centerY);

        g2d.setTransform(oldTransform);

    }

    private void drawGrid(Graphics2D g2d)
    {
        int stepSize = 32;

        int lV = 0;
        int lH = 0;

        g2d.setColor(Color.lightGray);
        int centerX = (int) (this.camera.getCenterPoint().getX());
        int centerY = (int) (this.camera.getCenterPoint().getY());


        centerX = 32*(centerX/32);
        centerY = 32*(centerY/32);

        g2d.setColor(Color.gray);
        // Draw rows
        for(int y = 0; y < this.getWidth(); y+=1)
        {
            g2d.setColor(Color.lightGray);
            //g2d.drawLine(-this.getWidth(), centerY + y * stepSize, this.getWidth(), centerY + y  * stepSize);
            g2d.drawLine(0, centerY + y * stepSize, this.getWidth(), centerY + y  * stepSize);
            g2d.setColor(Color.gray);
            g2d.drawString(y + "", centerX - 11, centerY + y * stepSize);
            g2d.setColor(Color.lightGray);
            g2d.drawLine(-this.getWidth(), centerY - y * stepSize, this.getWidth(), centerY - y * stepSize);
            g2d.setColor(Color.gray);
            g2d.drawString(-y + "", centerX - 11, centerY - y * stepSize);

            lH+=2;
        }

        // Draw columns
        for(int x = 0; x < this.getHeight(); x+=1)
        {
            g2d.setColor(Color.lightGray);
            g2d.drawLine(centerX + x * stepSize, -this.getHeight(), centerX + x * stepSize, this.getHeight());
            g2d.setColor(Color.gray);
            g2d.drawString(x + "", centerX + x * stepSize, centerY);
            g2d.setColor(Color.lightGray);
            g2d.drawLine(centerX - x * stepSize, -this.getHeight(), centerX - x * stepSize, this.getHeight());
            g2d.setColor(Color.gray);
            g2d.drawString(-x + "", centerX - x * stepSize, centerY );

            lV+=2;
        }

        this.linesH = lH;
        this.linesV = lV;
    }

    long lastTime = System.nanoTime();
    @Override
    public void actionPerformed(ActionEvent e) {
        long time = System.nanoTime();
        double elapsedTime = (time-lastTime) / 1.0e9;
        lastTime = time;


        for(Visitor v : visitors)
            v.update();

        repaint();
    }

    private boolean canSpawn(Point2D newPosition) {
        for(Visitor v : visitors)
            if(v.position.distance(newPosition) < 64)
                return false;
        return true;
    }
}
