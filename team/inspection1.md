# Team *T04* - Inspection *1*
 
Inspection | Details
----- | -----
Subject | *T04/web/src/components/app.js (lines 26-251)*
Meeting | *11/1/17, 10:00-10:50am, Usual classroom*
Checklist | *(Java Code Review Checklist (Dalbey)) http://users.csc.calpoly.edu/~jdalbey/301/Forms/CodeReviewChecklistJava.pdf*

### Roles
Name | Role | Preparation Time
---- | ---- | ----
Alison Finnman| End User/Developer| 1.5 hours
Evan Salzman|Maintainer/Developer | Approx. 2 hrs, 10 min
Chris Schaffer|Tester/Developer | 1 hour
George Hatch| Moderator/Developer| 1 hour

### Log
file:line | defect | h/m/l | github# | who
--- | --- |:---:|:---:| ---
 App. js: line 10|Variable tDist should be state variable | l | Issue #381 | Evan |
 App.js: line 27/28|Variables serverDestinations and dest are uninitialized and not used - need to remove| l | Issue #382 | Alison
 App.js: line 37|If statement has no functionality- may be used at later date | l | | Leave for now
 App.js: line 179|browseFile function can be simplified. It currently has three for-loops, we can break this down further, indentation makes it hard to follow | m | Issue #390 | Chris
 App.js: line 80|add additional information to new map file | m | Issue #388 | George
 App.js: line 159|Have both a local and global variable with the same name. Consolidate this to one variable | l | Issue #389| Evan
 App.js: line 59, 60, 62|Functions sent to home.jsx that aren't used in home.jsx. They can be removed | l | Issue #387 | Alison
 App.js: line 67, 68|Change getColumns and getUnits methods to setColumns and setUnits to better describe their functionality | l | Issue #384 | George
 App.js: 237 | Uses var rather than let. Declaration using let should be used in react | l | Issue #386| Evan
 
