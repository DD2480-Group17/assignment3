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
In the following section the experience of using the project Terasology is explained. The experiance where different depending
on the operativsystem of the used computer.

#### How easily can you build the project? Briefly describe if everything worked as documented or not:

Did you have to install a lot of additional tools to build the software?
* The only requirements were Java JDK 8 and Gradle 4.

Were those tools well documented?
* Yes.

Were other components installed automatically by the build script?
* `Linux` Yes, all necessary dependencies were downloaded and imported by Gradle.
* `Windows` No, there where a lot of problems with permission and deprecated versions of Java and Gradle. On one windows computer, it was enough to download Gradle 4 and Java 8. On another system the program was not able to compile in IDEA, but it was possible in CMD and Windows integrated Ubuntu support.
* `Mac` Test and program was not able to run from IDEA, the test could run from Terminal.

Did the build conclude automatically without errors?
* `Linux` No, there was one almost empty Java source file which caused syntax errors. Removing this file solved the issue.
* `Windows` No, 16 build error in IDEA, 3 build errors in CMD and 0 in Ubuntu terminal.
* `Mac` No.

How well do examples and tests run on your system(s)?
* `Linux` Both the tests and the actual game run well.
* `Windows` The game can be executed form CMD, the test can be executed from Ubuntu terminal, specific tests can be executed from IDEA.
* `Mac` No.

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

#### class SimplexNoise
* noise(float x, float y, float z) : Cxty = 10

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
* `buildPartial()` Cxty = 22 - 1 + 2 = 23. Yes,everyone got the same results. JaCoCo got the same result as we where Cxty = 23.
* `mergeFrom(NetData.NetMessage)` Cxty = 78 - 2 + 2 = 78. Yes, everyone got the same results. When measured with JaCoCo and lizard the CC was 79. This has to do with the fact that these tools don't decrease the CC with multiple exit points.

##### Are the functions/methods with high CC also very long in terms of LOC?
* `clear()` Yes. The number of lines is about 129 lines.
* `centerPointForNormal(Vector3f) ` No it´s not, the length is only 20 lines of code.
* `mergeFrom(NetData.ServerInfoMessage)` Yes. 215 lines of code.
* `buildPartial()` Yes. 110 lines of code.
* `NetData.ServerInfoMessage.Builder.ìsInitialized()` No it's not. The length of the function is 34 lines of code.
* `normalForPlaneClosestToOrigin() ` No it´s not, the length is only 37 lines of code.
* `mergeFrom(NetData.NetMessage)` Yes, 325 lines of code.
* `NetData.NetMessage.Builder.ìsInitialized()` Yes, 111 lines of code.
* `noise(float x, float y, float z)` Yes, 150 lines of code.

##### What is the purpose of these functions? Is it related to the high CC?
* `clear()` This function is related to called by the initialization and clear code paths to allow subclasses to reset any of their builtin fields back to the initial values. The reason for such high CC is that there are a lot of if-statements to check if the fields are not null. If they are null, they are initialized. Otherwise, clear method is called on a field if it is not null. However, there is a lot of code duplication. And, the problem should not have high essential complexity after refactoring.
* `centerPointForNormal(Vector3f) ` The purpose of this function is to compute the centerpoint of six available planes given a normal. I´s understandable that this function has high complexity because there are several criterias that needs to be checked. This will result in alot of nested if statments that will increase the complexity.
* `mergeFrom(NetData.ServerInfoMessage)` This function is used by Protocol message builders to merge two messages with each other. The reason for high CC is multiple is statements. However, there is a lot of code duplication which could be refactored.
* `buildPartial()` The purpose of this function is to change the values for the bit fields "to" and "from", and to set the different parts of the ServerInfoMessage. The reason for such high CC is that there are a lot of different parts of the ServerInfoMessage that needs to be set, and to do that they have constructed several if-statements that need to be checked before they return the ServerInfoMessage.
* `NetData.ServerInfoMessage.Builder.ìsInitialized()` The purpose of the method is to check that all components in the class is initialized. If all components and modules are initialized, the method returns true. Otherwise, the method returns false. There are a couple of if-statements in the method that contribute to the high CC. The if-statements could have been split up into different methods (because they are checking different initialization) to decrease the CC.
* `normalForPlaneClosestToOrigin() ` The purpose of this function is return the normal of the plane that is closest to the input point. The planes tested are specified as min and max, where the normal can be returned in x, y and z direction. The function can return several normals if the user is interested in more then one plane.
* `mergeFrom(NetData.NetMessage)` The purpose of this function is to merge a NetMessage.Builder object with a NetMessage object, to create a new NetMessage.Builder object. It has high CC because of ridiculous code duplication. It consists of the same block of code duplicated 11 times, in addition to 11 other if-statements.
* `NetData.NetMessage.Builder.ìsInitialized()` The purpose of this function is to check if all components of the NetMessage.Builder object have been initialized. The reason for the high CC is that it consists of a large amount of if-statements.
* `noise(float x, float y, float z)` The purpose of this function is to return a noise value in the interval [-1,1] by using an algorithm to decide the simplex. The reason for the high CC is that it consists of several if-statements.

