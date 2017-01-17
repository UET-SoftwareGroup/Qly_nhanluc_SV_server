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
    @Autowired
    InfoBySchoolRepository infoBySchoolRepository;
    //Show all student information
    public List<HashMap<String, String>> getAllInfo(){
        List<InfoBySchool> allInfoBySchool = (List<InfoBySchool>) infoBySchoolRepository.findAll();
        List<HashMap<String, String>> listPartnerInfo = new ArrayList<HashMap<String, String>>();
        for (InfoBySchool infoBySchool : allInfoBySchool){
            HashMap<String, String> lInfoBySChool = new HashMap<String, String>();
            Student student = studentRepository.findByStudentInfoId(infoBySchool.getId());
            User user = userRepository.findByStudentId(student.getId());
            String userId = String.valueOf(user.getId());
            String status = user.getStatus();
            String studentName = infoBySchool.getStudentName();
            String infoBySchoolId = String.valueOf(infoBySchool.getId());
            String gpa = String.valueOf(infoBySchool.getGPA());
            String diploma = infoBySchool.getDiploma();
            String grade = infoBySchool.getGrade();
            String graduationYear = infoBySchool.getGraduationYear();
            String major = infoBySchool.getMajor();
            String studentClass = infoBySchool.getStudentClass();
            String studentCode = String.valueOf(infoBySchool.getStudentCode());
            lInfoBySChool.put("userId", userId);
            lInfoBySChool.put("status", status);
            lInfoBySChool.put("studentName", studentName);
            lInfoBySChool.put("infoBySchoolId", infoBySchoolId);
            lInfoBySChool.put("gpa", gpa);
            lInfoBySChool.put("diploma", diploma);
            lInfoBySChool.put("grade", grade);
            lInfoBySChool.put("graduationYear", graduationYear);
            lInfoBySChool.put("major", major);
            lInfoBySChool.put("studentClass", studentClass);
            lInfoBySChool.put("studentCode", studentCode);
            listPartnerInfo.add(lInfoBySChool);
        }
        return listPartnerInfo;
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
