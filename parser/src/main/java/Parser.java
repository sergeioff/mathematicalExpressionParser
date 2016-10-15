import bracketChecker.BracketChecker;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Parses a line with mathematical expression
 * @author sergeioff
 */
public class Parser {
    private final Pattern validCharsPattern = Pattern.compile("[^0-9+-/*.(){}\\[\\] ]");
    private final String[] OPERATORS = {"+", "-", "*", "/"};
    private String line;
    private StringBuilder lineToParse;

    public Parser(String line) {
        if (!isValidLine(line)) {
            throw new IllegalArgumentException("Invalid line!");
        }

        this.line = line;
        this.lineToParse = new StringBuilder(line);
        removeSpaces();
    }

    /**
     * Computes a mathematical expression.
     * Uses BracketCheker class to extract values from nested brackets
     * @see BracketChecker
     * @return result of processing
     */
    public double compute() {
        int bracketPairsCount;
        do {
            BracketChecker bracketChecker = new BracketChecker(lineToParse.toString());
            TreeMap<Integer, Integer> brackets = new TreeMap<>(Comparator.reverseOrder());
            brackets.putAll(bracketChecker.getBracketPairs());

            bracketPairsCount = brackets.size();

            int lastEndIdx = 0;
            int lastStartIdx = 0;

            for (Map.Entry<Integer, Integer> entry : brackets.entrySet()) {
                int startIdx = entry.getKey();
                int endIdx = entry.getValue();
                if (startIdx < lastStartIdx && endIdx > lastEndIdx) {
                    continue;
                }

                String inBrackets = lineToParse.substring(startIdx + 1, endIdx);

                lastStartIdx = startIdx;
                lastEndIdx = endIdx;

                double value = processSimpleExpression(inBrackets);
                lineToParse.delete(startIdx, endIdx + 1);
                lineToParse.insert(startIdx, value);
            }
        } while (bracketPairsCount != 0);

        return processSimpleExpression(lineToParse.toString());
    }

    public String getLine() {
        return line;
    }

    private boolean isValidLine(String line) {
        return line.length() >= 0 && !validCharsPattern.matcher(line).find();
    }

    private void removeSpaces() {
        int idx;
        while ((idx = lineToParse.indexOf(" ")) >= 0) {
            lineToParse.deleteCharAt(idx);
        }
    }

    /**
     * Processes a line with simple mathematical expression (without brackets)
     * @param line line with simple mathematical expression
     * @return result of processing
     */
    private double processSimpleExpression(String line) {
        StringBuilder expressionBuilder = new StringBuilder(line);
        final String[] PRECEDENCE_OPERATORS = {"*", "/"};

        try {
            for (String operator : PRECEDENCE_OPERATORS) {
                for (int operatorIdx; (operatorIdx = expressionBuilder.indexOf(operator)) != -1; ) {
                    int leftIdx = operatorIdx - 1;
                    while (leftIdx >= 0 && !isOperator(Character.toString(expressionBuilder.charAt(leftIdx)))) {
                        leftIdx--;
                    }

                    Double leftPart = Double.parseDouble(expressionBuilder.substring(++leftIdx, operatorIdx));

                    int rightIdx = operatorIdx + 1;
                    while (rightIdx < expressionBuilder.length() && !isOperator(Character.toString(expressionBuilder.charAt(rightIdx)))) {
                        rightIdx++;
                    }

                    Double rightPart = Double.parseDouble(expressionBuilder.substring(operatorIdx + 1, rightIdx));

                    double value;
                    switch (operator) {
                        case "*":
                            value = leftPart * rightPart;
                            expressionBuilder.delete(leftIdx, rightIdx);
                            expressionBuilder.insert(leftIdx, value);
                            break;
                        case "/":
                            value = leftPart / rightPart;
                            expressionBuilder.delete(leftIdx, rightIdx);
                            expressionBuilder.insert(leftIdx, value);
                            break;
                    }
                }
            }

            StringTokenizer tokenizer = new StringTokenizer(expressionBuilder.toString(), "+-", true);

            double result;

            String token = tokenizer.nextToken();
            if (token.equals("-")) {
                result = 0 - Double.parseDouble(tokenizer.nextToken());
            } else {
                result = Double.parseDouble(token);
            }

            while (tokenizer.hasMoreTokens()) {
                String operator = tokenizer.nextToken();
                String srightOperand = tokenizer.nextToken();
                if (srightOperand.equals("-")) {
                    srightOperand += tokenizer.nextToken();
                }

                double rightOperand = Double.parseDouble(srightOperand);

                switch (operator) {
                    case "+":
                        result += rightOperand;
                        break;
                    case "-":
                        result -= rightOperand;
                        break;
                }
            }

            return result;
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new IllegalArgumentException("Illegal line!");
        }
    }

    private boolean isOperator(String value) {
        for (String operation : OPERATORS) {
            if (operation.equals(value)) return true;
        }

        return false;
    }
}
