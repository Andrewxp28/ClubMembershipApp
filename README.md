**Club Administration app** (Created by Andrewxp28.)

This is a console application that manages the memberships and members of a club.
This is a self-contained application using Sqlite with a single admin user; the database is
stored locally and can only be accessed by one user.

**Premise and Behaviour of The System:**

The club official needs to manage the memberships of members within the club. A member can
be a person with an active or no longer active membership. They are considered a member if 
at some point in time they have held a membership. A member can have multiple memberships 
and/or multiple active memberships at a given time. A member is only considered an active member
if they currently hold an active membership/s. Active membership is defined by if the start 
date has already passed, and the end date has not yet passed. Furthermore, a membership cannot 
exist without a corresponding existing member, so if a member is removed, all their 
memberships must be deleted.

The system must store a member's details of :
- Email address
- First name
- Last name
- Phone number

A membership is defined as:
- Membership ID
- Member email (who owns the membership)
- Start date
- End date
- Membership type



**Business Requirements**

This system was created with the following requirements in mind:
- Keep a record of the members
- Keep a record of the memberships
- Add new members
- Update existing members
- Remove members.
- Add new membership held by a member.
- Remove a membership held by a member.
- See all currently active members
- See all currently active memberships.


**Running The Console Application**

This project was built using Maven. So please load the dependencies
through Maven. Within an IDE, please build and run the app using the
class ConsoleApp as the entry point. (IntelliJ was used to work this project).