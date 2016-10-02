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
    @RequiredRoles({Role.ADMIN,Role.PARTNER1})
    @RequestMapping(value = "/intern", method = RequestMethod.GET)
    public List<Internship> getAllInterns(HttpServletRequest request) {
        String token= request.getHeader("auth-token");
        return internshipService.getAllIntern(token);
    }

    //Create a Internship
    @RequiredRoles({Role.ADMIN})
    @RequestMapping(value = "/student/{studentId}/intern", method = RequestMethod.POST)
    public Internship createIntern(@PathVariable("studentId") int studentId,@RequestBody InternshipDTO internshipDTO,HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return internshipService.createIntern(studentId,internshipDTO,token);
    }

    //find By Id
    @RequiredRoles({Role.ADMIN,Role.PARTNER1,Role.STUDENT})
    @RequestMapping(value = "/intern/{internId}", method = RequestMethod.GET)
    public Internship findInternById(@PathVariable("internId") int id,HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return internshipService.findInternById(id,token);
    }
    //change 1 internship
    @RequiredRoles({Role.ADMIN})
    @RequestMapping(value = "/intern/{internId}", method = RequestMethod.PUT)
    public Internship changeInternById(@PathVariable("internId") int id, @RequestBody InternshipDTO internshipDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return internshipService.changeById(id,internshipDTO,token);
    }
    @RequiredRoles({Role.ADMIN})
    @RequestMapping(value="/intern/{internId}/{studentId}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("internId") int id,@PathVariable("studentId") int studentId, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        internshipService.deleteById(id,studentId,token);
    }
}
