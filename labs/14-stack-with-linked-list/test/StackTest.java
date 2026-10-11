import static org.junit.Assert.*;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }
    @Test
    public final void constTest() {
        Stack<String> testT = this.constructorTest();
        Stack<String> testR = this.constructorRef();

        assertEquals(testR, testT);
    }
    @Test
    public final void pushTest() {
        Stack<String> testT = this.createFromArgsTest("1");
        Stack<String> testR = this.createFromArgsRef("2", "1");

        testT.push("2");

        assertEquals(testR, testT);
    }
    @Test
    public final void popTest() {
        Stack<String> testT = this.createFromArgsTest("1", "2");
        Stack<String> testR = this.createFromArgsRef("2");

        String test = testT.pop();


        assertEquals(testR, testT);
        assertEquals("1", test);
    }
    @Test
    public final void lengthTest() {
        Stack<String> testT = this.createFromArgsTest("1", "2");
        Stack<String> testR = this.createFromArgsRef("1", "2");

        int lenT = testT.length();
        int lenR = testR.length();

        assertEquals(testR, testT);
        assertEquals(lenR, lenT);
    }

    // TODO - add test cases for constructor, push, pop, and length

}
