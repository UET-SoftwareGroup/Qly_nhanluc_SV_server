package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.StudentDTO;
import uet.usercontroller.model.Student;
import uet.usercontroller.service.StudentService;

import java.util.List;

/**
 * Created by Trung on 7/8/2016.
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    //Show
    @RequestMapping(value="/student",method = RequestMethod.GET)
    public List<Student> getStudents() { return studentService.getStudents();}

    //Search
    @RequestMapping(value="/student/{studentId}",method = RequestMethod.GET)
    public Student findStudent(@PathVariable("studentId") int studentId) {
        return studentService.findStudent(studentId);
    }

    //Edit
    @RequestMapping(value="/student/{studentId}", method = RequestMethod.PUT)
    public Student editStudent(@PathVariable("studentId") int studentId, @RequestBody StudentDTO studentDTO){
        return studentService.editStudent(studentId, studentDTO);
    }

    //Delete
    @RequestMapping(value="/student/{studentId}", method = RequestMethod.DELETE)
    public void delStudent(@PathVariable("studentId") int studentId){
        studentService.delStudent(studentId);
    }
}
