package mapviewer;

import assets.Visitor;
import assets.mapviewer.Camera;
import assets.mapviewer.Target;
import assets.simulation.TextureLoader;
import assets.tiled.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.*;


/**
 * Created by Michel on 20-2-2017.
 */
public class MapViewer extends JPanel implements ActionListener {
    private TileMap map;
    public Camera camera;

    private AffineTransform cameraTransform;

    private int linesV;
    private int linesH;

    private int visitorMax = 200;
    private int visitorCount = 0;

    public ArrayList<Visitor> visitors;

    JCheckBox[] layerCheckboxes;

    TextureLoader tl = new TextureLoader();

    ArrayList<Target> stageTargets = new ArrayList<>();
    ArrayList<Point2D> spawnPoints = new ArrayList<>();
    ArrayList<Target> exitTargets = new ArrayList<>();


    Random rng;

    public static void main(String[] args) {
        try
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
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public MapViewer() throws IOException {

        this.map = new TileMap("/maps/festivalmap.json");
        this.camera = new Camera(this);

        tl.LoadTexture("m_visitor_32x32", "/characters/m_visitor_32x32.png");
        tl.LoadTexture("visitor_sprite", "/characters/visitor_sprite.png");

        this.visitors = new ArrayList<>();

        rng = new Random();

        stageTargets.add(new Target(map, 15, 12));
        stageTargets.add(new Target(map, 64, 11));
        stageTargets.add(new Target(map, 41, 55));

        spawnPoints.add(new Point(7, 79));
        spawnPoints.add(new Point(8, 79));
        spawnPoints.add(new Point(13, 79));
        spawnPoints.add(new Point(14, 79));
        spawnPoints.add(new Point(19, 79));
        spawnPoints.add(new Point(20, 79));

        exitTargets.add(new Target(map, 25, 79));
        exitTargets.add(new Target(map, 26, 79));
        exitTargets.add(new Target(map, 31, 79));
        exitTargets.add(new Target(map, 32, 79));
        exitTargets.add(new Target(map, 38, 79));
        exitTargets.add(new Target(map, 39, 79));

        this.layerCheckboxes = new JCheckBox[this.map.getLayers().size()];
        // Add Checkboxes
        for (int i = 0; i < this.map.getLayers().size(); i++) {
            boolean isSelected = this.map.getLayers().get(i).getOpacity() > 0 ? true : false;
            add(this.layerCheckboxes[i] = new JCheckBox(this.map.getLayers().get(i).getName(), isSelected));
            int finalI = i;
            this.layerCheckboxes[i].addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    for (int i = 0; i < layerCheckboxes.length; i++) {
                        if (layerCheckboxes[i].isSelected() && !map.getLayers().get(i).getForceRedraw()) {
                            if (map.getLayers().get(i).getName().equals("Path")) {
                                map.getLayers().get(i).setOpacity(0.4);
                            } else {
                                map.getLayers().get(i).setOpacity(1.0);
                            }
                        } else {
                            map.getLayers().get(i).setOpacity(0.0);
                        }
                    }
                }
            });
        }

        new Timer(1000 / 60, this).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform oldTransform = g2d.getTransform();

        cameraTransform = this.camera.getTransform(getWidth(), getHeight());

        // KEEP THIS ORDER - DO NOT EDIT UNLESS FUCKERY IS WANTED
        g2d.setTransform(cameraTransform);

        // Camera transfom is needed for the map to draw and to keep correct ratio's
        this.map.draw(g2d);
        //this.drawGrid(g2d);

        //this.numberedGrid(g2d);

        drawNodeMap(g2d);

        //drawOverlay(g2d, overlay);

        for (Visitor v : visitors)
            v.draw(g2d);

        map.getLayers().get(3).draw(g2d);

        g2d.setTransform(oldTransform);
        // YOU CAN EDIT BEYOND THIS POINT AGAIN!
    }

    private void numberedGrid(Graphics2D g2d)
    {
        int stepSize = 32;

        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 10));

        g2d.setColor(Color.lightGray);
        // Draw columns
        for(int x = 0; x < this.map.getHeight(); x+=1)
        {
            g2d.drawLine(x * stepSize, 0,x * stepSize, this.map.getHeight() * stepSize);
        }

        g2d.setColor(Color.lightGray);
        // Draw rows
        for(int y = 0; y < this.map.getWidth(); y+=1)
        {
            g2d.drawLine(0, y * stepSize, this.map.getWidth() * stepSize, y * stepSize);
        }

