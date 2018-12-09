package number;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class FibbonachiNumberTest {
    @Test
    void checkFibbonachiOutput0() {
        PrintStream save_out = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        FibbonachiNumber fibbonachiNumber = new FibbonachiNumber(0);
        fibbonachiNumber.printOnScreen();
        assertEquals("0\r\n[]\r\n", outputStream.toString());

        System.setOut(save_out);
    }

    @Test
    void checkFibbonachiOutput1() {
        PrintStream save_out = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        FibbonachiNumber fibbonachiNumber = new FibbonachiNumber(1);
        fibbonachiNumber.printOnScreen();
        assertEquals("1\r\n[0]\r\n", outputStream.toString());

        System.setOut(save_out);
    }

    @Test
    void checkFibbonachiOutput2() {
        PrintStream save_out = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        FibbonachiNumber fibbonachiNumber = new FibbonachiNumber(2);
        fibbonachiNumber.printOnScreen();
        assertEquals("1\r\n[0, 1]\r\n", outputStream.toString());

        System.setOut(save_out);
    }

    @Test
    void checkFibbonachiOutput5() {
        PrintStream save_out = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        FibbonachiNumber fibbonachiNumber = new FibbonachiNumber(5);
        fibbonachiNumber.printOnScreen();
        assertEquals("5\r\n[0, 1, 1, 2, 3]\r\n", outputStream.toString());

        System.setOut(save_out);
    }



    @Test
    void fibbonachiNumberNegative() {
        FibbonachiNumber fibbonachiNumber = new FibbonachiNumber(-10);
        assertAll(
                () -> assertEquals(-1, fibbonachiNumber.getIndexOfFibbonachiNumber(), "getIndex"),
                () -> assertEquals(-1, fibbonachiNumber.getFibbonachiNumber(), "getFibbonachiNumber"),
                () -> assertArrayEquals(new long[0], fibbonachiNumber.getPreviousFibbonachiNumbers(), "getArray")
        );
    }

    @Test
    void fibbonachiNumber0() {
        FibbonachiNumber fibbonachiNumber = new FibbonachiNumber(0);
        assertAll(
                () -> assertEquals(0, fibbonachiNumber.getIndexOfFibbonachiNumber(), "getIndex"),
                () -> assertEquals(0, fibbonachiNumber.getFibbonachiNumber(), "getFibbonachiNumber"),
                () -> assertArrayEquals(new long[0], fibbonachiNumber.getPreviousFibbonachiNumbers(), "getArray")
        );
    }

    @Test
    void fibbonachiNumber1() {
        FibbonachiNumber fibbonachiNumber = new FibbonachiNumber(1);
        assertAll(
                () -> assertEquals(1, fibbonachiNumber.getIndexOfFibbonachiNumber(), "getIndex"),
                () -> assertEquals(1, fibbonachiNumber.getFibbonachiNumber(), "getFibbonachiNumber"),
                () -> assertArrayEquals(new long[]{0}, fibbonachiNumber.getPreviousFibbonachiNumbers(), "getArray")
        );
    }

    @Test
    void fibbonachiNumber2() {
        FibbonachiNumber fibbonachiNumber = new FibbonachiNumber(2);
        assertAll(
                () -> assertEquals(2, fibbonachiNumber.getIndexOfFibbonachiNumber(), "getIndex"),
                () -> assertEquals(1, fibbonachiNumber.getFibbonachiNumber(), "getFibbonachiNumber"),
                () -> assertArrayEquals(new long[]{0, 1}, fibbonachiNumber.getPreviousFibbonachiNumbers(), "getArray")
        );
    }

    @Test
    void fibbonachiNumber3() {
        FibbonachiNumber fibbonachiNumber = new FibbonachiNumber(3);
        assertAll(
                () -> assertEquals(3, fibbonachiNumber.getIndexOfFibbonachiNumber(), "getIndex"),
                () -> assertEquals(2, fibbonachiNumber.getFibbonachiNumber(), "getFibbonachiNumber"),
                () -> assertArrayEquals(new long[]{0, 1, 1}, fibbonachiNumber.getPreviousFibbonachiNumbers(), "getArray")
        );
    }

    @Test
    void fibbonachiNumber5() {
        FibbonachiNumber fibbonachiNumber = new FibbonachiNumber(5);
        assertAll(
                () -> assertEquals(5, fibbonachiNumber.getIndexOfFibbonachiNumber(), "getIndex"),
                () -> assertEquals(5, fibbonachiNumber.getFibbonachiNumber(), "getFibbonachiNumber"),
                () -> assertArrayEquals(new long[]{0, 1, 1, 2, 3}, fibbonachiNumber.getPreviousFibbonachiNumbers(), "getArray")
        );
    }
}