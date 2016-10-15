package com.github.sergeioff.mathematicalExpressionParser.main;

import com.github.sergeioff.mathematicalExpressionParser.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Shows how to use mathematical com.github.sergeioff.parser.
 * @see Parser
 * @author sergeioff
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Hello! Enter mathematical expression:");
            for (String line; !(line = reader.readLine()).equals("exit"); ) {
                try {
                    Parser parser = new Parser(line);
                    System.out.printf("Result of parsing \"%s\":\n%.2f\n", parser.getLine(), parser.compute());
                    System.out.println("For exit enter \"exit\".");
                    System.out.println("Enter a new mathematical expression:");
                } catch (IllegalArgumentException e) {
                    System.err.println("Not a valid line! Please re-enter.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
