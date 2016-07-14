package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.JobSkillDTO;
import uet.usercontroller.model.JobSkill;
import uet.usercontroller.service.JobSkillService;

import java.util.List;

/**
 * Created by fgv on 7/8/2016.
 */
@RestController
public class JobSkillController {
    @Autowired
    private JobSkillService jobSkillService;
    //xem tat ca cac jobskill
    @RequestMapping(value = "/student/jobSkill",method = RequestMethod.GET)
    public List<JobSkill> getalljobskill(){
        return jobSkillService.getJobSkills();
    }
    //create 1 jobskill
    @RequestMapping(value="student/{studentId}/jobSkill",method = RequestMethod.POST)
    public JobSkill createJs(@RequestBody JobSkillDTO jobSkillDTO){
        return jobSkillService.createJs(jobSkillDTO);
    }
    //show 1 jobskill by id
    @RequestMapping(value="student/{studentId}/jobSkill/{jobSkillId}", method = RequestMethod.GET)
    public JobSkill showById(@PathVariable("jobSkillId") int id){
        return jobSkillService.showJobSkill(id);
    }
    //thay doi 1 jobskill by id
    @RequestMapping(value="student/{studentId}/jobSkill/{jobSkillId}", method = RequestMethod.PUT)
    public JobSkill ChangeJs(@PathVariable("jobSkillId") int id, @RequestBody JobSkillDTO jobSkillDTO){
        return jobSkillService.ChangeJsById(id,jobSkillDTO);
    }
    //delete 1 jobskill
    @RequestMapping(value="student/{studentId}/jobSkill/{jobSkillId}/remove", method = RequestMethod.DELETE)
    public String deleteJs(@PathVariable("jobSkillId") int id){
        return jobSkillService.deleteJobSkill(id);
    }


}
