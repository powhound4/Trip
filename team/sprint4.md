# Sprint 4 - *T04* - *4TheWin*

## Goal

### Worldwide trips!

## Definition of Done

* Ready for the demo.
* Sprint Review and Restrospectives completed.
* Product Increment release `v4.0` created on GitHub with appropriate version number and name, a description based on the template, and an executable JAR file for the demo.
* Version in pom.xml should be `<version>4.0.0</version>`.
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

## Plan 

Epics in the Sprint Backlog: *3-opts (#126), Destination Selection (#124), Manually Reorder Destionations (#350), Optimization Selection (#335), Remove Destinations (#349), Saved Trips, Stage 2 (#123), Website Transition (#334), Units of Measure (#121), Worldwide (#120)*.  

Total planned tasks in the Sprint Backlog: *28* 

Total story points in the Sprint Backlog: *43*

## Daily Scrums

Date | Tasks done this time | Tasks done next time | Impediments | Confidence
:--- | :--- | :--- | :--- | :---
10/23/2017 | none | planning| None | High
10/25/2017 |planning | #356 #336 #357 #355 #339| None | High
10/27/2017 |#336| #356 #357 #355 #339| Halloweekend, other assignments | Moderate
10/30/2017 |#376| Inspection1 and #356 #357 #355 #339| Inspection1, Halloween, other assignments | Moderate
11/1/2017 |#337, #378, #379, Inspection1| #381, #382, #384, #386, #390, #388, #389, #387, #356, #357, #355, #339| Other assignments, Work schedules | Moderate
11/3/2017 |#338, #341, #342, #343, #344, #345, #346, #347, #380, #386, #388, #389, #400 |#339, #351, #353, #356, #357, #367, #382, #387, #390, #422| Other assignments, work schedules, weekend | Fairly Confident
11/6/2017 |#340 #358 |Inspection 2, #339, #351, #353, #356, #357, #367, #382, #387, #390, #422| CS356 Assignment, Work Schedules, other classes | Low
11/6/2017 |Progress made towards completing tasks, but none completed | #339, #356, #357, #351, #353, #367, #382, #422| Inspection 2, CS356 assignment, work schedules, other classes | Low

## Review

#### Completed user stories (epics) in Sprint Backlog 
* *Worldwide  #120*: *A user can now plan a trip itinerary that includes destinations anywhere around the world, rather than in previous releases, where the user was limited to planning a trip within Colorado's borders.  This functionality is supported by a worldwide google map and an update to the SQL database to include worldwide destinations.*
* *Units of Measure  #121*:  *A user is now able to select itinerary distances either in miles or in kilometers.  Checkboxes have been added to the webpage interface to where a user can easily make this selection/distinction.  The checkbox selections are then recorded and passed to the Java code, where the preferred units are referenced to accurately calculate distances.*
* *Stage 2  #123*: *A user is able to select destinations from a large list.  The functionality now includes a 'select all' button through which a user can now select all of the destinations that a search of the database returns, in addition to the ability to select individual destinations.
* *3-Opt  #126*: *A user can now plan a trip with additional optimization.  The 3-Opt functionality improves upon the previously implemented 2-Opt so that a customer has access to planning the shortest possible trip.  This 3-Opt functionality is now available to the user in the Optimization Selection dropdown menu, where they can also choose other optimization methods.  However, for the user, the functionality of 3-Opt will always give them the shortest possible trip.
* *Website Transition  #334*: *The TripCo application can now be run off of a CS120 Lab machine, rather than a local file system.  The webpage can be opened up with a URL from any device on a CSU network connection*
* *Optimization Selection  #335*: *There is now a dropdown menu on the webpage that allows the user to choose their preferred optimization between In Order, Nearest Neighbor, 2-Opt and 3-Opt.  The selection is sent to the Java code where the chosen optimization technique is used and an itinerary is made accordingly.*

Completed *29* issues associated with these user stories.

#### Incomplete user stories / epics in Sprint Backlog 
* *Destination Selection  #124*: *A user is not yet able to add/select destinations to where they can build their trips in stages.  Once a selection has been made and an itinerary is printed out, a user must refresh the application to create an alternate itinerary.
* *Saved Trips  #125*: *A user is not yet able to save itineraries or load previously-created itineraries.  For a given itinerary a user must remake it each time they want to see a trip they intend to take. 
* *Remove Destinations  #349*: *A user is not yet able to remove destinations from the search and re-render the TripCo application.  Once an itinerary has been made, a user cannot dynamically remove destintations they don't want in their itinerary. 
* *Reorder Destinations  #350*:  *A user cannot reorder destinations in an itinerary that they have already built.  Currently, the table is static, so a user cannot interact with it and manually change the order of their destinations.

#### What went well
* *The team began working towards the Sprint Goal earlier on in the allocated period, than in previous Sprints.  Additionally, since the team members were not assigned specific tasks and user stories to complete, members were able to work together on each of them to complete them faster.  With this, the quantity of the user stories completed decreased, but the quality of the completion increased, which in the end, is directly considering of the customer's needs.*
* *A customer will be more satisfied with certain functionalities completed well, rather than all requested functionality completed with low quality.*
* *Moreover, the team handled change well and put in all possible time towards meeting the Sprint Goal.*


#### Problems encountered and resolutions
* *All members of the Development Team had assignments for other classes due during the time allocated for the Sprint 4 Milestone.  The problem was resolved, in that team members filled in for others where necessary. These matters were routinely considered in the Daily Scrums and Sprint Planning.*
* *The original distance formula was using atan(sin/cos) rather than atan2(sin,cos), which caused problems in caluclating distances that crossed the equator.  This took a good deal of time to debug and find.  When the team reviewed the wikipedia page for the Great Circle Distance Formula, it realized that atan2 is needed for worldwide distances.*
* *One of the nights that team members were working on 3-Opt, the Sprint 4 slides became corrupted, so the website resources were not available when looking to figure out the optimization.  The team members instead searched for other resources, after notifying the TA that the slides were unavailable.*
* *State variables in ReactJS cannot be deleted once they are set.  To resolve this, team members used .pop() to get rid of them.*

## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time |  |  | 
What we did well |  |  | 
What we need to work on |  |  |
What we will change next time |  |  | 
