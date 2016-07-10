package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.InfoBySchoolDTO;
import uet.usercontroller.model.InfoBySchool;
import uet.usercontroller.repository.InfoBySchoolRepository;
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
    @RequestMapping(value="info/{studentId}", method = RequestMethod.POST)
    public InfoBySchool createInfo(@PathVariable("studentId") int studentId, @RequestBody InfoBySchoolDTO infoBySchoolDTO){
        return infoBySchoolService.createInfo(studentId, infoBySchoolDTO);
    }

    //show info of a student
    @RequestMapping(value="info/{id}", method = RequestMethod.GET)
    public InfoBySchool getInfo(@PathVariable("id") int id){
        return infoBySchoolService.getInfo(id);
    }

    //edit info of a student
    @RequestMapping(value="info/{id}", method = RequestMethod.PUT)
    public InfoBySchool editInfo(@PathVariable("id") int id, InfoBySchoolDTO infoBySchoolDTO){
        return infoBySchoolService.editInfo(id, infoBySchoolDTO);
    }

    //delte info of a student
    @RequestMapping(value="info/{id}", method = RequestMethod.DELETE)
    public void deletaInfo(@PathVariable("id") int id){
        infoBySchoolService.deleteInfo(id);
    }
}
