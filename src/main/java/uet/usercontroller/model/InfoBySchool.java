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
    @Column(name="studentCode")
    private Integer studentCode;
    @Column(name="major")
    private String major;
    @Column(name="studentClass")
    private String studentClass;
    @Column(name="GPA")
    private Double GPA;
    @Column(name="diploma")
    private String diploma;
    @Column(name="grade")
    private String grade;
    @Column(name="graduationYear")
    private String graduationYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(Integer studentCode) {
        this.studentCode = studentCode;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Double getGPA() {
        return GPA;
    }

    public void setGPA(Double GPA) {
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

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
}
