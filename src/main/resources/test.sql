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

SELECT skill_matrix.owned_skill.person_id,
       p.id,
       p.expertis,
       p.first_name,
       p.last_name,
       diw.name_department,
       fiw.function_name,
       giw.name_group,
       tiw.name_team,
       gain_skill,
       skill_id
FROM skill_matrix.owned_skill
JOIN persons p on owned_skill.person_id = p.id
JOIN skills s on owned_skill.skill_id = s.id
JOIN departments_in_warehouse diw on p.departments_id = diw.id
JOIN function_in_warehouse fiw on p.function_id = fiw.id
JOIN groups_in_warehouse giw on p.groups_id = giw.id
JOIN teams_in_warehouse tiw on p.team_id = tiw.id
WHERE (SELECT COUNT(*) FROM skill_matrix.owned_skill AS os WHERE os.gain_skill = true AND s.departments_in_warehouse_id = 4 AND s.is_required = true) =
      (SELECT COUNT(*) FROM skill_matrix.skills WHERE departments_in_warehouse_id = 4 AND is_required = TRUE);

SELECT * FROM skill_matrix.owned_skill AS os
JOIN skills s on os.skill_id = s.id
WHERE os.gain_skill = true AND
      s.departments_in_warehouse_id= 4 AND
      s.is_required = true
ORDER BY os.person_id;

SELECT COUNT(*) FROM skills WHERE
departments_in_warehouse_id = 4 AND is_required = TRUE;





SELECT COUNT(*)
FROM owned_skill
         JOIN persons p on p.id = owned_skill.person_id
         JOIN skills s on s.id = owned_skill.skill_id
         JOIN departments_in_warehouse diw on s.departments_in_warehouse_id = diw.id
WHERE gain_skill = true AND p.departments_id = 7 AND s.departments_in_warehouse_id = 4 AND s.is_required = true;

SELECT COUNT(*)
FROM skills
WHERE is_required = true AND departments_in_warehouse_id = 4;




