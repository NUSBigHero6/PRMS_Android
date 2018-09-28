package sg.edu.nus.iss.phoenix.user.entity;

/**
 * Created by wengweichen on 25/9/18.
 */
import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private List<Role> roles = new ArrayList<Role>();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
