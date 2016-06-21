package uet.usercontroller.model;

import javax.persistence.*;

/**
 * Created by Tu on 02-May-16.
 */
@Entity
@Table(name="user")
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

    public int getId() {
        return id;
    }

    public String getUsername() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }
}
