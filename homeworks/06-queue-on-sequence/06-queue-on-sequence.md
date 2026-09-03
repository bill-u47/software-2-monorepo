# [Homework 6: Queue Implementation on Sequence][hw6]

- **Andrew Bilyeu**: <!-- TODO: fill with first and last name (e.g., Brutus Buckeye) -->
- **bilyeu.14**: <!-- TODO: fill with OSU dot number (e.g., buckeye.17) -->
- **09/02/26 @ 1:50pm EST**: <!-- TODO: fill out with due date and time (e.g., 10/17 @ 3:10 PM EST) -->

## Preparation

Previous students would have wanted you to know the following
before you get started (based on 9 reviews):

- Estimated time to complete the assignment: 0.362 hours
- Most common emotion before starting the assignment: Hope (5 reviews)
- Most common emotion while completing the assignment: Enjoyment (5 reviews)
- Most common emotion after completing the assignment: Gratitude & Joy (4 reviews)

If the information above is incomplete, you can help by [providing
your own feedback][feedback-form] after completing this assignment.

## Problems

**This homework is necessary preparation for the lab.** Make sure you
type your answers in files you bring to the lab so that you will not
have to waste time entering your code during the lab.

### Problem 1

> Carefully review the partial skeleton for [Queue3][queue3] where the
> Queue is represented as a Sequence.

### Problem 2

> Complete the implementation of Queue3 on Sequence in the skeleton
> provided by writing bodies for the kernel methods: enqueue, dequeue,
> and length.

<!-- TODO: fill out skeleton below -->

```java
package components.queue;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code Queue} represented as a {@code Sequence} of entries, with
 * implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @correspondence this = $this.entries
 */
public class Queue3<T> extends QueueSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Entries included in {@code this}.
     */
    private Sequence<T> entries;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.entries = new Sequence1L<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Queue3() {
        this.createNewRep();
    }

    /*
     * Standard methods removed to reduce clutter...
     */

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void enqueue(T x) {
        assert x != null : "Violation of: x is not null";

        this.add(this.entries.length(), x);
        // TODO - fill in body

    }

    @Override
    public final T dequeue() {
        assert this.length() > 0 : "Violation of: this /= <>";

        // TODO - fill in body
        this.entries.remove(0);
        // This line added just to make the component compilable.
        return null;
    }

    @Override
    public final int length() {

        // TODO - fill in body
        this.entries.length();
        // This line added just to make the component compilable.
        return 0;
    }

    /*
     * Iterator removed to reduce clutter...
     */

}
```

### Problem 3

> Complete the body of the secondary instance method front defined in
> interface Queue. front is not a kernel method because it can be implemented
> on top of the existing kernel methods. Layer the implementation of front
> on the QueueKernel (and Standard and Iterable) methods as if you were
> writing it in QueueSecondary.

```java
/**
 * Reports the front of {@code this}.
 *
 * @return the front entry of {@code this}
 * @aliases reference returned by {@code front}
 * @requires this /= <>
 * @ensures <front> is prefix of this
 */
@Override
public T front() {
    assert this.length() > 0 : "Violation of: this /= <>";

    this.entries.entry(0);
    // fill in body

}
```

## Submission

If you have completed the assignment using this template, VS Code should
automatically convert the template to a PDF on save. If you're not automatically
getting a PDF, please reach out to the instructor. If you're in a rush to
submit, you may use one of the alternative strategies described in this
[Markdown to PDF guide][markdown-to-pdf-guide]. You may also consider printing
the raw markdown directly. However, do not make a habit of this as the graders
reserve the right to give a zero.

[hw6]: https://cse22x1.engineering.osu.edu/2231/assignments/homeworks/queue-on-sequence/queue-on-sequence.html
[feedback-form]: https://forms.gle/qJ1gEM5N1r6X7Poy5
[markdown-to-pdf-guide]: https://therenegadecoder.com/blog/how-to-convert-markdown-to-a-pdf-3-quick-solutions/
[queue3]: https://cse22x1.engineering.osu.edu/2231/assignments/homeworks/queue-on-sequence/Queue3.java
