# Team *T04* - Inspection *2*
 
Inspection | Details
----- | -----
Subject | Home.jsx 
Meeting | 11/8/2017 10:00 - 10:50 AM
Checklist | 	Java Code Review Checklist (Dalbey) URL: http://users.csc.calpoly.edu/~jdalbey/301/Forms/CodeReviewChecklistJava.pdf

### Roles
Name | Role | Preparation Time
---- | ---- | ----
Alison Finnman| End User/ Developer| 1.5 hours
Evan Salzman| Maintainer/Developer | 1.5 hours 
Chris Schaffer| Test/Developer| 1 hour
George Hatch| Moderator/Developer | 1.75 hours 

### Log
file:line | defect | h/m/l | github# | who
--- | --- |:---:|:---:| ---
Home.jsx, line 11 | Remove the unused state variable, type: ''| l | Issue #438 | Alison
Home.jsx, lines 62-65 | The else if statement within the unit-recording function is no longer needed | m | Issue #436 | Evan
Home.jsx, line 75 | Remove the unused function handleOnSubmit, which had been used in the original checkbox code| l | Issue #436 | Evan
Home.jsx, line of each function declaration | Rename all "submit" functions, so that they have names that better identify what they are doing. | m | Issue #465 | George/Alison
Home.jsx, every line preceding a function | Add comments next to each function to clarify what each one is doing, clarify code and make it more readable | m | Issue #466 | Chris
Home.jsx, line 116 | The setOptimization function is never used, so it should be removed. | l | Issue #467 | Alison 
