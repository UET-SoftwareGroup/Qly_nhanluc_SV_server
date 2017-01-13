package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.PartnerDTO;
import uet.usercontroller.DTO.PartnerInfoDTO;
import uet.usercontroller.DTO.PostDTO;
import uet.usercontroller.DTO.StudentDTO;
import uet.usercontroller.model.*;
import uet.usercontroller.repository.*;

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
    @Autowired
    PartnerInfoRepository partnerInfoRepository;
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

//    //Create
//    public Student createStudent(int userId, StudentDTO studentDTO) {
//        User user = userRepository.findOne(userId);
//        Student student = new Student();
//        student.setStudentName(studentDTO.getStudentName());
//        user.setStudent(student);
//        return studentRepository.save(student);
//    }

    //Student search partner
    public List<PartnerInfo> searchPartner(PartnerInfoDTO partnerInfoDTO){
        List<PartnerInfo> allPartnerMatched = (List<PartnerInfo>) partnerInfoRepository.findByPartnerNameContaining(partnerInfoDTO.getPartnerName());
        return allPartnerMatched;
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
}
