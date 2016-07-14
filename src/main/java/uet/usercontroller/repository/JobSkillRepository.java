package uet.usercontroller.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.usercontroller.model.JobSkill;

@Repository
public interface JobSkillRepository extends CrudRepository<JobSkill,Integer> {
    JobSkill findById(int Id);
}
