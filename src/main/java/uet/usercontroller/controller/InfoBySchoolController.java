package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.InfoBySchoolDTO;
import uet.usercontroller.model.InfoBySchool;
import uet.usercontroller.model.Role;
import uet.usercontroller.service.InfoBySchoolService;
import uet.usercontroller.stereotype.RequiredRoles;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Tu on 07-Jul-16.
 */
@RestController
public class InfoBySchoolController {
    @Autowired
    private InfoBySchoolService infoBySchoolService;

    //Show all student information by school
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.PARTNER1,Role.ADMIN})
    @RequestMapping(value="/infobyschool", method = RequestMethod.GET)
    public List<InfoBySchool> getAllInfo(){
        return infoBySchoolService.getAllInfo();
    }

    //create info
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="/student/{studentId}/infobyschool", method = RequestMethod.POST)
    public InfoBySchool createInfo(@PathVariable("studentId") int studentId, @RequestBody InfoBySchoolDTO infoBySchoolDTO){
        return infoBySchoolService.createInfo(studentId, infoBySchoolDTO);
    }

    //show info of a student
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.STUDENT,Role.PARTNER1,Role.ADMIN})
    @RequestMapping(value="/infobyschool/{infoId}", method = RequestMethod.GET)
    public InfoBySchool getInfo(@PathVariable("infoId") int infoId, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return infoBySchoolService.getInfo(infoId, token);
    }

    //edit info of a student
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="/infobyschool/{infoId}", method = RequestMethod.PUT)
    public InfoBySchool editInfo(@PathVariable("infoId") int infoId,@RequestBody InfoBySchoolDTO infoBySchoolDTO){
        return infoBySchoolService.editInfo(infoId, infoBySchoolDTO);
    }

    //delete info of a student
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="/infobyschool/{infoId}", method = RequestMethod.DELETE)
    public void deleteInfo(@PathVariable("infoId") int infoId){
        infoBySchoolService.deleteInfo(infoId);
    }
}
