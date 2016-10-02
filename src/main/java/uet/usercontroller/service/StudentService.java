package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.StudentDTO;
import uet.usercontroller.model.Role;
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
    public Student findStudent(int studentId, String token) {
        User user = userRepository.findByToken(token);
        if( user.getRole() == Role.STUDENT ){
            Student student1 = studentRepository.findById(studentId);
            if(user.getStudent().equals(student1)){
                return student1;
            }
            else{
                throw new NullPointerException("No result.");
            }
        }
        else {
            Student student2 = studentRepository.findById(studentId);
            if (student2 != null) {
                return student2;
            } else {
                throw new NullPointerException("No result.");
            }
        }
    }
    //Edit
    public Student editStudent(int studentId, StudentDTO studentDTO, String token){
        User user=userRepository.findByToken(token);
        if( user.getRole()== Role.STUDENT) {
            Student student1 = studentRepository.findById(studentId);
            if (user.getStudent().equals(student1)) {
                if (studentDTO.getStudentName() != null) {
                    student1.setStudentName(studentDTO.getStudentName());
                }
            }
            return studentRepository.save(student1);
        }
        else {
            Student student2 = studentRepository.findOne(studentId);
            if(student2!=null){
                if (studentDTO.getStudentName() != null) {
                    student2.setStudentName(studentDTO.getStudentName());
                }
                    return studentRepository.save(student2);
                }
            else{
                    throw new NullPointerException("Edit failed.");
                }
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
//        student.setInfoBySchool(null);
//        student.setInternship(null);
//        student.setStudentInfo(null);
        student.setStudentName(null);
        studentRepository.save(student);

    }
}
