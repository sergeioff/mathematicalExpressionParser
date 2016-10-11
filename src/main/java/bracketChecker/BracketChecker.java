package bracketChecker;

import java.util.*;

public class BracketChecker {
    private String line;
    private Map<Integer, Integer> bracketPairs;

    public BracketChecker(String line) {

        this.line = line;
        this.bracketPairs = getBracketPairs();

        if (!isValidLine(line)) {
            throw new IllegalArgumentException("Not a valid line!");
        }
    }

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
                pairs.put(i, startingBracketIdx);
            }
        }

        return pairs;
    }

    public int findBracketPair(int bracketIdx) {
        return getBracketPairs().get(bracketIdx);
    }

    private boolean isValidLine(String line) {
        int openingBracketsCount = 0;

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            if (Bracket.isBracket(currentChar)) {
                Bracket bracket = new Bracket(currentChar);

                if (bracket.isOpeningBracket()) {
                    openingBracketsCount++;
                }
            }
        }

        return openingBracketsCount == bracketPairs.size() / 2;
    }
}
