package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.OwnedSkill;
import pl.skillmatrix.skillmatrixspringboot.model.Person;
import pl.skillmatrix.skillmatrixspringboot.model.PersonCount;
import pl.skillmatrix.skillmatrixspringboot.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Builder
public class PersonService{
    private final PersonRepository personRepository;
    private final SkillsRepository skillsRepository;
    private final DepartmentsInWarehouseRepository departmentRepository;
    private final GroupsInWarehouseRepository groupRepository;
    private final TeamsInWarehouseRepository teamRepository;

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
    public List<Person> showPerson(String personDepartment, String personGroup, String personTeam) {


        if (!personDepartment.isEmpty() && !personGroup.isEmpty() && !personTeam.isEmpty()) {
            return personRepository.findAllPersonWithDepartmentAndGroupsAndTeams(
                    departmentRepository.findByNameDepartment(personDepartment).getId(),
                    groupRepository.findByNameGroup(personGroup).getId(),
                    teamRepository.findByNameTeam(personTeam).getId());
        }

        if (!personDepartment.isEmpty() && !personGroup.isEmpty()) {
            return personRepository.findAllPersonWithDepartmentAndGroups(
                    departmentRepository.findByNameDepartment(personDepartment).getId(),
                    groupRepository.findByNameGroup(personGroup).getId());
        }

        if (!personDepartment.isEmpty()) {
            return personRepository.findAllPersonWithDepartment(
                    departmentRepository.findByNameDepartment(personDepartment).getId());
        }

        return new ArrayList<>();
    }

    public List<PersonCount> countPeopleByDepartmentID() {
        List<PersonCount> count = new ArrayList<>();
        List<DepartmentsInWarehouse> departments = departmentRepository.findDepartmentWithoutGeneral();

        for(int i = 0 ; i < departments.size(); i++) {
            int countPeople = 0;

            if(personRepository.countPersonByDepartmentID(departments.get(i).getId()) != null) {
                countPeople = personRepository.countPersonByDepartmentID(departments.get(i).getId());
            }

            count.add(new PersonCount(countPeople, departments.get(i).getNameDepartment()));
        }

        return count;
    }

}
