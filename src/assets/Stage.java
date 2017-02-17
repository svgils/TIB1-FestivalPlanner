package assets;

/**
 * Created by $Bob, Bram en Michel on $6-2-2017.
 */

public class Stage  {
    public int index;
    private String name;
    private String location;

    public Stage(String name, String location, int index){
        this.name = name;
        this.location = location;
        this.index = index;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

}
