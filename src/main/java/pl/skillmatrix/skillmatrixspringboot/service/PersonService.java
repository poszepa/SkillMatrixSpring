package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skillmatrix.skillmatrixspringboot.model.Person;
import pl.skillmatrix.skillmatrixspringboot.repository.PersonRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.SkillsRepository;

import java.util.List;
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
        personRepository.copySkillsToPersonWhileCreateNewPerson(personRepository.findByExpertis(person.getExpertis()).getId());
    }

    @Transactional
    public List<Person> findPersonBySkillNameAndDepartmentName(String skillName, String departmentName){
       return personRepository.everyPersonToThoseSkillNameAndDepartmentName()
               .stream().filter(person -> person.getSkillsList().contains(skillsRepository.findSkillByDepartmentNameAndNameSkill(skillName, departmentName)))
               .collect(Collectors.toList());
    }



}
