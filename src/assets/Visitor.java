package assets;

import assets.mapviewer.Target;
import assets.simulation.Drawable;
import assets.simulation.Texture;
import assets.simulation.Updatable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Bob, Bram en Michel on 6-2-2017.
 */

public class Visitor implements Updatable, Drawable {
    private String name;
    private String sex;

    public Point2D position;
    private double speed;
    private double angle;
    private Texture texture;
    public Point2D destination;

    private Target target;
    private int currentDistance;


    private Visitor[] otherVisitors;

    private boolean destinationReached;

    public Visitor(String name, String sex, Point2D position, Texture texture){
        this.name = name;
        this.sex = sex;

        this.position = position;
        this.speed = 5;
        this.angle = 0;
        this.destination = new Point2D.Double(0, 0);
        this.texture = setColorRandom(texture);
        this.destinationReached = false;
    }

    public Visitor(String name, String sex, Point2D position, Texture texture, Target target)
    {
        this(name, sex, position, texture);
        this.target = target;
        this.texture = setColorRandom(texture);
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public void update()
    {
        if (target != null && target.getData() != null)
        {
            int x = (int) Math.floor(position.getX() / 32);
            int y = (int) Math.floor(position.getY() / 32);

            if (!(x < 0 || y < 0 || x >= target.getData().length || y >= target.getData().length))
            {
                currentDistance = target.getData()[x][y];

                if(currentDistance == 0)
                {
                    destinationReached = true;
                    // Force AI algorithm to look around
                    currentDistance = -2;
                }

                // Initialise an array containing all possible direction.
                int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // [-1][0] = left, [1][0] = right, [0][-1] = down, [0][1] = up


                //BEGIN - vieze johan code
                Random random = new Random(x + y + hashCode());
                for(int i = 0; i < 10; i++) {
                    int a = random.nextInt(4);
                    int b = random.nextInt(4);

                    int cx = offsets[a][0];
                    int cy = offsets[a][1];
                    offsets[a][0] = offsets[b][0];
                    offsets[a][1] = offsets[b][1];
                    offsets[b][0] = cx;
                    offsets[b][1] = cy;
                }
                // EIND - vieze johan code


                // Iterate all possible directions
                for (int i = 0; i < offsets.length; i++) {
                    int desX = x + offsets[i][0];
                    int desY = y + offsets[i][1];

                    if (desX < 0 || desX >= target.getData().length || desY < 0 || desY >= target.getData().length)
                        continue;
                    if (target.getData()[desX][desY] >= currentDistance)
                        continue;
                    if (target.getData()[desX][desY] < 0)
                        continue;

                    destination = new Point2D.Double(desX * 32, desY * 32);
                    break;
                }
            }
        }

        double dx = destination.getX() - position.getX() + 16;
        double dy = destination.getY() - position.getY() + 16;

        double newAngle = Math.atan2(dy, dx);

        double difference = newAngle - angle;
        while (difference < -Math.PI)
            difference += 2 * Math.PI;
        while (difference > Math.PI)
            difference -= 2 * Math.PI;

        if (difference > 0.1)
            angle += 0.5;
        else if (difference < -0.1)
            angle -= 0.5;
        else
            angle = newAngle;

        double newX = position.getX() + speed * Math.cos(angle);
        double newY = position.getY() + speed * Math.sin(angle);

        Point2D newPosition = new Point2D.Double(
                newX,
                newY);

        boolean collided = false;

//        if(otherVisitors != null && otherVisitors.length > 0) {
//            for (Visitor v : otherVisitors) {
//                if (v == this)
//                    continue;
//                if (v.position.distance(newPosition) < 33) {
//                    collided = true;
//                    break;
//                }
//            }
//        }

        int x = (int) Math.floor(newPosition.getX() / 32);
        int y = (int) Math.floor(newPosition.getY() / 32);
        if(target.getData()[x][y] == -1)
            collided = true;


        if(!collided)
            position = newPosition;
        else
        {
            angle += 1.0;
        }
    }

    @Override
    public void draw(Graphics2D g) {

        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX(), position.getY());
        tx.rotate(angle);
        tx.translate(-texture.getImage().getWidth()/2, -texture.getImage().getHeight()/2);
        g.drawImage(texture.getImage(), tx, null);
//        g.setColor(Color.red);
//        g.drawRect((int)position.getX() - 16, (int)position.getY() - 16, texture.getImage().getTileWidth(), texture.getImage().getTileHeight());
//        g.setColor(Color.green);
//        g.fillOval((int)position.getX() - (5 / 2), (int)position.getY() - (5 / 2), 5, 5);
//        g.setColor(Color.pink);
//        g.fillOval((int)destination.getX() + 16, (int)destination.getY() + 16, 5, 5);
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target overlay)
    {
        destinationReached = false;
        this.target = overlay;
    }

    public boolean isDestinationReached() {
        return destinationReached;
    }

    public void setOtherVisitors(Visitor[] otherVisitors) {
        this.otherVisitors = otherVisitors;
    }

    public Texture setColorRandom(Texture texture){

        BufferedImage image = texture.getImage();
        int width = image.getWidth();
        int height = image.getHeight();
        int newColor = Color.getHSBColor((float)(Math.random() * 500.0f), 1, 1).getRGB();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color originalColor = new Color(image.getRGB(x, y));
                if (originalColor.equals(Color.green)) {
                    image.setRGB(x, y,newColor);
                }
            }
        }
        texture.setImage(image);
        return texture;

    }



    }
