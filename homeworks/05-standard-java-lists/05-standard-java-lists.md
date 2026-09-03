# [Homework 5: Standard Java Lists][hw5]

- **Andrew Bilyeu**: <!-- TODO: fill with first and last name (e.g., Brutus Buckeye) -->
- **bilyeu.14**: <!-- TODO: fill with OSU dot number (e.g., buckeye.17) -->
- **09/02 @ 1:50pm EST**: <!-- TODO: fill out with due date and time (e.g., 10/17 @ 3:10 PM EST) -->

## Preparation

Previous students would have wanted you to know the following
before you get started (based on 13 reviews):

- Estimated time to complete the assignment: 0.487 hours
- Most common emotion before starting the assignment: Hope (6 reviews)
- Most common emotion while completing the assignment: Enjoyment (8 reviews)
- Most common emotion after completing the assignment: Joy (5 reviews)

If the information above is incomplete, you can help by [providing
your own feedback][feedback-form] after completing this assignment.

## Problems

Below you'll find the problems for today's assignment.

### Problem 1

> Look up the online documentation for java.util.List, a standard Java
> container component (in the style of our Queue, Stack, Sequence, etc.).

### Problem 2

> Carefully read the following sections of the documentation:
>
> - the top-level description of the List component at the start of the page, and
> - the detailed descriptions of the methods add(E e), remove(int index), get(int index), and size().

### Problem 3

> Complete the following tracing table:

<!-- TODO: replace '?' marks with values -->

| Statement                                              | Variable Values       |
| ------------------------------------------------------ | --------------------- |
| `List<Integer> list = new SomeListImplementation<>();` |                       |
|                                                        | list = `[]`            |
| `list.add(7);`                                         |                       |
|                                                        | list = `[7]`            |
| `list.add(-12);`                                       |                       |
|                                                        | list = `[7,-12]`            |
| `list.add(3);`                                         |                       |
|                                                        | list = `[7,-12,3]`            |
| `int x = list.size();`                                 |                       |
|                                                        | list = `[7,-12,3]`<br>x = `3` |
| `x = list.get(1);`                                     |                       |
|                                                        | list = `[7,-12.3]`<br>x = `-12` |
| `x = list.remove(0);`                                  |                       |
|                                                        | list = `[-12,3]`<br>x = `7` |
| `x = list.remove(1);`                                  |                       |
|                                                        | list = `[-12]`<br>x = `3` |
| `x = list.size();`                                     |                       |
|                                                        | list = `[-12]`<br>x = `1` |

### Problem 4

> You may have observed that the add(E e) and remove(int index)
> methods are marked as optional operations. Briefly discuss the
> benefits vs. pitfalls of this design decision.

<!-- TODO: discuss -->
The benefit is that there's an easy-to-use add method, where you don't need to calculate
the end value of the List every time in order to use List.set(), but it's an unnecessary method to add, as you can just use
the .set() function. That's the more optimized version, most likely. On remove's side, I don't particularly see any pitfalls,
only the ability to remove items (which tends to come in handy).

### Problem 5

> Consider this quote from the java.util.List description:
>
> > Some list implementations have restrictions on the elements that
> > they may contain. For example, some implementations prohibit null
> > elements, and some have restrictions on the types of their elements.
>
> Briefly discuss the benefits vs. pitfalls of this design decision.

<!-- TODO: discuss -->

The benefit is that you're able to have whatever type of element you'd like, including placeholder values,
like if you were to use java to iterate over a CSV for some ungodly reason you'd find nulls, but that's the same
downside. If you're not careful, it is quite possible for inputs to your program to not be sanitized, and if your
functions don't handle all types of inputs, it can break the program.

## Submission

If you have completed the assignment using this template, VS Code should
automatically convert the template to a PDF on save. If you're not automatically
getting a PDF, please reach out to the instructor. If you're in a rush to
submit, you may use one of the alternative strategies described in this
[Markdown to PDF guide][markdown-to-pdf-guide]. You may also consider printing
the raw markdown directly. However, do not make a habit of this as the graders
reserve the right to give a zero.

[hw5]: https://cse22x1.engineering.osu.edu/2231/assignments/homeworks/java-util-list.html
[feedback-form]: https://forms.gle/qJ1gEM5N1r6X7Poy5
[markdown-to-pdf-guide]: https://therenegadecoder.com/blog/how-to-convert-markdown-to-a-pdf-3-quick-solutions/
