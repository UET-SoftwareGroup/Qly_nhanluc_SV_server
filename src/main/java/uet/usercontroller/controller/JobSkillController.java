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
    public List<JobSkill> getalljobskill(){
        return jobSkillService.getJobSkills();
    }

    //create 1 jobskill
    @RequiredRoles({Role.STUDENT})
    @RequestMapping(value="student/{studentId}/jobSkill",method = RequestMethod.POST)
    public JobSkill createJs(@PathVariable("studentId") int studentid ,@RequestBody JobSkillDTO jobSkillDTO){
        return jobSkillService.createJs(studentid,jobSkillDTO);
    }

    //show 1 jobskill by id
    @RequestMapping(value="student/{studentId}/jobSkill/{jobSkillId}", method = RequestMethod.GET)
    @RequiredRoles({Role.STUDENT,Role.ADMIN,Role.PARTNER1})
    public JobSkill showById(@PathVariable("jobSkillId") int jobskillId,@PathVariable("studentId") int studentId){
        return jobSkillService.showJobSkill(jobskillId,studentId);
    }
    //thay doi 1 jobskill by id
    @RequestMapping(value="student/{studentId}/jobSkill/{jobSkillId}", method = RequestMethod.PUT)
    @RequiredRoles({Role.STUDENT})
    public JobSkill ChangeJs(@PathVariable("studentId") int studentId,@PathVariable("jobSkillId") int id, @RequestBody JobSkillDTO jobSkillDTO){
        return jobSkillService.ChangeJsById(studentId,id,jobSkillDTO);
    }
    //delete 1 jobskill
    @RequestMapping(value="student/{studentId}/jobSkill/{jobSkillId}/remove", method = RequestMethod.DELETE)
    @RequiredRoles({Role.STUDENT})
    public String deleteJs(@PathVariable("studentId") int studentId,@PathVariable("jobSkillId") int id){
        return jobSkillService.deleteJobSkill(studentId,id);
    }


}
