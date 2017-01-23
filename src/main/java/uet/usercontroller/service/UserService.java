package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.CreateStudentDTO;
import uet.usercontroller.DTO.UserDTO;
import uet.usercontroller.model.*;
import uet.usercontroller.repository.*;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by Tu on 02-May-16.
 */
@SuppressWarnings("Since15")
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
//    @Autowired
//    InternshipRepository internshipRepository;
    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    PartnerInfoRepository partnerInfoRepository;

    //Show all user
    public List<User> getUsers(){
        List<User> allUsers = (List<User>) userRepository.findAll();
        return allUsers;
    }

    //signup
    public User createUser(UserDTO userDTO) {
        User user1 = userRepository.findByUserName(userDTO.getUserName());
        if (user1 == null) {
            if (userDTO.getUserName() != null && userDTO.getPassword() != null) {
                User user = new User();
                user.setUserName(userDTO.getUserName());
                user.setPassword(userDTO.getPassword());
                user.setStatus("A");
                user.setRole(Role.STUDENT);
                Student student = new Student();
                user.setStudent(student);
                //create StudentInfo
                StudentInfo studentInfo = new StudentInfo();
                student.setStudentInfo(studentInfo);
                studentInfoRepository.save(studentInfo);
                //create InfoByschool
                InfoBySchool infoBySchool = new InfoBySchool();
                student.setInfoBySchool(infoBySchool);
                infoBySchoolRepository.save(infoBySchool);
                studentRepository.save(student);
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

    //createAccount
    public User createAccount(UserDTO userDTO){
        User user1 = userRepository.findByUserName(userDTO.getUserName());
        if (user1 == null) {
            if ( userDTO.getUserName() != null && userDTO.getPassword() != null && userDTO.getRole() != null ) {
                User user = new User();
                user.setUserName(userDTO.getUserName());
                user.setPassword(userDTO.getPassword());
                user.setRole(userDTO.getRole());
                user.setStatus("A");
                if (user.getRole() == Role.VIP_PARTNER) {
                    Partner partner = new Partner();
                    user.setPartner(partner);

                    //create PartnerInfo
                    PartnerInfo partnerInfo = new PartnerInfo();
                    partnerInfo.setPartnerName(user.getUserName());
                    partner.setPartnerInfo(partnerInfo);
                    partnerInfoRepository.save(partnerInfo);

//                //create partnerContact
//                PartnerContact partnerContact = new PartnerContact();
//                partner.setPartnerContacts(partnerContact);
//                partnerContactRepository.save(partnerContact);

                    partnerRepository.save(partner);
                }
                if (user.getRole() == Role.ADMIN){
                    userRepository.save(user);
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
        if (user.getStatus().equals("A")) {
            if (userDTO.getPassword().equals(user.getPassword())) {
                if (user.getToken() == null) {
                    user.setToken(UUID.randomUUID().toString());
                    user.setExpiryTime(new Date(System.currentTimeMillis() + 1000 * 60 * 15));
                } else {
                    user.setExpiryTime(new Date(System.currentTimeMillis() + 1000 * 60 * 15));
                }
                return userRepository.save(user);
            } else {
                throw new NullPointerException("Wrong password.");
            }
        } else if (userDTO.getStatus().equals("D")) {
            throw new NullPointerException("Account was deactivated.");
        } else {
            throw new NullPointerException("Unknown Error.");
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
            return userRepository.save(user);
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

    //activate/deactivate
    public User changeUserStatus(int id){
        User user = userRepository.findOne(id);
            if(user.getStatus().equals("A")){
                user.setStatus("D");
            } else if(user.getStatus().equals("D")){
                user.setStatus("A");
            }
        return userRepository.save(user);
    }

    //deleteUser (o ham nay, user co id va user co token la 2 user khac nhau.)
    public void deleteUser(int id){
            userRepository.delete(id);
    }

    //create multi students
    public void createStudent(List<CreateStudentDTO> List) {
//        for(CreateStudentDTO List : createStudent) {
        List.forEach(createStudentDTO -> {
            User user = new User();
            user.setUserName(createStudentDTO.getUserName());
            user.setPassword(String.valueOf(createStudentDTO.getStudentCode()));
            user.setStatus("A");
            user.setRole(Role.STUDENT);
            Student student = new Student();
            user.setStudent(student);
            //create StudentInfo
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setFullName(createStudentDTO.getStudentName());
            student.setStudentInfo(studentInfo);
            studentInfoRepository.save(studentInfo);
            //create InfoByschool
            InfoBySchool infoBySchool = new InfoBySchool();
            infoBySchool.setDiploma(createStudentDTO.getDiploma());
            infoBySchool.setStudentName((createStudentDTO.getStudentName()));
            infoBySchool.setGPA(createStudentDTO.getGPA());
            infoBySchool.setGraduationYear(createStudentDTO.getGraduationYear());
            infoBySchool.setGrade(createStudentDTO.getGrade());
            infoBySchool.setMajor(createStudentDTO.getMajor());
            infoBySchool.setStudentClass(createStudentDTO.getStudentClass());
            infoBySchool.setStudentCode(createStudentDTO.getStudentCode());
            student.setInfoBySchool(infoBySchool);
            infoBySchoolRepository.save(infoBySchool);
            studentRepository.save(student);
            userRepository.save(user);
        });

//        }
    }
}
