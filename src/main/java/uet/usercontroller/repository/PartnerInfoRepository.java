package uet.usercontroller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.PartnerInfo;

/**
 * Created by Tu on 27-Aug-16.
 */
@Repository
public interface PartnerInfoRepository extends CrudRepository<PartnerInfo,Integer> {

}
