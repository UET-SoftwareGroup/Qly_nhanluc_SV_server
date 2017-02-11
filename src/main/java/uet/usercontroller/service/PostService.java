package uet.usercontroller.service;

//import com.sun.jmx.snmp.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.PostDTO;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.Post;
import uet.usercontroller.model.User;
import uet.usercontroller.repository.PartnerRepository;
import uet.usercontroller.repository.PostRepository;
import uet.usercontroller.repository.UserRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public Post createPost(int partnerId, PostDTO postDTO, String token) throws IOException {
        User user = userRepository.findByToken(token);
        Partner partner = partnerRepository.findOne(partnerId);
        if (user.getPartner().equals(partner)){
            Post post = new Post();
            post.setContent(postDTO.getContent());
            post.setDatePost(postDTO.getDatePost());
            post.setDescribePost(postDTO.getDescribePost());
            post.setPartnerId(partnerId);
            postRepository.save(post);
            String username = user.getUserName();
//            String postId = f()
            String pathname = "../Qly_SV_client/app/users_data/partner/" + username + "/post/";
            File directory = new File(pathname);
            if (! directory.exists()) {
                directory.mkdir();
            }
            pathname = pathname + String.valueOf(post.getId()) + "/";
            directory = new File(pathname);
            if (! directory.exists()) {
                directory.mkdir();
            }
            String directoryName = "users_data/partner/" + username + "/post/" + String.valueOf(post.getId()) + "/";
            String fileName = username + "_" + String.valueOf(post.getId()) + ".jpg";
            byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(postDTO.getImage());
            File of = new File( pathname + fileName);
            FileOutputStream osf = new FileOutputStream(of);
            osf.write(btDataFile);
            osf.flush();
            String result = "http://localhost:8000/" + directoryName + fileName;
            post.setImage(result);
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

    public Post uploadImage(int partnerId, PostDTO postDTO, String token) throws IOException {
        User user = userRepository.findByToken(token);
        Partner partner = partnerRepository.findOne(partnerId);
        if (user.getPartner().equals(partner)){
            Post post = new Post();
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String username = user.getUserName();
            String pathname = "../Qly_SV_client/app/users_data/tmp/";
            String directoryName = "users_data/tmp/";
            String fileName = username + "_" + ".jpg";
            byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(postDTO.getImage());
            File of = new File( pathname + fileName);
            FileOutputStream osf = new FileOutputStream(of);
            osf.write(btDataFile);
            osf.flush();
            String result = "http://localhost:8000/" + directoryName + fileName;
            post.setImage(result);
            return post;
        }
//        String pathname = "../Qly_SV_client/app/users_data/partner/" + username;
//        File directory = new File(pathname);
//        if (! directory.exists()) {
//            directory.mkdir();
//        }
////        directory.delete();
//        pathname = "../Qly_SV_client/app/users_data/partner/" + username + "/logo/";
//        String directoryName = "users_data/partner/" + username + "/logo/";
//        String fileName = username + "_logo.jpg";
//        directory = new File(pathname);
//        if (! directory.exists()) {
//            directory.mkdir();
//        }
//        byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(partnerInfoDTO.getLogo());
//        File of = new File( pathname + fileName);
//        FileOutputStream osf = new FileOutputStream(of);
//        osf.write(btDataFile);
//        osf.flush();
//        partnerInfo.setLogo("http://localhost:8000/" + directoryName + username + "_logo.jpg");
        else{
            throw new NullPointerException("User doesn't match with Partner.");
        }
    }
}
