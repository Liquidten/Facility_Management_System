
drop table if exists facility cascade;
drop table if exists facilityDetail cascade;
drop table if exists inspection cascade;
drop table if exists USE cascade;
drop table if exists maintenance cascade;
drop table if exists maintenanceRequest cascade;


CREATE TABLE facility (
  ID serial PRIMARY KEY
);


CREATE TABLE facilityDetail(
 FacilityID INTEGER,
 FacilityName VARCHAR (200) NOT NULL,
 NumberOfRooms INTEGER NOT NULL,
 PhoneNumber INTEGER NOT NULL,
CONSTRAINT facility_has_detail FOREIGN KEY (FacilityID)
      REFERENCES Facility (ID) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE inspection(
 InspectionID serial PRIMARY KEY,
 FacilityID INTEGER,
 InspectionType VARCHAR (50) NOT NULL,
 InspectionDetail VARCHAR (200) NOT NULL,
CONSTRAINT inspection_facility FOREIGN KEY (FacilityID)
      REFERENCES Facility (ID) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE USE(
 UseID serial PRIMARY KEY,
 FacilityID INTEGER,
 RoomNumber INTEGER NOT NULL,
 StartDate TIMESTAMP NOT NULL,
 EndDate TIMESTAMP NOT NULL,
CONSTRAINT facility_use FOREIGN KEY (FacilityID)
      REFERENCES Facility (ID) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE maintenance(
 MaintenanceID serial PRIMARY KEY,
 FacilityID INTEGER,
 Detail VARCHAR (200) NOT NULL,
 Cost INTEGER NOT NULL,
CONSTRAINT maintenance_facility FOREIGN KEY (FacilityID)
      REFERENCES Facility (ID) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE maintenanceRequest(
 MaintenanceRequestID serial PRIMARY KEY,
 FacilityID INTEGER,
 Detail VARCHAR (200) NOT NULL,
CONSTRAINT maintenance_request_facility FOREIGN KEY (FacilityID)
      REFERENCES Facility (ID) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);


