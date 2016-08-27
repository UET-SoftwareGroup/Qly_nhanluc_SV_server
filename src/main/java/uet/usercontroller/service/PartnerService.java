package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.PartnerDTO;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.PartnerRepository;
import uet.usercontroller.repository.UserRepository;

import java.util.List;

/**
 * Created by Tu on 28-Jun-16.
 */
@Service
public class PartnerService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PartnerRepository partnerRepository;

    //show list all partner
    public List<Partner> getPartners(){
        List<Partner> allPartners = (List<Partner>) partnerRepository.findAll();
        return allPartners;
    }

    //create a partner
    public Partner createPartner(int userId, PartnerDTO partnerDTO){
        User user = userRepository.findOne(userId);
        Partner partner = new Partner();
        partner.setPartnerName(partnerDTO.getPartnerName());
        //setPartnerInfo sau khi da noi bang PartnerInfo
        user.setPartner(partner);
        return partnerRepository.save(partner);
    }

    //edit a partner name
    public Partner editPartner(int partnerId, PartnerDTO partnerDTO, String token){
        User user = userRepository.findByToken(token);
        Partner partner = partnerRepository.findOne(partnerId);
        if ( user.getPartner().equals(partner)) {
            if (partnerDTO.getPartnerName() != null) {
                partner.setPartnerName(partnerDTO.getPartnerName());
            }
        }
        return partnerRepository.save(partner);
    }

    //show a partner
    public Partner showPartner(int partnerId){
        Partner partner = partnerRepository.findOne(partnerId);
        return partner;
    }

    //delete a partner
    public void deletePartner(int partnerId){
        Partner partner = partnerRepository.findOne(partnerId);
        partner.setPartnerName(null);
        partnerRepository.save(partner);
    }
}
