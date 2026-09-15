import static org.junit.Assert.*;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber3;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 * @author Dennis Huang
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {

        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {

        return new NaturalNumber3(i);
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {

        return new NaturalNumber3(s);
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {

        return new NaturalNumber3(n);
    }

    @Override
    protected final NaturalNumber constructorRef() {

        return new NaturalNumber1L();
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {

        return new NaturalNumber1L(i);
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {

        return new NaturalNumber1L(s);
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {

        return new NaturalNumber1L(n);
    }
    @Test
    public void constTestEmpty() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(nExpected, n);
    }

    @Test
    public void constTestInt() {
        NaturalNumber n = this.constructorTest(5);
        NaturalNumber nExpected = this.constructorRef(5);

        assertEquals(nExpected, n);
    }

    @Test
    public void constTestStr() {
        NaturalNumber n = this.constructorTest("5");
        NaturalNumber nExpected = this.constructorRef("5");

        assertEquals(nExpected, n);
    }

    @Test
    public void constTestNN() {

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
    public void multiplyByTenWithZeroTest() {

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
    public void isZeroTest() {

        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);

        boolean result = n.isZero();

        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void isZeroNonZeroTest() {

        NaturalNumber n = this.constructorTest(57);
        NaturalNumber nExpected = this.constructorRef(57);

        boolean result = n.isZero();

        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

}
