package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.PartnerDTO;
import uet.usercontroller.DTO.StudentInfoDTO;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.StudentInfo;
import uet.usercontroller.service.PartnerService;
import uet.usercontroller.stereotype.RequiredRoles;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tu on 28-Jun-16.
 */
@RestController
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    //show all list partner
    @RequiredRoles({Role.STUDENT, Role.ADMIN, Role.VIP_PARTNER})
    @RequestMapping(value = "/partner", method = RequestMethod.GET)
    public List<Partner> getPartners() {
        return partnerService.getPartners();
    }


    //show a partner info
    @RequiredRoles({Role.ADMIN, Role.STUDENT, Role.VIP_PARTNER})
    @RequestMapping(value = "/partner/{partnerId}", method = RequestMethod.GET)
    public Partner showPartner(@PathVariable("partnerId") int partnerId) {
        return partnerService.showPartner(partnerId);
    }

    //partner search students
    @RequiredRoles({Role.VIP_PARTNER, Role.ADMIN})
    @RequestMapping(value = "searchStudent", method = RequestMethod.POST)
    public List<StudentInfo> searchStudent(@RequestBody StudentInfoDTO studentInfoDTO) {
        return partnerService.searchStudent(studentInfoDTO);
    }
}
