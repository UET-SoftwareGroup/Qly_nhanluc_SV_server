package uet.usercontroller.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tu on 02-May-16.
 */
@Entity
@Table(name="User")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private int role;
    @Column(name = "token")
    private String token;
    @Column(name = "expiry_time")
    private String expiry_time;

    public int getId() { return id; }
    public String getUsername() { return user_name; }
    public String getPassword() {
        return password;
    }
    public int getRole() {
        return role;
    }
    public String getToken() {
        return token;
    }
    public String getExpiry_time() {
        return expiry_time;
    }
}
