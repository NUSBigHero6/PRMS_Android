package sg.edu.nus.iss.phoenix.user.entity;

/**
 * Created by wengweichen on 25/9/18.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {
    private String id;
    private String name;
    private String password;
    private ArrayList<Role> roles = new ArrayList<Role>();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String id, String name, String password, ArrayList<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public JSONArray getRolesJson() {
        JSONArray userRoles = new JSONArray();
        try {
            for (int i = 0; i < roles.size(); i++) {
                Role role = roles.get(i);
                JSONObject userRoleJson = new JSONObject();
                userRoleJson.put("role", role.getRole());
                userRoleJson.put("accessPrivilege", role.getAccessPrivilege());

                userRoles.put(userRoleJson);
            }
        }catch (JSONException ex){}
        return userRoles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }
}