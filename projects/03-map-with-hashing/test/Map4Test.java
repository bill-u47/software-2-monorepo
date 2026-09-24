import static org.junit.Assert.*;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;

/**
 * Customized JUnit test fixture for {@code Map4} using default constructor.
 */
public class Map4Test extends MapTest {

    @Override
    protected final Map<String, String> constructorTest() {
        return new Map4<String, String>();
    }

    @Override
    protected final Map<String, String> constructorRef() {
        return new Map1L<String, String>();
    }
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
    @Test
    public void addTest() {
        Map<String, String> m = this.createFromArgsTest("courage", "dog");
        Map<String, String> mExpected = this.createFromArgsRef("courage", "dog",
                "garfield", "cat");

        m.add("garfield", "cat");

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
        Map<String, String> m = this.createFromArgsTest("courage", "dog",
                "garfield", "cat");
        Map<String, String> mOriginal = this.createFromArgsRef("courage", "dog",
                "garfield", "cat");

        Map.Pair<String, String> removed = m.removeAny();

        assertTrue(mOriginal.hasKey(removed.key()));
        assertEquals(mOriginal.value(removed.key()), removed.value());

        mOriginal.remove(removed.key());
        assertEquals(mOriginal, m);
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

