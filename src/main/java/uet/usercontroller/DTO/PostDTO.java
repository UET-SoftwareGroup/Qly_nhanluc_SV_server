package uet.usercontroller.DTO;

import uet.usercontroller.model.Partner;

import java.sql.Blob;
import java.util.Date;

/**
 * Created by Trung on 8/29/2016.
 */
public class PostDTO {
    private int id;
    private String content;
    private Date datePost;
    private String describePost;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Date getDatePost() { return datePost; }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public String getDescribePost() { return describePost; }

    public void setDescribePost(String describePost) { this.describePost = describePost; }

    public String getImage() {
        return image;
    }

    public void setImage(String image) { this.image = image; }
}
