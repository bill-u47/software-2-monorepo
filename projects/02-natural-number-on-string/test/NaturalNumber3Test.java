import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber3;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
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

    //Tests the constructor without any passed arguments
    @Test
    public void testConstructorEmpty() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(nExpected, n);
    }

    //Tests the constructor when a type int is passed as an arguement
    @Test
    public void testConstructorOnInt() {
        NaturalNumber n = this.constructorTest(5);
        NaturalNumber nExpected = this.constructorRef(5);

        assertEquals(nExpected, n);
    }

    //Tests the constructor when a type string is passed as an arguement
    @Test
    public void testConstructorOnString() {
        NaturalNumber n = this.constructorTest("5");
        NaturalNumber nExpected = this.constructorRef("5");

        assertEquals(nExpected, n);
    }

    //Tests the constructor when a naturalNumber object is passed as an arguement
    @Test
    public void testConstructorOnNN() {

        NaturalNumber testNum = new NaturalNumber1L(5);

        NaturalNumber n = this.constructorTest(testNum);
        NaturalNumber nExpected = this.constructorRef(testNum);

        assertEquals(nExpected, n);
    }

    //Tests the multiplyBy10 method with a non zero arguement
    @Test
    public void testMultiplyBy10NonZero() {

        NaturalNumber n = this.constructorTest(5);
        NaturalNumber nExpected = this.constructorRef(55);

        n.multiplyBy10(5);

        assertEquals(nExpected, n);
    }

    //Tests the multiplyBy10 method with zero as an arguement
    @Test
    public void testMultiplyBy10OnZero() {

        NaturalNumber n = this.constructorTest(4);
        NaturalNumber nExpected = this.constructorRef(40);

        n.multiplyBy10(0);

        assertEquals(nExpected, n);
    }

    //Tests the divideBy10 method on a digit(s) > 1 NN
    @Test
    public void testDivideBy10DigitMoreThanOne() {

        NaturalNumber n = this.constructorTest(365);
        NaturalNumber nExpected = this.constructorRef(36);

        int result = n.divideBy10();
        int expectedResult = 5;

        assertEquals(nExpected, n);
        assertEquals(expectedResult, result);
    }

    //Tests the divideBy10 method on a 1 digit NN
    @Test
    public void testDivideBy10SingleDigit() {

        NaturalNumber n = this.constructorTest(7);
        NaturalNumber nExpected = this.constructorRef(0);

        int result = n.divideBy10();
        int expectedResult = 7;

        assertEquals(nExpected, n);
        assertEquals(expectedResult, result);
    }

    //Tests the isZero method on a NN with value of "0"
    @Test
    public void testIsZeroOnZero() {

        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);

        boolean result = n.isZero();

        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    //Tests the isZero method on a NN with value > 0
    @Test
    public void testIsZeroOnNotZero() {

        NaturalNumber n = this.constructorTest(57);
        NaturalNumber nExpected = this.constructorRef(57);

        boolean result = n.isZero();

        assertEquals(nExpected, n);
        assertEquals(false, result);
    }
}