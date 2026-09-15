# [Homework 9: Map Implementation on Queue][hw9]

- **Andrew Bilyeu**: <!-- TODO: fill with first and last name (e.g., Brutus Buckeye) -->
- **bilyeu.14**: <!-- TODO: fill with OSU dot number (e.g., buckeye.17) -->
- **09/15 @1:50pm EST**: <!-- TODO: fill out with due date and time (e.g., 10/17 @ 3:10 PM EST) -->

## Preparation

Previous students would have wanted you to know the following
before you get started (based on 9 reviews):

- Estimated time to complete the assignment: 0.944 hours
- Most common emotion before starting the assignment: Anxiety (4 reviews)
- Most common emotion while completing the assignment: Boredom (5 reviews)
- Most common emotion after completing the assignment: Gratitude (4 reviews)

If the information above is incomplete, you can help by [providing
your own feedback][feedback-form] after completing this assignment.

## Problems

**This homework is necessary preparation for the lab.** Make sure you
type your answers in files you bring to the lab so that you will not
have to waste time entering your code during the lab.

### Problem 1

> Implement the following method that, given a queue of Map.Pair<K, V>
> and a key of type K, searches for a pair with the given key in the
> given queue and, if it finds it, moves that pair to the front of the
> queue.

```java
/**
 * Finds pair with first component {@code key} and, if such exists, moves it
 * to the front of {@code q}.
 *
 * @param <K>
 *            type of {@code Pair} key
 * @param <V>
 *            type of {@code Pair} value
 * @param q
 *            the {@code Queue} to be searched
 * @param key
 *            the key to be searched for
 * @updates q
 * @ensures <pre>
 * perms(q, #q)  and
 * if there exists value: V (<(key, value)> is substring of q)
 *  then there exists value: V (<(key, value)> is prefix of q)
 * </pre>
 */
private static <K, V> void moveToFront(Queue<Pair<K, V>> q, K key) {
        assert q != null : "Violation of: q is not null";
        assert key != null : "Violation of: key is not null";

        // TODO - fill in body
        Queue<Pair<K, V>> temp = q.newInstance();
        Pair<K, V> keyMatch = null;
        while (q.length() != 0) {
            Pair<K, V> qKey = q.dequeue();
            if (qKey.key().equals(key)) {
                keyMatch = qKey;
            } else {
                temp.enqueue(qKey);
            }
        }
        q.enqueue(keyMatch);
        q.append(temp);
    }
```

> Note that moveToFront is a static, generic method: it is parameterized
> by the types K and V of the Map.Pair<K, V> entries in the queue. You can
> use the types K, V, and Pair<K, V> wherever you need to declare a variable
> that refers to an object of any of these types.
>
> Pay attention to the contract. There is no requires clause. If you have
> trouble reading and understanding the ensures clause, be sure to ask for help.
>
> If you need to construct a Pair object in your code (it should not be necessary
> in moveToFront, but you will need it in the lab), just use the only available
> implementation of the Map.Pair interface called MapSecondary.SimplePair<K, V>.
> Inside the Map2 class, you will be able to declare and initialize a Pair
> variable with a statement like this:

```java
Pair<K, V> p = new SimplePair<>(key, value);
```

> where key and value are some variables of type K and V, respectively.

### Problem 2

> Develop a complete test plan for the Map constructor and kernel methods:
> add, remove, removeAny, value, hasKey, and size and enter them in [MapTest][map-test].

