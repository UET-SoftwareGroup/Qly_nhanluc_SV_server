package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.UserDTO;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.Student;
import uet.usercontroller.model.User;
import uet.usercontroller.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Tu on 02-May-16.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //Show all user
    @RequestMapping(value="/user",method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    //signup
    @RequestMapping(value="/signup",method = RequestMethod.POST)
    public User createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    //login
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public User Login(@RequestBody UserDTO userDTO) {
        return userService.Login(userDTO);
    }

    //logout
    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public void Logout(HttpServletRequest request){
        String token = request.getHeader("auth-token");
        userService.Logout(token);
    }

    //editUser
    @RequestMapping(value="user/{id}", method = RequestMethod.PUT)
    public User editUser(@PathVariable("id") int id, @RequestBody UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }

    //deleteUser
    @RequestMapping(value="user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
    }

}
