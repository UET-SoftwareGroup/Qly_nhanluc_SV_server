package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.PostDTO;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.Post;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.PartnerRepository;
import uet.usercontroller.repository.PostRepository;
import uet.usercontroller.repository.UserRepository;

import java.util.List;

/**
 * Created by Trung on 8/29/2016.
 */
@Service
public class PostService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    private PostRepository postRepository;

    //show all post
    public List<Post> getAllPosts(){
        List<Post> allPosts = (List<Post>) postRepository.findAll();
        return allPosts;
    }

    //show list post of a partner
    public List<Post> showAllPost(int partnerId) {
        Partner partner = partnerRepository.findById(partnerId);
        return partner.getPost();
    }

    //show a post
    public Post showPost(int postId){
        Post post = postRepository.findOne(postId);
        return post;
    }

    //create a post
    public Post createPost(int partnerId, PostDTO postDTO, String token){
        User user = userRepository.findByToken(token);
        Partner partner = partnerRepository.findOne(partnerId);
        if (user.getPartner().equals(partner)){
            Post post = new Post();
            post.setContent(postDTO.getContent());
            post.setDatePost(postDTO.getDatePost());
            post.setDescribePost(postDTO.getDescribePost());
            post.setPartnerId(partnerId);
            return postRepository.save(post);
        }
        else{
            throw new NullPointerException("User doesn't match with Partner.");
        }
    }

    //edit a post
    public Post editPost(int postId, PostDTO postDTO, String token){
        User user = userRepository.findByToken(token);
        Partner partner = user.getPartner();
        Post post = postRepository.findById(postId);
        Partner partner1 = partnerRepository.findByPostId(postId);
        if ( partner1.equals(partner)){
            if (postDTO.getContent()!=null){
                post.setContent(postDTO.getContent());
            }
            if (postDTO.getDatePost()!=null){
                post.setDatePost(postDTO.getDatePost());
            }
            if (postDTO.getDescribePost()!=null){
                post.setDescribePost(postDTO.getDescribePost());
            }
            return postRepository.save(post);
        }
        else {
            throw new NullPointerException("User doesn't match with Partner.");
        }
    }

    //delete a post
    public void deletePost(int postId, String token){
        User user = userRepository.findByToken(token);
        Post  post = postRepository.findOne(postId);
        Partner partner = partnerRepository.findByPostId(postId);
        if (user.getPartner().equals(partner)) {
            postRepository.delete(post);
        }
        else {
            throw new NullPointerException("User doesn't match with Partner.");
        }
    }

}
