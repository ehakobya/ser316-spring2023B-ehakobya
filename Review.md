## Code Review Defect List

---
#### Reviewer: Edgar Hakobyan

#### GH Repo: https://github.com/ehakobya/ser316-spring2023B-ehakobya/tree/main


| ID# | Location (File - Line #)  |       Problem Description        | Problem (Category - Severity) |
|:---:|:-------------------------:|:--------------------------------:|:-----------------------------:|
|  1  |    Main.java - Line #7    |      Usage of magic numbers      |           CS - LOW            |
|  2  |      Character.java       |     No setter/getter methods     |            CS - MJ            |
|  3  | Character.java - Line #2  |     Public class attributes      |            CS - MJ            |
|  4  |  GamePlay.java - Line #1  | Usage of '*' to import libraries |           CS - LOW            |
|  5  |  GamePlay.java - Line #1  |            No header             |           CS - LOW            |
|  6  | GamePlay.java - Line #117 |      Usage of magic numbers      |           CS - LOW            |
|  7  | GamePlay.java - Line #126 | Usage of '==' instead of .equals |            FD - MJ            |

### <u>Category:</u> 
#### CS - Code Smell defect. 
#### CG - Violation of a coding guideline. Provide the guideline number. 
#### FD - Functional defect. Code will not produce the expected result. 
#### MD - Miscellaneous defect, for all other defects.

### <u>Severity:</u>
#### BR - Blocker, must be fixed asap. 
#### MJ - Major, of high importance but not a Blocker 
#### LOW  Low. 