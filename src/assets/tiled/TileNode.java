package assets.tiled;

import java.util.AbstractCollection;
import java.util.ArrayList;

/**
 * Created by Michel on 13-3-2017.
 */
public class TileNode {
    private int x;
    private int y;
    private boolean visited;
    private boolean obstacle;
    private ArrayList<TileNode> neighbourNodes;

    public TileNode(int positionX, int positionY)
    {
        this.x = positionX;
        this.y = positionY;
        this.neighbourNodes = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void AddNeighbourNode(TileNode node)
    {
        this.neighbourNodes.add(node);
    }

    public ArrayList<TileNode> getNeighbourNodes()
    {
        return this.neighbourNodes;
    }


    public boolean isObstacle() {
        return obstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    @Override
    public String toString() {
        return "X:"+x+",Y:"+y+" | Obstacle: " + obstacle;
    }
}
