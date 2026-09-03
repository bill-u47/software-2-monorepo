# [Homework 3: Sequence Smooth as a Function][hw3]

- **Name**: <!-- TODO: fill with first and last name (e.g., Brutus Buckeye) -->
- **Dot Number**: <!-- TODO: fill with OSU dot number (e.g., buckeye.17) -->
- **Due Date**: <!-- TODO: fill out with due date and time (e.g., 10/17 @ 3:10 PM EST) -->

## Preparation

Previous students would have wanted you to know the following
before you get started (based on 14 reviews):

- Estimated time to complete the assignment: 1.027 hours
- Most common emotion before starting the assignment: Anxiety (6 reviews)
- Most common emotion while completing the assignment: Enjoyment (5 reviews)
- Most common emotion after completing the assignment: Joy (6 reviews)

If the information above is incomplete, you can help by [providing
your own feedback][feedback-form] after completing this assignment.

## Problems

Consider one more time the following contract specification for the static method smooth.

```java
/**
 * Smooths a given {@code Sequence<Integer>}.
 *
 * @param s1
 *            the sequence to smooth
 * @param s2
 *            the resulting sequence
 *
 * @replaces s2
 * @requires |s1| >= 1
 * @ensures <pre>
 * |s2| = |s1| - 1  and
 *  for all i, j: integer, a, b: string of integer
 *      where (s1 = a * <i> * <j> * b)
 *    (there exists c, d: string of integer
 *       (|c| = |a|  and
 *        s2 = c * <(i+j)/2> * d))
 * </pre>
 */
public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {...}
```

Answer the following questions.

### Problem 1

> Redesign the method so that it is a function that returns the new
> (smoothed) sequence instead of replacing a parameter. You need to
> modify the method header and update the formal contract to reflect
> the changes.
/**
 * Smooths a given {@code Sequence<Integer>}.
 *
 * @param s1
 *            the sequence to smooth
 *
 *
 * @returns s2
                the returned smoothed sequence
 * @requires |s1| >= 1
 * @ensures <pre>
 * |s2| = |s1| - 1  and
 *  for all i, j: integer, a, b: string of integer
 *      where (s1 = a * <i> * <j> * b)
 *    (there exists c, d: string of integer
 *       (|c| = |a|  and
 *        s2 = c * <(i+j)/2> * d))
 * </pre>
 */

### Problem 2

> Provide two distinct implementations of the newly designed smooth
> method, one recursive and one iterative (i.e., not using recursion).
> While you may use method entry, do not use any other method that is
> introduced in the enhanced interface Sequence. Among the methods still
> permitted for your use are all those inherited by or introduced in
> SequenceKernel, including add, remove, and length.

### Recursive
note - I did iterative prior to recursive
        s2.clear();

        if (s1.length() > 1) {
            int int1 = s1.remove(0);
            int int2 = s1.entry(0);
            int average = (int1 + int2) / 2;
            smooth(s1, s2);
            s2.add(0, average);
            s1.add(0, int1);

### Iterative
 clearly I didn't realize I could use entry....

        if (s2.length() < 1){
            s1.clear();
        }
        s2.clear();
        int int1 = s1.remove(0);
        int temp = int1;
        Sequence<Integer> tempSeq = new Sequence1L<Integer>();
        tempSeq.add(tempSeq.length(), int1);

        while (s1.length() >= 1) {
            int int2 = s1.remove(0);
            int average = (temp + int2) / 2;
            s2.add(s2.length(), average);
            temp = int2;
            tempSeq.add(tempSeq.length(), int2);
        }

        s1.transferFrom(tempSeq);
        System.out.println(s1);
        System.out.println(s2);

## Submission

If you have completed the assignment using this template, VS Code should
automatically convert the template to a PDF on save. If you're not automatically
getting a PDF, please reach out to the instructor. If you're in a rush to
submit, you may use one of the alternative strategies described in this
[Markdown to PDF guide][markdown-to-pdf-guide]. You may also consider printing
the raw markdown directly. However, do not make a habit of this as the graders
reserve the right to give a zero.

[hw3]: https://cse22x1.engineering.osu.edu/2231/assignments/homeworks/smooth-function.html
[feedback-form]: https://forms.gle/qJ1gEM5N1r6X7Poy5
[markdown-to-pdf-guide]: https://therenegadecoder.com/blog/how-to-convert-markdown-to-a-pdf-3-quick-solutions/
