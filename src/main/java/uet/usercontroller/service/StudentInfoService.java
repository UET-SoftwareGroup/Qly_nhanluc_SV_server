package uet.usercontroller.service;

import com.sun.corba.se.impl.logging.InterceptorsSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.StudentInfoDTO;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.Student;
import uet.usercontroller.model.StudentInfo;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.StudentInfoRepository;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.repository.UserRepository;

import java.util.List;

/**
 * Created by root on 7/20/16.
 */
@Service
public class StudentInfoService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @Autowired
    private UserRepository userRepository;

    // show all student information
    public List<StudentInfo> getAllStudentInfo() {
        List<StudentInfo> allInfo = (List<StudentInfo>) studentInfoRepository.findAll();
        return allInfo;
    }

    // show info of a student
    public StudentInfo getStudentInfo( int id, String token) {
        User user = userRepository.findByToken(token);
        Student student = user.getStudent();
        StudentInfo studentinfo = studentInfoRepository.findOne(id);
        if (student.getStudentInfo().equals(studentinfo)) {
            return studentinfo;
        } else
            throw new NullPointerException("No result");
    }

    //edit info of a student
    public StudentInfo editStudentInfo(int id, StudentInfoDTO studentInfoDTO, String token) {
        User user = userRepository.findByToken(token);
        Student student = user.getStudent();
        StudentInfo studentinfo = studentInfoRepository.findOne(id);
        if (student.getStudentInfo().equals(studentinfo)){
            studentinfo.setFullName(studentInfoDTO.getFullName());
            studentinfo.setBirthday(studentInfoDTO.getBirthday());
            studentinfo.setAddress(studentInfoDTO.getAddress());
            studentinfo.setEmail(studentInfoDTO.getEmail());
            studentinfo.setPhoneNumber(studentInfoDTO.getPhoneNumber());
            studentinfo.setSkype(studentInfoDTO.getSkype());
            studentinfo.setDesire(studentInfoDTO.getDesire());
            return studentInfoRepository.save(studentinfo);
        } else
            throw new NullPointerException("Error ");
    }




    //delete info of a student
    public void deleteStudentInfo(int id) {
        StudentInfo studentinfo = studentInfoRepository.findOne(id);
        studentinfo.setFullName(null);
        studentinfo.setBirthday(null);
        studentinfo.setPhoneNumber(null);
        studentinfo.setAddress(null);
        studentinfo.setEmail(null);
        studentinfo.setSkype(null);
        studentinfo.setDesire(null);
        studentInfoRepository.save(studentinfo);
    }

}

