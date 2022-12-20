package src;

public class Venue {
    // attributes
    protected String name;
    protected String location;

    // constructor
    public Venue(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // methods
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
