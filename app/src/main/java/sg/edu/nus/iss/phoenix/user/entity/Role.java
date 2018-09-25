package sg.edu.nus.iss.phoenix.user.entity;

/**
 * Created by wengweichen on 25/9/18.
 */

public class Role {
    private String role;
    private String accessPrivilege;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccessPrivilege() {
        return accessPrivilege;
    }

    public void setAccessPrivilege(String accessPrivilege) {
        this.accessPrivilege = accessPrivilege;
    }
}
