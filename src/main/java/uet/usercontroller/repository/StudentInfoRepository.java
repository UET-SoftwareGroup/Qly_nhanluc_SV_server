package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import uet.usercontroller.model.StudentInfo;

/**
 * Created by root on 7/20/16.
 */
public interface StudentInfoRepository extends CrudRepository<StudentInfo, Integer> {
}
