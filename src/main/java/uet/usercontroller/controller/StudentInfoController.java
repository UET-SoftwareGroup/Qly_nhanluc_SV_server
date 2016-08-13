package uet.usercontroller.controller;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.StudentInfoDTO;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.StudentInfo;
import uet.usercontroller.service.StudentInfoService;
import uet.usercontroller.stereotype.NoAuthentication;
import uet.usercontroller.stereotype.RequiredRoles;
import uet.usercontroller.model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by root on 7/20/16.
 */
@RestController

public class StudentInfoController {
    @Autowired
    private StudentInfoService studentinfoService;
    
    

    //show all student information
    @NoAuthentication
    @RequestMapping(value = "/student/studentInfo",method = RequestMethod.GET)
    public List<StudentInfo> getAllStudentInfo(HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return studentinfoService.getAllStudentInfo(token);
    }

    //show info of a student
    @NoAuthentication
    @RequestMapping(value = "student/{studentId}/studentInfo/studentInfoId}",method = RequestMethod.GET)
    public StudentInfo getStudentInfo(@PathVariable("studentId") int studentId, @PathVariable("studentInfoId") int id, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return studentinfoService.getStudentInfo(studentId,id,token);
    }

    //create info
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/student/{studentId}/studentInfo",method = RequestMethod.POST)
    public StudentInfo createStudentInfo(@PathVariable("studentId") int studentId, @RequestBody StudentInfoDTO studentInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return studentinfoService.createStudentInfo(studentId,studentInfoDTO,token);
    }

    //edit info of a student
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/student/{studentId}/studentInfo/{studentInfoId}",method = RequestMethod.PUT)
    public StudentInfo editStudentInfo(@PathVariable("studentId") int studentId, @PathVariable("studentInfoId") int id,@RequestBody StudentInfoDTO studentInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return studentinfoService.editStudentInfo(studentId,id,studentInfoDTO,token);
    }

   //delete info of a student
    @RequiredRoles({Role.STUDENT,Role.ADMIN})
    @RequestMapping(value = "/student/{studentId}/studentInfo/{studentInfoId}",method = RequestMethod.DELETE)
    public void deleteStudentInfo(@PathVariable("studentId") int studentId, @PathVariable("studentinfoId") int id, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        studentinfoService.deleteStudentInfo(studentId,id,token);
    }
}
