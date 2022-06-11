package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skillmatrix.skillmatrixspringboot.model.Person;
import pl.skillmatrix.skillmatrixspringboot.model.PersonGetRequiredSkills;
import pl.skillmatrix.skillmatrixspringboot.model.Skills;
import pl.skillmatrix.skillmatrixspringboot.repository.*;

import java.util.ArrayList;
import java.util.List;

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
    private final OwnedSkillService ownedSkillService;

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

    public PersonGetRequiredSkills getPersonRequiredSkill(Integer personID) {
        PersonGetRequiredSkills getListPersonWithSkills = new PersonGetRequiredSkills();
        getListPersonWithSkills.setPersonID(personID);
        getListPersonWithSkills.setValuePercentGainedSkill(ownedSkillService.getPercentValueGainedSkillToEveryRequiredSkill(personID));
        return getListPersonWithSkills;
    }

    public List<PersonGetRequiredSkills> getListPersonRequiredSkill() {
        List<Person> listPersonActive = personRepository.findPersonsByActiveTrue();

        List<PersonGetRequiredSkills> getRequiredSkillsList = new ArrayList<>();

        listPersonActive.forEach(person ->
                getRequiredSkillsList.add(getPersonRequiredSkill(person.getId())));

        return getRequiredSkillsList;

    }






}
