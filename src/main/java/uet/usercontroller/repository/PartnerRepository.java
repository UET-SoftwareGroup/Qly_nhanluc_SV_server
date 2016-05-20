package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.Partner;

/**
 * Created by Tu on 03-May-16.
 */
@Repository
public interface PartnerRepository extends CrudRepository<Partner, String>{

}
