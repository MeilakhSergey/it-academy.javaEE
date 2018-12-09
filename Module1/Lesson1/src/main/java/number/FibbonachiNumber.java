package number;

import java.util.Arrays;

public class FibbonachiNumber {
    private long indexOfFibbonachiNumber;
    private long fibbonachiNumber;
    private long[] previousFibbonachiNumbers;

    public FibbonachiNumber(int n) {
        setFibbonachiNumber(n);
    }

    public void setFibbonachiNumber(int n) {
        if (n < 0) {
            indexOfFibbonachiNumber = -1;
            fibbonachiNumber = -1;
            previousFibbonachiNumbers = new long[0];
            return;
        }

        indexOfFibbonachiNumber = n;

        if (n == 0) {
            fibbonachiNumber = 0;
            previousFibbonachiNumbers = new long[0];
        } else if (n == 1) {
            fibbonachiNumber = 1;
            previousFibbonachiNumbers = new long[]{0};
        } else {
            previousFibbonachiNumbers = new long[n];
            previousFibbonachiNumbers[0] = 0;
            previousFibbonachiNumbers[1] = 1;
            for (int i = 2; i < n; i++) {
                previousFibbonachiNumbers[i] = previousFibbonachiNumbers[i-1] + previousFibbonachiNumbers[i-2];
            }
            fibbonachiNumber = previousFibbonachiNumbers[n-1] + previousFibbonachiNumbers[n-2];
        }
    }

    public void printOnScreen() {
        System.out.println(fibbonachiNumber);
        System.out.println(Arrays.toString(previousFibbonachiNumbers));
    }

    public long getIndexOfFibbonachiNumber() {
        return indexOfFibbonachiNumber;
    }

    public long getFibbonachiNumber() {
        return fibbonachiNumber;
    }

    public long[] getPreviousFibbonachiNumbers() {
        return previousFibbonachiNumbers;
    }
}
