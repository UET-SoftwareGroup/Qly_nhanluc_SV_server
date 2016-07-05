package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uet.usercontroller.model.Student;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.service.StudentService;

import java.util.List;

/**
 * Created by Tu on 28-Jun-16.
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    //Hiển thị tất cả các thông tin về sinh viên
    @RequestMapping(value="/student", method = RequestMethod.GET)
    public List<Student> geStudents(){ return studentService.getStudents(); }
}
