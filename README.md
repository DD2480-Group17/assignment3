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
####How easily can you build the project? Briefly describe if everything worked as documented or not:

Did you have to install a lot of additional tools to build the software?
* The only requirements were Java JDK 8 and Gradle 4.

Were those tools well documented?
* Yes.

Were other components installed automatically by the build script?
* `Linux` Yes, all necessary dependencies were downloaded and imported by Gradle.
* `Windows` No, there where a lot of problems with premisision and depricated versions of Java and Gradle. On one windows computer
it was enough to download Gradle 4 and Java 8. On another system the program where not able to compile in IDEA, but it was possible
in CMD and Windows integrated Ubuntu support.
* `Mac` Test and program where not able to run from IDEA, the test could run from Terminal.

Did the build conclude automatically without errors?
* `Linux` No, there was one almost empty Java source file which caused syntax errors. Removing this file solved the issue.
* `Windows` No, 16 build error in IDEA, 3 build errors in CMD and 0 in Ubuntu terminal
* `Mac` No

How well do examples and tests run on your system(s)?

* `Linux` Both the tests and the actual game run well.
* `Windows` The game can be executed form CMD, the test can be executed from Ubuntu terminal, specific tests can be executed from IDEA.
* `Mac` No


## Complexity

1. What are your results for ten complex functions?
   * Did all tools/methods get the same result?
   * Are the results clear?
2. Are the functions just complex, or also long?
3. What is the purpose of the functions?
4. Are exceptions taken into account in the given measurements?
5. Is the documentation clear w.r.t. all the possible outcomes?

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
