package uet.usercontroller.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.InternshipDTO;
import uet.usercontroller.model.*;
import uet.usercontroller.repository.InternshipRepository;
import uet.usercontroller.repository.PartnerRepository;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.repository.UserRepository;

import javax.validation.constraints.Null;
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
    @Autowired
    private PartnerRepository partnerRepository;
    //show all Internships
    public List<Internship> getAllIntern(String token){
        List<Internship> All = (List<Internship>) internshipRepository.findAll();
        return  All;
    }

    //show all internships of a partner
    public List<Internship> getAllInPartner(int partnerId, String token){
        User user=userRepository.findByToken(token);
        Partner partner=partnerRepository.findById(partnerId);
        if(user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.VIP_PARTNER))
        {
            return partner.getInternships();
        }
        else {
            throw new NullPointerException("You don's have permission");
        }

    }
    //find a internship
    public Internship findInternById(int id,String token) {
        User user = userRepository.findByToken(token);
        Internship internship = internshipRepository.findById(id);
        if(user.getRole()==Role.STUDENT){
            Student student = user.getStudent();
            if(student.getInternship().equals(internship)){
                return internship;
            }
            else{
                throw new NullPointerException("You don't have permission");
            }
        }
        else {
            return internship;
        }
    }


    //Create a internship
    public Internship createIntern(int studentId,int partnerId,InternshipDTO internshipDTO,String token) {
        User user = userRepository.findByToken(token);
        Student student = studentRepository.findOne(studentId);
        if(user.getRole().equals(Role.ADMIN)){
            if(student.getInternship()==null) {
                Internship internship = new Internship();
                internship.setPartnerId(partnerId);
                internship.setStartDate(internshipDTO.getStartDate());
                internship.setEndDate(internshipDTO.getEndDate());
                internship.setCompany(internshipDTO.getCompany());
                internship.setSupervisor(internshipDTO.getSupervisor());
                student.setInternship(internship);
                return internshipRepository.save(internship);
            }
            else {
                throw new NullPointerException("This student had internship ");
            }
        }
        else{
            throw new NullPointerException("You don't have permission");
        }
    }

    //Edit a internship
    public Internship changeById( int internId, InternshipDTO internshipDTO,String token){
        User user = userRepository.findByToken(token);
        if(user.getRole().equals(Role.ADMIN)) {
            Internship internship = internshipRepository.findOne(internId);
            internship.setPartnerId(internshipDTO.getPartnerId());
            internship.setCompany(internshipDTO.getCompany());
            internship.setStartDate(internshipDTO.getStartDate());
            internship.setEndDate(internshipDTO.getEndDate());
            internship.setSupervisor(internshipDTO.getSupervisor());
            return internshipRepository.save(internship);
        }
        else{
            throw new NullPointerException("You don't have permission");
        }
    }

    //Delete by Id
    public void deleteById(int id,String token){
        User user = userRepository.findByToken(token);
        Student student = studentRepository.findByInternshipId(id);
        if (user.getRole().equals(Role.ADMIN)) {
            student.setInternship(null);
            internshipRepository.delete(id);
        }
        else {
            throw new NullPointerException("You don't have permission");
        }
    }
}
