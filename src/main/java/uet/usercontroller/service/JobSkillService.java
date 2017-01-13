package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.JobSkillDTO;
import uet.usercontroller.model.JobSkill;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.Student;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.JobSkillRepository;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.repository.UserRepository;

import java.util.List;

/**
 * Created by fgv on 7/8/2016.
 */
@Service
public class JobSkillService {
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    //show list jobskill
    public List<JobSkill> getJobSkills(){
        List<JobSkill> getAll = (List<JobSkill>) jobSkillRepository.findAll();
        return getAll;
    }

    //show list jobskill cua 1 student
    public List<JobSkill> getallInStudent(int studentId){
        Student student = studentRepository.findById(studentId);
        return student.getJobSkills();

    }

    //create jobskill
    public JobSkill createJs(int studentId,JobSkillDTO jobSkillDTO,String token){
        User user = userRepository.findByToken(token);
        Student student = studentRepository.findById(studentId);
        if(user.getStudent().equals(student)) {
            JobSkill jobSkill = new JobSkill();
            jobSkill.setCompany(jobSkillDTO.getCompany());
            jobSkill.setSkill(jobSkillDTO.getSkill());
            jobSkill.setUpdateTime(jobSkillDTO.getUpdateTime());
            jobSkill.setStudentId(studentId);
            return jobSkillRepository.save(jobSkill);

        }
        else {
            throw new NullPointerException("User doesn't match with Student");
        }
    }


    //show 1 jobskill
    public JobSkill showJobSkill( int id){
        JobSkill  jobSkill = jobSkillRepository.findById(id);
        return jobSkill;
    }

    //Edit a Jobskill by id
    public JobSkill ChangeJsById(int id, JobSkillDTO jobSkillDTO,String token){
        User user = userRepository.findByToken(token);
        JobSkill jobSkill = jobSkillRepository.findById(id);
        Student student = studentRepository.findByJobSkillsId(id);
        if (user.getStudent().equals(student)) {
            jobSkill.setCompany(jobSkillDTO.getCompany());
            jobSkill.setUpdateTime(jobSkillDTO.getUpdateTime());
            jobSkill.setSkill(jobSkillDTO.getSkill());
            return jobSkillRepository.save(jobSkill);
        }
        else {
                throw new NullPointerException("User doesn't match with Student");
        }
    }

    //Delete a jobskill by id
    public void deleteJobSkill(int id,String token){
        User user = userRepository.findByToken(token);
        JobSkill jobSkill = jobSkillRepository.findById(id);
        Student student = studentRepository.findByJobSkillsId(id);
        if(user.getRole()==Role.STUDENT) {
            if (user.getStudent().equals(student)) {
                jobSkillRepository.delete(jobSkill);
            } else {
                throw new NullPointerException("User doesn't match with Student");
            }
        }
        else if (user.getRole().equals(Role.ADMIN)){
            jobSkillRepository.delete(jobSkill);
        }
        else {
            throw new NullPointerException("You don's have permission");
        }
    }
}

