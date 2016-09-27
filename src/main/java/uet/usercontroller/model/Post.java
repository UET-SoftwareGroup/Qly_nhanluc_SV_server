package uet.usercontroller.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Trung on 8/27/2016.
 */
@Entity
@Table(name="Post")
public class Post {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name="datePost")
    private Date datePost;

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    @Column(name="describePost")
    private String describePost;

    public String getDescribePost() {
        return describePost;
    }

    public void setDescribePost(String describePost) {
        this.describePost = describePost;
    }

    @ManyToOne
    @Column(name="partnerId")
    private Partner partner;

    public Partner getPartner() { return partner; }

    public void setPartner(Partner partner) { this.partner = partner; }
}
