# Rang Xiao's personal project for CPSC 121

## Student file management system for BC high school

This is a student file management system designed for BC high school. This application can store basic information for 
students in the school. Information includes student's name, PEN, address, contact information (address, email address,
phone number, emergency contact). Also, it can track students' academic information including courses took, grades, 
planed courses for following semester, and required course for their dreaming university program. For more detail, it 
will also hold detailed information for their courses, for example teacher for courses, and weekly schedule. 

I have planned to build a similar system for years back to when I worked in a private high school as an officer. I found
at the start of each term, we need to spend a lot of time to plan courses for students as most of their record are paper-based,
and they are hard to tracking. With this system, it will be much easier to find student's information and track their 
academic history. 

### Major information kept and function: 

#### Basic Student Information:
- Name
- Address
- Nationality
- Contact information
    - Email address
    - Phone number
- Emergency contactor's information
  - Name
  - Relation with student
  - Address
  - Phone number

#### Student Academic Information
- Course history
  - Grade
  - Finish time
- Current course 
  - Teacher
  - Time block 
- Planned course for next term
  - Teacher
  - Time block
- Planned course for future (this is based on student's choice of university and program)
- Course grade average (by selecting courses which are wanted to be counted)

#### Course schedule for current term and following term (if applicable)

## User Story
- As a user I want to add new student into the system
- As a user I want to view all students currently in the system, and choose anyone to view his or her detailed information
- As a user I want to edit basic information of a student including his or her address, email address, phone number, PEN, and emgency contactor's information
- As a user I want to view student's course history
- As a user I want to add a new course to a student
- As a user I want to get student's current average grades
- As a user I want to get student's schedule for this term including course name and time block for each course
- As a user I want to edit course information for a student, I may need to change course name, time block, teacher name, and so on
- As a user I want to save data I inputted 
- As a user I want to load pre-saved data
- As a user I want to delete certain student from the list 
- As a user I want to delete a pre-existing course from a student

## Phase 4: Task 2
Sun Nov 21 12:39:11 PST 2021
rang xiao's email address changed

Sun Nov 21 12:39:11 PST 2021
rang xiao's nationality changed

Sun Nov 21 12:39:11 PST 2021
rang xiao's phone number changed

Sun Nov 21 12:39:11 PST 2021
rang xiao's student number changed

Sun Nov 21 12:39:11 PST 2021
change address unit number

Sun Nov 21 12:39:11 PST 2021
change address street address

Sun Nov 21 12:39:11 PST 2021
change address city

Sun Nov 21 12:39:11 PST 2021
change address province

Sun Nov 21 12:39:11 PST 2021
change address postal code

Sun Nov 21 12:39:11 PST 2021
rang xiao's address changed

Sun Nov 21 12:39:11 PST 2021
rang xiao's DOB changed

## Phase 4: Task 3
See PDF file UML_Design_Diagram in the root 

#### Refactor of design:
Add bidirectional relation between student and course, emergency contactor

Add bidirectional relation between student and address

Add bidirectional relation between emergency contactor and address

Extract an abstract class for StudentInfoUI and StudentInfoPaneUI