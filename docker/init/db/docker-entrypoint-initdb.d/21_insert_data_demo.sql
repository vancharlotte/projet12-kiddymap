
	INSERT INTO profil (profil_id, username, description)
	VALUES ('31482d4d-2124-414c-b186-8dbf1886af7f',	'profil 1',	'description du profil 1'	);

	INSERT INTO location (location_id, name, description)
	VALUES ('d2d4b202-1fa1-4934-9ebb-b2d3202ffa48',	'location 1',	'description du lieu'	);

    INSERT INTO equipment (equipment_id, name)
    VALUES ('75892ad1-24ce-4ea6-b05e-ac33a5423954',	'table à langer');

    INSERT INTO location_equipment (location_id, equipment_id)
    VALUES ('d2d4b202-1fa1-4934-9ebb-b2d3202ffa48','75892ad1-24ce-4ea6-b05e-ac33a5423954');

    INSERT INTO profil_location_favorite (profil_id, location_id)
    VALUES ('31482d4d-2124-414c-b186-8dbf1886af7f','d2d4b202-1fa1-4934-9ebb-b2d3202ffa48');

