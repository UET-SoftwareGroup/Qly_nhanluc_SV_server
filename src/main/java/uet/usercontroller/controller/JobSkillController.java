package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.JobSkillDTO;
import uet.usercontroller.model.JobSkill;
import uet.usercontroller.model.Role;
import uet.usercontroller.service.JobSkillService;
import uet.usercontroller.stereotype.RequiredRoles;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fgv on 7/8/2016.
 */
@RestController
public class JobSkillController {

    @Autowired
    private JobSkillService jobSkillService;

    //xem tat ca cac jobskill
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.STUDENT, Role.PARTNER1,Role.ADMIN})
    @RequestMapping(value = "/jobSkill",method = RequestMethod.GET)
    public List<JobSkill> getalljobskill(){
        return jobSkillService.getJobSkills();
    }

    //create 1 jobskill
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.STUDENT})
    @RequestMapping(value="/student/{studentId}/jobSkill",method = RequestMethod.POST)
    public JobSkill createJs(@PathVariable("studentId") int studentId,@RequestBody JobSkillDTO jobSkillDTO,HttpServletRequest request){
        String token =request.getHeader("auth-token");
        return jobSkillService.createJs(studentId,jobSkillDTO,token);
    }

    //show toan bo jobskill cua 1 student
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.STUDENT, Role.PARTNER1,Role.ADMIN})
    @RequestMapping(value="student/{studentId}/jobSkill",method = RequestMethod.GET)
    public List<JobSkill> getallInStudent(@PathVariable("studentId") int studentId){
        return jobSkillService.getallInStudent(studentId);
    }
    //show 1 jobskill by id
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.STUDENT,Role.ADMIN,Role.PARTNER1})
    @RequestMapping(value="/jobSkill/{jobSkillId}", method = RequestMethod.GET)
    public JobSkill showById(@PathVariable("jobSkillId") int jobskillId){
        return jobSkillService.showJobSkill(jobskillId);
    }
    //thay doi 1 jobskill by id
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.STUDENT})
    @RequestMapping(value="/jobSkill/{jobSkillId}", method = RequestMethod.PUT)
    public JobSkill ChangeJs(@PathVariable("jobSkillId") int id, @RequestBody JobSkillDTO jobSkillDTO,HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return jobSkillService.ChangeJsById(id,jobSkillDTO,token);
    }
    //delete 1 jobskill
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequestMapping(value="/jobSkill/{jobSkillId}", method = RequestMethod.DELETE)
    @RequiredRoles({Role.STUDENT,Role.ADMIN})
    public void deleteJs(@PathVariable("jobSkillId") int id,HttpServletRequest request){
        String token = request.getHeader("auth-token");
        jobSkillService.deleteJobSkill(id,token);
    }

}
