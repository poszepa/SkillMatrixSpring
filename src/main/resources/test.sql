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

INSERT INTO skill_matrix.owned_skill (gain_skill, skill_id, person_id) SELECT false,31 ,id FROM skill_matrix.persons