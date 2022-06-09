INSERT INTO skill_matrix.skills_person_list (skills_list_id, person_list_id) SELECT skill_matrix.skills.id, 14 FROM skill_matrix.skills;


SELECT *
FROM skill_matrix.skills_person_list
INNER JOIN persons
ON skills_person_list.person_list_id = persons.id
INNER JOIN skills
ON skills_person_list.skills_list_id = skills.id
WHERE skills.departments_in_warehouse_id = 2 AND
      skills.name_skill =  'Szok';

INSERT INTO owned_skill (gain_skill, person_id, skill_id) SELECT false, 13, id FROM skills;

INSERT INTO skill_matrix.owned_skill (gain_skill, skill_id, person_id) SELECT false,31 ,id FROM skill_matrix.persons;




SELECT person_id, COUNT(*) FROM owned_skill
JOIN skills s on owned_skill.skill_id = s.id
JOIN persons p on p.id = owned_skill.person_id
WHERE
    p.active = true AND
    p.departments_id = 7 AND
    gain_skill = TRUE AND
    s.departments_in_warehouse_id = 2 AND
    s.is_required = TRUE
GROUP BY person_id
HAVING COUNT(*) = 3;








SELECT COUNT(*)
FROM owned_skill
         JOIN persons p on p.id = owned_skill.person_id
         JOIN skills s on s.id = owned_skill.skill_id
         JOIN departments_in_warehouse diw on s.departments_in_warehouse_id = diw.id
WHERE gain_skill = true AND p.departments_id = 7 AND s.departments_in_warehouse_id = 4 AND s.is_required = true;

SELECT COUNT(*)
FROM skills
WHERE is_required = true AND departments_in_warehouse_id = 2;




