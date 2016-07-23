package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.StudentInfoDTO;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.StudentInfo;
import uet.usercontroller.service.StudentInfoService;
import uet.usercontroller.stereotype.NoAuthentication;
import uet.usercontroller.stereotype.RequiredRoles;
import uet.usercontroller.model.Student;
import java.util.List;

/**
 * Created by root on 7/20/16.
 */
@RestController
public class StudentInfoController {
    @Autowired
    private StudentInfoService studentinfoService;


    //show all student information
    // @RequiredRoles({Role.ADMIN,Role.PARTNER1,Role.PARTNER2})
    @NoAuthentication
    @RequestMapping(value = "/student/studentInfo",method = RequestMethod.GET)
    public List<StudentInfo> getAllStudentInfo(){
        return studentinfoService.getAllStudentInfo();
    }

    //show info of a student
//  @RequiredRoles({Role.STUDENT,Role.PARTNER1,Role.ADMIN,Role.PARTNER2})
    @NoAuthentication
    @RequestMapping(value = "student/{studentId}/studentInfo/studentInfoId}",method = RequestMethod.GET)
    public StudentInfo getStudentInfo(@PathVariable("studentId") int studentId, @PathVariable("studentInfoId") int id){
        return studentinfoService.getStudentInfo(studentId,id);
    }

//    create info
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/student/{studentId}/studentInfo",method = RequestMethod.POST)
    public StudentInfo createStudentInfo(@PathVariable("studentId") int studentId, @RequestBody StudentInfoDTO studentInfoDTO){
        return studentinfoService.createStudentInfo(studentId,studentInfoDTO);
    }

    //edit info of a student
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/student/{studentId}/studentInfo/{studentInfoId}",method = RequestMethod.PUT)
    public StudentInfo editStudentInfo(@PathVariable("studentId") int studentId, @PathVariable("studentInfoId") int id, StudentInfoDTO studentInfoDTO){
        return studentinfoService.editStudentInfo(studentId,id,studentInfoDTO);
    }

   //delete info of a student
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/student/{studentId}/studentInfo/{studentInfoId}",method = RequestMethod.DELETE)
    public void deleteStudentInfo(@PathVariable("studentId") int studentId, @PathVariable("studentinfoId") int id){
        studentinfoService.deleteStudentInfo(studentId,id);
    }
}
