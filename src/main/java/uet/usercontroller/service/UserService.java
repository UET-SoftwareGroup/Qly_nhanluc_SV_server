package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tu on 02-May-16.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    //Show all user
    public List<User> getUsers(){
        List<User> allUsers = (List<User>) userRepository.findAll();
        return allUsers;
    }

    //signup
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        if (userRepository.findByUserName(user.getUserName()) == null) {
            if (userDTO.getRole() == 1 || userDTO.getRole() == 2) {
                user.setUserName(userDTO.getUserName());
                user.setPassword(userDTO.getPassword());
                user.setRole(userDTO.getRole());
            }
        }
        return userRepository.save(user);
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
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);

    }

    //deleteUser
    public void deleteUser(int id){
        userRepository.delete(id);
    }
}
