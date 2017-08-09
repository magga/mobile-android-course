package id.magga.settingupfirebase;

/**
 * Created by magga on 8/9/2017.
 */

public class User {
    private String id;
    private String name;
    private String organization;

    public User(String id, String name, String organization) {
        this.id = id;
        this.name = name;
        this.organization = organization;
    }

    public User() {
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
