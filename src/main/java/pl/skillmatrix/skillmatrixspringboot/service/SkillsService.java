package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void modifySkills(Skills skills) {
        Skills skillFromDataBase = skillsRepository.findSkillsById(skills.getId());
        skillFromDataBase.setGainSkill(false);
        skillFromDataBase.setPersonList(personRepository.findAll());
        skillFromDataBase.setNameSkill(skills.getNameSkill());
        skillsRepository.save(skillFromDataBase);
    }

}
