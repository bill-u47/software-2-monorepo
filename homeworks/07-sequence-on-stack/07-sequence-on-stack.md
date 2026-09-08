# [Homework 7: Sequence Implementation on Stack][hw7]

- **Andrew Bilyeu**: <!-- TODO: fill with first and last name (e.g., Brutus Buckeye) -->
- **bilyeu.14**: <!-- TODO: fill with OSU dot number (e.g., buckeye.17) -->
- **9/8 @ 1:50pm EST**: <!-- TODO: fill out with due date and time (e.g., 10/17 @ 3:10 PM EST) -->

## Preparation

Previous students would have wanted you to know the following
before you get started (based on 10 reviews):

- Estimated time to complete the assignment: 1.005 hours
- Most common emotion before starting the assignment: Hope (3 reviews)
- Most common emotion while completing the assignment: Enjoyment (3 reviews)
- Most common emotion after completing the assignment: Joy & Pride (3 reviews each)

If the information above is incomplete, you can help by [providing
your own feedback][feedback-form] after completing this assignment.

## Problems

**This homework is necessary preparation for the lab.** Make sure you
type your answers in files you bring to the lab so that you will not
have to waste time entering your code during the lab.

### Problem 1

> Implement the following method that, given two stacks and an
> integer, moves entries between the two stacks so that the length
> of the first stack is equal to the given integer. Note that, as
> the ensures clause states, rev(leftStack) * rightStack must not
> be changed by the method.

```java
/**
 * Shifts entries between {@code leftStack} and {@code rightStack}, keeping
 * reverse of the former concatenated with the latter fixed, and resulting
 * in length of the former equal to {@code newLeftLength}.
 *
 * @param <T>
 *            type of {@code Stack} entries
 * @param leftStack
 *            the left {@code Stack}
 * @param rightStack
 *            the right {@code Stack}
 * @param newLeftLength
 *            desired new length of {@code leftStack}
 * @updates leftStack, rightStack
 * @requires <pre>
 * 0 <= newLeftLength  and
 * newLeftLength <= |leftStack| + |rightStack|
 * </pre>
 * @ensures <pre>
 * rev(leftStack) * rightStack = rev(#leftStack) * #rightStack  and
 * |leftStack| = newLeftLength}
 * </pre>
 */
private static <T> void setLengthOfLeftStack(Stack<T> leftStack,
        Stack<T> rightStack, int newLeftLength) {
        int leftLen = leftStack.length();
        Stack<T> leftLenRev = new Stack1L<>();
        leftStack.flip();
        leftLenRev.transferFrom(leftStack);

        while ((leftLen - newLeftLength) != 0) {
            T nextVal = leftLenRev.pop();
            rightStack.push(nextVal);
            leftLen = leftStack.length();
        }
        leftStack.transferFrom(leftLenRev);
        leftStack.flip();
        rightStack.flip();

        }
```

> Note that setLengthOfLeftStack is a static, generic method:
> it is parameterized by the type T of the entries in the stacks.
> You can use the type T wherever you need to declare a variable
> that refers to an object of type T.

### Problem 2

> Develop a complete test plan for the Sequence constructor
> and kernel methods (add, remove, and length) and enter
> them in [SequenceTest][sequence-test].

<!-- TODO: fill out skeleton below -->

```java
import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */

    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    @Test
    /**
     *
     * @updates
     *      Sequence<String> addTest
     * @ensures
     *      Sequence<String> is filled with the given args
     *
     * @param args
     *            the entries for the sequence
     * @return the modified sequence
     *  Sequence<String> = Sequence<String> + args;
     */
    private Sequence<String> addTest(String... args) {
        Sequence<String> addTest = this.createFromArgsTest();
        for (arg : args){
            addTest.add(addTest.length(), arg);
        }
        return addTest;
    }

    @Test
    /**
     *
     * @updates
     *      Sequence<String> removeTest
     * @ensures
     *      Sequence<String> is now empty
     *
     * @param args
     *            the entries for the sequence
     * @return the empty sequence
     *  Sequence<String> removeTest = <>;
     */
    private Sequence<String> remove(String... args) {
        int i = 0;
        Sequence<String> removeTest = this.createFromArgsTest();
        for (String arg : args){
            Iterator<String> it = removeTest.iterator();
            i = 0;
            while (it.hasNext() && !it.next().equals(arg)){
                i = i + 1;
            }
            removeTest.remove(i);
        }
        return removeTest;
    }

    @Test
    /**
     *
     *
     *
     * @param args
     *            the input sequence
     * @return the length of the sequence
     *
     * @ensures
     *      |Sequence<String>| == |#Sequence<String>|
     */
    private int lengthTest(String... args) {
        Sequence<String> lenTest = this.createFromArgsTest(args);
        Iterator<String> it = lenTest.iterator();
        int i = 0;
        while (it.hasNext()){
            it.Next();
            i = i + 1;
        }
        return i;
    }

    // TODO - add test cases for constructor, add, remove, and length

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

[hw7]: https://cse22x1.engineering.osu.edu/2231/assignments/homeworks/sequence-on-stack/sequence-on-stack.html
[feedback-form]: https://forms.gle/qJ1gEM5N1r6X7Poy5
[markdown-to-pdf-guide]: https://therenegadecoder.com/blog/how-to-convert-markdown-to-a-pdf-3-quick-solutions/
[sequence-test]: https://cse22x1.engineering.osu.edu/2231/assignments/homeworks/sequence-on-stack/SequenceTest.java
