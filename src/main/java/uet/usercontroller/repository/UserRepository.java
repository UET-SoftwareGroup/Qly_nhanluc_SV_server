package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.User;


/**
 * Created by Tu on 02-May-16.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByUserName(String userName);
    User findByToken(String token);

}
