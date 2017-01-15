package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.InfoBySchoolDTO;
import uet.usercontroller.model.InfoBySchool;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.Student;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.InfoBySchoolRepository;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tu on 07-Jul-16.
 */
@Service
public class InfoBySchoolService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private InfoBySchoolRepository infoBySchoolRepository;

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

    //create info
    public InfoBySchool createInfo(int studentId, InfoBySchoolDTO infoBySchoolDTO){
        Student student = studentRepository.findOne(studentId);
        InfoBySchool infoBySchool = new InfoBySchool();
        infoBySchool.setStudentCode(infoBySchoolDTO.getStudentCode());
        infoBySchool.setMajor(infoBySchoolDTO.getMajor());
        infoBySchool.setGPA(infoBySchoolDTO.getGPA());
        infoBySchool.setDiploma(infoBySchoolDTO.getDiploma());
        infoBySchool.setGrade(infoBySchoolDTO.getGrade());
        infoBySchool.setGraduationYear(infoBySchoolDTO.getGraduationYear());
        infoBySchool.setStudentClass(infoBySchoolDTO.getStudentClass());
        student.setInfoBySchool(infoBySchool);
        return infoBySchoolRepository.save(infoBySchool);
    }

    //show info of a student
    public InfoBySchool getInfo(int infoId, String token){
        User user = userRepository.findByToken(token);
        if ( user.getRole() == Role.STUDENT ) {
            Student student = user.getStudent();
            InfoBySchool info = infoBySchoolRepository.findOne(infoId);
            if (student.getInfoBySchool().equals(info)) {
                return info;
            }
            else {
                throw new NullPointerException("No result.");
            }
        }
        else {
            InfoBySchool info = infoBySchoolRepository.findOne(infoId);
            return info;
        }
    }

    //edit info of a student
    public InfoBySchool editInfo(int infoId, InfoBySchoolDTO infoBySchoolDTO) {
        InfoBySchool info = infoBySchoolRepository.findOne(infoId);
        if (infoBySchoolDTO.getStudentCode() != null) {
            info.setStudentCode(infoBySchoolDTO.getStudentCode());
        }
        if (infoBySchoolDTO.getMajor() != null) {
            info.setMajor(infoBySchoolDTO.getMajor());
        }
        if (infoBySchoolDTO.getGPA() != null) {
            info.setGPA(infoBySchoolDTO.getGPA());
        }
        if (infoBySchoolDTO.getDiploma() != null) {
            info.setDiploma(infoBySchoolDTO.getDiploma());
        }
        if (infoBySchoolDTO.getGrade() != null) {
            info.setGrade(infoBySchoolDTO.getGrade());
        }
        if (infoBySchoolDTO.getGraduationYear() != null) {
            info.setGraduationYear(infoBySchoolDTO.getGraduationYear());
        }
        if (infoBySchoolDTO.getStudentClass() != null) {
            info.setStudentClass(infoBySchoolDTO.getStudentClass());
        }
        return infoBySchoolRepository.save(info);
    }

    //delete info of a student
    public void deleteInfo(int infoId){
        InfoBySchool info = infoBySchoolRepository.findOne(infoId);
        info.setStudentClass(null);
        info.setGrade(null);
        info.setDiploma(null);
        info.setMajor(null);
        info.setGPA(null);
        info.setGraduationYear(null);
        info.setStudentCode(null);
        infoBySchoolRepository.save(info);
    }
}

