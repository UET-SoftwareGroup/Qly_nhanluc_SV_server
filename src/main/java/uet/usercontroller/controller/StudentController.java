package uet.usercontroller.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.PartnerInfoDTO;
import uet.usercontroller.DTO.PostDTO;
import uet.usercontroller.model.*;
import uet.usercontroller.service.StudentService;
import uet.usercontroller.stereotype.RequiredRoles;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Trung on 7/8/2016.
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    //Show all
    @RequiredRoles({Role.STUDENT,Role.VIP_PARTNER,Role.ADMIN})
    @RequestMapping(value="/student",method = RequestMethod.GET)
    public List<Student> getStudents() { return studentService.getStudents();}

//    //Create
//    @RequiredRoles({Role.STUDENT,Role.ADMIN})
//    @RequestMapping(value="/user/{userId}/student", method = RequestMethod.POST)
//    public Student createStudent(@PathVariable("userId") int userId, @RequestBody StudentDTO studentDTO){
//        return studentService.createStudent(userId, studentDTO);
//    }

    //Show student
    @RequiredRoles({Role.STUDENT,Role.VIP_PARTNER,Role.ADMIN})
    @RequestMapping(value="student/{studentId}",method = RequestMethod.GET)
    public Student findStudent(@PathVariable("studentId") int studentId, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return studentService.findStudent(studentId, token);
    }

    //Student search partner
    @RequiredRoles({Role.STUDENT, Role.ADMIN})
    @RequestMapping(value="searchPartner", method = RequestMethod.POST)
    public List<PartnerInfo> searchPartner(@RequestBody PartnerInfoDTO partnerInfoDTO){
        return studentService.searchPartner(partnerInfoDTO);
    }

    //Student search post description
    @RequiredRoles({Role.STUDENT, Role.ADMIN})
    @RequestMapping(value="searchDescription", method = RequestMethod.POST)
    public List<Post> searchDescription(@RequestBody PostDTO postDTO){
        return studentService.searchDescription(postDTO);
    }

    //Student search post by content
    @RequiredRoles({Role.STUDENT, Role.ADMIN})
    @RequestMapping(value="searchContent", method = RequestMethod.POST)
    public List<Post> searchContent(@RequestBody PostDTO postDTO){
        return studentService.searchContent(postDTO);
    }

}
