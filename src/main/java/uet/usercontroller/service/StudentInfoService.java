package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.StudentInfoDTO;
import uet.usercontroller.model.Student;
import uet.usercontroller.model.StudentInfo;
import uet.usercontroller.repository.StudentInfoRepository;
import uet.usercontroller.repository.StudentRepository;

import java.util.List;

/**
 * Created by root on 7/20/16.
 */
@Service
public class StudentInfoService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    // show all student information
    public List<StudentInfo> getAllStudentInfo() {
        List<StudentInfo> allInfo = (List<StudentInfo>) studentInfoRepository.findAll();
        return allInfo;
    }

    // show info of a student
    public StudentInfo getStudentInfo(int studentId, int id){
        Student student = studentRepository.findOne(studentId);
        StudentInfo info = studentInfoRepository.findOne(id);
        if (student.getStudentInfo().equals(info)){
            return info;
        }
        else{
            throw new NullPointerException("No result.");
        }
    }

    //create info
    public StudentInfo createStudentInfo(int studentId, StudentInfoDTO studentInfoDTO){
        Student student = studentRepository.findOne(studentId);
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setBirthday(studentInfoDTO.getBirthday());
        studentInfo.setAddress(studentInfoDTO.getAddress());
        studentInfo.setPhoneNumber(studentInfoDTO.getPhoneNumber());
        studentInfo.setEmail(studentInfoDTO.getEmail());
        studentInfo.setSkype(studentInfoDTO.getSkype());
        studentInfo.setDesire(studentInfoDTO.getDesire());
        student.setStudentInfo(studentInfo);
        return studentInfoRepository.save(studentInfo);
    }

    //edit info of a student
    public StudentInfo editStudentInfo(int studentId, int id, StudentInfoDTO studentInfoDTO) {
        Student student = studentRepository.findOne(studentId);
        StudentInfo info = studentInfoRepository.findOne(id);
        if (student.getStudentInfo().equals(info)){
            if (studentInfoDTO.getBirthday() != null){
                info.setBirthday(studentInfoDTO.getBirthday());
            }
            if (studentInfoDTO.getAddress() != null){
                info.setAddress(studentInfoDTO.getAddress());
            }
            if (studentInfoDTO.getEmail() != null){
                info.setEmail(studentInfoDTO.getEmail());
            }
            if (studentInfoDTO.getPhoneNumber() != null){
                info.setPhoneNumber(studentInfoDTO.getPhoneNumber());
            }
            if (studentInfoDTO.getSkype() != null){
                info.setSkype(studentInfoDTO.getSkype());
            }
            if (studentInfoDTO.getDesire() != null){
                info.setDesire(studentInfoDTO.getDesire());
            }
        }
        else{
            throw new NullPointerException("Edit failed.");
        }
        return studentInfoRepository.save(info);
    }




    //delete info of a student
    public void deleteStudentInfo(int studentId, int id) {
        Student student = studentRepository.findOne(studentId);
        StudentInfo info = studentInfoRepository.findOne(id);
        if (student.getStudentInfo().equals(info)){
            studentInfoRepository.delete(info);
        }
        else{
            throw new NullPointerException("Delete failed.");
        }
    }

}

//co cai git gui thi up de hon, co ma ko co cung ko sao. doi ti toi tim