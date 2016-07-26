package uet.usercontroller.controller;

import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.JobSkillDTO;
import uet.usercontroller.model.JobSkill;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.Student;
import uet.usercontroller.service.JobSkillService;
import uet.usercontroller.service.StudentService;
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
    @RequiredRoles({Role.STUDENT, Role.PARTNER1,Role.ADMIN})
    @RequestMapping(value = "/student/jobSkill",method = RequestMethod.GET)
    public List<JobSkill> getalljobskill(HttpServletRequest request){
        String token =request.getHeader("auth-token");
        return jobSkillService.getJobSkills(token);
    }

    //create 1 jobskill
    @RequiredRoles({Role.STUDENT})
    @RequestMapping(value="student/{studentId}/jobSkill",method = RequestMethod.POST)
    public JobSkill createJs(@PathVariable("studentId") int studentId ,@RequestBody JobSkillDTO jobSkillDTO,HttpServletRequest request){
        String token =request.getHeader("auth-token");
        return jobSkillService.createJs(studentId,jobSkillDTO,token);
    }

    //show toan bo jobskill cua 1 student
    @RequiredRoles({Role.STUDENT, Role.PARTNER1,Role.ADMIN})
    @RequestMapping(value="student/{studentId}/jobSkill",method = RequestMethod.GET)
    public List<JobSkill> getallInStudent(@PathVariable("studentId") int studentId,HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return jobSkillService.getallInStudent(studentId,token);
    }
    //show 1 jobskill by id
    @RequiredRoles({Role.STUDENT,Role.ADMIN,Role.PARTNER1})
    @RequestMapping(value="student/{studentId}/jobSkill/{jobSkillId}", method = RequestMethod.GET)
    public JobSkill showById(@PathVariable("jobSkillId") int jobskillId,@PathVariable("studentId") int studentId,HttpServletRequest request){
        String token= request.getHeader("auth-token");
        return jobSkillService.showJobSkill(studentId,jobskillId,token);
    }
    //thay doi 1 jobskill by id
    @RequiredRoles({Role.STUDENT})
    @RequestMapping(value="student/{studentId}/jobSkill/{jobSkillId}", method = RequestMethod.PUT)
    public JobSkill ChangeJs(@PathVariable("studentId") int studentId,@PathVariable("jobSkillId") int id, @RequestBody JobSkillDTO jobSkillDTO,HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return jobSkillService.ChangeJsById(studentId,id,jobSkillDTO,token);
    }
    //delete 1 jobskill
    @RequestMapping(value="student/{studentId}/jobSkill/{jobSkillId}", method = RequestMethod.DELETE)
    @RequiredRoles({Role.STUDENT})
    public String deleteJs(@PathVariable("studentId") int studentId,@PathVariable("jobSkillId") int id,HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return jobSkillService.deleteJobSkill(studentId,id,token);
    }

}
