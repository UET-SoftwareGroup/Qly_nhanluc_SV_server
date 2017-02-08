package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.StudentInfoDTO;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.StudentInfo;
import uet.usercontroller.service.StudentInfoService;
import uet.usercontroller.stereotype.RequiredRoles;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 7/20/16.
 */
@RestController

public class StudentInfoController {
    @Autowired
    private StudentInfoService studentinfoService;

    //show all student information
    @RequiredRoles({Role.VIP_PARTNER,Role.ADMIN})
    @RequestMapping(value = "/studentInfo",method = RequestMethod.GET)
    public List<HashMap<String, String>> getAllStudentInfo(){
        return (List<HashMap<String, String>>) studentinfoService.getAllStudentInfo();
    }

    //show info of a student
    @RequiredRoles({Role.ADMIN,Role.STUDENT,Role.VIP_PARTNER})
    @RequestMapping(value = "/studentInfo/{id}",method = RequestMethod.GET)
    public StudentInfo getStudentInfo(@PathVariable("id") int id, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return studentinfoService.getStudentInfo(id,token);
    }

    //edit info of a student
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/studentInfo/{id}",method = RequestMethod.PUT)
    public StudentInfo editStudentInfo(@PathVariable("id") int id, @RequestBody StudentInfoDTO studentInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return studentinfoService.editStudentInfo(id,studentInfoDTO,token);
    }

    //change avatar
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "changeAva", method = RequestMethod.PUT)
    public void changeAva(@RequestBody StudentInfoDTO studentInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        studentinfoService.changeAva(studentInfoDTO, token);
    }

   //delete info of a student
    @RequiredRoles({Role.STUDENT,Role.ADMIN})
    @RequestMapping(value = "/studentInfo/{id}",method = RequestMethod.DELETE)
    public void deleteStudentInfo(@PathVariable("id") int id, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        studentinfoService.deleteStudentInfo(id, token);
    }
}
