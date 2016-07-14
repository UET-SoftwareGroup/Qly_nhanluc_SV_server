package uet.usercontroller.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fgv on 7/8/2016.
 */
@Entity
@Table(name="JobSkill")
public class JobSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "id")
    private int id;
    @Column(name = "studentId")
    private int studentId;
    @Column(name = "skill")
    private String skill;
    @Column(name = "company")
    private String company;
    @Column(name = "updateTime")
    private Date updateTime;

    public JobSkill(){
    }
    public JobSkill(int id, int studentId, String skill, String company, Date updateTime){
        this.id=id;
        this.studentId=studentId;
        this.skill=skill;
        this.company=company;
        this.updateTime=updateTime;
    }

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