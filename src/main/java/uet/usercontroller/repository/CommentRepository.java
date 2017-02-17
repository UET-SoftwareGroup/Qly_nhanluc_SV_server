package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.Comment;

/**
 * Created by Tu on 16-Feb-17.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer>{
}
