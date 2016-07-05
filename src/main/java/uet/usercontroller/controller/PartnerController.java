package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uet.usercontroller.model.Partner;
import uet.usercontroller.service.PartnerService;

import java.util.List;

/**
 * Created by Tu on 28-Jun-16.
 */
@RestController
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    //Hiển thị tất cả các thông tin về đối tác
    @RequestMapping(value="/partner", method = RequestMethod.GET)
    public List<Partner> getPartners(){
        return partnerService.getPartners();
    }
}
