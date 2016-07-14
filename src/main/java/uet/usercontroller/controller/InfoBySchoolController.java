package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.InfoBySchoolDTO;
import uet.usercontroller.model.InfoBySchool;
import uet.usercontroller.service.InfoBySchoolService;

import java.util.List;

/**
 * Created by Tu on 07-Jul-16.
 */
@RestController
public class InfoBySchoolController {
    @Autowired
    private InfoBySchoolService infoBySchoolService;

    //Show all student information
    @RequestMapping(value="/info", method = RequestMethod.GET)
    public List<InfoBySchool> getAllInfo(){
        return infoBySchoolService.getAllInfo();
    }

    //create info
    @RequestMapping(value="/student/{studentId}/info", method = RequestMethod.POST)
    public InfoBySchool createInfo(@PathVariable("studentId") int studentId, @RequestBody InfoBySchoolDTO infoBySchoolDTO){
        return infoBySchoolService.createInfo(studentId, infoBySchoolDTO);
    }

    //show info of a student
    @RequestMapping(value="/student/{studentId}/info/{infoId}", method = RequestMethod.GET)
    public InfoBySchool getInfo(@PathVariable("infoId") int infoId){
        return infoBySchoolService.getInfo(infoId);
    }

    //edit info of a student
    @RequestMapping(value="/student/{studentId}/info/{infoId}", method = RequestMethod.PUT)
    public InfoBySchool editInfo(@PathVariable("id") int id, InfoBySchoolDTO infoBySchoolDTO){
        return infoBySchoolService.editInfo(id, infoBySchoolDTO);
    }

    //delte info of a student
    @RequestMapping(value="/student/{studentId}/info/{infoId}", method = RequestMethod.DELETE)
    public void deletaInfo(@PathVariable("infoId") int infoId){
        infoBySchoolService.deleteInfo(infoId);
    }
}
