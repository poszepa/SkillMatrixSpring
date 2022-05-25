package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skillmatrix.skillmatrixspringboot.model.Skills;
import pl.skillmatrix.skillmatrixspringboot.repository.PersonRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.SkillsRepository;

@RequiredArgsConstructor
@Service
public class SkillsService {
    private final SkillsRepository skillsRepository;
    private final PersonRepository personRepository;

    @Transactional
    public void modifySkills(Skills skills) {
        skills.setPersonList(personRepository.findAll());
        skills.setGainSkill(false);
        skillsRepository.save(skills);
    }

}
