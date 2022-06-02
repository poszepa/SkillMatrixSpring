package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skillmatrix.skillmatrixspringboot.model.Person;
import pl.skillmatrix.skillmatrixspringboot.model.Skills;
import pl.skillmatrix.skillmatrixspringboot.repository.PersonRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.SkillsRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Builder
public class SkillsService {
    private final SkillsRepository skillsRepository;
    private final PersonRepository personRepository;

    @Modifying
    @Transactional
    public void modifySkills(Skills skills) {
        Skills skillFromDataBase = skillsRepository.findSkillsById(skills.getId());
        skillFromDataBase.setPersonList(personRepository.findAll());
        skillFromDataBase.setNameSkill(skills.getNameSkill());
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
