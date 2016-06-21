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
    @Column(name = "partner_name")
    private String partner_name;
    @Column(name = "website")
    private String website;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;

    public int getId() {
        return id;
    }

    public String getPartner_name() {
        return partner_name;
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

    public String getEmail() {
        return email;
    }
}

