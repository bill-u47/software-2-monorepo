import static org.junit.Assert.*;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

   //Tests the constructor without any passed arguments
    @Test
    public void constructTestEmpty() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(nExpected, n);
    }

    @Test
    public void constructTestInt() {
        NaturalNumber n = this.constructorTest(5);
        NaturalNumber nExpected = this.constructorRef(5);

        assertEquals(nExpected, n);
    }

    @Test
    public void constructTestStr() {
        NaturalNumber n = this.constructorTest("5");
        NaturalNumber nExpected = this.constructorRef("5");

        assertEquals(nExpected, n);
    }

    @Test
    public void constructTestNN() {

        NaturalNumber testNum = new NaturalNumber1L(5);

        NaturalNumber n = this.constructorTest(testNum);
        NaturalNumber nExpected = this.constructorRef(testNum);

        assertEquals(nExpected, n);
    }

    @Test
    public void multiplyBy10Test() {

        NaturalNumber n = this.constructorTest(5);
        NaturalNumber nExpected = this.constructorRef(55);

        n.multiplyBy10(5);

        assertEquals(nExpected, n);
    }

    @Test
    public void multiplyByTenwithZeroTest() {

        NaturalNumber n = this.constructorTest(4);
        NaturalNumber nExpected = this.constructorRef(40);

        n.multiplyBy10(0);

        assertEquals(nExpected, n);
    }


    @Test
    public void divideBy10Test() {

        NaturalNumber n = this.constructorTest(7);
        NaturalNumber nExpected = this.constructorRef(0);

        int result = n.divideBy10();
        int expectedResult = 7;

        assertEquals(nExpected, n);
        assertEquals(expectedResult, result);
    }

    @Test
    public void isZeroNotZeroTest() {

        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);

        boolean result = n.isZero();

        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void isZeroTest() {

        NaturalNumber n = this.constructorTest(57);
        NaturalNumber nExpected = this.constructorRef(57);

        boolean result = n.isZero();

        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

}
