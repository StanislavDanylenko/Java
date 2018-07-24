package ua.nure.danylenko.Practice8.db.entity;

public class Group {

    private int id;

    private String name;

    public Group(){}

    public Group(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String login) {
        this.name = login;
    }

    public static Group createGroup(String user) {
        Group newUser = new Group(user);
        return newUser;
    }

    @Override
    public String toString() {
        return "Group [id=" + id + ", name=" + name + "]";
    }

}
