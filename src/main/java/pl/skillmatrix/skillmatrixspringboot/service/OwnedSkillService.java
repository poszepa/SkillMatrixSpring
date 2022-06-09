package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.OwnedSkill;
import pl.skillmatrix.skillmatrixspringboot.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnedSkillService {
    private final OwnedSkillRepository ownedSkillRepository;
    private final DepartmentsInWarehouseRepository departmentRepository;
    private final GroupsInWarehouseRepository groupRepository;
    private final TeamsInWarehouseRepository teamRepository;
    private final SkillsRepository skillsRepository;
    private final PersonRepository personRepository;

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

    //###############################################################
//    Below here is method to gain every skills where skill is required
    //###############################################################
    public List<Integer> countEveryGainedSkillFromAllDepartmentsAndSkillIsRequired(Integer personID) {
        List<Integer> countsGainedSkill = new ArrayList<>();
        for (int i = 0; i < departmentRepository.listOfNameEveryDepartment().size(); i++) {
            countsGainedSkill.add(skillsRepository.countEverySkillWhereHeIsRequiredAndChoosedGainSkill(
                    personID,
                    departmentRepository.listOfNameEveryDepartment().get(i),
                    true));
        }
        return countsGainedSkill;
    }

    public List<Integer> countEverySkillFromAllDepartmentsAndSkillIsRequired(Integer personID) {
        List<Integer> countsEveryRequiredSkill = new ArrayList<>();
        for (int i = 0; i < departmentRepository.listOfNameEveryDepartment().size(); i++) {
            countsEveryRequiredSkill.add(skillsRepository.countEverySkillWhereHeIsRequired(
                    personID,
                    departmentRepository.listOfNameEveryDepartment().get(i)));
        }
        return countsEveryRequiredSkill;
    }

    public List<Double> getPercentValueGainedSkillToEveryRequiredSkill(Integer personID) {
        List<Double> percentValues = new ArrayList<>();
        List<Integer> gainedSkill = countEveryGainedSkillFromAllDepartmentsAndSkillIsRequired(personID);
        List<Integer> everyRequiredSkill = countEverySkillFromAllDepartmentsAndSkillIsRequired(personID);

        for (int i = 0; i < everyRequiredSkill.size(); i++) { // here isn't important choose size from gainedSkill or everyRequiredSkill bcs those have the same size equal count department
            Double value = 0.0;

            if(everyRequiredSkill.get(i) != 0) {
                value =Math.round( (gainedSkill.get(i) * 1.0 / everyRequiredSkill.get(i) * 1.0)* 100.00) / 100.00;
            }
            percentValues.add(value);
        }
        return percentValues;
    }
    //###############################################################################################


    //###############################################################
//    Below here is method to gain every skill without required skill
    //###############################################################
    public List<Integer> countEveryGainedSkillFromAllDepartments(Integer personID) {
        List<Integer> countsGainedSkill = new ArrayList<>();
        for (int i = 0; i < departmentRepository.listOfNameEveryDepartment().size(); i++) {
            countsGainedSkill.add(skillsRepository.countEverySkillWithChoosedDepartmentWithGainSkill(
                    personID,
                    departmentRepository.listOfNameEveryDepartment().get(i),
                    true));
        }
        return countsGainedSkill;
    }

    public List<Integer> countEverySkillFromAllDepartments(Integer personID) {
        List<Integer> countsEverySkill = new ArrayList<>();
        for (int i = 0; i < departmentRepository.listOfNameEveryDepartment().size(); i++) {
            countsEverySkill.add(skillsRepository.countEverySkillChoosedDepartment(
                    personID,
                    departmentRepository.listOfNameEveryDepartment().get(i)));
        }
        return countsEverySkill;
    }

    public List<Double> getPercentValueGainedSkillToEverySkill(Integer personID) {
        List<Double> percentValues = new ArrayList<>();
        List<Integer> gainedSkills = countEveryGainedSkillFromAllDepartments(personID);
        List<Integer> everySkills = countEverySkillFromAllDepartments(personID);

        for (int i = 0; i < everySkills.size(); i++) { // here isn't important choose size from gainedSkill or everyRequiredSkill bcs those have the same size equal count department
            Double value = 0.0;

            if(everySkills.get(i) != 0) {
                value =Math.round( (gainedSkills.get(i) * 1.0 / everySkills.get(i) * 1.0)* 100.00) / 100.00;
            }
            percentValues.add(value);
        }
        return percentValues;
    }
    //###############################################################################################


    public Integer getCountEmployeeWhoGotFullSkillsFromDepartment(Integer personDepartment, Integer skillDepartment){
        Integer countMustHaveSkill = Integer.MIN_VALUE;    // here we initialize min value from integer to not count people.

        if(skillsRepository.countSkillRequiredOnSpecifyDepartment(skillDepartment) != null) {
            countMustHaveSkill = skillsRepository.countSkillRequiredOnSpecifyDepartment(skillDepartment);
        }

        List<Integer> countPeopleWithAllGainedSkill = personRepository.countPersonWhoGainedAllSkillFromDepartment(personDepartment, skillDepartment, countMustHaveSkill);
        return countPeopleWithAllGainedSkill.size();
    }
}
