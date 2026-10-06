import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Dennis Huang
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size
    @Test
    public void constructTest() {
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();

        assertEquals(sExpected, s);
    }

    @Test
    public void addTest() {
        Set<String> s = this.createFromArgsTest("snek");
        Set<String> sExpected = this.createFromArgsRef("snek", "birb");

        s.add("birb");

        assertEquals(sExpected, s);
    }

    @Test
    public void removeTest() {
        Set<String> s = this.createFromArgsTest("snek", "birb", "ears");
        Set<String> sExpected = this.createFromArgsRef("snek", "birb");

        String result = s.remove("ears");
        String expectedResult = "ears";

        assertEquals(sExpected, s);
        assertEquals(expectedResult, result);
    }

    @Test
    public void removeAnyTest() {
        Set<String> s = this.createFromArgsTest("snek", "birb", "ears");
        Set<String> sOriginal = this.createFromArgsRef("snek", "birb", "ears");

        String removedItem = s.removeAny();

        assertTrue(sOriginal.contains(removedItem));

        sOriginal.remove(removedItem);
        assertEquals(sOriginal, s);
    }

    @Test
    public void containsTest() {
        Set<String> s = this.createFromArgsTest("snek", "birb", "ears");
        Set<String> sExpected = this.createFromArgsRef("snek", "birb", "ears");

        boolean result1 = s.contains("snek");
        boolean result2 = sExpected.contains("snek");
        boolean expectedResult = true;

        assertEquals(expectedResult, result1);
        assertEquals(expectedResult, result2);
    }

    @Test
    public void sizeTest() {
        Set<String> s = this.createFromArgsTest("snek", "birb", "ears");
        Set<String> sExpected = this.createFromArgsRef("snek", "birb", "ears");

        int size1 = s.size();
        int size2 = sExpected.size();
        int expectedSize = 3;

        assertEquals(expectedSize, size1);
        assertEquals(expectedSize, size2);
    }

}
