//package uet.usercontroller.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import uet.usercontroller.DTO.PartnerContactDTO;
//import uet.usercontroller.DTO.PartnerInfoDTO;
//import uet.usercontroller.model.*;
//import uet.usercontroller.repository.PartnerContactRepository;
//import uet.usercontroller.repository.PartnerInfoRepository;
//import uet.usercontroller.repository.PartnerRepository;
//import uet.usercontroller.repository.UserRepository;
//
//import java.util.List;
//
///**
// * Created by fgv on 9/2/2016.
// */
//@Service
//public class PartnerContactService {
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PartnerRepository partnerRepository;
//    @Autowired
//    private PartnerContactRepository partnerContactRepository;
//
//    //show all partner contact
//    public List<PartnerContact> getpartnerContacts(){
//        List<PartnerContact> allPartners = (List<PartnerContact>) partnerContactRepository.findAll();
//        return allPartners;
//    }
//
//    //show a partner contact
//    public PartnerContact showContact(int partnerContactId){
//        PartnerContact partnerContact = partnerContactRepository.findOne(partnerContactId);
//        return partnerContact;
//    }
//
//    //create a partner contact
//    public PartnerContact createContact(int partnerId, PartnerContactDTO partnerContactDTO, String token){
//        User user = userRepository.findByToken(token);
//        Partner partner = partnerRepository.findOne(partnerId);
//        if (user.getPartner().equals(partner) && partnerContactDTO.getPartnerId()==partnerId){
//            PartnerContact partnerContact = new PartnerContact();
//            partnerContact.setId(partnerContactDTO.getId());
//            partnerContact.setPartnerId(partnerContactDTO.getPartnerId());
//            partnerContact.setAddress(partnerContactDTO.getAddress());
//            partnerContact.setContactName(partnerContactDTO.getContactName());
//            partnerContact.setEmail(partnerContactDTO.getEmail());
//            partnerContact.setSkype(partnerContactDTO.getSkype());
//            partnerRepository.save(partner);
//            return partnerContactRepository.save(partnerContact);
//
//        }
//        else{
//            throw new NullPointerException("User doesn't match with Partner.");
//        }
//    }
//
//    //edit contact of a partner
//    public PartnerContact editContact(int partnerContactId, PartnerContactDTO partnerContactDTO, String token){
//        User user = userRepository.findByToken(token);
//        Partner partner = user.getPartner();
//        PartnerContact  partnerContact = partnerContactRepository.findOne(partnerContactId);
//        if ( partnerContact.getId()==partnerContactDTO.getId() && partner.getId()==partnerContactDTO.getPartnerId()){
//            partnerContact.setId(partnerContactDTO.getId());
//            partnerContact.setPartnerId(partnerContactDTO.getPartnerId());
//            partnerContact.setAddress(partnerContactDTO.getAddress());
//            partnerContact.setContactName(partnerContactDTO.getContactName());
//            partnerContact.setEmail(partnerContactDTO.getEmail());
//            partnerContact.setSkype(partnerContactDTO.getSkype());
//            return partnerContactRepository.save(partnerContact);
//        }
//        else {
//            throw new NullPointerException("Can't edit contact");
//        }
//    }
//
//    //delete contact of a partner
//    public PartnerContact deleteContact(int partnerContactId, String token){
//        User user = userRepository.findByToken(token);
//        Partner partner =user.getPartner();
//        PartnerContact partnerContact = partnerContactRepository.findOne(partnerContactId);
//        if(partner.getId()== partnerContact.getId()){
//            partnerContactRepository.delete(partnerContact);
//            return partnerContact;
//        }
//       else{
//            throw new NullPointerException("cant't delete");
//        }
//    }
//}
