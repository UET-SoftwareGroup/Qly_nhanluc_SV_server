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
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.STUDENT,Role.PARTNER1,Role.ADMIN})
    @RequestMapping(value="/student",method = RequestMethod.GET)
    public List<Student> getStudents() { return studentService.getStudents();}

//    //Create
//    @RequiredRoles({Role.STUDENT,Role.ADMIN})
//    @RequestMapping(value="/user/{userId}/student", method = RequestMethod.POST)
//    public Student createStudent(@PathVariable("userId") int userId, @RequestBody StudentDTO studentDTO){
//        return studentService.createStudent(userId, studentDTO);
//    }

    //Show student
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.STUDENT,Role.PARTNER1,Role.ADMIN})
    @RequestMapping(value="student/{studentId}",method = RequestMethod.GET)
    public Student findStudent(@PathVariable("studentId") int studentId, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return studentService.findStudent(studentId, token);
    }

//    //Edit
//    @RequiredRoles({Role.STUDENT,Role.ADMIN})
//    @RequestMapping(value="/student/{studentId}", method = RequestMethod.PUT)
//    public Student editStudent(@PathVariable("studentId") int studentId, @RequestBody StudentDTO studentDTO, HttpServletRequest request){
//        String token = request.getHeader("auth-token");
//        return studentService.editStudent(studentId, studentDTO, token);
//    }

    //Delete
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="/student/{studentId}", method = RequestMethod.DELETE)
    public void delStudent(@PathVariable("studentId") int studentId){
        studentService.delStudent(studentId);
    }
}
