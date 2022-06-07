package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import pl.skillmatrix.skillmatrixspringboot.model.Person;
import pl.skillmatrix.skillmatrixspringboot.model.Skills;
import pl.skillmatrix.skillmatrixspringboot.repository.*;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Builder
public class SkillsService {
    private final SkillsRepository skillsRepository;
    private final PersonRepository personRepository;
    private final OwnedSkillRepository ownedSkillRepository;
    private final DepartmentsInWarehouseRepository departmentRepository;
    private final GroupsInWarehouseRepository groupRepository;
    private final TeamsInWarehouseRepository teamRepository;

    @Modifying
    @Transactional
    public void modifySkills(Skills skills) {
        Skills skillFromDataBase = skillsRepository.findSkillsById(skills.getId());
        skillFromDataBase.setPersonList(personRepository.findAll());
        skillFromDataBase.setNameSkill(skills.getNameSkill());
        skillFromDataBase.setIsRequired(skills.getIsRequired());
        skillsRepository.save(skillFromDataBase);
    }

    @Modifying
    @Transactional
    public void saveSkill(Skills skill) {
        skillsRepository.save(Skills.builder()
                        .nameSkill(skill.getNameSkill())
                        .isRequired(skill.getIsRequired())
                        .departmentsInWarehouse(skill.getDepartmentsInWarehouse())
                        .personList(personRepository.findAll())
                        .build());
        skillsRepository.copySkillsToPersonWhileCreateNewSkills(
                skillsRepository.findSkillByDepartmentIDNameAndNameSkill(
                        skill.getNameSkill(),
                        skill.getDepartmentsInWarehouse().getId()
                ).getId());
    }






}
