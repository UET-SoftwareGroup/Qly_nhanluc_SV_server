package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.UserDTO;
import uet.usercontroller.model.*;
import uet.usercontroller.repository.InfoBySchoolRepository;
import uet.usercontroller.repository.StudentInfoRepository;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tu on 02-May-16.
 */
@Service
public class UserService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    StudentInfoRepository studentInfoRepository;
    @Autowired
    InfoBySchoolRepository infoBySchoolRepository;

    //Show all user
    public List<User> getUsers(){
        List<User> allUsers = (List<User>) userRepository.findAll();
        return allUsers;
    }

    //signup
    public User createUser(UserDTO userDTO) {
        User user1 = userRepository.findByUserName(userDTO.getUserName());
        if (user1 == null) {
            if ( userDTO.getUserName() != null && userDTO.getPassword() != null && userDTO.getRole() != null ) {
                User user = new User();
                user.setUserName(userDTO.getUserName());
                user.setPassword(userDTO.getPassword());
                user.setRole(userDTO.getRole());
                if (user.getRole() == Role.STUDENT) {
                    Student student = new Student();
                    student.setStudentName(user.getUserName());
                    user.setStudent(student);
                    StudentInfo studentInfo = new StudentInfo();
                    student.setStudentInfo(studentInfo);
                    studentInfoRepository.save(studentInfo);
                    InfoBySchool infoBySchool = new InfoBySchool();
                    student.setInfoBySchool(infoBySchool);
                    infoBySchoolRepository.save(infoBySchool);
                    studentRepository.save(student);
                }
                return userRepository.save(user);
            }
            else {
                throw new NullPointerException("Missing information.");
            }
        }
        else{
            throw new NullPointerException("User existed.");
        }
    }

    //login
    public User Login(UserDTO userDTO){
        User user = userRepository.findByUserName(userDTO.getUserName());
        if ( user.getRole()==Role.STUDENT ) {
            if (userDTO.getPassword().equals(user.getPassword())) {
                if (user.getToken() == null) {
                    user.setToken(UUID.randomUUID().toString());
                    user.setExpiryTime(new Date(System.currentTimeMillis() + 1000 * 60 * 15));
                } else {
                    user.setExpiryTime(new Date(System.currentTimeMillis() + 1000 * 60 * 15));
                }
            }
            user = userRepository.save(user);
            User result = new User();
            result.setId(user.getId());
            result.setUserName(user.getUserName());
            result.setRole(user.getRole());
            result.setToken(user.getToken());
            result.setStudent(user.getStudent());
            return result;
        }
        else if ( user.getRole()==Role.PARTNER1 ) {
            throw new NullPointerException("chua viet partner");
        }
        else {
            throw new NullPointerException("Login failed.");
        }
    }

    //admin login
    public User adminLogin (UserDTO userDTO){
        User user = userRepository.findByUserName(userDTO.getUserName());
        if ( user.getRole() == Role.ADMIN ) {
            if (userDTO.getPassword().equals(user.getPassword())) {
                if (user.getToken() == null) {
                    user.setToken(UUID.randomUUID().toString());
                    user.setExpiryTime(new Date(System.currentTimeMillis() + 1000 * 60 * 15));
                } else {
                    user.setExpiryTime(new Date(System.currentTimeMillis() + 1000 * 60 * 15));
                }
            }
            user = userRepository.save(user);
            User result = new User();
            result.setId(user.getId());
            result.setUserName(user.getUserName());
            result.setRole(user.getRole());
            result.setToken(user.getToken());
            result.setStudent(user.getStudent());
            return result;
        }
        else {
            throw new NullPointerException("Account is not an admin account.");
        }
    }

    //logout
    public User Logout(String token){
        User user = userRepository.findByToken(token);
        user.setToken(null);
        user.setExpiryTime(null);
        return userRepository.save(user);
    }

    //editUser
    public User editUser(int id, UserDTO userDTO, String token){
        User user = userRepository.findOne(id);
        if ( user.getToken() == token ) {
            if (user.getRole()==Role.ADMIN || user.getRole()==Role.STUDENT){
                if(userDTO.getPassword()!=null) {
                    user.setPassword(userDTO.getPassword());
                }
            }
        }
        return userRepository.save(user);
    }

    //deleteUser (o ham nay, user co id va user co token la 2 user khac nhau.)
    public void deleteUser(int id){
            userRepository.delete(id);
    }
}
