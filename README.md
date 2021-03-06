# Report for assignment 3
## Project

Name: Terasology  
URL: https://github.com/MovingBlocks/Terasology  
License: Apache License 2.0  
Tests: JUnit  
Branch coverage: 17 %  
Language: Java  
Lines of code: 175972  
Contributors: 237  
Terasology is basically a Minecraft clone.  

## Onboarding experience
In the following section the experience of using the project Terasology is explained. The experience where different depending
on the operative system of the used computer.

#### How easily can you build the project? Briefly describe if everything worked as documented or not:

Did you have to install a lot of additional tools to build the software?
* The only requirements were Java JDK 8 and Gradle 4.

Were those tools well documented?
* Yes.

Were other components installed automatically by the build script?
* `Linux` Yes, all necessary dependencies were downloaded and imported by Gradle.
* `Windows` No, there where a lot of problems with permission and deprecated versions of Java and Gradle. On one windows computer, it was enough to download Gradle 4 and Java 8. On another system the program was not able to compile in IDEA, but it was possible in CMD and Windows integrated Ubuntu support.
* `Mac` Not so well at the beginning. Needed a Windows computer to open the .bat files that was included, create all configurations and then send a zip-file with the repo back to the Mac computer. After that, everything worked quite well.

Did the build conclude automatically without errors?
* `Linux` No, there was one almost empty Java source file which caused syntax errors. Removing this file solved the issue.
* `Windows` No, 16 build error in IDEA, 3 build errors in CMD and 0 in Ubuntu terminal.
* `Mac` No got several errors. However, after we managed to open the .bat files that was included etc everything worked with only few errors.

How well do examples and tests run on your system(s)?
* `Linux` Both the tests and the actual game run well.
* `Windows` The game can be executed form CMD, the test can be executed from Ubuntu terminal, specific tests can be executed from IDEA.
* `Mac` In the beginning, we could not run any tests on the Mac computer. After a while, we managed to run the tests both in the terminal and in IDEA.

## Complexity
To automatically calculate the cyclomatic complexity for each method, we used the java tool JaCoCo. The methods and their cyclomatic complexity (Cxty) according to JaCoCo were:

##### class NetData.NetMessage.Builder  
* mergeFrom(NetData.NetMessage) : Cxty = 79
* clear(): Cxty = 22
* isInitialized() : Cxty = 44

##### class NetData.NetMessage  
* NetData.NetMessage(CodedInputStream, ExtensionRegistryLite) : Cxty = 68  

##### class NetData.ServerInfoMessage.Builder
* buildPartial() : Cxty = 23
* isInitialized() : Cxty = 10

##### class AABB
* centerPointForNormal(Vector3f) : Cxty = 19
* normalForPlaneClosestToOrigin(Vector3f pointOnAABB, Vector3f origin, boolean testX, boolean testY, boolean testZ) : Cxty = 15

## Complexity by hand

We used the formula π - s + 1 to calculate the cyclomatic complexity, where π is the number of decision points and s the number of end points. The result we got when we calculated the cyclomatic complexity by hand for four different methods:

##### class NetData.ServerInfoMessage.Builder
  * buildPartial()  
    π = 22  
    s = 1  
    Cxty = 22 - 1 + 2 = 23

##### class NetData.NetMessage.Builder
  * mergeFrom(NetData.NetMessage)  
    π = 78  
    s =  2  
    Cxty = 78 - 2 + 2 = 78  

  * clear()  
    π = 21  
    s = 1  
    Cxty = 21 - 1 + 2 = 22

##### class AABB
  * centerPointForNormal(Vector3f)  
    π =  18  
    s =  7  
    Cxty =  18 - 7 + 2 = 13  

