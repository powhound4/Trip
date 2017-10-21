# Sprint 3 - *T04* - *4TheWin*

## Goal

### A web based solution with shorter trips and a large data source.

## Definition of Done

* Ready for the demo.
* Sprint Review and Restrospectives completed.
* Product Increment release `v3.0` created on GitHub with appropriate version number and name, a description based on the template, and an executable JAR file for the demo.
* Version in pom.xml should be `<version>3.0.0</version>`.
* Unit tests for all new features and public methods at a minimum.
* Clean continuous integration build/test on master branch.

## Policies

* Tests and Javadoc are written before/with code.  
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* Continuous integration always builds and tests successfully.
* All commits with more than 1 line of change include a task/issue number.
* All Java dependencies in pom.xml.

## Plan 

User stories (epics) in the Sprint Backlog: *list the epics in your sprint backlog here*.
* #113 #208 #207 #117 #122 #125

Total planned tasks / issues in the Sprint Backlog: *number of issues in backlog at start* 
* 17

## Daily Scrums

Date | Tasks done this time (Issue # Only) | Tasks done next time (Issue # Only) | Impediments | Confidence
:--- | :--- | :--- | :--- | :---
 |10/4/17|Added Itinerary class and sprint3.md to master|Rename Brewery class to Destination (use find and replace), look at ways to optimize nearest neighbor, javascript|Work schedules, other classes, other assignments| Fairly confident
 | 10/6/17 | #210 | #220 #218 #113 | Understanding react and Javascript | Low
 | 10/9/17 | Dropdown menu with styling | More javascript functionality, understand the web server | Understanding react and Javascript, CS 356 | Moderate to Low
 | 10/11/17 | A search bar and button added; pull request up | Javascript functionality, work on server.java | Understanding react and Javascript, the web server and managing with CS 356 | Low
 | 10/13/17 | Lookup table working (stores the distances) | Get server calls working, IntelliJ (download) and coverage report with white box testing | Proficiency with react, Javascript and using a web server | Low confidence
 | 10/16/17 | Search box and dropdown work independently, search box styled | Server calls, complete a coverage report with white box testing, JSON encoding of what's entered into the search box | Work schedules, proficiency with react, Javascript and using a web server | Low confidence
 | 10/18/17 | Web server functioning and querying database, dropdown menu of location information dynamically created based on labels from JSON file | 2 Opt functioning properly, display only selected information about locations based on selection from dropdown | Javascript code from sprint 2 not yet completed, one path crossing in 2-opt algorithm | fairly confident 

## Review

#### Completed user stories (epics) in Sprint Backlog 
* *web server*:  
*the web page is now loaded from a web server, rather than a file; one can now interact with the server rather than a local file system; user may select destinations from a large database.*
* *2-opt*:
*the 2-opt optimization algorithm is now applied to all of the nearest neighbor tours to find the shortest*
* *useful information in itinerary*: 
*a user can now dynamically select the information they would like to see in their itinerary from the web server database.*

Completed *17* issues associated with these user stories.

#### Incomplete user stories / epics in Sprint Backlog 
* *Saved trips (optional)*: 
*This was one of the optional epics and it was not completed. A user can select items for an itinerary, but it will not be saved if they choose to start a new trip itinerary.*
* *Select a starting location (optional)*: 
*This was one of the optional epics and it was not completed. A user is not yet able to select a starting location.  At this point, the starting location is always the first destination in the ordering of them in the data file.*
* *Fast Response (optional)*: 
*This was one of the optional epics and it was not completed.  A user is not yet able to get a fast response.*

#### What went well
* *The team collaborated more on the tasks of their respective epics.*      
* *Additionally, the team spent more time working together than in previous sprints.*
* *Progress was also made and on the epics not completed in sprint 2.*
* *The team is now caught up on the required epics.*

#### Problems encountered and resolutions
* *Iterating through Objects and arrays of objects.  Working with the TAs helped to resolve this difficulty.*
* *Understanding how to work with a server, as it is something new for everyone in the group. The Piazza post helped with this.*
* *Understanding SQL, for those who didn't already have experience.  The Piazza post was a great help in doing this.*
* *Working around assignments for other classes.  This was resolved with other team members helping each other out where necessary, to still get the user stories completed it time.*

## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time |Work collaboratively|Find times each week or day, if possible, to meet and work together.| Time management, GitHub, CS 120 lab
What we did well |Handle change and take ownership of user needs|When a change needed to be made to what we were working on, we managed to fit it into what we needed to complete, despite having less time to do it.  The webpage is now more conducive to the user's needs.|Javascript, scrum meetings, group messaging, CS 120 lab, office hours
What we need to work on |Communication with the rest of the team|Communicate/give updates more frequently on where we are with our tasks and user story and give updates on the days when we do not meet in class and have a scrum meeting.|Group texts/messaging
What we will change next time |As a team, we will look to put more effort into completing the optional user stories.|If some team members have finished their tasks and whole user story(ies), they can attempt the optional user stories.  By doing this, we are getting ourselves and a more manageable position for the next sprint.|Time management, GitHub board, office hours, CS 120 lab
