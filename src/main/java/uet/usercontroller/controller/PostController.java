package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.PostDTO;
import uet.usercontroller.model.Post;
import uet.usercontroller.model.Role;
import uet.usercontroller.service.PostService;
import uet.usercontroller.stereotype.RequiredRoles;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Trung on 8/29/2016.
 */
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    //Show all post
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.ADMIN,Role.PARTNER1,Role.STUDENT})
    @RequestMapping(value="/post",method= RequestMethod.GET)
    public List<Post> getAllPosts() { return postService.getAllPosts();}

    //Show list post of a partner
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.ADMIN,Role.PARTNER1,Role.STUDENT})
    @RequestMapping(value="/partner/{partnerId}/post",method = RequestMethod.GET)
    public List<Post> showAllPost(@PathVariable("partnerId") int partnerId){
        return postService.showAllPost(partnerId);
    }

    //Show a post
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.ADMIN,Role.PARTNER1,Role.STUDENT})
    @RequestMapping(value="/post/{postId}",method = RequestMethod.GET)
    public Post showPost(@PathVariable("postId") int postId){
        return postService.showPost(postId);
    }

    //Create post
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles({Role.PARTNER1})
    @RequestMapping(value="/partner/{partnerId}/post",method = RequestMethod.POST)
    public Post createPost(@PathVariable("partnerId") int partnerId, @RequestBody PostDTO postDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return postService.createPost(partnerId, postDTO, token);
    }

    //Edit post
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles(Role.PARTNER1)
    @RequestMapping(value="/post/{postId}",method = RequestMethod.PUT)
    public Post editPost(@PathVariable("postId") int postId, @RequestBody PostDTO postDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return postService.editPost(postId, postDTO, token);
    }

    //Delete post
    @CrossOrigin(origins = "http://112.137.130.47:8000")
    @RequiredRoles(Role.PARTNER1)
    @RequestMapping(value="/post/{postId}",method = RequestMethod.DELETE)
    public void deletePost(@PathVariable("postId") int postId, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        postService.deletePost(postId, token);
    }
}
