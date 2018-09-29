package sg.edu.nus.iss.phoenix.user.entity;

/**
 * Created by wengweichen on 25/9/18.
 */

public class Role {
    private String role;
    private String accessPrivilege;

    public Role(String role) {
        this.role = role;
    }

    public Role(String role, String accessPrivilege) {
        this.role = role;
        this.accessPrivilege = accessPrivilege;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (role != null ? !role.equals(role1.role) : role1.role != null) return false;
        return accessPrivilege != null ? accessPrivilege.equals(role1.accessPrivilege) : role1.accessPrivilege == null;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (accessPrivilege != null ? accessPrivilege.hashCode() : 0);
        return result;
    }
}