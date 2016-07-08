package uet.usercontroller.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tu on 28-Jun-16.
 */
@Entity
@Table(name="InfoBySchool")
public class InfoBySchool {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="studentId")
    private int studentId;
    @Column(name="studentCode")
    private int studentCode;
    @Column(name="major")
    private String major;
    @Column(name="studentClass")
    private String studentClass;
    @Column(name="GPA")
    private double GPA;
    @Column(name="diploma")
    private String diploma;
    @Column(name="grade")
    private String grade;
    @Column(name="graduationYear")
    private Date graduationYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(int studentCode) {
        this.studentCode = studentCode;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Date graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
}
