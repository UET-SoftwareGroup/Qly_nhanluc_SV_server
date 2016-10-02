package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.PartnerContact;

/**
 * Created by fgv on 9/2/2016.
 */
@Repository
public interface PartnerContactRepository extends CrudRepository<PartnerContact,Integer> {
    PartnerContact findById(int id);
}
