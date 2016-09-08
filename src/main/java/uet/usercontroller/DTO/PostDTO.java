package uet.usercontroller.DTO;

import java.util.Date;

/**
 * Created by Trung on 8/29/2016.
 */
public class PostDTO {
    private int id;
    private String content;
    private String datePost;
    private String describePost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatePost() { return datePost; }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getDescribePost() { return describePost; }

    public void setDescribePost(String describePost) { this.describePost = describePost; }
}
