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
    public void constructTest() {
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();

        assertEquals(mExpected, m);
    }

    @Test
    public void addTest() {
        Map<String, String> m = this.createFromArgsTest("courage", "dog");
        Map<String, String> mExpected = this.createFromArgsRef("courage", "dog",
                "garfield", "cat");

        m.add("yellow", "cat");

        assertEquals(mExpected, m);
    }

    @Test
    public void removeTest() {
        Map<String, String> m = this.createFromArgsTest("courage", "dog",
                "garfield", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("garfield",
                "cat");

        m.remove("courage");

        assertEquals(mExpected, m);
    }

    @Test
    public void removeAnyTest() {
        /*
         * Sets up map objects for the tests
         */
        Map<String, String> m = this.createFromArgsTest("courage", "dog",
                "garfield", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("courage", "dog",
                "garfield", "cat");
        Map<String, String> mOriginal = this.createFromArgsTest("courage",
                "dog", "garfield", "cat");
        Map<String, String> mExpectedOriginal = this
                .createFromArgsRef("courage", "dog", "garfield", "cat");

        /*
         * Removes an arbitrary pair and checks if the key of the pair exists in
         * #m and #mExpected. Additionally, makes sure |m| = |#m| - 1 and
         * |mExpected| = |#mExpected| - 1
         */
        Map.Pair<String, String> anyPair1 = m.removeAny();
        Map.Pair<String, String> anyPair2 = mExpected.removeAny();

        String pairKey1 = anyPair1.key();
        String pairKey2 = anyPair2.key();

        Boolean mHasKey = mOriginal.hasKey(pairKey1);
        Boolean mExpectedHasKey = mExpectedOriginal.hasKey(pairKey2);
        Boolean expectedResult = true;

        int size1 = m.size();
        int size2 = mExpected.size();
        int sizeExpected = 1;

        assertEquals(expectedResult, mHasKey);
        assertEquals(expectedResult, mExpectedHasKey);
        assertEquals(sizeExpected, size1);
        assertEquals(sizeExpected, size2);
    }

    @Test
    public void valueTest() {
        Map<String, String> m = this.createFromArgsTest("courage", "dog",
                "garfield", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("courage", "dog",
                "garfield", "cat");

        String value1 = m.value("garfield");
        String value2 = mExpected.value("garfield");
        String valueExpected = "cat";

        assertEquals(valueExpected, value1);
        assertEquals(valueExpected, value2);
    }

    @Test
    public void hasKeyTest() {
        Map<String, String> m = this.createFromArgsTest("courage", "dog",
                "garfield", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("courage", "dog",
                "garfield", "cat");

        boolean result1 = m.hasKey("courage");
        boolean result2 = mExpected.hasKey("courage");
        boolean expectedResult = true;

        assertEquals(expectedResult, result1);
        assertEquals(expectedResult, result2);
    }

    @Test
    public void sizeTest() {
        Map<String, String> m = this.createFromArgsTest("courage", "dog",
                "garfield", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("courage", "dog",
                "garfield", "cat");

        int size1 = m.size();
        int size2 = mExpected.size();
        int sizeExpected = 2;

        assertEquals(sizeExpected, size1);
        assertEquals(sizeExpected, size2);
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

}
