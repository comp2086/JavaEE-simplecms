package cms.business;

import java.io.Serializable;

public class User implements Serializable {
    
    private String firstName;
    
    public User() {
        this.firstName = "";
    }
    
    public User(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
