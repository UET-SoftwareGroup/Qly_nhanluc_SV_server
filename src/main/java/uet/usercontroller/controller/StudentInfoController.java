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
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.PARTNER1,Role.ADMIN})
    @RequestMapping(value = "/studentinfo",method = RequestMethod.GET)
    public List<StudentInfo> getAllStudentInfo(){
        return studentinfoService.getAllStudentInfo();
    }

    //show info of a student
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.ADMIN,Role.STUDENT,Role.PARTNER1})
    @RequestMapping(value = "/studentinfo/{id}",method = RequestMethod.GET)
    public StudentInfo getStudentInfo(@PathVariable("id") int id, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return studentinfoService.getStudentInfo(id,token);
    }

    //edit info of a student
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/studentinfo/{id}",method = RequestMethod.PUT)
    public StudentInfo editStudentInfo(@PathVariable("id") int id, @RequestBody StudentInfoDTO studentInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return studentinfoService.editStudentInfo(id,studentInfoDTO,token);
    }

   //delete info of a student
   @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.STUDENT,Role.ADMIN})
    @RequestMapping(value = "/studentinfo/{id}",method = RequestMethod.DELETE)
    public void deleteStudentInfo(@PathVariable("id") int id, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        studentinfoService.deleteStudentInfo(id, token);
    }
}
