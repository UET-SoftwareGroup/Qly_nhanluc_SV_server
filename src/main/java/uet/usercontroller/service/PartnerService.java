package uet.usercontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.usercontroller.DTO.PartnerDTO;
import uet.usercontroller.DTO.StudentDTO;
import uet.usercontroller.DTO.StudentInfoDTO;
import uet.usercontroller.model.*;
import uet.usercontroller.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tu on 28-Jun-16.
 */
@Service
public class PartnerService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    PartnerInfoRepository partnerInfoRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentInfoRepository studentInfoRepository;

    //show list all partner
    public List<Partner> getPartners(){
        List<Partner> allPartners = (List<Partner>) partnerRepository.findAll();
        return allPartners;
    }

    //show a partner
    public Partner showPartner(int partnerId){
        Partner partner = partnerRepository.findOne(partnerId);
        return partner;
    }

    //partner search students
    public List<StudentInfo> searchStudent(StudentInfoDTO studentInfoDTO){
        List<StudentInfo> allStudentMatched = (List<StudentInfo>) studentInfoRepository.findByFullNameContaining(studentInfoDTO.getFullName());
        return allStudentMatched;
    }
}
