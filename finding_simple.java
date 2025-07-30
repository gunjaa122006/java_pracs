/*You are building a utility to scan long input strings to find all occurrences
of a given pattern (substring or regex) with performance optimization. This
is designed for competitive programming inputs with 106+ characters.
● Substring search
● Performance benchmarking
● Regex vs manual search comparison */

import java.util.*;
import java.util.regex.*;

public class finding_simple {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Pattern Finding Utility ===");
        System.out.println("1. Enter your own text");
        System.out.print("Enter the text to search in: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter the pattern to search for: ");
        String pattern = scanner.nextLine();
        
        System.out.println("\nText length: " + text.length());
        System.out.println("Pattern: \"" + pattern + "\"");
        System.out.println("------------------------");
        
        performBenchmark(text, pattern);
        
        System.out.println("\n=== Overlapping Matches ===");
        countOverlappingMatches(text, pattern);
        
        System.out.println("\n=== Pattern Replacement ===");
        String modifiedText = replacePatternWithStar(text, pattern);
        System.out.println("Non-overlapping replacement:");
        System.out.println("Original: " + text);
        System.out.println("Modified: " + modifiedText);
        
        String overlappingReplace = replaceOverlappingWithStar(text, pattern);
        System.out.println("\nOverlapping replacement:");
        System.out.println("Original: " + text);
        System.out.println("Modified: " + overlappingReplace);
        
      
        scanner.close();
    }

    // Method 1: Using String.indexOf() - Built-in Java method
    public static List<Integer> findWithIndexOf(String text, String pattern) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(pattern);
        
        while (index >= 0) {
            positions.add(index);
            index = text.indexOf(pattern, index + 1);
        }
        return positions;
    }

    // Method 2: Manual search - Simple loop
    public static List<Integer> findManual(String text, String pattern) {
        List<Integer> positions = new ArrayList<>();
        
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (text.substring(i, i + pattern.length()).equals(pattern)) {
                positions.add(i);
            }
        }
        return positions;
    }

    // Method 3: Using Pattern and Matcher - Regex approach
    public static List<Integer> findWithRegex(String text, String regexPattern) {
        List<Integer> positions = new ArrayList<>();
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regexPattern);
        java.util.regex.Matcher matcher = pattern.matcher(text);
        
        while (matcher.find()) {
            positions.add(matcher.start());
        }
        
        System.out.printf("Regex '%s' found %d matches%n", regexPattern, positions.size());
        if (!positions.isEmpty()) {
            System.out.println("First few positions: " + 
                positions.subList(0, Math.min(3, positions.size())));
        }
        return positions;
    }

    public static void performBenchmark(String text, String pattern) {
        long start = System.nanoTime();
        List<Integer> result1 = findWithIndexOf(text, pattern);
        long time1 = System.nanoTime() - start;
        
        start = System.nanoTime();
        List<Integer> result2 = findManual(text, pattern);
        long time2 = System.nanoTime() - start;
        
        start = System.nanoTime();
        List<Integer> result3 = findWithRegex(text, java.util.regex.Pattern.quote(pattern));
        long time3 = System.nanoTime() - start;
        
        System.out.printf("indexOf():   %d matches in %.2f ms%n", 
            result1.size(), time1 / 1_000_000.0);
        System.out.printf("Manual:      %d matches in %.2f ms%n", 
            result2.size(), time2 / 1_000_000.0);
        System.out.printf("Regex:       %d matches in %.2f ms%n", 
            result3.size(), time3 / 1_000_000.0);
        
        if (time1 <= time2 && time1 <= time3) {
            System.out.println("Winner: indexOf() method");
        } else if (time2 <= time3) {
            System.out.println("Winner: Manual search");
        } else {
            System.out.println("Winner: Regex search");
        }
        
        System.out.println("Sample positions: " + 
            result1.subList(0, Math.min(5, result1.size())));
    }

    public static void countOverlappingMatches(String text, String pattern) {
        List<Integer> overlappingPositions = new ArrayList<>();
        
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (text.substring(i, i + pattern.length()).equals(pattern)) {
                overlappingPositions.add(i);
            }
        }
        
        System.out.printf("Non-overlapping matches: %d%n", findWithIndexOf(text, pattern).size());
        System.out.printf("Overlapping matches: %d%n", overlappingPositions.size());
        
        if (!overlappingPositions.isEmpty()) {
            System.out.println("All overlapping positions: " + 
                overlappingPositions.subList(0, Math.min(10, overlappingPositions.size())));
            if (overlappingPositions.size() > 10) {
                System.out.println("... and " + (overlappingPositions.size() - 10) + " more");
            }
        }
    }

    public static String replacePatternWithStar(String text, String pattern) {
        List<Integer> positions = findWithIndexOf(text, pattern);
        
        if (positions.isEmpty()) {
            System.out.println("No matches found to replace.");
            return text;
        }
        
        String replacement = "*".repeat(pattern.length());
        
        String replacedText = text.replace(pattern, replacement);
        
        System.out.printf("Replaced %d occurrences of '%s' with '%s'%n", 
            positions.size(), pattern, replacement);
        
        return replacedText;
    }

    public static String replaceOverlappingWithStar(String text, String pattern) {
        char[] textArray = text.toCharArray();
        List<Integer> overlappingPositions = new ArrayList<>();
        
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (text.substring(i, i + pattern.length()).equals(pattern)) {
                overlappingPositions.add(i);
            }
        }
        
        boolean[] toReplace = new boolean[text.length()];
        for (int pos : overlappingPositions) {
            for (int j = 0; j < pattern.length(); j++) {
                toReplace[pos + j] = true;
            }
        }
        
        for (int i = 0; i < textArray.length; i++) {
            if (toReplace[i]) {
                textArray[i] = '*';
            }
        }
        
        System.out.printf("Replaced characters from %d overlapping matches%n", overlappingPositions.size());
        return new String(textArray);
    }

}
