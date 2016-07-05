package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.model.Student;
import uet.usercontroller.repository.StudentRepository;

import java.util.List;

/**
 * Created by Tu on 28-Jun-16.
 */
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public List<Student> getStudents(){
        List<Student> allStudents = (List<Student>) studentRepository.findAll();
        return allStudents;
    }

}
