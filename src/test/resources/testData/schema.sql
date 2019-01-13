-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS edited_car_park DEFAULT CHARACTER SET utf8 ;
USE edited_car_park ;

-- -----------------------------------------------------
-- Table test.car
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS edited_car_park.car(
  car_id INT(11) NOT NULL AUTO_INCREMENT,
  model VARCHAR(100) NULL DEFAULT NULL,
  year DATE NULL DEFAULT NULL,
  type VARCHAR(45) NOT NULL,
  PRIMARY KEY (car_id))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table test.person
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS edited_car_park.person(
  person_id INT(11) NOT NULL AUTO_INCREMENT,
  login VARCHAR(45) NULL DEFAULT NULL,
  password VARCHAR(45) NULL DEFAULT NULL,
  first_name VARCHAR(50) NULL DEFAULT NULL,
  second_name VARCHAR(50) NULL DEFAULT NULL,
  role VARCHAR(45) NULL DEFAULT 'UNDEFINED',
  license VARCHAR(45) NOT NULL,
  PRIMARY KEY (person_id),
  UNIQUE INDEX person_login_uindex (login ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table test.person_to_car
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS edited_car_park.person_to_car(
  id INT(11) NOT NULL AUTO_INCREMENT,
  fk_person_id INT(11) NOT NULL,
  fk_car_id INT(11) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_person_to_car_person1_idx (fk_person_id ASC),
  INDEX fk_person_to_car_car1_idx (fk_car_id ASC),
  CONSTRAINT fk_person_to_car_car1
    FOREIGN KEY (fk_car_id)
    REFERENCES edited_car_park.car (car_id),
  CONSTRAINT fk_person_to_car_person1
    FOREIGN KEY (fk_person_id)
    REFERENCES edited_car_park.person (person_id))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table test.route
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS edited_car_park.route(
  route_id INT(11) NOT NULL AUTO_INCREMENT,
  start VARCHAR(90) NULL DEFAULT NULL,
  finish VARCHAR(90) NULL DEFAULT NULL,
  start_ua VARCHAR(90) NULL DEFAULT NULL,
  finish_ua VARCHAR(90) NULL DEFAULT NULL,
  PRIMARY KEY (route_id))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table edited_car_park.route edited_car_park data
-- -----------------------------------------------------
INSERT INTO edited_car_park.route(start, finish, start_ua, finish_ua)
VALUES ("Krakov", "Berlin", "Краків", "Берлін");
-- -----------------------------------------------------
-- Table test.assignment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS edited_car_park.assignment(
  assignment_id INT(11) NOT NULL AUTO_INCREMENT,
  date_start DATETIME NULL DEFAULT NULL,
  status VARCHAR(45) NOT NULL DEFAULT 'assigned',
  route_id INT(11) NOT NULL,
  person_to_car_id INT(11) NOT NULL,
  PRIMARY KEY (assignment_id),
  INDEX fk_assignment_route_idx (route_id ASC),
  INDEX fk_assignment_person_to_car1_idx (person_to_car_id ASC),
  CONSTRAINT fk_assignment_person_to_car1
    FOREIGN KEY (person_to_car_id)
    REFERENCES edited_car_park.person_to_car (id),
  CONSTRAINT fk_assignment_route
    FOREIGN KEY (route_id)
    REFERENCES edited_car_park.route (route_id))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;