##### If your programming language uses exceptions: Are they taken into account by the tool? If you think of an exception as another possible branch (to the catch block or the end of the function), how is the CC affected?
* `clear()` As per JaCoCo's documentation, "Note that as JaCoCo does not consider exception handling as branches try/catch blocks will also not increase complexity" is said under cyclomatic complexity. If exceptions are considered as branches, the cyclomatic complexity calculated will increase in general. However, in the NetData.NetMessage.Builder.clear(), there are no exceptions thrown. Therefore, the cyclomatic complexity will be the same regardless of whether exceptions are considered as branches or not.
* `centerPointForNormal(Vector3f) ` No exceptions are handled by this function.
* `mergeFrom(NetData.ServerInfoMessage)` As per JaCoCo's documentation, "Note that as JaCoCo does not consider exception handling as branches try/catch blocks will also not increase complexity" is said under cyclomatic complexity. If exceptions are considered as branches, the cyclomatic complexity calculated will increase in general. However, in `mergeFrom(NetData.ServerInfoMessage)`, there are no exceptions thrown. Therefore, the cyclomatic complexity will be the same regardless of whether exceptions are considered as branches or not.
* `buildPartial()` No exception are handled by this function. Therefore, the cyclomatic complexity will be the same.
* `NetData.ServerInfoMessage.Builder.ìsInitialized()` No exception are handled by this function. Therefore, the cyclomatic complexity will be the same.
* `normalForPlaneClosestToOrigin() ` The function does not use exceptions.
* `mergeFrom(NetData.NetMessage)` The function does not throw any exceptions.
* `NetData.NetMessage.Builder.ìsInitialized()` The function does not throw any exceptions.
* `noise(float x, float y, float z)` The function does not throw any exceptions.

##### Is the documentation of the function clear about the different possible outcomes induced by different branches taken?
* `clear()` It is relatively clear. However, I had to read through the code a bit to get an idea about the how the documentation maps to the code.
* `centerPointForNormal(Vector3f) ` No, it´s not clear at all what the function does, the only thing that is documented is what the function does, but not clearly explained.
* `mergeFrom(NetData.ServerInfoMessage)` There is no explicit documentation. I had to look up the method usage, and then look at the other method's documentation (to be technically correct, i had to look at the documentation of the superclass of the method). The using method's documentation is relatively clear. However, i had to read through the code a bit to get an idea about the how the documentation maps to the code.
* `buildPartial()` There is no explicit documentation for this function. I had to read through several parts of the code to be able to understand what the functions does.
* `NetData.ServerInfoMessage.Builder.ìsInitialized()` There is no explicit documentation for this function. However, the function is relatively clear anyway.
* `normalForPlaneClosestToOrigin() ` The documentation is understandable, however it does not state that several normals can be returned.
* `mergeFrom(NetData.NetMessage)` There is no documentation available, and since the function is ovver 300 LOC and consists of bitwise operations it is very difficult to comprehend.
* `NetData.NetMessage.Builder.ìsInitialized()` There is no documentation available, but since the function only returns a boolean value it is yet quite comprehensible.
* `noise(float x, float y, float z)` There is some documentation available and the authors of the function have used Javadoc. However, they have not described the different possible outcomes induced by the different branches. 

