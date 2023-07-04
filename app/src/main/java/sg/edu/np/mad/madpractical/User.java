package sg.edu.np.mad.madpractical;

public class User {
    public String name;
    public String description;
    public int id;
    public boolean followed = false;

    public User(){}

    public User(String Name, String Description, int Id, boolean Followed){
        name = Name;
        description = Description;
        id = Id;
        followed = Followed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }
}
