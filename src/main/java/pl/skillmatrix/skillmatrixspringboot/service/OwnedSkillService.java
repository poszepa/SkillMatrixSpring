package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skillmatrix.skillmatrixspringboot.model.OwnedSkill;
import pl.skillmatrix.skillmatrixspringboot.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnedSkillService {
    private final OwnedSkillRepository ownedSkillRepository;
    private final DepartmentsInWarehouseRepository departmentRepository;
    private final GroupsInWarehouseRepository groupRepository;
    private final TeamsInWarehouseRepository teamRepository;
    private final SkillsRepository skillsRepository;

    @Transactional
    @Modifying
    public void changeSkilledBoolean(Long id) {
        OwnedSkill ownedSkillFromDB = ownedSkillRepository.findOwnedSkillById(id);
        boolean changeSkill = ownedSkillFromDB.isGainSkill();
        if(changeSkill == false) {
            changeSkill = true;
        }else{
            changeSkill = false;
        }
        ownedSkillRepository.save(OwnedSkill.builder()
                .id(ownedSkillFromDB.getId())
                .gainSkill(changeSkill)
                .skills(ownedSkillFromDB.getSkills())
                .person(ownedSkillFromDB.getPerson())
                .build());
    }

    @Transactional
    public List<OwnedSkill> showPersonWithSkill(String personDepartment, String personGroup, String personTeam, String skillName, String department) {


        if (!personDepartment.isEmpty() && !personGroup.isEmpty() && !personTeam.isEmpty()) {
            return ownedSkillRepository.findAllPersonBySkillIDAndDepartmentIDAndGroupIDAndTeamID(
                            skillsRepository.findSkillByDepartmentNameAndNameSkill(skillName, department).getId(),
                            departmentRepository.findByNameDepartment(personDepartment).getId(),
                            groupRepository.findByNameGroup(personGroup).getId(),
                            teamRepository.findByNameTeam(personTeam).getId());
        }

        if (!personDepartment.isEmpty() && !personGroup.isEmpty()) {
            return
                    ownedSkillRepository.findAllPersonBySkillIDAndDepartmentIDAndGroupID(
                            skillsRepository.findSkillByDepartmentNameAndNameSkill(skillName, department).getId(),
                            departmentRepository.findByNameDepartment(personDepartment).getId(),
                            groupRepository.findByNameGroup(personGroup).getId());
        }
        if (!personDepartment.isEmpty()) {
            return ownedSkillRepository.findAllPersonBySkillIDAndDepartmentName(
                            skillsRepository.findSkillByDepartmentNameAndNameSkill(skillName, department).getId(),
                            departmentRepository.findByNameDepartment(personDepartment).getId());
        }

        return new ArrayList<>();
    }

    public List<OwnedSkill> showEveryPersonWithSkill(String skillName, String department) {
        return ownedSkillRepository.findAllPersonBySkillID(skillsRepository.findSkillByDepartmentNameAndNameSkill(skillName, department).getId());

    }
}
