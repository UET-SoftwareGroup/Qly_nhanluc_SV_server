package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.Partner;
import uet.usercontroller.model.Post;

import java.util.List;

/**
 * Created by Trung on 8/29/2016.
 */
@Repository
public interface PostRepository extends CrudRepository<Post,Integer> {
    Post findById(int id);

    List<Post> findByDescribePostContaining(String describePost);

    List<Post> findByContentContaining(String content);
}