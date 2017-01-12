package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.PartnerDTO;
import uet.usercontroller.DTO.PostDTO;
import uet.usercontroller.DTO.StudentDTO;
import uet.usercontroller.model.*;
import uet.usercontroller.repository.PartnerRepository;
import uet.usercontroller.repository.PostRepository;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Trung on 7/8/2016.
 */
@Service
public class StudentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    PostRepository postRepository;
    //Show all
    public List<Student> getStudents() {
        List<Student> allStudents = (List<Student>) studentRepository.findAll();
        return allStudents;
    }
    //Show
    public Student findStudent(int studentId, String token) {
        User user = userRepository.findByToken(token);
        if( user.getRole() == Role.STUDENT ){
            Student student1 = studentRepository.findById(studentId);
            if(user.getStudent().equals(student1)){
                return student1;
            }
            else{
                throw new NullPointerException("No result.");
            }
        }
        else {
            Student student2 = studentRepository.findById(studentId);
            if (student2 != null) {
                return student2;
            } else {
                throw new NullPointerException("No result.");
            }
        }
    }
    //Edit
    public Student editStudent(int studentId, StudentDTO studentDTO, String token){
        User user=userRepository.findByToken(token);
        if( user.getRole()== Role.STUDENT) {
            Student student1 = studentRepository.findById(studentId);
            if (user.getStudent().equals(student1)) {
                if (studentDTO.getStudentName() != null) {
                    student1.setStudentName(studentDTO.getStudentName());
                }
            }
            return studentRepository.save(student1);
        }
        else {
            Student student2 = studentRepository.findOne(studentId);
            if(student2!=null){
                if (studentDTO.getStudentName() != null) {
                    student2.setStudentName(studentDTO.getStudentName());
                }
                    return studentRepository.save(student2);
                }
            else{
                    throw new NullPointerException("Edit failed.");
                }
        }
    }

//    //Create
//    public Student createStudent(int userId, StudentDTO studentDTO) {
//        User user = userRepository.findOne(userId);
//        Student student = new Student();
//        student.setStudentName(studentDTO.getStudentName());
//        user.setStudent(student);
//        return studentRepository.save(student);
//    }

    //Student search partner
    public List<HashMap<String, String>> searchPartner(PartnerDTO partnerDTO){
        List<Partner> allPartnerMatched = (List<Partner>) partnerRepository.findByPartnerNameContaining(partnerDTO.getPartnerName());
        List<HashMap<String, String>> searchList = new ArrayList<HashMap<String, String>>();
        for (Partner partner : allPartnerMatched){
            HashMap<String, String> lPartner = new HashMap<String, String>();
            String id = String.valueOf(partner.getId());
            String partnerName = partner.getPartnerName();
            lPartner.put("id", id);
            lPartner.put("partnerName", partnerName);
            searchList.add(lPartner);
        }
        return searchList;
    }

    //Student search post description
    public List<Post> searchDescription(PostDTO postDTO){
        List<Post> allPostMatched = (List<Post>) postRepository.findByDescribePostContaining(postDTO.getDescribePost());
        return allPostMatched;
    }

    //Student search post by content
    public List<Post> searchContent(PostDTO postDTO){
        List<Post> allPostMatched = (List<Post>) postRepository.findByContentContaining(postDTO.getContent());
        return allPostMatched;
    }

    //Delete
    public void delStudent(int studentId) {
        Student student = studentRepository.findOne(studentId);
//        student.setInfoBySchool(null);
//        student.setInternship(null);
//        student.setStudentInfo(null);
        student.setStudentName(null);
        studentRepository.save(student);

    }
}
