package uet.usercontroller.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Tu on 27-Aug-16.
 */
public class PartnerDTO {
    private PartnerInfoDTO partnerInfoDTO;
    private PartnerContactDTO partnerContactDTO;

    public PartnerInfoDTO getPartnerInfoDTO() {
        return partnerInfoDTO;
    }

    public void setPartnerInfoDTO(PartnerInfoDTO partnerInfoDTO) {
        this.partnerInfoDTO = partnerInfoDTO;
    }

    public PartnerContactDTO getPartnerContactDTO() {
        return partnerContactDTO;
    }

    public void setPartnerContactDTO(PartnerContactDTO partnerContactDTO) {
        this.partnerContactDTO = partnerContactDTO;
    }

    private PostDTO postDTO;

    public PostDTO getPostDTO() {
        return postDTO;
    }

    public void setPostDTO(PostDTO postDTO) {
        this.postDTO = postDTO;
    }

    private int id;

    private String partnerName;

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
}
