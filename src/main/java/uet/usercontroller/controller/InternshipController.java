package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.InternshipDTO;
import uet.usercontroller.model.Internship;
import uet.usercontroller.model.Role;
import uet.usercontroller.service.InternshipService;
import uet.usercontroller.stereotype.RequiredRoles;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fgv on 7/12/2016.
 */
@RestController
public class InternshipController {
    @Autowired
    private InternshipService internshipService;

    //show all Internships
    @RequiredRoles({Role.ADMIN,Role.VIP_PARTNER})
    @RequestMapping(value = "/intern", method = RequestMethod.GET)
    public List<Internship> getAllInterns(HttpServletRequest request) {
        String token= request.getHeader("auth-token");
        return internshipService.getAllIntern(token);
    }

    //show all Internship of a partner
    @RequiredRoles({Role.ADMIN,Role.VIP_PARTNER})
    @RequestMapping(value= "/partner/{partnerId}/internship", method = RequestMethod.GET)
    public List<Internship> getAllInPartner(@PathVariable int partnerId, HttpServletRequest request){
        String token= request.getHeader("auth-token");
        return internshipService.getAllInPartner(partnerId,token);
    }

    //Create a Internship
    @RequiredRoles({Role.ADMIN})
    @RequestMapping(value = "/student/{studentId}/{partnerId}/intern", method = RequestMethod.POST)
    public Internship createIntern(@PathVariable("studentId") int studentId,@PathVariable("partnerId") int partnerId,@RequestBody InternshipDTO internshipDTO,HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return internshipService.createIntern(studentId,partnerId,internshipDTO,token);
    }

    //find a internship
    @RequiredRoles({Role.ADMIN,Role.VIP_PARTNER,Role.STUDENT})
    @RequestMapping(value = "/intern/{internId}", method = RequestMethod.GET)
    public Internship findInternById(@PathVariable("internId") int id,HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return internshipService.findInternById(id,token);
    }
    //Edit a internship
    @RequiredRoles({Role.ADMIN})
    @RequestMapping(value = "/intern/{internId}", method = RequestMethod.PUT)
    public Internship changeInternById(@PathVariable("internId") int id, @RequestBody InternshipDTO internshipDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return internshipService.changeById(id,internshipDTO,token);
    }

    //Delete a internship
    @RequiredRoles({Role.ADMIN})
    @RequestMapping(value="/intern/{internId}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("internId") int id, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        internshipService.deleteById(id, token);
    }
}
