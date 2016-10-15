package com.github.sergeioff.mathematicalExpressionParser.bracketChecker;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class BracketTests {
    private final Map<Bracket, Character> bracketsWithPairs = new HashMap<>();
    private final Map<Bracket, Boolean> bracketTypes = new HashMap<>();

    @Before
    public void initBracketWithPairsList() {
        bracketsWithPairs.put(new Bracket('('), ')');
        bracketsWithPairs.put(new Bracket(')'), '(');
        bracketsWithPairs.put(new Bracket('{'), '}');
        bracketsWithPairs.put(new Bracket('}'), '{');
        bracketsWithPairs.put(new Bracket('['), ']');
        bracketsWithPairs.put(new Bracket(']'), '[');
    }

    @Before
    public void initBracketTypesList() {
        bracketTypes.put(new Bracket('('), true);
        bracketTypes.put(new Bracket(')'), false);
        bracketTypes.put(new Bracket('{'), true);
        bracketTypes.put(new Bracket('}'), false);
        bracketTypes.put(new Bracket('['), true);
        bracketTypes.put(new Bracket(']'), false);
    }

    @Test
    public void pairBracketTest() {
        for (Map.Entry<Bracket, Character> entry : bracketsWithPairs.entrySet()) {
            assertTrue(entry.getValue() == entry.getKey().getPairBracketChar());
        }
    }

    @Test
    public void bracketTypeTest() {
        for (Map.Entry<Bracket, Boolean> entry : bracketTypes.entrySet()) {
            assertTrue(entry.getValue() == entry.getKey().isOpeningBracket());
        }
    }
}
