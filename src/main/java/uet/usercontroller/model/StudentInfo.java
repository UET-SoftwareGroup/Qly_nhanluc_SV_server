package uet.usercontroller.model;


import sun.util.calendar.BaseCalendar;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by root on 7/20/16.
 */
@Entity
@Table(name = "StudentInfo")
public class StudentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fullName;

    private String birthday;

    private String phoneNumber;

    private String address;

    private String skype;

    private String email;

    private String desire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName){ this.fullName = fullName; }

    public String getFullName() { return fullName; }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesire() {
        return desire;
    }

    public void setDesire(String desire) {
        this.desire = desire;
    }



}
