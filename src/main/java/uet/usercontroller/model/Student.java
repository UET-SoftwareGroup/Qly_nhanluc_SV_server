package uet.usercontroller.model;

import javax.persistence.*;

/**
 * Created by Tu on 20-May-16.
 */
@Entity
@Table(name="student_detail")
public class Student {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "full_name")
    private String full_name;
    @Column(name = "age")
    private int age;
    @Column(name = "course")
    private String course;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    public int getId() { return id; }
    public String getFull_name() { return full_name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

}
