package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.InfoBySchool;

/**
 * Created by Tu on 07-Jul-16.
 */
@Repository
public interface InfoBySchoolRepository extends CrudRepository<InfoBySchool, Integer>{

}
