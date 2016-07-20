package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.InfoBySchoolDTO;
import uet.usercontroller.model.InfoBySchool;
import uet.usercontroller.model.Role;
import uet.usercontroller.service.InfoBySchoolService;
import uet.usercontroller.stereotype.NoAuthentication;
import uet.usercontroller.stereotype.RequiredRoles;

import java.util.List;

/**
 * Created by Tu on 07-Jul-16.
 */
@RestController
public class InfoBySchoolController {
    @Autowired
    private InfoBySchoolService infoBySchoolService;

    //Show all student information
    @NoAuthentication
    @RequestMapping(value="/student/info", method = RequestMethod.GET)
    public List<InfoBySchool> getAllInfo(){
        return infoBySchoolService.getAllInfo();
    }

    //create info
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="/student/{studentId}/info", method = RequestMethod.POST)
    public InfoBySchool createInfo(@PathVariable("studentId") int studentId, @RequestBody InfoBySchoolDTO infoBySchoolDTO){
        return infoBySchoolService.createInfo(studentId, infoBySchoolDTO);
    }

    //show info of a student
    @RequiredRoles({Role.STUDENT,Role.PARTNER1})
    @RequestMapping(value="/student/{studentId}/info/{infoId}", method = RequestMethod.GET)
    public InfoBySchool getInfo(@PathVariable("studentId") int studentId, @PathVariable("infoId") int infoId){
        return infoBySchoolService.getInfo(studentId, infoId);
    }

    //edit info of a student
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="/student/{studentId}/info/{infoId}", method = RequestMethod.PUT)
    public InfoBySchool editInfo(@PathVariable("studentId") int studentId, @PathVariable("infoId") int infoId, InfoBySchoolDTO infoBySchoolDTO){
        return infoBySchoolService.editInfo(studentId, infoId, infoBySchoolDTO);
    }

    //delte info of a student
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="/student/{studentId}/info/{infoId}", method = RequestMethod.DELETE)
    public void deletaInfo(@PathVariable("studentId") int studentId, @PathVariable("infoId") int infoId){
        infoBySchoolService.deleteInfo(studentId, infoId);
    }
}
