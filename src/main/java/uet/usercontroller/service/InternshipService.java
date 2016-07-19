package uet.usercontroller.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.InternshipDTO;
import uet.usercontroller.model.Internship;
import uet.usercontroller.model.Student;
import uet.usercontroller.repository.InternshipRepository;
import uet.usercontroller.repository.StudentRepository;

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

    //show all Internships
    public List<Internship> getAllIntern(){
        List<Internship> All = (List<Internship>) internshipRepository.findAll();
        return  All;
    }
    //find Internship By Id
    public Internship findInternById(int id){
        Internship internship =internshipRepository.findById(id);
        return internship;
    }
    //Delete by Id
    public String deleteById(int id){
        Internship internship = internshipRepository.findById(id);
        internshipRepository.delete(internship);
        return "delete";
    }
    //change 1 internship By Id
    public Internship changeById(int id, InternshipDTO internshipDTO){
        Internship internship = internshipRepository.findById(id);
        internship.setId(internshipDTO.getId());
//        internship.setPartnerId(internshipDTO.getPartnerId());
//        internship.setStudentId(internshipDTO.getStudentId());
        internship.setCompany(internshipDTO.getCompany());
        internship.setStartDate(internshipDTO.getStartDate());
        internship.setEndDate(internshipDTO.getEndDate());
        internship.setSupervisor(internshipDTO.getSupervisor());
        internshipRepository.save(internship);
        return internship;
    }
    //create 1 internship
    public Internship createIntern(int studentId,InternshipDTO internshipDTO){
        Student student = studentRepository.findOne(studentId);
        Internship internship = new Internship();
        internship.setStartDate(internshipDTO.getStartDate());
        internship.setEndDate(internshipDTO.getEndDate());
        internship.setCompany(internshipDTO.getCompany());
        internship.setSupervisor(internshipDTO.getSupervisor());
        student.setInternship(internship);
        return  internshipRepository.save(internship);
    }

}
