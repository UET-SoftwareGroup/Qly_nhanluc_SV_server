package uet.usercontroller.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.InternshipDTO;
import uet.usercontroller.model.Internship;
import uet.usercontroller.model.Role;
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
        User user = userRepository.findByToken(token);
        List<Internship> All = (List<Internship>) internshipRepository.findAll();
        return  All;
    }
    //find Internship By Id
    public Internship findInternById(int id,String token) {
        User user = userRepository.findByToken(token);
        Internship internship = internshipRepository.findById(id);
        if(user.getRole()==Role.STUDENT){
            Student student = user.getStudent();
            if(student.getInternship().equals(internship)){
                return internship;
            }
            else{
                throw new NullPointerException("you can't access");
            }
        }
        else {
            return internship;
        }
    }
    //Delete by Id
    public void deleteById(int id,int studentId,String token){
        User user = userRepository.findByToken(token);
        Student student = studentRepository.findOne(studentId);
        student.setInternship(null);
        studentRepository.save(student);
        internshipRepository.delete(id);
        //studentRepository.delete(internshipId);
//        internship.setPartnerId(0);
//        internship.setCompany(null);
//        internship.setStartDate(null);
//        internship.setEndDate(null);
//        internship.setSupervisor(null);
//        internshipRepository.save(internship);
    }
    //change 1 internship By Id
    public Internship changeById( int internId, InternshipDTO internshipDTO,String token){
            User user = userRepository.findByToken(token);
            Internship internship = internshipRepository.findOne(internId);
            internship.setPartnerId(internshipDTO.getPartnerId());
            internship.setCompany(internshipDTO.getCompany());
            internship.setStartDate(internshipDTO.getStartDate());
            internship.setEndDate(internshipDTO.getEndDate());
            internship.setSupervisor(internshipDTO.getSupervisor());
            return internshipRepository.save(internship);
    }
    //create 1 internship
    public Internship createIntern(int studentId,InternshipDTO internshipDTO,String token) {
        User user = userRepository.findByToken(token);
        Student student = studentRepository.findOne(studentId);
        if(student.getInternship()==null) {
            Internship internship = new Internship();
            internship.setPartnerId(internshipDTO.getPartnerId());
            internship.setStartDate(internshipDTO.getStartDate());
            internship.setEndDate(internshipDTO.getEndDate());
            internship.setCompany(internshipDTO.getCompany());
            internship.setSupervisor(internshipDTO.getSupervisor());
            student.setInternship(internship);
            return internshipRepository.save(internship);
        }
        else{
            throw new NullPointerException("This student had internship");
        }
    }
}
