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



    //roi day, ma sua lại tên các trường nhé. sao lai lien quan gi den ten truong. tuc la ko dc dung dau _ a`, u`, để tao sửa lại trên gg doc
//    User findbyUser_name(String user_name);
    //đoạn vừa nãy m chỉ cấn enter enter thôi, hiểu kok, bh dc roi
    // biet the da nhe, gio bi chiem phong roi buổi chiều ở đây có nhóm khác
}
