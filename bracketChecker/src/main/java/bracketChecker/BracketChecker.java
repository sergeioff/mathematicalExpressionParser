package bracketChecker;

import java.util.*;

/**
 * Class that allow determine bracket pairs.
 * @author sergeioff
 */
public class BracketChecker {
    private String line;
    private Map<Integer, Integer> bracketPairs;

    public BracketChecker(String line) {
        if (!isValidLine(line)) {
            throw new IllegalArgumentException("Not a valid line!");
        }

        this.line = line;
        this.bracketPairs = getBracketPairs();
    }

    /**
     * Determines bracket pairs.
     * @return indices of bracket pairs
     */
    public Map<Integer, Integer> getBracketPairs() {
        Map<Integer, Integer> pairs = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);

            if (!Bracket.isBracket(currentChar)) {
                continue;
            }

            Bracket bracket = new Bracket(currentChar);
            if (bracket.isOpeningBracket()) {
                stack.push(i);
            } else {
                int startingBracketIdx = stack.pop();
                pairs.put(startingBracketIdx, i);
            }
        }

        return pairs;
    }

    /**
     * Determines pair for bracket.
     * @param bracketIdx bracket index
     * @return index of pair bracket
     */
    public int findBracketPair(int bracketIdx) {
        Map<Integer, Integer> pairs = getBracketPairs();

        int pairIdx = -1;

        for (Map.Entry<Integer, Integer> entry : pairs.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if (key == bracketIdx) {
                pairIdx = value;
            } else if (value == bracketIdx) {
                pairIdx = key;
            }
        }

        return pairIdx;
    }

    /**
     * Checks line for valid expression
     * @param line line to check
     * @return true - if line is valid
     */
    private boolean isValidLine(String line) {
        Stack<Character> openingBrackets = new Stack<>();
        int foundBrackets = 0;

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);

            if (!Bracket.isBracket(currentChar)) {
                continue;
            }

            foundBrackets++;
            Bracket bracket = new Bracket(currentChar);

            if (bracket.isOpeningBracket()) {
                openingBrackets.push(bracket.getBracketChar());
            } else if ((openingBrackets.size() == 0) || (openingBrackets.pop() != bracket.getPairBracketChar())) {
                    return false;
                }
            }
        return openingBrackets.size() == 0 && foundBrackets != 0;
    }
}
