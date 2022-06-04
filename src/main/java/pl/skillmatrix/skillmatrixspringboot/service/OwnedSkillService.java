package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skillmatrix.skillmatrixspringboot.model.OwnedSkill;
import pl.skillmatrix.skillmatrixspringboot.repository.OwnedSkillRepository;

@Service
@RequiredArgsConstructor
public class OwnedSkillService {
    private final OwnedSkillRepository ownedSkillRepository;

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
}