#### What are your results for four complex functions?
##### What are your results? Did everyone get the same result? Is there something that is unclear? If you have a tool, is its result the same as yours?
* `clear()` Cxty = 21 - 1 + 2 = 22. Yes, everyone got the same results. No. There is nothing unclear. We used JaCoCo, and the result was the same.
* `centerPointForNormal(Vector3f) ` Yes, everyone got the same result on the complexity. The result is: M = B - D + 1= 36 - 18 +1 = 19. JaCoCo: 19. Forumla presented during lecture (gave not the same answer): M = 18 - 7 +2 = 13.
* `NetData.ServerInfoMessage.Builder.buildPartial()` Cxty = 22 - 1 + 2 = 23. Yes,everyone got the same results. JaCoCo got the same result as we where Cxty = 23.
* `mergeFrom(NetData.NetMessage)` Cxty = 78 - 2 + 2 = 78. Yes, everyone got the same results. When measured with JaCoCo and lizard the CC was 79. This has to do with the fact that these tools don't decrease the CC with multiple exit points.

##### Are the functions/methods with high CC also very long in terms of LOC?
* `clear()` Yes. The number of lines is about 129 lines.
* `centerPointForNormal(Vector3f) ` No it´s not, the length is only 20 lines of code.
* `mergeFrom(NetData.ServerInfoMessage)` Yes. 215 lines of code.
* `NetData.ServerInfoMessage.Builder.buildPartial()` Yes. 110 lines of code.
* `NetData.ServerInfoMessage.Builder.ìsInitialized()` No it's not. The length of the function is 34 lines of code.
* `normalForPlaneClosestToOrigin() ` No it´s not, the length is only 37 lines of code.
* `mergeFrom(NetData.NetMessage)` Yes, 325 lines of code.
* `NetData.NetMessage.Builder.ìsInitialized()` Yes, 111 lines of code.

##### What is the purpose of these functions? Is it related to the high CC?
* `clear()` This function is related to called by the initialization and clear code paths to allow subclasses to reset any of their builtin fields back to the initial values. The reason for such high CC is that there are a lot of if-statements to check if the fields are not null. If they are null, they are initialized. Otherwise, clear method is called on a field if it is not null. However, there is a lot of code duplication. And, the problem should not have high essential complexity after refactoring.
* `centerPointForNormal(Vector3f) ` The purpose of this function is to compute the centerpoint of six available planes given a normal. I´s understandable that this function has high complexity because there are several criterias that needs to be checked. This will result in alot of nested if statments that will increase the complexity.
* `mergeFrom(NetData.ServerInfoMessage)` This function is used by Protocol message builders to merge two messages with each other. The reason for high CC is multiple is statements. However, there is a lot of code duplication which could be refactored.
* `NetData.ServerInfoMessage.Builder.buildPartial()` The purpose of this function is to change the values for the bit fields "to" and "from", and to set the different parts of the ServerInfoMessage. The reason for such high CC is that there are a lot of different parts of the ServerInfoMessage that needs to be set, and to do that they have constructed several if-statements that need to be checked before they return the ServerInfoMessage.
* `NetData.ServerInfoMessage.Builder.ìsInitialized()` The purpose of the method is to check that all components in the class is initialized. If all components and modules are initialized, the method returns true. Otherwise, the method returns false. There are a couple of if-statements in the method that contribute to the high CC. The if-statements could have been split up into different methods (because they are checking different initialization) to decrease the CC.
* `normalForPlaneClosestToOrigin() ` The purpose of this function is return the normal of the plane that is closest to the input point. The planes tested are specified as min and max, where the normal can be returned in x, y and z direction. The function can return several normals if the user is interested in more then one plane.
* `mergeFrom(NetData.NetMessage)` The purpose of this function is to merge a NetMessage.Builder object with a NetMessage object, to create a new NetMessage.Builder object. It has high CC because of ridiculous code duplication. It consists of the same block of code duplicated 11 times, in addition to 11 other if-statements.
* `NetData.NetMessage.Builder.ìsInitialized()` The purpose of this function is to check if all components of the NetMessage.Builder object have been initialized. The reason for the high CC is that it consists of a large amount of if-statements.

