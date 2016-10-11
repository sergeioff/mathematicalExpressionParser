package bracketChecker;

import java.util.HashMap;

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

    char getBracketChar() {
        return bracketChar;
    }

    char getPairBracketChar() {
        return pairBracketChar;
    }

    boolean isOpeningBracket() {
        return isOpeningBracket;
    }
}
