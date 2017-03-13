package assets;

import assets.simulation.Drawable;
import assets.simulation.SimulationObject;
import assets.simulation.Updatable;
import mapviewer.MapViewer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Bob, Bram en Michel on 6-2-2017.
 */

public class Visitor extends SimulationObject{
    private String name;
    private String sex;

    public Point2D position;
    private double speed;
    private double angle;
    private BufferedImage image;
    public Point2D destination;

    public Visitor(String name, String sex, Point2D position){
        this.name = name;
        this.sex = sex;

        this.position = position;
        this.speed = 5;
        this.angle = 0;
        this.image = null;
        this.destination = new Point2D.Double(0, 0);

        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/characters/m_visitor_32x32.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public void update() {
        double dx = destination.getX() - position.getX();
        double dy = destination.getY() - position.getY();

        double newAngle = Math.atan2(dy, dx);

        double difference = newAngle - angle;
        while(difference < -Math.PI)
            difference += 2 * Math.PI;
        while(difference > Math.PI)
            difference -= 2 * Math.PI;

        if(difference > 0.1)
            angle += 0.1;
        else if(difference < -0.1)
            angle -= 0.1;
        else
            angle = newAngle;

        Point2D newPosition = new Point2D.Double(
                position.getX() + speed * Math.cos(angle),
                position.getY() + speed * Math.sin(angle));

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

        if(!collided)
            position = newPosition;
        else
        {
            angle += 0.2;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform old = g.getTransform();
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX(), position.getY());
        tx.rotate(angle);
        tx.translate(-image.getWidth()/2, -image.getHeight()/2);
        g.drawImage(image, tx, null);
        g.setTransform(old);
    }
}
