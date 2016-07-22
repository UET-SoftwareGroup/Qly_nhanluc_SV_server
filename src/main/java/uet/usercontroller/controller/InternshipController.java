package uet.usercontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.usercontroller.DTO.InternshipDTO;
import uet.usercontroller.model.Internship;
import uet.usercontroller.service.InternshipService;

import java.util.List;

/**
 * Created by fgv on 7/12/2016.
 */
@RestController
public class InternshipController {
    @Autowired
    private InternshipService internshipService;

    //show all Internships
    @RequestMapping(value = "/student/intern", method = RequestMethod.GET)
    public List<Internship> getAllInterns() {
        return internshipService.getAllIntern();
    }

    //Create a Internship
    @RequestMapping(value = "/student/{studentId}/intern", method = RequestMethod.POST)
    public Internship createIntern(@PathVariable("studentId") int studentId,@RequestBody InternshipDTO internshipDTO) {
        return internshipService.createIntern(studentId, internshipDTO);
    }

    //find By Id
    @RequestMapping(value = "/student/{studentId}/intern/{internId}", method = RequestMethod.GET)
    public Internship findInternById(@PathVariable("studentId") int studentId,@PathVariable("internId") int id) {
        return internshipService.findInternById(studentId,id);
    }

    //change 1 internship
    @RequestMapping(value = "/student/{studentId}/intern/{internId}", method = RequestMethod.PUT)
    public Internship changeInternById(@PathVariable("studentId")int studentId,@PathVariable("internId") int id, @RequestBody InternshipDTO internshipDTO) {
        return internshipService.changeById(studentId,id,internshipDTO);
    }
    @RequestMapping(value="/student/{studentId}/intern/{internId}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable("studentId")int studentId,@PathVariable("internId") int id){
        return internshipService.deleteById(studentId,id);
    }
}
