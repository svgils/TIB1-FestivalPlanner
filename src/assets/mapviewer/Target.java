package assets.mapviewer;

import assets.tiled.TileLayer;
import assets.tiled.TileMap;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Michel on 17-3-2017.
 */
public class Target {
    private TileMap map;
    private int[][] data;

    public Target(TileMap map) {
        this.map = map;
    }

    public Target(TileMap map, int endPointX, int endPointY)
    {
        this(map);
        data = generateOverlay(endPointX, endPointY);
    }

    /**
     * Generate overlay based on a simplified X/Y coordinate system.
     *
     *
     * @param endPointX either 0 or TileMap max column
     * @param endPointY either 0 or TileMap max row
     * @return
     */
    public int[][] generateOverlay(int endPointX, int endPointY) {
        if(map != null || map.getLayers().size() < 1)
            return null;

        // Get the required layer of the map
        TileLayer layer = (TileLayer) map.getLayers().get(6);

        // Initialise the overlay data with the same size as the layer
        data = new int[layer.getWidth()][layer.getHeight()];

        // Initialise 2d overlay array with impassable tiles
        for (int x = 0; x < layer.getHeight(); x++) {
            for (int y = 0; y < layer.getWidth(); y++) {
                data [x][y] = -1;
            }
        }

        ArrayList<Point> toVisit = new ArrayList<>();

        data[endPointX][endPointY] = 0;
        toVisit.add(new Point(endPointX, endPointY));

        // As long as the list has > 0 items, keep going until the list is empty.
        while (!toVisit.isEmpty()) {
            // Retrieve first element in the list and remove it.
            Point currentPoint = toVisit.get(0);
            toVisit.remove(0);

            // Initialise current point and get the distance.
            int currentDistance = data[currentPoint.x][currentPoint.y];

            // Initialise an array containing all possible direction.
            int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // [-1][0] = left, [1][0] = right, [0][-1] = down, [0][1] = up

            // Iterate all possible directions
            for (int i = 0; i < offsets.length; i++) {
                int newX = currentPoint.x + offsets[i][0];
                int newY = currentPoint.y + offsets[i][1];

                if (newX < 0 || newX >= layer.getWidth() || newY < 0 || newY >= layer.getHeight())
                    continue;
                if (data[newX][newY] >= 0)
                    continue;
                if (layer.hasCollision(newX, newY))
                    continue;

                data[newX][newY] = currentDistance + 1;
                toVisit.add(new Point(newX, newY));
            }
        }

        return data;
    }

    public int[][] getData()
    {
        return data;
    }
}