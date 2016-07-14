package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.Internship;

/**
 * Created by fgv on 7/11/2016.
 */
@Repository
public interface InternshipRepository extends CrudRepository<Internship,Integer>{
    Internship findById(int id);
}
