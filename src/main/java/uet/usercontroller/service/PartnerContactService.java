package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.PartnerContactDTO;
import uet.usercontroller.DTO.PartnerInfoDTO;
import uet.usercontroller.model.*;
import uet.usercontroller.repository.PartnerContactRepository;
import uet.usercontroller.repository.PartnerInfoRepository;
import uet.usercontroller.repository.PartnerRepository;
import uet.usercontroller.repository.UserRepository;

import java.util.List;

/**
 * Created by fgv on 9/2/2016.
 */
@Service
public class PartnerContactService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    private PartnerContactRepository partnerContactRepository;

    //show all partner contact
    public List<PartnerContact> getpartnerContacts(){
        List<PartnerContact> allPartners = (List<PartnerContact>) partnerContactRepository.findAll();
        return allPartners;
    }

    //show list post of a partner
    public List<PartnerContact> showAllContact(int partnerId) {
        Partner partner = partnerRepository.findById(partnerId);
        return partner.getPartnerContacts();
    }

    //show a partner contact
    public PartnerContact showContact(int partnerContactId){
        PartnerContact partnerContact = partnerContactRepository.findOne(partnerContactId);
        return partnerContact;
    }

    //create a partner contact
    public PartnerContact createContact(int partnerId, PartnerContactDTO partnerContactDTO, String token){
        User user = userRepository.findByToken(token);
        Partner partner = partnerRepository.findOne(partnerId);
        if (user.getPartner().equals(partner)){
            PartnerContact partnerContact = new PartnerContact();
            partnerContact.setAddress(partnerContactDTO.getAddress());
            partnerContact.setPartnerId(partnerId);
            partnerContact.setContactName(partnerContactDTO.getContactName());
            partnerContact.setEmail(partnerContactDTO.getEmail());
            partnerContact.setSkype(partnerContactDTO.getSkype());
            return partnerContactRepository.save(partnerContact);
        }
        else{
            throw new NullPointerException("User doesn't match with Partner.");
        }
    }

    //edit contact of a partner
    public PartnerContact editContact(int partnerContactId, PartnerContactDTO partnerContactDTO, String token){
        User user = userRepository.findByToken(token);
        PartnerContact partnerContact = partnerContactRepository.findOne(partnerContactId);
        Partner partner1 = partnerRepository.findByPartnerContactsId(partnerContactId);
        if ( user.getPartner().equals(partner1)){
            partnerContact.setAddress(partnerContactDTO.getAddress());
            partnerContact.setContactName(partnerContactDTO.getContactName());
            partnerContact.setEmail(partnerContactDTO.getEmail());
            partnerContact.setSkype(partnerContactDTO.getSkype());
            return partnerContactRepository.save(partnerContact);
        }
        else {
            throw new NullPointerException("User doesn't match with Partner");
        }
    }

    //delete contact of a partner
    public void deleteContact(int partnerContactId, String token){
        User user = userRepository.findByToken(token);
        PartnerContact partnerContact = partnerContactRepository.findOne(partnerContactId);
        Partner partner = partnerRepository.findByPartnerContactsId(partnerContactId);
        if(user.getPartner().equals(partner)){
            partnerContactRepository.delete(partnerContact);
        }
       else{
            throw new NullPointerException("User doesn't match with Partner");
        }
    }
}
