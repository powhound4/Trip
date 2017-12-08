# Sprint 5 - *T04* - *4TheWin*

## Goal

### Reliable first release with clean code!

## Definition of Done

* Ready for demo / customer release.
* Sprint Review and Restrospectives completed.
* Product Increment release `v5.0` created on GitHub with appropriate version number and name, a description based on the template, and an executable JAR file for the demo.
* Version in pom.xml should be `<version>5.0.0</version>`.
* Unit tests for all new features and public methods at a minimum.
* Coverage at least 50% overall and for each class.
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


## Metrics

Statistic | Planned | Completed
--- | ---: | ---:
Tasks |  *22*   | *16* 
Story Points |  *36*  | *19* 


Statistic | Start | End
--- | ---: | ---:
Overall Test Coverage | 28% | 60%
Smells | *43* | *27* 
Duplication | *40* | *30* 
Technical Debt Ratio | *23.8* | *15.2* 

## Plan

Epics planned for this release.

* *122 Select a starting location*
* *125 Saved Trips*
* *126 3-Opt*
* *349 Remove Destinations*
* *350 Manually reorder destinations*
* *491 Test coverage*
* *492 Clean Code*
* *493 Map with zoom and pan*

## Planning Decisions Made Based on Velocity of Previous Sprints
* The purpose of this sprint for our team is to get caught up from the previous sprint. 
* We will be adding some functionality, but will focus on completing all Sprint 4 epics, cleaning up our code, adding tests and updating our user interface
* We will start earlier to allow time to review all aspects of our release and make sure that everything is complete and in perfect working order.
* We will plan times to meet as a group for collaboration and to work through any issues we are having.
* Time management for this sprint will be critical as we all have several other projects due around the same time
* Each team member will work through the backlog starting with the items at the top first, communicating to the team about what they are working on and when. This will reduce conflicts in the code.

## Daily Scrums

Date | Tasks done now | Tasks done next | Impediments | Coverage | Smells | Duplication | Technical Debt Ratio
:--- | :--- | :--- | :--- | ---: | ---: | ---: | ---:
*date* | *issue numbers only* | *issue numbers only* | *High* | *50* | *10* | *20* | *15*
 |11/13/17, 11/15/17 | None | Sprint Planning | Other coursework | 28% | 43 | 40 | 23.8
 | 11/17/2017 | Sprint 5 planning is completed | 3-opt, saved trips | Thanksgiving break, teammates out of town | 28% | 43 | 40 | 23.8
 11/27/2017 | None | 3-opt, saved trips | other classes | 28% | 43 | 40 | 23.8
 11/29/2017 | Ability to save trips | Meeting with TA to work on 3 opt, testing/test coverage, work on code smells | Several other school projects, difficulty with 3 opt | 28% | 43 | 40 | 23.8
 12/1/2017 | None | KML, Meet with TA to work on 3 opt, test coverage, code climate issues | Several other school projects, difficulty with 3 opt | 28% | 43 | 44 | 23.7
 12/4/2017 | KML functionality, 3-opt improvements, Cleaned code, ++test coverage  | 3 opt with TA help, more test coverage, code climate issues | CS356, Several other school projects, difficulty with 3 opt, dead week | 30% | 26 | 36 | 17
12/6/2017 | Cleaned code, ++test coverage | page refresh issue, more test coverage, code climate cleaning | CS356, difficulty with 3 opt, dead week | 30% | 27 | 34 | 16.4

## Review

#### Completed user stories (epics) in Sprint Backlog 
* *125 Saved Trips:* A user can now save an Itinerary that they've created through the application with a "Save Itinerary" button.  With this new functionality, a user can look at itineraries they've created after building them, rather than having to rebuild it.
* *349 Remove Destinations:* A user can remove destinations when the come up in "selected" box, after selecting destinations, and have them removed from the built itinerary.  However, this is the only place that the destinations can be removed from.
* *491 Test coverage:* The application now has more test coverage, including testing for .java classes, which were not tested at all in previous Increments.
* *492 Clean Code:* The application has cleaner code now.  The technical debt ratio is now at 15.2%, down from 23.8%, where it started at for this Sprint.  Additionally, many issues were resolved, including those relating to smells and duplication.  The number of smells is now at 27, down from 43 (initial) and the duplication at 30, down from 40 (initial). With this release hundreds of "other issues" are also solved.
* *493 Map with zoom and pan:* a user was able to zoom and pan on a map provided by Google Docs during the last Sprint Increment, but a user can now save a KML save file of the map, with a button provided on the webpage.

#### Incomplete user stories / epics in Sprint Backlog 
* *122 Select a starting location:* A user cannot select a starting location.  The starting location is always the first one that comes up in the query results.
* *126, 3-Opt:* A user is not able to use 3-Opt optimization on their itinerary.  The user is still able to use previously-released optimizations, but will not be able to have a 3-Opt trip.  A bug remains in the code that cannot be found or resolved and the TAs could not fix it either.
* *350 Manually reorder destinations:* Implementing the code for this functionality would not work with how the code is structured.  There was not enough to rewrite all of the related code to get it to work.


#### What went well
* The Google Earth component was easy to set up and went smoothly.
* Two team members took a lot of time troubleshooting 3 opt and worked well together using problem solving techniques.
* We improved our Code Climate technical debt ratio down from 23.8% to 15.2%.
* We added additional tests bringing our coverage up to more than 50%.

### Problems encountered and resolutions
* The team had a hard time getting 3 opt working. To help resolve this we scheduled times to meet with the TA to get assistance.
* The way our code was set up didn't allow for a react drag-and-drop component to be set up easily. Due to this, we chose to forgo the option for re-ordering the trip intinerary.
* Due to this sprint occurring over the span of Thanksgiving break, our team wasn't as cohesive as prior sprints. We all did what we could individually, but had trouble meeting on a regular schedule.


## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time | Worked together to plan for Sprint 5. | Write tests at the same time as code, communicate well about what each person is working on so there are no conflicts with the code on master. Work towards cleaning code based on Code Climate scores and removing unusued methods/variables. | Using the new components of the app, such as the .KML file. Running the code using new commands.
What we did well | We wrote tests and fixed bugs in the code. Some of us met up and pairs of team members worked out problems together. | We improved this sprint by starting earlier to allow for time to overcome challenges. | IntelliJ, Code Climate, Test Coverage, Google Earth
What we need to work on | Better-encompasing plan for the Sprint, be more conservative in choosing what we have time to finish. We should let go of user stories we're hung up on and try something else; know when to move on to something that's more feasible to complete. | Use the planning session more methodically  | GitHub estimates, Code Climate, Planning Poker, Boards
What we will change next time | Write cleaner code from the start that's more easily understood by all team members. Improve acceptance accountability in getting tasks completed.  If a team member cannot contribute to the Sprint or come to class, find times outside of class to meet up and discuss things. | Try to communicate about what's happening on an individual level.  Choose the first user stories/tasks to work on, change and update as the sprint progresses and challenges arise. | Cell phones, Code Climate, Slack
