package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.PartnerInfoDTO;
import uet.usercontroller.model.PartnerInfo;
import uet.usercontroller.model.Role;
import uet.usercontroller.service.PartnerInfoService;
import uet.usercontroller.stereotype.RequiredRoles;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tu on 27-Aug-16.
 */
@RestController
public class PartnerInfoController {
    @Autowired
    private PartnerInfoService partnerInfoService;

    //show all partner info
    @RequiredRoles({Role.ADMIN, Role.VIP_PARTNER, Role.STUDENT})
    @RequestMapping(value="partnerInfo", method = RequestMethod.GET)
    public List<HashMap<String, String>> getAllInfo(){
        return (List<HashMap<String, String>>) partnerInfoService.getAllInfo();
    }

    //show a partner info
    @RequiredRoles({Role.ADMIN, Role.VIP_PARTNER, Role.STUDENT})
    @RequestMapping(value="partnerInfo/{partnerInfoId}", method = RequestMethod.GET)
    public PartnerInfo showInfo(@PathVariable("partnerInfoId") int partnerInfoId){
        return partnerInfoService.showInfo(partnerInfoId);
    }

    //create a partner info
    @RequiredRoles(Role.VIP_PARTNER)
    @RequestMapping(value="partner/{partnerId}/partnerInfo", method = RequestMethod.POST)
    public PartnerInfo createInfo(@PathVariable("partnerId") int partnerId, @RequestBody PartnerInfoDTO partnerInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return partnerInfoService.createInfo(partnerId, partnerInfoDTO, token);
    }

    //edit info of a partner
    @RequiredRoles({Role.VIP_PARTNER, Role.NORMAL_PARTNER})
    @RequestMapping(value="partnerInfo/{partnerInfoId}", method = RequestMethod.PUT)
    public PartnerInfo editInfo(@PathVariable("partnerInfoId") int partnerInfoId, @RequestBody PartnerInfoDTO partnerInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return partnerInfoService.editInfo(partnerInfoId, partnerInfoDTO, token);
    }

    //change logo
    @RequiredRoles({Role.VIP_PARTNER, Role.NORMAL_PARTNER})
    @RequestMapping(value="changeLogo", method = RequestMethod.PUT)
    public void changeLogo(@RequestBody PartnerInfoDTO partnerInfoDTO, HttpServletRequest request) throws IOException {
        String token = request.getHeader("auth-token");
        partnerInfoService.changeLogo(partnerInfoDTO, token);
    }

    //get partner vip logo
    @RequiredRoles({Role.ADMIN, Role.VIP_PARTNER, Role.STUDENT})
    @RequestMapping(value="partnerLogo", method = RequestMethod.GET)
    public List<HashMap<String, String>> getPartnerVipLogo(){
        return (List<HashMap<String, String>>) partnerInfoService.getPartnerViplogo();
    }

    //delete info of a partner
    @RequiredRoles({Role.ADMIN,Role.VIP_PARTNER, Role.NORMAL_PARTNER})
    @RequestMapping(value="partner/{partnerId}/partnerInfo", method = RequestMethod.DELETE)
    public PartnerInfo deleteInfo(@PathVariable("partnerId") int partnerId, @RequestBody PartnerInfoDTO partnerInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return partnerInfoService.deleteInfo(partnerId, partnerInfoDTO, token);
    }
}
