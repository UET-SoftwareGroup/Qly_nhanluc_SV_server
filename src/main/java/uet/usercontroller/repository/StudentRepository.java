package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.Student;


/**
 * Created by Tu on 20-May-16.
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student findById(int id);

    Student findByJobSkillsId(int jobSkills);
}