package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.DTO.UserDTO;
import uet.usercontroller.model.User;

/**
 * Created by Tu on 02-May-16.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByUserName(String userName);
//chac do db roi, thu chay lai di, gio tao lai db khac a,k
    User findByToken(String token);

}
