package uet.usercontroller.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.InternshipDTO;
import uet.usercontroller.model.Internship;
import uet.usercontroller.model.Student;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.InternshipRepository;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.repository.UserRepository;

import java.util.List;

/**
 * Created by fgv on 7/11/2016.
 */
@Service
public class InternshipService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private InternshipRepository internshipRepository;
    @Autowired
    private UserRepository userRepository;
    //show all Internships
    public List<Internship> getAllIntern(String token){
        User user =userRepository.findByToken(token);
        List<Internship> All = (List<Internship>) internshipRepository.findAll();
        return  All;
    }
    //find Internship By Id
    public Internship findInternById(int studentId,int id,String token) {
        User user = userRepository.findByToken(token);
        Internship internship = internshipRepository.findById(id);
        if (internship.getStudentId() == studentId) {
            return internship;
        }
        else{
            throw new NullPointerException(" khong thuc hien dc ");
        }
    }
    //Delete by Id
    public String deleteById(int studentId,int id,String token){
        User user = userRepository.findByToken(token);
        Internship internship = internshipRepository.findById(id);
        Student student = studentRepository.findById(studentId);
        if(internship.getStudentId()==student.getId()&& internship.getId()==id) {
            internshipRepository.delete(internship);
            return "delete";
        }
        else{
            throw new NullPointerException("can't delete");
        }
    }
    //change 1 internship By Id
    public Internship changeById( int studentId,int id, InternshipDTO internshipDTO,String token){
        User user = userRepository.findByToken(token);
        Internship internship = internshipRepository.findById(id);
        Student student = studentRepository.findById(studentId);
        if(internship.getStudentId()==student.getId()) {
            internship.setId(internshipDTO.getId());
            internship.setPartnerId(internshipDTO.getPartnerId());
            internship.setStudentId(internshipDTO.getStudentId());
            internship.setCompany(internshipDTO.getCompany());
            internship.setStartDate(internshipDTO.getStartDate());
            internship.setEndDate(internshipDTO.getEndDate());
            internship.setSupervisor(internshipDTO.getSupervisor());
            internshipRepository.save(internship);
            student.setInternship(internship);
            studentRepository.save(student);
            return internship;
        }
        else {
            throw new NullPointerException("can't change this internship");
        }
    }
    //create 1 internship
    public Internship createIntern(int studentId,InternshipDTO internshipDTO,String token){
        User user =userRepository.findByToken(token);
        Student student = studentRepository.findOne(studentId);
        Internship internship = new Internship();
        internship.setStudentId(internshipDTO.getStudentId());
        internship.setPartnerId(internshipDTO.getPartnerId());
        internship.setStartDate(internshipDTO.getStartDate());
        internship.setEndDate(internshipDTO.getEndDate());
        internship.setCompany(internshipDTO.getCompany());
        internship.setSupervisor(internshipDTO.getSupervisor());
        student.setInternship(internship);
        studentRepository.save(student);
        return  internshipRepository.save(internship);
    }

}
