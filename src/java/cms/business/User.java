package cms.business;

import java.io.Serializable;

public class User implements Serializable {
    
    private int id;
    private String firstName;
    
    public User() {
        this.firstName = "";
    }
    
    public User(String firstName) {
        this.firstName = firstName;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
