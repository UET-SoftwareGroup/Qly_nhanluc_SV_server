package uet.usercontroller.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.StudentDTO;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.Student;
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
    @RequiredRoles({Role.STUDENT,Role.PARTNER1,Role.ADMIN})
    @RequestMapping(value="user/student",method = RequestMethod.GET)
    public List<Student> getStudents() { return studentService.getStudents();}

    //Create
    @RequiredRoles({Role.STUDENT,Role.ADMIN})
    @RequestMapping(value="/user/{userId}/student", method = RequestMethod.POST)
    public Student createStudent(@PathVariable("userId") int userId, @RequestBody StudentDTO studentDTO){
        return studentService.createStudent(userId, studentDTO);
    }

    //Search
    @RequiredRoles({Role.STUDENT,Role.PARTNER1,Role.ADMIN})
    @RequestMapping(value="/user/{userId}/student/{studentId}",method = RequestMethod.GET)
    public Student findStudent(@PathVariable("userId") int userId,@PathVariable("studentId") int studentId) {
        return studentService.findStudent(userId, studentId);
    }

    //Edit
    @RequiredRoles({Role.STUDENT,Role.ADMIN})
    @RequestMapping(value="/user/{userId}/student/{studentId}", method = RequestMethod.PUT)
    public Student editStudent(@PathVariable("userId") int userId ,@PathVariable("studentId") int studentId, @RequestBody StudentDTO studentDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return studentService.editStudent(token,userId, studentId, studentDTO);
    }

    //Delete
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="/user/{userId}/student/{studentId}", method = RequestMethod.DELETE)
    public void delStudent(@PathVariable("userId") int userId, @PathVariable("studentId") int studentId){
        studentService.delStudent(userId, studentId);
    }
}
