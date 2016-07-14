package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.StudentDTO;
import uet.usercontroller.model.Student;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.repository.UserRepository;

import java.util.List;

/**
 * Created by Trung on 7/8/2016.
 */
@Service
public class StudentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        List<Student> allStudents = (List<Student>) studentRepository.findAll();
        return allStudents;
    }

    public Student findStudent(int studentId) {
        return studentRepository.findOne(studentId);
    }

    public Student editStudent(int studentId, StudentDTO studentDTO){
        Student student = studentRepository.findOne(studentId);
        if(studentDTO.getStudentName()!=null){
            student.setStudentName(studentDTO.getStudentName());
        }
        return studentRepository.save(student);

    }
    public void delStudent(int studentId) { studentRepository.delete(studentId); }

    public Student createStudent(int userId, StudentDTO studentDTO) {
        User user = userRepository.findOne(userId);
        Student student = new Student();
        student.setStudentName(studentDTO.getStudentName());
        user.setStudent(student);
        return studentRepository.save(student);
    }
}
