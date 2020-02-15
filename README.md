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

class NetMessage.Builder
* mergeFrom(NetData.NetMessage) : Cxty = 79
* clear(): Cxty = 22
* isInitialized() : Cxty = 44

class NetMessage
* NetData.NetMessage(CodedInputStream, ExtensionRegistryLite) : Cxty = 68
* isInitialized() : Cxty = 46

class ServerInfoMessage
* buildPartial() : Cxty = 23
* isInitialized() : Cxty = 10

class AABB
* centerPointForNormal(Vector3f) : Cxty = 19


We used the formula pi - s + 1 to calculate the cyclomatic complexity, where pi where number of decisions, s number of endpoints. The result we got when we calculated the cyclomatic complexity by hand for four different methods:

class ServerInfoMessage
  * buildPartial()  
    pi = 22  
    s = 1  
    Cxty = 22 - 1 + 2 = 23

class NetData.NetMessage.Builder
  * mergeFrom(NetData.NetMessage)  
    pi = 81  
    s =  2  
    Cxty = 81 - 2 + 2 = 81  

  * clear()  
    pi = 21  
    s = 1  
    Cxty = 21 - 1 + 2 = 22

class AABB
  * centerPointForNormal(Vector3f)  
    pi =  18  
    s =  7  
    Cxty =  18 - 7 + 2 = 13   
    (If we use the formula M = B - D + 1 we get M = 36 - 18 + 1 = 19)

#### What are your results for four complex functions?
##### What are your results? Did everyone get the same result? Is there something that is unclear? If you have a tool, is its result the same as yours?
* `clear()` Cxty = 21 - 1 + 2 = 22. Yes. Everyone got the same results. No. There is nothing unclear. We used Jacoco, and the result was the same.
* `centerPointForNormal(Vector3f) ` Yes everyone got the same result on the complexity. The result is: M = B - D + 1= 36 - 18 +1 = 19. Jacoco: 19. Forumla presented during lecture (gave not the same answer): M = 18 - 7 +2 = 13.
* `mergeFrom(NetData.ServerInfoMessage)` Jacoco cyclomatic complexity = 47.

##### Are the functions/methods with high CC also very long in terms of LOC?
* `clear()` Yes. The number of lines is about 129 lines.
* `centerPointForNormal(Vector3f) ` No it´s not, the lenght is only 20 lines of code.
* `mergeFrom(NetData.ServerInfoMessage)` Yes. 215 lines of code.

##### What is the purpose of these functions? Is it related to the high CC?
* `clear()` This function is related to called by the initialization and clear code paths to allow subclasses to reset any of their builtin fields back to the initial values. The reason for such high CC is that there are a lot of if-statements to check if the fields are not null. If they are null, they are initialized. Otherwise, clear method is called on a field if it is not null. However, there is a lot of code duplication. And, the problem should not have high essential complexity after refactoring.
* `centerPointForNormal(Vector3f) ` The purpose of this function is to compute the centerpoint of six available planes given a normal. I´s understandable that this function has high complexity becuse ther are sevral criterias that needs to be checked. This will result in alot of nested if statments that will increase the complexity.
* `mergeFrom(NetData.ServerInfoMessage)` This function is used by Protocol message builders to merge two messages with each other. The reason for high CC is multiple is statements. However, there is a lot of code duplication which could be refactored.

##### If your programming language uses exceptions: Are they taken into account by the tool? If you think of an exception as another possible branch (to the catch block or the end of the function), how is the CC affected?
* `clear()` As per Jacoco's documentation, "Note that as JaCoCo does not consider exception handling as branches try/catch blocks will also not increase complexity" is said under cyclomatic complexity. If exceptions are considered as branches, the cyclomatic complexity calculated will increase in general. However, in the NetData.NetMessage.Builder.clear(), there are no exceptions thrown. Therefore, the cyclomatic complexity will be the same regardless of whether exceptions are considered as branches or not.
* `centerPointForNormal(Vector3f) ` No exceptions are handled by this function.
* `mergeFrom(NetData.ServerInfoMessage)` As per Jacoco's documentation, "Note that as JaCoCo does not consider exception handling as branches try/catch blocks will also not increase complexity" is said under cyclomatic complexity. If exceptions are considered as branches, the cyclomatic complexity calculated will increase in general. However, in `mergeFrom(NetData.ServerInfoMessage)`, there are no exceptions thrown. Therefore, the cyclomatic complexity will be the same regardless of whether exceptions are considered as branches or not.

##### Is the documentation of the function clear about the different possible outcomes induced by different branches taken?
* `clear()` It is relatively clear. However, I had to read through the code a bit to get an idea about the how the documentation maps to the code.
* `centerPointForNormal(Vector3f) ` No, it´s not clear at all what the function does, the only thing that is documented is what the function does, but not clearly explained.
* `mergeFrom(NetData.ServerInfoMessage)` There is no explicit documentation. I had to look up the method usage, and then look at the other method's documentation (to be technically correct, i had to look at the documentation of the superclass of the method). The using method's documentation is relatively clear. However, i had to read through the code a bit to get an idea about the how the documentation maps to the code.

## Coverage

### Tools

Document your experience in using a "new"/different coverage tool.

How well was the tool documented? Was it possible/easy/difficult to
integrate it with your build environment?

### DYI

Show a patch (or link to a branch) that shows the instrumented code to
gather coverage measurements.

The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:

git diff ...

What kinds of constructs does your tool support, and how accurate is
its output?

### Evaluation

1. How detailed is your coverage measurement?

2. What are the limitations of your own tool?

3. Are the results of your tool consistent with existing coverage tools?

### Coverage improvement

Show the comments that describe the requirements for the coverage.

Report of old coverage: [link]

Report of new coverage: [link]

Test cases added:

git diff ...

## Refactoring

Plan for refactoring complex code:

Estimated impact of refactoring (lower CC, but other drawbacks?).

Carried out refactoring (optional)

git diff ...

## Overall experience

What are your main take-aways from this project? What did you learn?

Is there something special you want to mention here?
