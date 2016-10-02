package uet.usercontroller.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.UserDTO;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.User;
import uet.usercontroller.service.UserService;
import uet.usercontroller.stereotype.NoAuthentication;
import uet.usercontroller.stereotype.RequiredRoles;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Tu on 02-May-16.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //Show all user, ham nay chi de chạy thu khi test code, khong co trong he thong
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @NoAuthentication
    @RequestMapping(value="/user",method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    //signup
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @NoAuthentication
    @RequestMapping(value="/signup",method = RequestMethod.POST)
    public User createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    //create partner
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="/createAccount",method = RequestMethod.POST)
    public User createAccount(@RequestBody UserDTO userDTO) {
        return userService.createAccount(userDTO);
    }

    //login
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @NoAuthentication
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public User Login(@RequestBody UserDTO userDTO) {
        return userService.Login(userDTO);
    }

    //admin login
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @NoAuthentication
    @RequestMapping(value="admin/login", method = RequestMethod.POST)
    public User adminLogin(@RequestBody UserDTO userDTO){
        return userService.adminLogin(userDTO);
    }

    //logout
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @NoAuthentication
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public User Logout(HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return userService.Logout(token);
    }

    //editUser
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.ADMIN,Role.STUDENT})
    @RequestMapping(value="user/{id}", method = RequestMethod.PUT)
    public User editUser(@PathVariable("id") int id, @RequestBody UserDTO userDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return userService.editUser(id, userDTO, token);
    }

    //deleteUser
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles(Role.ADMIN)
    @RequestMapping(value="user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
    }

}
