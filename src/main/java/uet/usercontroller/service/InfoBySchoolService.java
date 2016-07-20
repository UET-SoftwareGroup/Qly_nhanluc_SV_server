package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.InfoBySchoolDTO;
import uet.usercontroller.model.InfoBySchool;
import uet.usercontroller.model.Student;
import uet.usercontroller.repository.InfoBySchoolRepository;
import uet.usercontroller.repository.StudentRepository;

import java.util.List;

/**
 * Created by Tu on 07-Jul-16.
 */
@Service
public class InfoBySchoolService {
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
//        InfoBySchool info = infoBySchoolRepository.findOne(studentId);
//        info.setStudentCode(infoBySchoolDTO.getStudentCode());
//        info.setMajor(infoBySchoolDTO.getMajor());
//        info.setGPA(infoBySchoolDTO.getGPA());
//        info.setDiploma(infoBySchoolDTO.getDiploma());
//        info.setGrade(infoBySchoolDTO.getGrade());
//        info.setGraduationYear(infoBySchoolDTO.getGraduationYear());
//        info.setStudentClass(infoBySchoolDTO.getStudentClass());
        Student student = studentRepository.findOne(studentId);
        InfoBySchool infoBySchool = new InfoBySchool();
        infoBySchool.setDiploma(infoBySchoolDTO.getDiploma());
        infoBySchool.setGrade(infoBySchoolDTO.getGrade());
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
    public InfoBySchool editInfo(int studentId, int infoId, InfoBySchoolDTO infoBySchoolDTO){
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
        }
        else{
            throw new NullPointerException("Edit failed.");
        }
        return infoBySchoolRepository.save(info);
    }

    //delete info of a student
    public void deleteInfo(int studentId, int infoId){
        Student student = studentRepository.findOne(studentId);
        InfoBySchool info = infoBySchoolRepository.findOne(infoId);
        if( student.getInfoBySchool().equals(info) ) {
            infoBySchoolRepository.delete(infoId);
        }
        else{
            throw new NullPointerException("Delete failed.");
        }
    }
}
