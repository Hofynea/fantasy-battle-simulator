# Code Review Defect List

**Reviewer:**  Daria Ilchenko

**GH Repo:**  `ser316-spring2023C-dilchenk`

## Category

- `CS` Code Smell defect
- `CG` Violation of a coding guideline. Provide the guideline number
- `FD` Functional defect. Code will not produce the expected result
- `MD` Miscellaneous defect, for all other defects

## Severity

- `BR` Blocker, must be fixed asap
- `MJ` Major, of high importance but not a Blocker
- `LOW` Low

## Defects

| ID# | Location (File and Line Number) | Problem Description                                 | Problem Category        | Problem Severity |
| --- | ------------------------------- |-----------------------------------------------------| ----------------------- | ---------------- |
|  1  | GamePlay.java, Line #118        | `@param` character missing Javadoc comment          | CG                      | LOW              |
|  2  | Character.java, Line #2 - #8    | Class member variables are not private              | CG                      | MJ               |
|  3  | Character.java, Line #2 and #8  | Literal value '100' is not declared as a constant   | CG                      | MJ               |
|  4  | BlackBoxGiven.java, Line #357   | Identifier is too long                              | CS                      | LOW              |
|  5  | Character.java, Line #10        | `printInfo()`: does not do anything (lazy class)    | CS                      | LOW              |
|  6  | GamePlay.java, Line #109        | `takeDamage()` always returns 1                     | FD                      | BR               |
|  7  | GamePlay.java, Lines #162, #163 | `levelUp()` does not return the correct value       | FD                      | BR               |