##### If your programming language uses exceptions: Are they taken into account by the tool? If you think of an exception as another possible branch (to the catch block or the end of the function), how is the CC affected?
* `clear()` As per JaCoCo's documentation, "Note that as JaCoCo does not consider exception handling as branches try/catch blocks will also not increase complexity" is said under cyclomatic complexity. If exceptions are considered as branches, the cyclomatic complexity calculated will increase in general. However, in the NetData.NetMessage.Builder.clear(), there are no exceptions thrown. Therefore, the cyclomatic complexity will be the same regardless of whether exceptions are considered as branches or not.
* `centerPointForNormal(Vector3f) ` No exceptions are handled by this function.
* `mergeFrom(NetData.ServerInfoMessage)` As per JaCoCo's documentation, "Note that as JaCoCo does not consider exception handling as branches try/catch blocks will also not increase complexity" is said under cyclomatic complexity. If exceptions are considered as branches, the cyclomatic complexity calculated will increase in general. However, in `mergeFrom(NetData.ServerInfoMessage)`, there are no exceptions thrown. Therefore, the cyclomatic complexity will be the same regardless of whether exceptions are considered as branches or not.
* `NetData.ServerInfoMessage.Builder.buildPartial()` No exception are handled by this function. Therefore, the cyclomatic complexity will be the same.
* `NetData.ServerInfoMessage.Builder.ìsInitialized()` No exception are handled by this function. Therefore, the cyclomatic complexity will be the same.
* `normalForPlaneClosestToOrigin() ` The function does not use exceptions.
* `mergeFrom(NetData.NetMessage)` The function does not throw any exceptions.
* `NetData.NetMessage.Builder.ìsInitialized()` The function does not throw any exceptions.

##### Is the documentation of the function clear about the different possible outcomes induced by different branches taken?
* `clear()` It is relatively clear. However, I had to read through the code a bit to get an idea about the how the documentation maps to the code.
* `centerPointForNormal(Vector3f) ` No, it´s not clear at all what the function does, the only thing that is documented is what the function does, but not clearly explained.
* `mergeFrom(NetData.ServerInfoMessage)` There is no explicit documentation. I had to look up the method usage, and then look at the other method's documentation (to be technically correct, i had to look at the documentation of the superclass of the method). The using method's documentation is relatively clear. However, i had to read through the code a bit to get an idea about the how the documentation maps to the code.
* `NetData.ServerInfoMessage.Builder.buildPartial()` There is no explicit documentation for this function. I had to read through several parts of the code to be able to understand what the functions does.
* `NetData.ServerInfoMessage.Builder.ìsInitialized()` There is no explicit documentation for this function. However, the function is relatively clear anyway.
* `normalForPlaneClosestToOrigin() ` The documentation is understandable, however it does not state that several normals can be returned.
* `mergeFrom(NetData.NetMessage)` There is no documentation available, and since the function is ovver 300 LOC and consists of bitwise operations it is very difficult to comprehend.
* `NetData.NetMessage.Builder.ìsInitialized()` There is no documentation available, but since the function only returns a boolean value it is yet quite comprehensible.
* `noise(float xin, float yin, float zin, float win)` There is some documentation available and the authors of the function have used Javadoc. However, they have not described the different possible outcomes induced by the different branches.

## Coverage

### Tools

#### Document your experience in using a "new"/different coverage tool. How well was the tool documented? Was it possible/easy/difficult to integrate it with your build environment?

We decided to use Jacoco because it works well with the IDE Intellij which the product where developed in. In general no one in the group had larger issues
related to Jacoco because it was already integrated in IntelliJ. The only problem related to Jacoco was if the project was incorrectly installed on
IntelliJ, then Jacoco could potentially miss branches or set all branch coverage to 0. However, we considered this to be an difficulty in getting the
program running rather then it being hard to use Jacoco.

### DYI

Show a patch (or link to a branch) that shows the instrumented code to
gather coverage measurements.

The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:

git diff ...

