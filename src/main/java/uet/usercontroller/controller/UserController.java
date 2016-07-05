package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.UserDTO;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.Student;
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

    //Show tất cả các tài khoản người dùng(bao gồm cả sv, admin, đối tác)
    @RequestMapping(value="/users",method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.getUsers();
    }

    //signup
    @RequestMapping(value="/signup",method = RequestMethod.POST)
    public User createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    //login
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public User checkLogin(@RequestBody UserDTO userDTO) { return userService.doLogin(userDTO); }

    //Tìm kiếm 1 user theo id
    @RequestMapping(value="/users/{id}",method = RequestMethod.GET)
    public User showUser(@PathVariable("id") int id) {
        return userService.findUser(id);
    }



    //Kiểm tra tài khoản user đó là admin, đối tác hay sinh viên
    @RequestMapping(value = "users/{id}/type",method = RequestMethod.GET)
    public String checkType(@PathVariable("id") int id){
        return userService.checkType(id);
    }

    @RequestMapping(value="/users/student/{user_id}",method = RequestMethod.POST)
//    public String show(){
//        return "abc";
//    }
    public User createStudent(@PathVariable("user_id") int user_id, @RequestBody Student student) {
        return userService.createStudent(user_id,student);
    }
    
}
