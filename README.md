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

1. How easily can you build the project? Briefly describe if everything worked as documented or not:

(a) Did you have to install a lot of additional tools to build the software?

The only requirements were JDK 8 and Gradle.

(b) Were those tools well documented?

Yes.

(c) Were other components installed automatically by the build script?

Yes, all necessary dependencies were downloaded and imported by Gradle.

(d) Did the build conclude automatically without errors?

No, there was one almost empty Java source file which caused syntax errors. Removing this file solved the issue.

(e) How well do examples and tests run on your system(s)?

Both the tests and the actual game run well.

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