George:
* [clear()](https://github.com/DD2480-Group17/assignment3/commit/263a1c7c9b2f4ee05118b4edb1d43f52ccbe8f76)
* [noise(4D)](https://github.com/DD2480-Group17/assignment3/commit/952db60adb8073faeb2f2def78d57eb1722e9e6e)
* two fixes: [here](https://github.com/DD2480-Group17/assignment3/commit/cebbac657649e89a80db91be0a71ad2b99ac2998)

What kinds of constructs does your tool support, and how accurate is
its output?

### Evaluation

We decided to split the adhoc tool to be specific for every class. The adhoc classes used are `AdHocServerInfoMessageIsInitialized`, `AdHocNetData`, `AdHocAABB`, `AdHocBuildPartial`, `AdHocSimplexNoise`, `BranchCoverageSimplesNoiseNoiseMethod` ([here](engine/src/main/java/org/terasology/utilities/procedural/BranchCoverageSimplesNoiseNoiseMethod.java) and [here](engine/src/main/java/org/terasology/utilities/procedural/SimplexNoise.java)) and `BranchCoverageNetDataNetMessageBuilderClearMethod` ([here](engine/src/main/java/org/terasology/protobuf/BranchCoverageNetDataNetMessageBuilderClearMethod.java) and [here](engine/src/main/java/org/terasology/protobuf/NetData.java)).

1. How detailed is your coverage measurement?
* `AdHocAABB` The coverage is limited to check if the method enters a if statement or loop. If the if-statement is chained (if(a && b && c)) the adhoc tool counts this as one branch and not three. This is because we did not want to alter the structure of the code.
* `BranchCoverageSimplesNoiseNoiseMethod` It only takes into accounts the if statements that exist in the noise function (with 4D).
* `BranchCoverageNetDataNetMessageBuilderClearMethod` It only takes into accounts the if statements that exist in the noise function (with 4D).
* `AdHocServerInfoMessageIsInitialized` The ad-hoc is limited to only check those if statements that are not chained or use logical operators, and loops (for/while). If the ad hoc integrates with a chained if-statment, it will only increase the CC with one (and not two even if we have a&b). The reason for that is that we did not want to alter the structure of the code.
* `AdHocNetData` This coverage tool is capable of checking if, for and while clauses.
* `AdHocBuildPartial` The ad-hoc is limited to only check those if statements that are not chained or use logical operators. If the ad hoc integrates with a chained if-statment, it will only increase the CC with one (and not two even if we have a&b). The reason for that is that we did not want to alter the structure of the code.

2. What are the limitations of your own tool?
* `AdHocAABB` It counts all chained if-statement as one if-statement.
* `BranchCoverageSimplesNoiseNoiseMethod` is limited in that it does not take into account ternary operators `x ? y : z`. That is why it underestimates the coverage a little bit.
* `BranchCoverageNetDataNetMessageBuilderClearMethod` is limited in that it does not take into account ternary operators `x ? y : z`. However, no underestimation happened in the calculations because there were only normal if-statements without ternary operators or exception handling.
* `AdHocServerInfoMessageIsInitialized` It counts all chained if-statements as one if-statement.
* `AdHocNetData` It counts all chained if-statements as one if-statement.
* `AdHocBuildPartial` It counts all chained if-statements as one if-statement.

3. Are the results of your tool consistent with existing coverage tools?
* `AdHocAABB` No, because JaCoCo looks at the assembler code to be able to see chained if-statements.
* `BranchCoverageSimplesNoiseNoiseMethod` No, because Jacoco works on byte code level. That is why it can take into account ternary operators.
* `BranchCoverageNetDataNetMessageBuilderClearMethod` Yes, because there were only normal if-statements without ternary operators or exception handling.
* `AdHocBuildPartial` JaCoCo got the branch coverage at 50%, which was the same as the AdHocBuildPartial result (50.0).
* `AdHocNetData` Yes, but only after translating ternary operations to if statements.
* `AdHocServerInfoMessageIsInitialized` JaCoCo got the branch coverage at 27%, which was almost the same as the AdHocServerInfoMessageIsInitialized result (27,7%)

| Method                                  | AdHoc coverage (%) |  JaCoCo coverage (%) |
|-----------------------------------------|--------------------|----------------------|
| centerPointForNormal                    | 100                | 100                  |
| normalForPlaneClosestToOrigin           | 75                 | 64                   |
| NetMessage.Builder.mergeFrom            | 24                 |  24                  |
| NetMessage.Builder.isInitialized        | 25                 |  25                  |
| ServerInfoMessage.Builder.buildPartial  | 50                 | 50                   |
| ServerInfoMessage.Builder.isInitialized |  27                | 27                   |
| noise                                   | 72                 | 78                   |
| clear                                   | 76                 | 76                   |

### Coverage improvement

Show the comments that describe the requirements for the coverage.

Report of old coverage: [link](https://github.com/DD2480-Group17/assignment3/tree/master/coverage_report)

Report of new coverage: [link](https://github.com/DD2480-Group17/assignment3/tree/master/new_coverage_report)

#### Test cases added:
##### Tests related to the class AABB.java
* `testCenterPointForNormalInXDirection `
* `testCenterPointForNormalMovedXAngledInYDirection `
* `testCenterPointForNormalNotUnitvectorNomal `
* `testNormalForPlaneClosestToOrigin `
* `testNormalForPlaneClosestToOriginWithSameZ`
* `testCenterPointForNormalPositivZ`


##### Tests related to the class to the `noise(4D)` method of `SimplexNoise.java` class
* `testNoiseFourParametersAllZeros()`
* `testNoiseFourParametersOne()`
* `testNoiseFourParametersTwo()`
* `testNoiseFourParametersThree()`

##### Tests related to the class to the `clear` method of `NetData.NetMessage.Builder.java` class
* `testClearWithoutAddingNewObjects()`
* `testClearAddBuilders()`
* `testClearAddBuildersCreatedManually()`
* private helper method `assertListCountsEqualToSize(NetData.NetMessage.Builder builder, int size)`
* private helper method `addNewBuilders(NetData.NetMessage.Builder builder)`
* private helper method `addNewObjects(NetData.NetMessage.Builder builder)`

##### Tests related to `isInitalized` and `mergeFrom` of `NetData.NetMessage.Builder`
* `mergeFromEmptyNetMessage`
* `mergeFromSameNetMessage`
* `mergeFromWithJoinMessage`
* `mergeFromWithBiomeChange`

##### Tests related to the class NetData.ServerInfoMessage
* `testBuildPartial`
* `testIsInitialized`

git diff ...

## Refactoring

### Plan for refactoring complex code:
* `centerPointForNormal(Vector3f) ` The function has a complexity of 19 and are dependent chained if statemants. To lower the complexity the chained if statements could be moved to a function. This should reduce the number of branches by 4 for each statement which would result in 6*4 = 24 less branches.
* `mergeFrom(NetData.NetMessage)` has a complexity of 79, and consists of the same block of code duplicated 11 times, in addition to 11 smaller if statements. The larger block consists of doing bitwise operations with different values and calling functions on its 11 containing objects that extend com.google.protobuf.GeneratedMessage.ExtendableMessage. Since all of these 11 objects are extensions of this class, the code block could be be put into the super class, or an extension of the super class, to avoid code duplication. This would cut the CC of the function to approximately 1/5 of what it is now, a reduction of 80 %.
* `clear()` The function consist of 22 blocks with similar patterns again and again. Functional programming paradigm was used to decrease the complexity from 22 to 1 by passing the different part in each pattern to two different helper functions that were created. This different part in each pattern can not be changed without referring to the fields of the object. That is why functional programming paradigm was used.
* `noise(4D)` what makes this function is a lot of if-statements. Most of the function seems to be essential complexity because it seems that they are just calculations essential to get a specific result. An easy way to refactor this function is to refactor the function by grouping the if-statements in different functions to give a better overview of what the algorithm does and reduce the cyclomatic complexity.
* `NetData.ServerInfoMessage.Builder.buildPartial()` consists of a lot of different if-statements, where some of them if-statements are nested and check if a certain builder object is null or not. If they are not, we will enter a new if-statements/branch. Those four if-statements could be moved into new functions called for instance buildComponent(), buildModule(), buildEvent() etc and be replaced by a function call (which takes the ServerInfoMessage object as a parameter that the buildPartial() function has created). We will probably need to use an if-statement to check the output of the function call. However, we will decrease the CC with 4 because we will get rid of one of the nested if-statements in buildPartial().


* `NetData.ServerInfoMessage.Builder.buildPartial()` consists of a lot of different if-statements, where some of them if-statements are nested and check if a certain builder object is null or not. If they are not, we will enter a new if-statements/branch. Those four if-statements could be moved into new functions called for instance buildComponent(), buildModule(), buildEvent() etc and be replaced by a function call (which takes the ServerInfoMessage object as a parameter that the buildPartial() function has created). We will probably need to use an if-statement to check the output of the function call. However, we will decrease the CC with 4 because we will get rid of one of the nested if-statements in buildPartial().


### Estimated impact of refactoring (lower CC, but other drawbacks?).
* `centerPointForNormal(Vector3f) ` The estimated impact of refactoring is M = B-D+1 = 12-6 +1 = 7. Original complexity where 19, which would result in a (19-7)/19 = 0.64 = 63% reduction of cyclomatic complexity.
* `clear()` the estimated impact of refactoring is to reduce the cyclomatic omplexity to nearly 1 because it is mostly repeated patterns. So, the impact is nearly 100 % reduction of cyclomatic complexity.
* `noise(4D)` estimated impact by grouping the if-statements in different functions could be to reduce the complexity to 1. The reason is that if there is a natural grouping of operations in the algorithm, it would be easy to create different functions that handle different parts of the calculations and results and accumulate the results in the main calling method.
* `NetData.ServerInfoMessage.Builder.buildPartial()` The estimated impact of the refactoring above is pi = 22 - 4*2 + 4 (remove the nested if-statments and replace with a single one), s = 1, M = 18 - 1 = 19. Original complexity where 23, which would decrease the cyclomatic complexity with 4/23 = 0,17 = 17%
* `mergeFrom(NetData.NetMessage)` This would cut the CC of the function to approximately 1/5 of what it is now, a reduction of 80 %.

* `NetData.ServerInfoMessage.Builder.buildPartial()` The estimated impact of the refactoring above is pi = 22 - 4*2 + 4 (remove the nested if-statments and replace with a single one), s = 1, M = 18 - 1 = 19. Original complexity where 23, which would decrease the cyclomatic complexity with 4/23 = 0,17 = 17%

### Carried out refactoring (optional)
* `centerPointForNormal(Vector3f) ` All chained if-statements where replaced by a function call. Example if(normal.x==1 && normal.y==0 && normal.z==0) where changed to if(normalPositiveX). After the changes jcoco calculated the cyclomatic complexity to 7 which is the same as the estimated reduction of complexity.
* `clear()` The function consist of 22 blocks with similar patterns again and again. Functional programming paradigm was used to decrease the complexity from 22 to 1 by passing the different part in each pattern to two different helper functions that were created. This different part in each pattern can not be changed without referring to the fields of the object. That is why functional programming paradigm was used.

## Overall experience

What are your main take-aways from this project? What did you learn?

* How to calculate and decide the cyclomatic complexity by using different formulas
* How branch coverage is calculated and measured
* Implement external tools and Open Source Projects for different OS

Is there something special you want to mention here?

## Contributions

Edvin Ardö:
* AdHocNetData, which are used for isInitalized() and mergeFrom()
* Created four different tests in the test class NetDataTest
* Documentation, answered the questions in the README and code documentation.


Marcus Jonsson Ewerbring:
* AdHocAABB, which are used for centerPointForNormal() and normalForPlaneClosestToOrigin()
* Created four different tests in the test class AABBTest
* Documentation, answered the questions in the README and code documentation.


Johanna Iivanainen:
* AdHocBuildPartial, AdHocServerInfoMessageIsInitialized, which are used for buildPartial() and isInitalized()
* Created two tests in NetData and two tests in AABBTest
* Documentation, answered the questions in the README and code documentation.


George Rezkalla:
* BranchCoverageSimplesNoiseNoiseMethod, BranchCoverageNetDataNetMessageBuilderClearMethod which are used for noise() and clear()
* Created two tests in SimplexNoiseTest and two tests in NetDataTest
* Documentation, answered the questions in the README and code documentation.
