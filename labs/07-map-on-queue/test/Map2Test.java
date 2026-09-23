import static org.junit.Assert.*;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;


/**
 * Customized JUnit test fixture for {@code Map2}.
 */
public class Map2Test extends MapTest {

    @Override
    protected final Map<String, String> constructorTest() {
        return new Map2<String, String>();
    }

    @Override
    protected final Map<String, String> constructorRef() {
        return new Map1L<String, String>();
    }
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i+1]);
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
            map.add(args[i], args[i+1]);
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
    public void removeAnyTest() {
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
