package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.PartnerRepository;
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

    public String checkAdmin(int id){
        User user = userRepository.findOne(id);
        int check = user.getAdmin();
        if(check==1){
            return("This user is an admin.");
        }
        else{
            return("This user is not an admin.");
        }
    }

}
