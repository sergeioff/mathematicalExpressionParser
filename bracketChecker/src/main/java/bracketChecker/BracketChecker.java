package bracketChecker;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Class that allows determine bracket pairs.
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
        this.bracketPairs = determineBracketPairs();
    }

    /**
     * Determines bracket pairs.
     * @return indices of bracket pairs
     */
    private Map<Integer, Integer> determineBracketPairs() {
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
        int pairIdx = -1;

        for (Map.Entry<Integer, Integer> entry : bracketPairs.entrySet()) {
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
     * Checks line for valid expression (all brackets has pairs and opening
     * brackets are going before closing brackets)
     * @param line line to check
     * @return true - if line is valid
     */
    private boolean isValidLine(String line) {
        Stack<Character> openingBrackets = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);

            if (!Bracket.isBracket(currentChar)) {
                continue;
            }

            Bracket bracket = new Bracket(currentChar);

            if (bracket.isOpeningBracket()) {
                openingBrackets.push(bracket.getBracketChar());
            } else if ((openingBrackets.size() == 0) || (openingBrackets.pop() != bracket.getPairBracketChar())) {
                    return false;
                }
            }
        return openingBrackets.size() == 0;
    }

    public Map<Integer, Integer> getBracketPairs() {
        return bracketPairs;
    }
}
