package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.JobSkillDTO;
import uet.usercontroller.model.JobSkill;
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
    public List<JobSkill> getJobSkills(String token){
        User user = userRepository.findByToken(token);
        List<JobSkill> getAll = (List<JobSkill>) jobSkillRepository.findAll();
        return getAll;
    }
//    show list jobskill cua 1 student
    public List<JobSkill> getallInStudent(int studentId,String token){
            User user =userRepository.findByToken(token);
            Student student = studentRepository.findById(studentId);
            return student.getJobSkills();
    }
    //create jobskill
    public JobSkill createJs( int studentId,JobSkillDTO jobSkillDTO,String token){
        User user = userRepository.findByToken(token);
        Student student = studentRepository.findById(studentId);
        if(user.getStudent().equals(student) && student.getId()== studentId && studentId==jobSkillDTO.getStudentId()) {
            JobSkill jobSkill = new JobSkill();
            jobSkill.setId(jobSkillDTO.getId());
            jobSkill.setStudentId(jobSkillDTO.getStudentId());
            jobSkill.setCompany(jobSkillDTO.getCompany());
            jobSkill.setSkill(jobSkillDTO.getSkill());
            jobSkill.setUpdateTime(jobSkillDTO.getUpdateTime());
            jobSkillRepository.save(jobSkill);
            student.getJobSkills().add(jobSkill);
            studentRepository.save(student);
            return jobSkill;
        }
        else{
            throw  new NullPointerException("Id ko chinh xac");
        }
    }

    //delete 1 jobskill by id
    public String deleteJobSkill(int studentId,int id,String token){
        User user = userRepository.findByToken(token);
        Student student = studentRepository.findById(studentId);
        JobSkill jobSkill = jobSkillRepository.findById(id);
        if(user.getStudent().equals(student) && student.getId()==studentId && jobSkill.getId()==id) {
            jobSkillRepository.delete(jobSkill);
            return "deleted";
        }
        else{
            throw  new NullPointerException("can't delete");
        }
    }
    //show 1 jobskill
    public JobSkill showJobSkill(int studentId, int id,String token){
        User user = userRepository.findByToken(token);
        Student student = studentRepository.findById(studentId);
        JobSkill jobSkill = jobSkillRepository.findById(id);
        if(student.getId()==studentId && jobSkill.getId()==id) {
            return jobSkill;
        }
        else{
            throw new NullPointerException("can't create jobskill");
        }
    }
    //change 1 Jobskill by id
    public JobSkill ChangeJsById(int studentId,int id, JobSkillDTO jobSkillDTO,String token){
        User user = userRepository.findByToken(token);
        Student student =studentRepository.findById(studentId);
        JobSkill jobSkill = jobSkillRepository.findById(id);
        if(user.getStudent().equals(student) && jobSkillDTO.getStudentId()==student.getId() && jobSkill.getId()==id){
            jobSkill.setId(jobSkillDTO.getId());
            jobSkill.setCompany(jobSkillDTO.getCompany());
            jobSkill.setStudentId(jobSkillDTO.getStudentId());
            jobSkill.setUpdateTime(jobSkillDTO.getUpdateTime());
            jobSkill.setSkill(jobSkillDTO.getSkill());
            jobSkillRepository.save(jobSkill);
            return jobSkill;
        }
        else{
            throw new NullPointerException("can't change this jobskill");
        }
    }
}

