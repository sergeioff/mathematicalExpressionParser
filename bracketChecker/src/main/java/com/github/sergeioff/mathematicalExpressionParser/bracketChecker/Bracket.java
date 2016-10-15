package com.github.sergeioff.mathematicalExpressionParser.bracketChecker;

import java.util.HashMap;

/**
 * @author sergeioff
 */
class Bracket {
    private static final char[] OPENING_BRACKETS = {'(', '{', '['};
    private static final char[] CLOSING_BRACKETS = {')', '}', ']'};

    private static final HashMap<Character, Character> pairs = new HashMap<Character, Character>() {{
        put('(', ')');
        put(')', '(');
        put('{', '}');
        put('}', '{');
        put('[', ']');
        put(']', '[');
    }};

    /**
     * @param character character to check
     * @return true - if character is bracket
     */
    static boolean isBracket(char character) {
        for (char bracket : OPENING_BRACKETS) {
            if (bracket == character) return true;
        }

        for (char bracket : CLOSING_BRACKETS) {
            if (bracket == character) return true;
        }

        return false;
    }

    private char bracketChar;
    private char pairBracketChar;
    private boolean isOpeningBracket;

    Bracket(char character) {
        if (!isBracket(character)) {
            throw new IllegalArgumentException("Not a bracket!");
        }

        this.bracketChar = character;
        this.pairBracketChar = pairs.get(character);
        isOpeningBracket = determineBracketType();
    }

    /**
     * Determines bracket type
     * @return true - opening bracket; false - closing bracket
     */
    private boolean determineBracketType() {
        for (char bracket : OPENING_BRACKETS) {
            if (bracket == bracketChar) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return character that represents this bracket
     */
    char getBracketChar() {
        return bracketChar;
    }

    /**
     * @return pair for this bracket
     */
    char getPairBracketChar() {
        return pairBracketChar;
    }

    /**
     * @return true - opening bracket; false - closing bracket
     */
    boolean isOpeningBracket() {
        return isOpeningBracket;
    }
}
