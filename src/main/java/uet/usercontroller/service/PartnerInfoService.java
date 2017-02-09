package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.PartnerInfoDTO;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.PartnerInfo;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.PartnerInfoRepository;
import uet.usercontroller.repository.PartnerRepository;
import uet.usercontroller.repository.UserRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tu on 27-Aug-16.
 */
@Service
public class PartnerInfoService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    private PartnerInfoRepository partnerInfoRepository;

    //show all partner info
    public List<HashMap<String, String>> getAllInfo(){
        List<PartnerInfo> allPartners = (List<PartnerInfo>) partnerInfoRepository.findAll();
        List<HashMap<String, String>> listPartnerInfo = new ArrayList<HashMap<String, String>>();
        for (PartnerInfo partnerInfo : allPartners){
            HashMap<String, String> lPartnerInfo = new HashMap<String, String>();
            Partner partner = partnerRepository.findByPartnerInfoId(partnerInfo.getId());
            User user = userRepository.findByPartnerId(partner.getId());
            String userId = String.valueOf(user.getId());
            String status = user.getStatus();
            String partnerInfoId = String.valueOf(partnerInfo.getId());
            String address = partnerInfo.getAddress();
            String director = partnerInfo.getDirector();
            String email = partnerInfo.getEmail();
            String fax = partnerInfo.getFax();
            String fieldWork = partnerInfo.getFieldWork();
            String partnerName = partnerInfo.getPartnerName();
            String phone = partnerInfo.getPhone();
            String taxCode = partnerInfo.getTaxCode();
            String website = partnerInfo.getWebsite();
            String logo = partnerInfo.getLogo();
            lPartnerInfo.put("userId", userId);
            lPartnerInfo.put("status", status);
            lPartnerInfo.put("partnerInfoId", partnerInfoId);
            lPartnerInfo.put("address", address);
            lPartnerInfo.put("director", director);
            lPartnerInfo.put("email", email);
            lPartnerInfo.put("fax", fax);
            lPartnerInfo.put("fieldWork", fieldWork);
            lPartnerInfo.put("partnerName", partnerName);
            lPartnerInfo.put("phone", phone);
            lPartnerInfo.put("taxCode", taxCode);
            lPartnerInfo.put("website", website);
            lPartnerInfo.put("logo", logo);
            listPartnerInfo.add(lPartnerInfo);
        }
        return listPartnerInfo;
    }

    //show a partner info
    public PartnerInfo showInfo(int partnerInfoId){
        PartnerInfo partnerInfo = partnerInfoRepository.findOne(partnerInfoId);
        return partnerInfo;
    }

    //create a partner info
    public PartnerInfo createInfo(int partnerId, PartnerInfoDTO partnerInfoDTO, String token){
        User user = userRepository.findByToken(token);
        Partner partner = partnerRepository.findOne(partnerId);
        if (user.getPartner().equals(partner)){
            PartnerInfo partnerInfo = new PartnerInfo();
            partnerInfo.setPartnerName(partnerInfoDTO.getPartnerName());
            partnerInfo.setTaxCode(partnerInfoDTO.getTaxCode());
            partnerInfo.setDirector(partnerInfoDTO.getDirector());
            partnerInfo.setFieldWork(partnerInfoDTO.getFieldWork());
            partnerInfo.setWebsite(partnerInfoDTO.getWebsite());
            partnerInfo.setAddress(partnerInfoDTO.getAddress());
            partnerInfo.setPhone(partnerInfoDTO.getPhone());
            partnerInfo.setFax(partnerInfoDTO.getFax());
            partnerInfo.setEmail(partnerInfoDTO.getEmail());
            partnerInfo.setLogo(partnerInfoDTO.getLogo());
            return partnerInfoRepository.save(partnerInfo);
        }
        else{
            throw new NullPointerException("User doesn't match with Partner.");
        }
    }

    //edit info of a partner
    public PartnerInfo editInfo(int partnerInfoId, PartnerInfoDTO partnerInfoDTO, String token){
        User user = userRepository.findByToken(token);
        Partner partner = user.getPartner();
        PartnerInfo  partnerInfo = partnerInfoRepository.findOne(partnerInfoId);
        if ( partner.getPartnerInfo().equals(partnerInfo) ){
            if (partnerInfoDTO.getPartnerName()!=null){
                partnerInfo.setPartnerName(partnerInfoDTO.getPartnerName());
            }
            if (partnerInfoDTO.getTaxCode()!=null){
                partnerInfo.setTaxCode(partnerInfoDTO.getTaxCode());
            }
            if (partnerInfoDTO.getDirector()!=null){
                partnerInfo.setDirector(partnerInfoDTO.getDirector());
            }
            if (partnerInfoDTO.getFieldWork()!=null){
                partnerInfo.setFieldWork(partnerInfoDTO.getFieldWork());
            }
            if (partnerInfoDTO.getWebsite()!=null){
                partnerInfo.setWebsite(partnerInfoDTO.getWebsite());
            }
            if (partnerInfoDTO.getAddress()!=null){
                partnerInfo.setAddress(partnerInfoDTO.getAddress());
            }
            if (partnerInfoDTO.getPhone()!=null){
                partnerInfo.setPhone(partnerInfoDTO.getPhone());
            }
            if (partnerInfoDTO.getFax()!=null){
                partnerInfo.setFax(partnerInfoDTO.getFax());
            }
            if (partnerInfoDTO.getEmail()!=null){
                partnerInfo.setEmail(partnerInfoDTO.getEmail());
            }
            return partnerInfoRepository.save(partnerInfo);
        }
        else {
            throw new NullPointerException("User doesn't match with Partner.");
        }
    }

    //change Logo
    public void changeLogo (PartnerInfoDTO partnerInfoDTO, String token) throws IOException {
        User user = userRepository.findByToken(token);
        Partner partner = user.getPartner();
        String username = user.getUserName();
        PartnerInfo partnerInfo = partner.getPartnerInfo();
        //code đổi tên image thành partner_id.jpg và save vào database
        String pathname = "../Qly_SV_client/app/users_data/partner/" + username;
        File directory = new File(pathname);
        if (! directory.exists()) {
            directory.mkdir();
        }
//        directory.delete();
        pathname = "../Qly_SV_client/app/users_data/partner/" + username + "/logo/";
        String directoryName = "users_data/partner/" + username + "/logo/";
        String fileName = username + "_logo.jpg";
        directory = new File(pathname);
        if (! directory.exists()) {
            directory.mkdir();
        }
        byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(partnerInfoDTO.getLogo());
        File of = new File( pathname + fileName);
        FileOutputStream osf = new FileOutputStream(of);
        osf.write(btDataFile);
        osf.flush();
        partnerInfo.setLogo("http://localhost:8000/" + directoryName + username + "_logo.jpg");
        partnerInfoRepository.save(partnerInfo);
    }

    //delete info of a partner
    public PartnerInfo deleteInfo(int partnerInfoId, PartnerInfoDTO partnerInfoDTO, String token){
        User user = userRepository.findByToken(token);
        Partner partner = user.getPartner();
        PartnerInfo  partnerInfo = partnerInfoRepository.findOne(partnerInfoId);
        if (user.getRole()== Role.VIP_PARTNER) {
            if (partner.getPartnerInfo().equals(partnerInfo)) {
                partnerInfo.setPartnerName(null);
                partnerInfo.setTaxCode(null);
                partnerInfo.setDirector(null);
                partnerInfo.setFieldWork(null);
                partnerInfo.setWebsite(null);
                partnerInfo.setEmail(null);
                partnerInfo.setFax(null);
                partnerInfo.setPhone(null);
                partnerInfo.setAddress(null);
                return partnerInfoRepository.save(partnerInfo);
            }
        }
        if (user.getRole()==Role.ADMIN){
            partnerInfo.setPartnerName(null);
            partnerInfo.setTaxCode(null);
            partnerInfo.setDirector(null);
            partnerInfo.setFieldWork(null);
            partnerInfo.setWebsite(null);
            partnerInfo.setEmail(null);
            partnerInfo.setFax(null);
            partnerInfo.setPhone(null);
            partnerInfo.setAddress(null);
            return partnerInfoRepository.save(partnerInfo);
        }
        else {
            throw new NullPointerException("User doesn't match with Partner.");
        }
    }
}
