package assets.mapviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Michel on 20-2-2017.
 */
public class Camera implements MouseListener, MouseMotionListener, MouseWheelListener
{
    private Point2D centerPoint = new Point2D.Double(0.0D, 0.0D);
    private double zoom = 1.0D;
    private Point2D lastMousePos;
    private JPanel panel;
    private double maxZoom;
    private double minZoom;

    public Camera(JPanel panel)
    {
        this(panel, 1.0D, new Point2D.Double(0.0D, 0.0D));
    }

    public Camera(JPanel panel, double zoom, Point2D centerPoint)
    {
        this.panel = panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseWheelListener(this);

        maxZoom = Double.MAX_VALUE;
        minZoom = Double.MIN_VALUE;

        this.zoom = zoom;
        this.centerPoint = centerPoint;
    }

    public AffineTransform getTransform(int windowWidth, int windowHeight)
    {
        AffineTransform tx = new AffineTransform();
        tx.translate(windowWidth / 2, windowHeight / 2);
        tx.scale(this.zoom, this.zoom);
        tx.translate(this.centerPoint.getX(), this.centerPoint.getY());

        return tx;
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e)
    {
        this.lastMousePos = e.getPoint();
    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseDragged(MouseEvent e)
    {
        if (SwingUtilities.isMiddleMouseButton(e) || SwingUtilities.isLeftMouseButton(e))
        {
            this.centerPoint = new Point2D.Double(
                    this.centerPoint.getX() - (this.lastMousePos.getX() - e.getX()) / this.zoom,
                    this.centerPoint.getY() - (this.lastMousePos.getY() - e.getY()) / this.zoom);

            this.lastMousePos = e.getPoint();

            //this.panel.repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {  }

    public void mouseWheelMoved(MouseWheelEvent e)
    {
        this.zoom *= (1.0F - e.getWheelRotation() / 25.0F);

        //this.panel.repaint();
    }

    public double getZoom()
    {
        return this.zoom;
    }

    public Point2D getCenterPoint()
    {
        return this.centerPoint;
    }

    public Point2D getMousePoint()
    {
        return this.lastMousePos;
    }

    public double getMaxZoom() {
        return maxZoom;
    }

    public void setMaxZoom(double maxZoom) {
        this.maxZoom = maxZoom;
    }

    public double getMinZoom() {
        return minZoom;
    }

    public void setMinZoom(double minZoom) {
        this.minZoom = minZoom;
    }

}
