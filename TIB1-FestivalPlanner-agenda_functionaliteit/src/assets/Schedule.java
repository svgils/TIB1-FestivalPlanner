package assets;

import java.util.ArrayList;

/**
 * Created by Michel on 13-2-2017.
 */
public class Schedule {
    private ArrayList<Performance> performances;

    public Schedule() {
        performances = new ArrayList<>();
    }

    public void addPerformance(Performance performance)
    {
        performances.add(performance);
    }

    public Performance get(int index)
    {
        return performances.get(index);
    }
}
