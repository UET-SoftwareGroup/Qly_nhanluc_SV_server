package uet.usercontroller.DTO;

import java.util.Date;

/**
 * Created by fgv on 7/8/2016.
 */
public class JobSkillDTO {
    private int id;
    private String company;
    private String skill;
    private Date updateTime;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }
}
