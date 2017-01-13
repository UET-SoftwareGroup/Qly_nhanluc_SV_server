package uet.usercontroller.model;

import javax.persistence.*;
import java.util.Date;
/**
 * Created by fgv on 7/8/2016.
 */
@Entity
@Table(name="JobSkill")
public class JobSkill{
    private int id;
    private String skill;
    private String company;
    private Date updateTime;
    private int studentId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    //    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "studentId")


    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}