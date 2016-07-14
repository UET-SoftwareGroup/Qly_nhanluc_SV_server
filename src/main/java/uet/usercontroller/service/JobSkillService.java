package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.JobSkillDTO;
import uet.usercontroller.model.JobSkill;
import uet.usercontroller.repository.JobSkillRepository;

import java.util.List;

/**
 * Created by fgv on 7/8/2016.
 */
@Service
public class JobSkillService {
    @Autowired
    private JobSkillRepository jobSkillRepository;
    //show list jobskill
    public List<JobSkill> getJobSkills(){
        List<JobSkill> getAllJobskill = (List<JobSkill>) jobSkillRepository.findAll();
        return getAllJobskill;
    }
    //create jobskill
    public JobSkill createJs(JobSkillDTO jobSkillDTO){
        JobSkill jobSkill =new JobSkill();
        jobSkill.setId(jobSkillDTO.getId());
        jobSkill.setStudentId(jobSkillDTO.getStudentId());
        jobSkill.setCompany(jobSkillDTO.getCompany());
        jobSkill.setSkill(jobSkillDTO.getSkill());
        jobSkill.setUpdateTime(jobSkillDTO.getUpdateTime());
        jobSkillRepository.save(jobSkill);
        return jobSkill;
    }

    //delete 1 jobskill by id
    public String deleteJobSkill(int id){
        JobSkill jobSkill = jobSkillRepository.findById(id);
        jobSkillRepository.delete(jobSkill);
        return "delete";
    }
    //show 1 jobskill
    public JobSkill showJobSkill(int id){
        JobSkill jobSkill =jobSkillRepository.findById(id);
        return jobSkill;
    }
    //change 1 Jobskill by id
    public JobSkill ChangeJsById(int id, JobSkillDTO jobSkillDTO){
        JobSkill jobSkill=jobSkillRepository.findById(id);
        jobSkill.setId(jobSkillDTO.getId());
        jobSkill.setCompany(jobSkillDTO.getCompany());
        jobSkill.setStudentId(jobSkillDTO.getStudentId());
        jobSkill.setUpdateTime(jobSkillDTO.getUpdateTime());
        jobSkill.setSkill(jobSkillDTO.getSkill());
        jobSkillRepository.save(jobSkill);
        return  jobSkill;
    }
}

