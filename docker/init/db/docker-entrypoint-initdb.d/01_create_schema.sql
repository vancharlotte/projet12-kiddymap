
CREATE TABLE profil (
                                 profil_id UUID PRIMARY KEY,
                                 auth_id VARCHAR(100) NULL,
                                 username VARCHAR(255) NOT NULL,
                                 description VARCHAR(255) NOT NULL
);


CREATE TABLE location (
                                   location_id UUID PRIMARY KEY,
                                   longitude FLOAT NOT NULL,
                                   latitude FLOAT NOT NULL,
                                   name VARCHAR(100) NOT NULL,
                                   description VARCHAR(255) NOT NULL

);

CREATE TABLE equipment (
                                    equipment_id UUID PRIMARY KEY,
                                    name VARCHAR(50) NOT NULL
);


CREATE TABLE location_equipment (

                            equipment_id UUID NOT NULL,

                            location_id UUID NOT NULL,

                            FOREIGN KEY (equipment_id) REFERENCES equipment (equipment_id) ON DELETE RESTRICT ON UPDATE CASCADE,

                            FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE RESTRICT ON UPDATE CASCADE,

                            PRIMARY KEY (equipment_id, location_id)
);




CREATE TABLE profil_location_favorite (

                                             profil_id UUID NOT NULL,

                                             location_id UUID NOT NULL,

                                             CONSTRAINT FK_PROFIL FOREIGN KEY (profil_id) REFERENCES profil (profil_id) ON DELETE RESTRICT ON UPDATE CASCADE,

                                             CONSTRAINT FK_LOCATION FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE RESTRICT ON UPDATE CASCADE,

                                             PRIMARY KEY (profil_id, location_id)
);