//        g2d.setColor(Color.yellow);
//        for(int x = 0; x < this.map.getHeight(); x++)
//        {
//            for(int y = 0; y < this.map.getWidth(); y++)
//            {
//                g2d.drawString("["+x+","+y+"]", x * stepSize, y * stepSize + 10);
//            }
//        }
//        g2d.setColor(Color.lightGray);
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

        if(visitors.size() < visitorMax)
        {
            Point2D spawnPoint = spawnPoints.get(rng.nextInt(spawnPoints.size()));

            if(canSpawn(spawnPoint))
            {
                spawnPoint = new Point2D.Double(spawnPoint.getX() * 32, spawnPoint.getY() * 32);
                Target target = stageTargets.get(rng.nextInt(stageTargets.size()));

                Visitor[] threadSafeVisitorArray = visitors.toArray(new Visitor[visitors.size()]);


                Visitor v = (new Visitor("Henk", "m", spawnPoint, tl.getTexture(1), target));
                v.setOtherVisitors(threadSafeVisitorArray);
                visitors.add(v);
            }
        }

        Visitor[] threadSafeVisitorArray = visitors.toArray(new Visitor[visitors.size()]);
        for(Visitor v : threadSafeVisitorArray)
        {
            //v.setOtherVisitors(threadSafeVisitorArray);
            v.update();

            if(v.isDestinationReached())
            {
                if(exitTargets.contains(v.getTarget()))
                {
                    visitors.remove(v);
                }

                // Calculate a chance
                int chance = ((int)(Math.random() * 100));

                if(chance <= 2)
                    v.setTarget(exitTargets.get(rng.nextInt(exitTargets.size())));
                else if(chance <= 5)
                    v.setTarget(stageTargets.get(rng.nextInt(stageTargets.size())));
                else
                    v.setTarget(stageTargets.get(rng.nextInt(stageTargets.size())));
            }
        }

        repaint();
    }

    private boolean canSpawn(Point2D newPosition) {
        Visitor[] threadSafeVisitorArray = visitors.toArray(new Visitor[visitors.size()]);
        for(Visitor v : threadSafeVisitorArray)
            if(v.position.distance(newPosition) < 64)
                return false;
        return true;
    }

    private void drawNodeMap(Graphics2D g)
    //private void drawNodeMap()
    {
        //System.out.println(overlay[0][0]);
        TileLayer layer = (TileLayer) map.getLayers().get(6);


        //X:1264,Y:2512 | Obstacle: false

        int ovalSize = 7;
        int stepSize = 32;

        //nodes = new TileNode[layer.getHeight()][layer.getWidth()];

        //g.setColor(Color.red);

        for(int y = 0; y < layer.getHeight(); y++)
        {
            for(int x = 0; x < layer.getWidth(); x++)
            {

                int posX = x * stepSize + (stepSize / 2);
                int posY = y * stepSize + (stepSize / 2);
                //TileNode node = new TileNode(posX, posY);

                if(layer.hasCollision(x, y))
                {
                    g.setColor(Color.red);
                    g.fillOval(posX - (ovalSize / 2), posY - (ovalSize / 2), ovalSize, ovalSize);

                    //node.setObstacle(false);

                    g.setColor(Color.white);
                    g.drawString("["+x+","+y+"]", x * 32, y * 32 +32);
                }
                else
                {
                    g.setColor(Color.blue);
                    g.fillOval(posX - (ovalSize / 2), posY - (ovalSize / 2), ovalSize, ovalSize);

                    //node.setObstacle(true);

                    g.setColor(Color.white);
                    g.drawString("["+x+","+y+"]", x * 32, y * 32 +32);
                }

                //nodes[y][x] = node;

                //System.out.println(node.toString());
            }
        }
    }

    public void drawOverlay(Graphics2D g, int[][] overlay)
    {
        g.setColor(Color.white);
        for(int x = 0; x < overlay.length; x++)
        {
            for(int y = 0; y < overlay.length; y++)
            {
                if(overlay[x][y] > -1) {
                    if(overlay[x][y] == 0)
                    {
                        g.setColor(Color.RED);
                        g.drawRect(x * 32, y * 32, 32, 32);
                        g.setColor(Color.white);
                        g.drawString(overlay[x][y] + "", x * 32 + (20 / 2), y * 32 + (40 / 2));
                    }
                    else
                    {
                        g.setColor(Color.white);
                        g.drawString(overlay[x][y] + "", x * 32 + (20 / 2), y * 32 + (40 / 2));
                    }
                }
            }
        }
    }
}