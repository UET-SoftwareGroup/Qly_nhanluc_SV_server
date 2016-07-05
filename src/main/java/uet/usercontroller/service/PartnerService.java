package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.model.Partner;
import uet.usercontroller.repository.PartnerRepository;

import java.util.List;

/**
 * Created by Tu on 28-Jun-16.
 */
@Service
public class PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    public List<Partner> getPartners(){
        List<Partner> allPartners = (List<Partner>) partnerRepository.findAll();
        return allPartners;
    }
}
