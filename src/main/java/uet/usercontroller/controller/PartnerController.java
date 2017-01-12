package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.PartnerDTO;
import uet.usercontroller.DTO.StudentDTO;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.Role;
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
    @RequiredRoles({Role.STUDENT,Role.ADMIN,Role.PARTNER1})
    @RequestMapping(value="/partner", method = RequestMethod.GET)
    public List<Partner> getPartners(){
        return partnerService.getPartners();
    }

    //create a partner
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="/user/{userId}/partner", method = RequestMethod.POST)
    public Partner createPartner(@PathVariable("userId") int userId, @RequestBody PartnerDTO partnerDTO){
        return partnerService.createPartner(userId, partnerDTO);
    }

    //edit a partner name
    @RequiredRoles({Role.ADMIN, Role.PARTNER1})
    @RequestMapping(value="/partner/{partnerId}", method = RequestMethod.PUT)
    public Partner editPartner(@PathVariable("partnerId") int partnerId, @RequestBody PartnerDTO partnerDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return partnerService.editPartner(partnerId, partnerDTO, token);
    }

    //show a partner info
    @RequiredRoles({Role.ADMIN, Role.STUDENT, Role.PARTNER1})
    @RequestMapping(value="/partner/{partnerId}", method = RequestMethod.GET)
    public Partner showPartner(@PathVariable("partnerId") int partnerId){
        return partnerService.showPartner(partnerId);
    }

    //partner search students
    @RequiredRoles({Role.PARTNER1, Role.ADMIN})
    @RequestMapping(value="searchStudent", method = RequestMethod.POST)
    public List<HashMap<String, String>> searchStudent(@RequestBody StudentDTO studentDTO){
        return (List<HashMap<String, String>>) partnerService.searchStudent(studentDTO);
    }

    //delete a partner
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="/partner/{partnerId}", method = RequestMethod.DELETE)
    public void deletePartner(@PathVariable("partnerId") int partnerId){
        partnerService.deletePartner(partnerId);
    }
}
