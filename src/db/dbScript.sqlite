-- All tables are created to store the different programs and subjects.
-- Each program and subject has a unique ID and name.

-- Table for study programs
create table Program (
                         program_id integer primary key,
                         name text not null -- not null ensures each program has a name
);

-- table for subjects
create table Subject (
                         subject_id integer primary key,
                         name text not null,
                         program_id integer, -- Links each subject to a program in the Program table
                         foreign key (program_id) references Program(program_id)
    -- Foreign key ensures that there is a relationship with the program table,
    -- references ensures that every subject's program_id matches a program_id in the program table.
);

-- table for electives
create table Elective (
                          elective_id integer primary key,
                          name text not null
);

-- Table for activities related to each subject or elective
-- This table stores specific activities that can be associated with a subject or an elective
-- Each activity has a unique ID, a name, and an ECTS value.
create table Activity (
                          activity_id integer primary key,
                          name text not null,
                          ects integer not null,
                          subject_id integer,
                          elective_id integer,
                          foreign key (subject_id) references Subject(subject_id),
                          foreign key (elective_id) references Elective(elective_id)
);

-- Insert data into Program
insert into Program (name) values ('HumTek');

-- Insert data into Subject
insert into Subject (name,program_id) values ('Computer Science', 1);
insert into Subject (name,program_id) values ('Informatik',1);

-- Insert subjects into Activity
insert into Activity (name, ects, subject_id) values ('Software Development',10, 1);
insert into Activity (name, ects, subject_id) values ('Essential Computing',5,1);
insert into Activity (name, ects, subject_id) values ('Interactive Digital Systems', 5,2);

-- Insert electives into Activity
insert into Activity (name, ects, elective_id) values ('Scientific Computing', 10, 1);
insert into Activity (name, ects, elective_id) values ('Functional Programming and languages',10,1);

-- Tables made for calculate the total ECTS points that a student has signed up for.
-- Student table
create table Student (
                         student_id integer,
                         name text not null
);

-- Student Activity table
create table StudentActivity (
                                 student_activity_id integer,
                                 student_id integer,
                                 activity_id integer,
                                 foreign key (student_id) references Student(student_id),
                                 foreign key (activity_id) references Activity(activity_id)
);

-- Insert students
insert into Student (student_id, name) values (83742, 'Elisabeth');
insert into Student (student_id, name) values (92101, 'Emilie');
insert into Student (student_id, name) values (91201, 'Julius');

-- linking students to activities
insert into StudentActivity (student_id, activity_id) values (83742, 1); -- Elisabeth in Software Development
insert into StudentActivity (student_id, activity_id) values (92101, 2); -- Emilie in Essential Computing
insert into StudentActivity (student_id, activity_id) values (92101, 1); -- Emilie in Software Development

-- Calculates the total ECTS points for a specific student
SELECT Student.name AS StudentName, SUM(Activity.ects) AS TotalECTS
FROM Student
         JOIN StudentActivity ON Student.student_id = StudentActivity.student_id
         JOIN Activity ON StudentActivity.activity_id = Activity.activity_id
WHERE Student.student_id = 92101;
