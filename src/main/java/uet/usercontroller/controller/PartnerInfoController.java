package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.PartnerInfoDTO;
import uet.usercontroller.model.PartnerInfo;
import uet.usercontroller.model.Role;
import uet.usercontroller.repository.PartnerInfoRepository;
import uet.usercontroller.service.PartnerInfoService;
import uet.usercontroller.service.PartnerService;
import uet.usercontroller.stereotype.RequiredRoles;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Tu on 27-Aug-16.
 */
@RestController
public class PartnerInfoController {
    @Autowired
    private PartnerInfoService partnerInfoService;

    //show all partner info
    @RequiredRoles({Role.ADMIN, Role.PARTNER1, Role.STUDENT})
    @RequestMapping(value="partnerInfo", method = RequestMethod.GET)
    public List<PartnerInfo> getAllInfo(){
        return partnerInfoService.getAllInfo();
    }

    //show a partner info
    @RequiredRoles({Role.ADMIN, Role.PARTNER1, Role.STUDENT})
    @RequestMapping(value="partnerInfo/{partnerInfoId}", method = RequestMethod.GET)
    public PartnerInfo showInfo(@PathVariable("partnerInfoId") int partnerInfoId){
        return partnerInfoService.showInfo(partnerInfoId);
    }
    //create a partner info
    @RequiredRoles(Role.PARTNER1)
    @RequestMapping(value="partner/{partnerId}/partnerInfo", method = RequestMethod.POST)
    public PartnerInfo createInfo(@PathVariable("partnerId") int partnerId, @RequestBody PartnerInfoDTO partnerInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return partnerInfoService.createInfo(partnerId, partnerInfoDTO, token);
    }

    //edit info of a partner
    @RequiredRoles(Role.PARTNER1)
    @RequestMapping(value="partnerInfo/{partnerInfoId}", method = RequestMethod.PUT)
    public PartnerInfo editInfo(@PathVariable("partnerInfoId") int partnerInfoId, @RequestBody PartnerInfoDTO partnerInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return partnerInfoService.editInfo(partnerInfoId, partnerInfoDTO, token);
    }

    //delete info of a partner
    @RequiredRoles({Role.ADMIN,Role.PARTNER1})
    @RequestMapping(value="partner/{partnerId}/partnerInfo", method = RequestMethod.DELETE)
    public PartnerInfo deleteInfo(@PathVariable("partnerId") int partnerId, @RequestBody PartnerInfoDTO partnerInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return partnerInfoService.deleteInfo(partnerId, partnerInfoDTO, token);
    }
}
