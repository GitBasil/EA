package myapplication.security.userservice;


public class Role {
    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Role{" +
                ", role='" + role + '\'' +
                '}';
    }
}
