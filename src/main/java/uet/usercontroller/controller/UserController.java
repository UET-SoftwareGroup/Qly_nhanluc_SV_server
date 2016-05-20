package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.User;
import uet.usercontroller.service.UserService;

import java.util.List;

/**
 * Created by Tu on 02-May-16.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/users",method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @RequestMapping(value="/users",method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(value="/users/{id}",method = RequestMethod.GET)
    public User showUser(@PathVariable("id") int id) {
        return userService.findUser(id);
    }

    @RequestMapping(value="/partner", method = RequestMethod.GET)
    public List<Partner> getPartners(){
        return userService.getPartners();
    }

    @RequestMapping(value = "users/{id}/admin",method = RequestMethod.GET)
    public String checkAdmin(@PathVariable("id") int id){
        return userService.checkAdmin(id);
    }
}
