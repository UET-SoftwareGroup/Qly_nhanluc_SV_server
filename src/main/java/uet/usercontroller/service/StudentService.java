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
    //Show all
    public List<Student> getStudents() {
        List<Student> allStudents = (List<Student>) studentRepository.findAll();
        return allStudents;
    }
    //Show
    public Student findStudent(int studentId) {
        Student student = studentRepository.findOne(studentId);
        if (true){
            return student;
        }
        else{
            throw new NullPointerException("No result.");
        }
    }
    //Edit
    public Student editStudent(String token,int studentId, StudentDTO studentDTO){
        Student student = studentRepository.findOne(studentId);
        if (true) {
            if (studentDTO.getStudentName() != null) {
                student.setStudentName(studentDTO.getStudentName());
            }
            return studentRepository.save(student);
        }
        else{
            throw new NullPointerException("Edit failed.");
        }
    }

//    //Create
//    public Student createStudent(int userId, StudentDTO studentDTO) {
//        User user = userRepository.findOne(userId);
//        Student student = new Student();
//        student.setStudentName(studentDTO.getStudentName());
//        user.setStudent(student);
//        return studentRepository.save(student);
//    }

    //Delete
    public void delStudent(int studentId) {
        Student student = studentRepository.findOne(studentId);
        student.setInfoBySchool(null);
        student.setInternship(null);
        student.setStudentInfo(null);
        student.setStudentName(null);
        studentRepository.save(student);

    }
}
