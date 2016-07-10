package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.InfoBySchoolDTO;
import uet.usercontroller.model.InfoBySchool;
import uet.usercontroller.repository.InfoBySchoolRepository;

import java.util.List;

/**
 * Created by Tu on 07-Jul-16.
 */
@Service
public class InfoBySchoolService {
    @Autowired
    private InfoBySchoolRepository infoBySchoolRepository;

    //Show all student information
    public List<InfoBySchool> getAllInfo(){
        List<InfoBySchool> allStudents = (List<InfoBySchool>) infoBySchoolRepository.findAll();
        return allStudents;
    }

    //create info
    public InfoBySchool createInfo(int studentId, InfoBySchoolDTO infoBySchoolDTO){
        InfoBySchool info = infoBySchoolRepository.findOne(studentId);
        info.setStudentCode(infoBySchoolDTO.getStudentCode());
        info.setMajor(infoBySchoolDTO.getMajor());
        info.setGPA(infoBySchoolDTO.getGPA());
        info.setDiploma(infoBySchoolDTO.getDiploma());
        info.setGrade(infoBySchoolDTO.getGrade());
        info.setGraduationYear(infoBySchoolDTO.getGraduationYear());
        info.setStudentClass(infoBySchoolDTO.getStudentClass());
        return infoBySchoolRepository.save(info);
    }

    //show info of a student
    public InfoBySchool getInfo(int id){
        InfoBySchool info = infoBySchoolRepository.findOne(id);
        return info;
    }

    //edit info of a student
    public InfoBySchool editInfo(int id, InfoBySchoolDTO infoBySchoolDTO){
        InfoBySchool info = infoBySchoolRepository.findOne(id);
        info.setStudentCode(infoBySchoolDTO.getStudentCode());
        info.setMajor(infoBySchoolDTO.getMajor());
        info.setGPA(infoBySchoolDTO.getGPA());
        info.setDiploma(infoBySchoolDTO.getDiploma());
        info.setGrade(infoBySchoolDTO.getGrade());
        info.setGraduationYear(infoBySchoolDTO.getGraduationYear());
        info.setStudentClass(infoBySchoolDTO.getStudentClass());
        return infoBySchoolRepository.save(info);
    }

    //delete info of a student
    public void deleteInfo(int id){
        infoBySchoolRepository.delete(id);
    }
}
