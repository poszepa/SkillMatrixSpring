INSERT INTO skill_matrix.skills_person_list (skills_list_id, person_list_id) SELECT skill_matrix.skills.id, 14 FROM skill_matrix.skills;


SELECT *
FROM skill_matrix.skills_person_list
INNER JOIN persons
ON skills_person_list.person_list_id = persons.id
INNER JOIN skills
ON skills_person_list.skills_list_id = skills.id
WHERE skills.departments_in_warehouse_id = 2 AND
      skills.name_skill =  'Szok';