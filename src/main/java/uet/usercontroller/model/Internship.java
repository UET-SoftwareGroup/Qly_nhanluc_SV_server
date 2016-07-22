package uet.usercontroller.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fgv on 7/11/2016.
 */
@Entity
@Table(name="Internship")
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;
    @Column(name="studentId")
    private int studentId;

    @Column(name="partnerId")

    private int partnerId;
    @Column(name="company")
    private String company;
    @Column(name="startDate")
    private Date startDate;
    @Column(name="endDate")
    private Date  endDate;
    @Column(name="Supervisor")
    private String supervisor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

}
