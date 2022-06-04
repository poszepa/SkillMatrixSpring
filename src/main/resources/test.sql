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
JOIN departments_in_warehouse diw on p.deparments_id = diw.id
JOIN function_in_warehouse fiw on p.function_id = fiw.id
JOIN groups_in_warehouse giw on p.groups_id = giw.id
JOIN teams_in_warehouse tiw on p.team_id = tiw.id
WHERE skill_id = 34;

