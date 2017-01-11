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
//    show list jobskill cua 1 student
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
            jobSkill.setStudent(student);
            jobSkillRepository.save(jobSkill);
            student.getJobSkills().add(jobSkill);
            studentRepository.save(student);
            return jobSkill;
        }
        else {
            throw new NullPointerException("User doesn't match with Student");
        }
    }

    //delete 1 jobskill by id
    public void deleteJobSkill(int id,String token){
        User user = userRepository.findByToken(token);
        Student student = user.getStudent();
        JobSkill jobSkill = jobSkillRepository.findById(id);
        Student student1 = studentRepository.findByJobSkillsId(id);
        if(user.getRole()==Role.STUDENT) {
            if (student.equals(student1)) {
                jobSkillRepository.delete(jobSkill);
            } else {
                throw new NullPointerException("User doesn't match with Student");
            }
        }else{
            jobSkillRepository.delete(jobSkill);
        }
    }
    //show 1 jobskill
    public JobSkill showJobSkill( int id){
        JobSkill  jobSkill = jobSkillRepository.findById(id);
        return jobSkill;
    }
    //change 1 Jobskill by id
    public JobSkill ChangeJsById(int id, JobSkillDTO jobSkillDTO,String token){
        User user = userRepository.findByToken(token);
        JobSkill jobSkill = jobSkillRepository.findById(id);
        Student student = user.getStudent();
        Student student1 = studentRepository.findByJobSkillsId(id);
        if (student.equals(student1)) {
            jobSkill.setCompany(jobSkillDTO.getCompany());
            jobSkill.setUpdateTime(jobSkillDTO.getUpdateTime());
            jobSkill.setSkill(jobSkillDTO.getSkill());
            jobSkillRepository.save(jobSkill);
            return jobSkill;
            } else {
                throw new NullPointerException("User doesn't match with Student");
            }
    }


}

