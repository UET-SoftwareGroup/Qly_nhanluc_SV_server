package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.Post;

/**
 * Created by Trung on 8/29/2016.
 */
@Repository
public interface PostRepository extends CrudRepository<Post,Integer> {

}