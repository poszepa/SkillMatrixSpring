package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skillmatrix.skillmatrixspringboot.model.Person;
import pl.skillmatrix.skillmatrixspringboot.model.Skills;
import pl.skillmatrix.skillmatrixspringboot.repository.PersonRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.SkillsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Builder
public class PersonService{
    private final PersonRepository personRepository;
    private final SkillsRepository skillsRepository;

    @Modifying
    @Transactional
    public void savePerson(Person person) {
        personRepository.save(Person.builder()
                .active(true)
                .departmentsInWarehouse(person.getDepartmentsInWarehouse())
                .expertis(person.getExpertis())
                .functionInWarehouse(person.getFunctionInWarehouse())
                .groupsInWarehouse(person.getGroupsInWarehouse())
                .name(person.getName())
                .skillsList(skillsRepository.findAll())
                .surname(person.getSurname())
                .teamsInWarehouse(person.getTeamsInWarehouse())
                .build());
        personRepository.copySkillsToPerson(personRepository.findByExpertis(person.getExpertis()).getId());
    }

    @Transactional
    public List<Person> findPersonBySkillNameAndDepartmentName(String skillName, String departmentName){
       return personRepository.everyPersonToThoseSkillNameAndDepartmentName()
               .stream().filter(person -> person.getSkillsList().contains(skillsRepository.findSkillByDepartmentNameAndNameSkill(skillName, departmentName)))
               .collect(Collectors.toList());
    }

    @Transactional
    @Modifying
    public void changeGainedSkill(Integer personID, boolean gainedSkill, String skillName, String departmentName) {
        Person person = personRepository.findPersonById(personID);
        person.getSkillsList().stream().filter(skills -> skills.getNameSkill().equals(skillName) &&
                skills.getDepartmentsInWarehouse().getNameDepartment().equals(departmentName))
                .forEach(skills -> skills.setGainSkill(gainedSkill));
        personRepository.save(person);
    }

}
