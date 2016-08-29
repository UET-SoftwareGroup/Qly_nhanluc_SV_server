package uet.usercontroller.DTO;

import java.util.Date;

/**
 * Created by Trung on 8/29/2016.
 */
public class PostDTO {
    private int id;
    private String content;
    private Date datePost;
    private String describle;

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

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }
}
