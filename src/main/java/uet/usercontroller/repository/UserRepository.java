package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.Role;
import uet.usercontroller.model.User;

import java.util.List;


/**
 * Created by Tu on 02-May-16.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByUserName(String userName);
    User findByToken(String token);
    User findByPartnerId(int id);
    User findByStudentId(int id);
    List<User> findByRole(Role role);
}
