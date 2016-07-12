package uet.usercontroller.model;

import javax.persistence.*;

/**
 * Created by Trung on 7-8-2016.
 */

@Entity
@Table(name="Student")
public class Student {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "userId")
    private int userId;
    @Column(name = "studentName")
    private String studentName;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getId() {    return id; }

    public int getUserId() {
        return userId;
    }

    public String getStudentName() {
        return studentName;
    }
}
