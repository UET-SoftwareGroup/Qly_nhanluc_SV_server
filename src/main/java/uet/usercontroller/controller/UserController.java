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
    //Show tất cả các tài khoản người dùng(bao gồm cả sv, admin, đối tác)
    @RequestMapping(value="/users",method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.getUsers();
    }
    //Tạo 1 user mới
    @RequestMapping(value="/users",method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    //Tìm kiếm 1 user theo id
    @RequestMapping(value="/users/{id}",method = RequestMethod.GET)
    public User showUser(@PathVariable("id") int id) {
        return userService.findUser(id);
    }
    //Hiển thị tất cả các thông tin về đối tác
    @RequestMapping(value="/partners", method = RequestMethod.GET)
    public List<Partner> getPartners(){
        return userService.getPartners();
    }
    //Kiểm tra tài khoản user đó là admin, đối tác hay sinh viên
    @RequestMapping(value = "users/{id}/type",method = RequestMethod.GET)
    public String checkType(@PathVariable("id") int id){
        return userService.checkType(id);
    }
    
}
