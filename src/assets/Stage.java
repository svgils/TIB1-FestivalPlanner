package assets;

/**
 * Created by $Bob, Bram en Michel on $6-2-2017.
 */

public class Stage  {
    private String name;
    private String location;

    public Stage(String name, String location){
        this.name = name;
        this.location = location;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

}
