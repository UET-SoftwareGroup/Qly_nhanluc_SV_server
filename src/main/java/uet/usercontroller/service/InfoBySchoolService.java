package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.InfoBySchoolDTO;
import uet.usercontroller.model.InfoBySchool;
import uet.usercontroller.model.Student;
import uet.usercontroller.repository.InfoBySchoolRepository;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.repository.UserRepository;

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
    public List<InfoBySchool> getAllInfo(){
        List<InfoBySchool> allStudents = (List<InfoBySchool>) infoBySchoolRepository.findAll();
        return allStudents;
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
    public InfoBySchool getInfo(int studentId, int infoId){
        Student student = studentRepository.findOne(studentId);
        InfoBySchool info = infoBySchoolRepository.findOne(infoId);
        if (student.getInfoBySchool().equals(info)){
            return info;
        }
        else{
            throw new NullPointerException("No result.");
        }
    }

    //edit info of a student
    public InfoBySchool editInfo(int studentId, int infoId, InfoBySchoolDTO infoBySchoolDTO, String token) {
        Student student = studentRepository.findOne(studentId);
        InfoBySchool info = infoBySchoolRepository.findOne(infoId);
        if (student.getInfoBySchool().equals(info)) {
            if (infoBySchoolDTO.getStudentCode() != 0) {
                info.setStudentCode(infoBySchoolDTO.getStudentCode());
            }
            if (infoBySchoolDTO.getMajor() != null) {
                info.setMajor(infoBySchoolDTO.getMajor());
            }
            if (infoBySchoolDTO.getGPA() != 0) {
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
        } else {
            throw new NullPointerException("Edit failed.");
        }
        return infoBySchoolRepository.save(info);
    }

    //delete info of a student
    public void deleteInfo(int studentId, int infoId){
        Student student = studentRepository.findOne(studentId);
        InfoBySchool info = infoBySchoolRepository.findOne(infoId);
        if( student.getInfoBySchool().equals(info) ) {
            student.setInfoBySchool(null);
            infoBySchoolRepository.delete(infoId);
        }
        else{
            throw new NullPointerException("Delete failed.");
        }
    }
}

