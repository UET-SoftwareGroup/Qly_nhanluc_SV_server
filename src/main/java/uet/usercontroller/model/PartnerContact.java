package uet.usercontroller.model;

import javax.persistence.*;

/**
 * Created by fgv on 8/30/2016.
 */
@Entity
@Table(name="partnerContact")
public class PartnerContact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int partnerId;
    private String contactName;
    private String address;
    private String skype;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartnerId() { return partnerId; }

    public void setPartnerId(int partnerId) { this.partnerId = partnerId; }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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
}