```java
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    Map<String, String> newMap = new Map1L<String, String>();
    return newMap;
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    @Test
    public void constructorTestTest() {
        Map<String, String> constTest = this.constructorTest();
        Map<String, String> constRef = this.constructorRef();

        assertEquals(constRef, constTest);
    }
    @Test
    public void addTest() {
        Map<String, String> addTester = this.createFromArgsTest();
        Map<String, String> addExpected = this.createFromArgsRef("ktest", "valTest");

        addTester.add("ktest", "valTest");

        assertEquals(addExpected, addTester);
    }
    @Test
    public void removeTest() {
        Map<String, String> remTest = this.createFromArgsTest("ktest", "valTest");
        Map<String, String> remExpected = this.createFromArgsRef();

        Map.Pair<String, String> removedPair = remTest.remove("ktest");

        assertEquals("ktest", removedPair.key());
        assertEquals("valTest", removedPair.value());
        assertEquals(remExpected, remTest);
    }
    @Test
    public void removeAnyTest(){
        Map<String, String> remAnyTest = this.createFromArgsTest("ktest",
        "valTest", "test2", "valTest2", "test3", "valTest3");
        Map<String, String> remAnyExpected = this.createFromArgsRef("ktest",
        "valTest", "test2", "valTest2", "test3", "valTest3");

        Map.Pair<String, String> remAnyPair = remAnyTest.removeAny();

        remAnyExpected.remove(remAnyPair.key());

        assertEquals(remAnyExpected, remAnyTest);
    }
    @Test
    public void valueTest() {
        Map<String, String> valTest = this.createFromArgsTest("ktest", "valTest");
        Map<String, String> valExpected = this.createFromArgsRef("ktest", "valTest");

        String valTestPair = valTest.value("ktest");
        String valExpPair = valExpected.value("ktest");

        assertEquals(valExpPair, valTestPair);
    }
    @Test
    public void hasKeyTest() {
        Map<String, String> valTest = this.createFromArgsTest("ktest", "valTest");
        Map<String, String> valExpected = this.createFromArgsRef("ktest", "valTest");

        boolean valTestKey = valTest.hasKey("ktest");
        boolean valExpKey = valExpected.hasKey("ktest");

        assertEquals(valExpKey, valTestKey);

    }
    @Test
    public void sizeTest() {
        Map<String, String> sizeTest = this.createFromArgsTest("ktest", "valTest");
        Map<String, String> sizeExpected = this.createFromArgsRef("ktest", "valTest");

        int lenOfST = sizeTest.size();
        int lenOfSE = sizeExpected.size();

        assertEquals(lenOfSE, lenOfST);
    }


}
```

### Problem 3

> Complete (and print with your homework) the following tracing table.
> We care greatly that you use correct punctuation when you write objects'
> values; we care so much that we will dock you points on exams when you
> use wrong punctuation when supplying objects' values to our questions.
> We urge you to practice writing correctly the values of objects. For
> example, after the 4th statement one of the six correct ways of showing
> this value is: m = {("zero", 0), ("negative one", -1), ("one", 1)}. There are
> six (or three factorial, 3!) correct ways because the order in which we
> show the three elements of the set does not matter. Note the use of curly
> braces, rounded parentheses, commas, double quote marks, and the absence
> thereof. As another example, after the 5th statement the only correct value
> for p is: p = ("zero", 0). It is a good idea to consult the mathematical
> models for MapKernel and Map.Pair and make a connection between them and
> the notation used in the correct answers shown here just above. Of course,
> it is also a good idea to consult the contract for each method or constructor
> called.

| Statement                                   | Variable Values    |
| ------------------------------------------- | ------------------ |
| Map<String, Integer> m = new Map1L<>();     |                    |
|                                             | m = `{}`            |
| m.add("one", 1);                            |                    |
|                                             | m = `{("one", 1)}`            |
| m.add("zero", 0);                           |                    |
|                                             | m = `{("one", 1), ("zero", 0)}`            |
| m.add("negative one", -1);                  |                    |
|                                             | m = `{("one", 1), ("zero", 0), ("negative one", -1)}`            |
| Pair<String, Integer> p = m.remove("zero"); |                    |
|                                             | m = `{("one", 1), ("negative one", -1)}`<br>p = `("zero", 0)` |
| m.remove("one");                            |                    |
|                                             | m = `{("negative one", -1)}`<br>p = `("zero", 0)` |
| m.add("cipher", p.value());                 |                    |
|                                             | m = `{("negative one", -1), ("cipher", 0)}`<br>p = `("zero", 0)` |
| m.add(p.key(), p.value());                  |                    |
|                                             | m = `{("negative one", -1), ("cipher", 0), ("zero", 0)}`<br>p = `("zero", 0)` |
| m.remove("negative one");                   |                    |
|                                             | m = `{("cipher", 0), ("zero", 0)}`<br>p = `("zero", 0)` |
| m.remove("cipher");                         |                    |
|                                             | m = `{("zero", 0)}`<br>p = `("zero", 0)` |
| p = m.removeAny();                          |                    |
|                                             | m = `{}`<br>p = `("zero", 0)` |

## Submission

If you have completed the assignment using this template, VS Code should
automatically convert the template to a PDF on save. If you're not automatically
getting a PDF, please reach out to the instructor. If you're in a rush to
submit, you may use one of the alternative strategies described in this
[Markdown to PDF guide][markdown-to-pdf-guide]. You may also consider printing
the raw markdown directly. However, do not make a habit of this as the graders
reserve the right to give a zero.

[hw9]: https://cse22x1.engineering.osu.edu/2231/assignments/homeworks/map-on-queue/map-on-queue.html
[feedback-form]: https://forms.gle/qJ1gEM5N1r6X7Poy5
[markdown-to-pdf-guide]: https://therenegadecoder.com/blog/how-to-convert-markdown-to-a-pdf-3-quick-solutions/
[map-test]: https://cse22x1.engineering.osu.edu/2231/assignments/homeworks/map-on-queue/MapTest.java
