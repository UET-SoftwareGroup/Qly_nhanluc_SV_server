package uet.usercontroller.model;

import javax.persistence.*;

/**
 * Created by Tu on 20-May-16.
 */
@Entity
@Table(name="Student")
public class Student {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;
    @Column(name = "student_name")
    private String student_name;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "address")
    private String address;
    @Column(name = "skype")
    private String skype;
    @Column(name = "email")
    private String email;
    @Column(name = "desire")
    private String desire;


    public int getId() { return id; }
    public int getUser_id() { return user_id; }
    public String getStudent_name() { return student_name; }
    public String getBirthday() { return birthday; }
    public String getPhone_number() { return phone_number; }
    public String getAddress() { return address; }
    public String getSkype() { return skype; }
    public String getEmail() { return email; }
    public String getDesire() { return desire; }


}
