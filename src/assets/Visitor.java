package assets;

import assets.mapviewer.Overlay;
import assets.simulation.Drawable;
import assets.simulation.Texture;
import assets.simulation.Updatable;

import javax.imageio.ImageIO;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    private Overlay overlay;
    private int currentDistance;
    private Point2D localPosition;

    public Visitor(String name, String sex, Point2D position, Texture texture){
        this.name = name;
        this.sex = sex;

        this.position = position;
        this.speed = 5;
        this.angle = 0;
        this.destination = new Point2D.Double(0, 0);
        this.texture = texture;
    }

    public Visitor(String name, String sex, Point2D position, Texture texture, Overlay overlay)
    {
        this(name, sex, position, texture);
        this.overlay = overlay;
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
        if (overlay != null)
        {
            int x = (int) Math.floor(position.getX() / 32);
            int y = (int) Math.floor(position.getY() / 32);

            if (!(x < 0 || y < 0 || x >= overlay.getData().length || y >= overlay.getData().length)) {

                //
                currentDistance = overlay.getData()[x][y];

                // Initialise an array containing all possible direction.
                int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // [-1][0] = left, [1][0] = right, [0][-1] = down, [0][1] = up
//vieze johan code
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
                // Iterate all possible directions
                for (int i = 0; i < offsets.length; i++) {
                    int desX = x + offsets[i][0];
                    int desY = y + offsets[i][1];

                    if(currentDistance == 0)
                        speed = 0;

                    if (desX < 0 || desX >= overlay.getData().length || desY < 0 || desY >= overlay.getData().length)
                        continue;
                    if (overlay.getData()[desX][desY] >= currentDistance)
                        continue;
                    if (overlay.getData()[desX][desY] < 0)
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

//        for(Visitor v : mapViewer.visitors)
//        {
//            if(v == this)
//                continue;
//            if(v.position.distance(newPosition) < 32) {
//                collided = true;
//                break;
//            }
//        }

        int x = (int) Math.floor(newPosition.getX() / 32);
        int y = (int) Math.floor(newPosition.getY() / 32);
        if(overlay.getData()[x][y] == -1)
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
        g.setColor(Color.red);
        g.drawRect((int)position.getX() - 16, (int)position.getY() - 16, texture.getImage().getTileWidth(), texture.getImage().getTileHeight());
        g.setColor(Color.green);
        g.fillOval((int)position.getX() - (5 / 2), (int)position.getY() - (5 / 2), 5, 5);

        g.setColor(Color.pink);
        g.fillOval((int)destination.getX(), (int)destination.getY(), 5, 5);
    }

    public Overlay getOverlay() {
        return overlay;
    }

    public void setOverlay(Overlay overlay) {
        this.overlay = overlay;
    }
}
