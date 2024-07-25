package FinalPractice.classes;

public class Item {
    private String id;
    private String name;
    private String username;

    public Item() {
    }

    public Item(String id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", username='" + getUsername() + "'" +
            "}";
    }
}
