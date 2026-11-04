import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Dennis Huang
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    @Test
    public final void testAddNotEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "red", "blue", "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "blue", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "red", "blue", "green");

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirst() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "red",
                "blue", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "red");

        m.removeFirst();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testIsInInsertionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "blue", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "red", "blue", "green");

        assertEquals(true, m.isInInsertionMode());
        assertEquals(true, mExpected.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testOrder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "blue", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "red", "blue", "green");

        Comparator<String> order = m.order();
        Comparator<String> orderExpected = m.order();

        assertEquals(orderExpected, order);
    }

    @Test
    public final void testSize() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "blue", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "red", "blue", "green");

        int expectedSize = 3;
        int size1 = m.size();
        int size2 = mExpected.size();

        assertEquals(expectedSize, size1);
        assertEquals(expectedSize, size2);
    }

}
