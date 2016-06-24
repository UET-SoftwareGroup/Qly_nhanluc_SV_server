package uet.usercontroller.model;

import javax.persistence.*;

/**
 * Created by Tu on 03-May-16.
 */
@Entity
@Table(name="Partner")
public class Partner {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "partner_name")
    private String partner_name;
    @Column(name = "tax_code")
    private String tax_code;
    @Column(name = "director")
    private String director;
    @Column(name = "field_work")
    private String field_work;
    @Column(name = "website")
    private String website;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "fax")
    private String fax;
    @Column(name = "email")
    private String email;

    public int getId() {
        return id;
    }
    public int getUser_id() {
        return user_id;
    }
    public String getPartner_name() {
        return partner_name;
    }
    public String getTax_code() {
        return tax_code;
    }
    public String getDirector() {
        return director;
    }
    public String getField_work() {
        return field_work;
    }
    public String getWebsite() {
        return website;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getFax() {
        return fax;
    }
    public String getEmail() {
        return email;
    }
}