## Coverage

### Tools

#### Document your experience in using a "new"/different coverage tool. How well was the tool documented? Was it possible/easy/difficult to integrate it with your build environment?

We decided to use Jcoco because it works well with the IDE Itellij which the product where developed in. In general no one in the group had larger issues
related to Jcoco because it was already integrated in IntelliJ. The only problem related to Jcoco was if the project was incorrectly installed on
IntelliJ, then Jcoco could potentially miss braches or set all branch coverage to 0. However, we considered this to be an difficulty in getting the 
program running rather then it being hard to use Jcoco. 



### DYI

Show a patch (or link to a branch) that shows the instrumented code to
gather coverage measurements.

The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:

git diff ...

What kinds of constructs does your tool support, and how accurate is
its output?

### Evaluation

We decided to split the adhoc tool to be specific for every class. The adhoc classes used are `AdHocAABB`, `AdHocBuildPartial` and `AdHocSimplexNoise`.
1. How detailed is your coverage measurement?
* `AdHocAABB` The coverage is limited to check if the method enters a if statement or loop. If the if-statement is chained (if(a && b && c)) the adhoc tool
counts this as one branch and not three. This is because we did not want to alter the structure of the code.

2. What are the limitations of your own tool?
* `AdHocAABB` It counts all chained if-statement as one if-statement.

3. Are the results of your tool consistent with existing coverage tools?
* `AdHocAABB` No, because Jcoco looks at the assembler code to be able to see chained if-statements.

### Coverage improvement

Show the comments that describe the requirements for the coverage.

Report of old coverage: [link]

Report of new coverage: [link]

Test cases added:

Tests related to the class AABB.java
* `testCenterPointForNormalInXDirection `

* `testCenterPointForNormalMovedXAngledInYDirection `

* `testCenterPointForNormalNotUnitvectorNomal `

* `testNormalForPlaneClosestToOrigin `


git diff ...

## Refactoring

### Plan for refactoring complex code:
* `centerPointForNormal(Vector3f) ` The function has a complexity of 19 and are dependent chained if statemants. To lower
the complexity the chained if statements could be moved to a function. This should reduce the number of branches by 4 for each
statement which would result in 6*4 = 24 less branches.

* `mergeFrom(NetData.NetMessage)` has a complexity of 79, and consists of the same block of code duplicated 11 times,
in addition to 11 smaller if statements. The larger block consists of doing bitwise operations with different values and
calling functions on its 11 containing objects that extend com.google.protobuf.GeneratedMessage.ExtendableMessage. Since
all of these 11 objects are extensions of this class, the code block could be be put into the super class, or an
extension of the super class, to avoid code duplication. This would cut the CC of the function to approximately 1/5 of
what it is now, a reduction of 80 %.

### Estimated impact of refactoring (lower CC, but other drawbacks?).
* `centerPointForNormal(Vector3f) ` The estimated impact of refactoring is M = B-D+1 = 12-6 +1 = 7. Original complexity where 19,
which would result in a (19-7)/19 = 0.64 = 63% reduction of cyclomatic complexity.

### Carried out refactoring (optional)

* `centerPointForNormal(Vector3f) ` All chained if-statements where replaced by a function call. Example if(normal.x==1 && normal.y==0 && normal.z==0) 
where changed to if(normalPositiveX). After the changes jcoco calculated the cyclomatic complexity to 7 which is the same as the
estimated reduction of complexity.

## Overall experience

What are your main take-aways from this project? What did you learn?

Is there something special you want to mention here?
