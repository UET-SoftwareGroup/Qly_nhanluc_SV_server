package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.UserDTO;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.Student;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.PartnerRepository;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.repository.UserRepository;


import javax.jws.soap.SOAPBinding;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * Created by Tu on 02-May-16.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    //Show tất cả các tài khoản người dùng(bao gồm cả sv, admin, đối tác)
    public List<User> getUsers(){
        List<User> allUsers = (List<User>) userRepository.findAll();
        return allUsers;
    }

    //signup
    public User createUser(UserDTO userDTO){
        User user = new User();

        if ( userRepository.findByUserName(userDTO.getUserName()).equals("") ){
            user.setUserName(userDTO.getUserName());
            user.setPassword(userDTO.getPassword());
            user.setRole(userDTO.getRole());
        }
        return userRepository.save(user);
    }

    //login
    public User doLogin(UserDTO userDTO){
        User user = userRepository.findByUserName(userDTO.getUserName());
        return userRepository.save(user);
    }


    //Tìm kiếm 1 user theo id
    public User findUser(int id) {
        return userRepository.findOne(id);
    }

    public String checkType(int id){
        User user = userRepository.findOne(id);
        int check = user.getRole();
        if(check==3){
            return("This user is an admin.");
        }
        if(check==2){
            return("This user is a partner.");
        }
        else{
            return("This user is a student.");
        }
    }

    @Autowired
    private StudentRepository studentRepository;
    public User createStudent(int user_id,Student student) {
        User user = new User();
        user = userRepository.findOne(user_id);
        user.setStudent(student);
        studentRepository.save(student);
        return user;
    }
}
