package uet.usercontroller.model;

/**
 * Created by Tu on 28-Jun-16.
 */

import javax.persistence.*;

@Entity
@Table(name="PartnerInfo")
public class PartnerInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
//    @Column(name = "partner_id")
//    private int partner_id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public void setTax_code(String tax_code) {
        this.tax_code = tax_code;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setField_work(String field_work) {
        this.field_work = field_work;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public String getFax() { return fax; }
    public String getEmail() {
        return email;
    }
}
