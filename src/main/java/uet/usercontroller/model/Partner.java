package uet.usercontroller.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "partnerName")
    private String partnerName;
    @OneToOne(cascade = CascadeType.ALL)
    private PartnerInfo partnerInfo;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> post;

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<PartnerContact> partnerContacts = new ArrayList<PartnerContact>();

    public List<PartnerContact> getPartnerContacts() {
        return partnerContacts;
    }

    public void setPartnerContacts(List<PartnerContact> partnerContacts) {
        this.partnerContacts = partnerContacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public PartnerInfo getPartnerInfo() {
        return partnerInfo;
    }

    public void setPartnerInfo(PartnerInfo partnerInfo) {
        this.partnerInfo = partnerInfo;
    }

}



