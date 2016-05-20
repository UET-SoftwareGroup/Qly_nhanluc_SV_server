package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.Student;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.PartnerRepository;
import uet.usercontroller.repository.StudentRepository;
import uet.usercontroller.repository.UserRepository;


import java.util.List;

/**
 * Created by Tu on 02-May-16.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        List<User> allUsers = (List<User>) userRepository.findAll();
        return allUsers;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User findUser(int id) {
        return userRepository.findOne(id);
    }

    @Autowired
    private PartnerRepository partnerRepository;

    public List<Partner> getPartners(){
        List<Partner> allPartners = (List<Partner>) partnerRepository.findAll();
        return allPartners;
    }

    @Autowired
    private StudentRepository studentRepository;
    public List<Student> getStudents(){
        List<Student> allStudents = (List<Student>) studentRepository.findAll();
        return allStudents;
    }

    public String checkType(int id){
        User user = userRepository.findOne(id);
        int check = user.getType();
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

}
