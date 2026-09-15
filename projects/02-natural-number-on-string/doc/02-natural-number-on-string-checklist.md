# Project 2 Checklist

Project 2 is NaturalNumber on String. The following information may be
helpful in getting started:

- Estimated time to complete the project (based on 7 reviews): 3.214 hours
- Most common emotion before starting the assignment: Anxiety (4 reviews)
- Most common emotion while completing the assignment: Enjoyment (4 reviews)
- Most common emotion after completing the assignment: Joy & Pride (3 reviews each)
- Link to the project description: [Project 02][project]

When your team is finished, check this list to ensure you have
completed all the tasks for this assignment.

## Getting Started Tasks

- [x] We have followed the recommended approach for getting started
  - [x] We have read this document top to bottom
  - [x] We have reviewed the project instructions for this assignment
  - [x] We have reviewed the rubric for this assignment
  - [x] We have considered the estimated time to complete the assignment
  - [x] We have referred to testing resources that could help us write a systematic test plan
    - [x] We have referred to the [QueueOnSequence lab][queue-on-sequence]
    - [x] We have referred to the [5 Beginner Tricks for Writing Your Own Tests][5-tricks-for-testing] article

## Ongoing Tasks

- [x] We have followed the project instructions
  - [x] We have setup the Eclipse project according to the [project description][project]
  - [x] We have completed the private method `createNewRep()`
  - [x] We have implemented the four NaturalNumber constructors
  - [x] We have implemented all of the kernel methods:
    - [x]  We have completed `multiplyBy10()`
    - [x]  We have completed `divideBy10()`
    - [x]  We have completed `isZero()`
  - [x] We have adhered to the representation invariant listed in the `@convention` tag in our methods
        (i.e., our methods do not modify the representation in a way that violates the convention)
  - [x] We have adhered to the abstraction function listed in the `@correspondence` tag in our methods
        (i.e., our methods do not assume an interpretation of the representation that differs from the correspondence)
- [x] We have tested our project code (**note**: tests may fail if not all kernel methods are implemented)
  - [x] We have completed the bodies of the four `constructorTest()` methods
  - [x] We have completed the bodies of the four `constructorRef()` methods
  - [x] We have developed a systematic test plan for the NaturalNumberKernel implementation (**note**: consider a "0, 1, many" approach)
    - [x] We have tested `multiplyBy10()`
    - [x] We have tested `divideBy10()`
    - [x] We have tested `isZero()`
    - [x] We have tested the four constructors
- [x] We have considered best practices
  - [x] We have added our names in separate `@author` tags
  - [x] We have addressed all spotbugs and checkstyle warnings
  - [x] We have regularly committed changes to the project files using version control with good commit messages
  - [x] We have regularly updated our local copies of the project files using version control and handled any merge conflicts

## Submission Tasks

- [ ] We have followed the submission instructions
  - [ ] We have created a zip archive of our project directory
  - [ ] We have created PDF(s) of our project file(s)
  - [ ] We have submitted the zip archive to Carmen
  - [ ] We have submitted the PDF(s) to Carmen

## Optional Tasks

- [ ] We have filled out the the [feedback form][feedback-form] for this project, rubric, and/or checklist

[feedback-form]: https://forms.gle/qJ1gEM5N1r6X7Poy5
[project]: https://cse22x1.engineering.osu.edu/2231/assignments/projects/natural-number-on-string/natural-number.html
[queue-on-sequence]: https://cse22x1.engineering.osu.edu/2231/extras/instructions/version-control/version-control.html
[5-tricks-for-testing]: https://therenegadecoder.com/code/beginner-tricks-for-writing-your-own-unit-tests/
