package bracketChecker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BracketCheckerTests {
    @Test
    public void findPairBracketTest() {
        BracketChecker bracketChecker = new BracketChecker("((()))");
        assertEquals(5, bracketChecker.findBracketPair(0));
        assertEquals(4, bracketChecker.findBracketPair(1));
        assertEquals(3, bracketChecker.findBracketPair(2));

        assertEquals(0, bracketChecker.findBracketPair(5));
        assertEquals(1, bracketChecker.findBracketPair(4));
        assertEquals(2, bracketChecker.findBracketPair(3));

        bracketChecker = new BracketChecker("((a+b)-(c-a))");
        assertEquals(5, bracketChecker.findBracketPair(1));
        assertEquals(11, bracketChecker.findBracketPair(7));
        assertEquals(12, bracketChecker.findBracketPair(0));

        assertEquals(1, bracketChecker.findBracketPair(5));
        assertEquals(7, bracketChecker.findBracketPair(11));
        assertEquals(0, bracketChecker.findBracketPair(12));

        bracketChecker = new BracketChecker("[{(a + b) * (c + d)} * a + [a]]");
        assertEquals(30, bracketChecker.findBracketPair(0));
        assertEquals(19, bracketChecker.findBracketPair(1));
        assertEquals(8, bracketChecker.findBracketPair(2));
        assertEquals(18, bracketChecker.findBracketPair(12));
        assertEquals(29, bracketChecker.findBracketPair(27));

        assertEquals(0, bracketChecker.findBracketPair(30));
        assertEquals(1, bracketChecker.findBracketPair(19));
        assertEquals(2, bracketChecker.findBracketPair(8));
        assertEquals(12, bracketChecker.findBracketPair(18));
        assertEquals(27, bracketChecker.findBracketPair(29));
    }

    @Test(expected = IllegalArgumentException.class)
    public void notValidLineTest() {
        BracketChecker willFail = new BracketChecker("(()");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notValidLineTest1() {
        BracketChecker willFail = new BracketChecker(")(");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notValidLineTest2() {
        BracketChecker willFail = new BracketChecker("{(})");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notValidLineTest3() {
        BracketChecker willFail = new BracketChecker("a");
    }
}
