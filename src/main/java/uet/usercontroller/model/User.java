package uet.usercontroller.model;

import javax.persistence.*;

/**
 * Created by Tu on 02-May-16.
 */
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="username")
    private String username;
    @Column(name="realname")
    private String realname;
    @Column(name="age")
    private int age;
    @Column(name="sex")
    private int sex;
    @Column(name="email")
    private String email;
    @Column(name="course")
    private String course;
    @Column(name="admin")
    private int admin;

    public int getId() { return id; }
    public String getUsername(){
        return username;
    }
    public String getRealname() { return realname; }
    public int getAge() { return age; }
    public int getSex() { return sex; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public int getAdmin() { return admin; }
}
