package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uet.usercontroller.model.Comment;
import uet.usercontroller.model.Role;
import uet.usercontroller.service.CommentService;
import uet.usercontroller.stereotype.RequiredRoles;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Tu on 16-Feb-17.
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    //show all comments
    @RequiredRoles({Role.STUDENT, Role.ADMIN})
    @RequestMapping(value="/showAllComment", method = RequestMethod.GET)
    public List<HashMap<String, String>> showAllComment(){
        return (List<HashMap<String, String>>) commentService.showAllComment();
    }

    //show all comments of a partner
    @RequiredRoles({Role.STUDENT, Role.ADMIN})
    @RequestMapping(value="/showAllCommentOfOnePartner/{partnerId}", method = RequestMethod.GET)
    public List<Comment> showAllCommentOfOnePartner(@PathVariable("partnerId") int partnerId) {
        return commentService.showAllCommentOfOnePartner(partnerId);
    }
}
