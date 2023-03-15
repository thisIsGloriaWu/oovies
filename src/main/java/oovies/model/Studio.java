package oovies.model;

public class Studio{

    protected int studioId;
    protected String name;
    protected String location;

    // This constructor can be used for reading records from MySQL, where we have all fields,
	// including the PostId.
    public Studio(int studioId, String name, String location){
        this.studioId = studioId;
        this.name = name;
        this.location = location;
    }

    // This constructor can be used for reading records from MySQL, where we only have the postId,
	// such as a foreign key reference to PostId.
	// Given PostId, we can fetch the full BlogPost record.
    public Studio(int studioId){
        this.studioId = studioId;
    }

    // This constructor can be used for creating new records, where the postId may not be
	// assigned yet since it is auto-generated by MySQL.
    public Studio(String name, String locaiton){
        this.name = name;
        this.locaiton = location;
    }


    public int getStudioId() {
        return this.studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Studio)) {
            return false;
        }
        Studio studio = (Studio) o;
        return studioId == studio.studioId && Objects.equals(name, studio.name) && Objects.equals(location, studio.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studioId, name, location);
    }


}