package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.UserDTO;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.Student;
import uet.usercontroller.model.User;
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
        if ( userDTO.getPassword().equals(user.getPassword())){
            if ( user.getToken()==null ) {
                user.setToken(UUID.randomUUID().toString());
                user.setExpiryTime(new Date(System.currentTimeMillis()+1000*60*15));
            }
            else{
                user.setExpiryTime(new Date(System.currentTimeMillis()+1000*60*15));
            }
        }
        user = userRepository.save(user);
        User result = new User();
        result.setId(user.getId());
        result.setUserName(user.getUserName());
        result.setRole(user.getRole());
        result.setToken(user.getToken());
        return result;
    }

    //logout
    public void Logout(String token){
        User user = userRepository.findByToken(token);
        user.setToken(null);
        userRepository.save(user);
    }

    //editUser
    public User editUser(int id, UserDTO userDTO){
        User user = userRepository.findOne(id);
        if(userDTO.getUserName()!=null){
            user.setUserName(userDTO.getUserName());
        }
        if(userDTO.getPassword()!=null) {
            user.setPassword(userDTO.getPassword());
        }
        return userRepository.save(user);

    }

    //deleteUser
    public void deleteUser(int id){
        userRepository.delete(id);
    }
}
//dau nhi, hinh nhu phải tim 1 user co id bang id kia và delete(user) ma
//đây là xóa 1 user luôn, ý t đang bảo t xóa user thì mất trong db còn hàm bên kia thì ko