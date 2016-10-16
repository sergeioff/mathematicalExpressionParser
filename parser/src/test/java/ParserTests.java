import org.junit.Test;
import com.github.sergeioff.mathematicalExpressionParser.parser.Parser;

import static org.junit.Assert.assertEquals;

public class ParserTests {
    @Test
    public void processExpression() {
        assertEquals(402, new Parser("2 + 2   * 200").compute(), 0.0001);
        assertEquals(6, new Parser("2 + 2   * 2").compute(), 0.0001);
        assertEquals(37.5, new Parser("2 +   2  * 4 * ( 5 - (1 + 2)) + 20 * 1.5 - (3.5 * 3)").compute(), 0.0001);
        assertEquals(241.5, new Parser("2 * {(2 +2 / (2) + 3.5 + 1 + (2 * 3) * (20 * 10))} / 10").compute(), 0.0001);
        assertEquals(241.5, new Parser("[2 * {(2 +2 / (2) + 3.5 + 1 + (2 * 3) * (20 * 10))} / 10]").compute(), 0.0001);
        assertEquals(0, new Parser("0").compute(), 0.0001);
        assertEquals(-99, new Parser("(-99)").compute(), 0.0001);
        assertEquals(2, new Parser("1 - (-1)").compute(), 0.0001);
        assertEquals(2, new Parser("1 --1").compute(), 0.0001);
        assertEquals(2, new Parser("1 - -1").compute(), 0.0001);
        assertEquals(-2, new Parser("2 + 2 * -2").compute(), 0.0001);
        assertEquals(100 % 6, new Parser("100 % 6").compute(), 0.0001);
        assertEquals(1024, new Parser("2 ^ 10").compute(), 0.0001);
        assertEquals(512, new Parser("2 ^ (10 - 1)").compute(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void willFail() {
        new Parser("a").compute();
    }

    @Test(expected = IllegalArgumentException.class)
    public void willFail1() {
        new Parser("1 + \2312").compute();
    }

    @Test(expected = IllegalArgumentException.class)
    public void willFail2() {
        new Parser("2 +2 +").compute();
    }

    @Test(expected = IllegalArgumentException.class)
    public void willFail3() {
        new Parser("2 ++2").compute();
    }
}
