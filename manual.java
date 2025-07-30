import java.util.*;
import java.util.regex.*;

public class manual {

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
        
        // Performance comparison
        performBenchmark(text, pattern);
        
        // Count overlapping matches
        System.out.println("\n=== Overlapping Matches ===");
        countOverlappingMatches(text, pattern);
        
        // Replace pattern with "*"
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

    public static List<Integer> findWithIndexOf(String text, String pattern) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(pattern);
        
        while (index >= 0) {
            positions.add(index);
            index = text.indexOf(pattern, index + 1);
        }
        return positions;
    }

    public static void performBenchmark(String text, String pattern) {
        // Test indexOf method
        long start = System.nanoTime();
        List<Integer> result1 = findWithIndexOf(text, pattern);
        long time1 = System.nanoTime() - start;
        
        // Test manual method
        start = System.nanoTime();
        List<Integer> result2 = findManual(text, pattern);
        long time2 = System.nanoTime() - start;
        
        // Test regex method
        start = System.nanoTime();
        List<Integer> result3 = findWithRegex(text, java.util.regex.Pattern.quote(pattern));
        long time3 = System.nanoTime() - start;
        
        // Display results
        System.out.printf("indexOf():   %d matches in %.2f ms%n", 
            result1.size(), time1 / 1_000_000.0);
        System.out.printf("Manual:      %d matches in %.2f ms%n", 
            result2.size(), time2 / 1_000_000.0);
        System.out.printf("Regex:       %d matches in %.2f ms%n", 
            result3.size(), time3 / 1_000_000.0);
        
        // Show winner
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

    // Count overlapping matches
    public static void countOverlappingMatches(String text, String pattern) {
        List<Integer> overlappingPositions = new ArrayList<>();
        
        // Find all overlapping matches by advancing only one character at a time
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

    // Replace pattern with "*" in the original string
    public static String replacePatternWithStar(String text, String pattern) {
        List<Integer> positions = findWithIndexOf(text, pattern);
        
        if (positions.isEmpty()) {
            System.out.println("No matches found to replace.");
            return text;
        }
        
        // Create a replacement string with the same length as pattern
        String replacement = "*".repeat(pattern.length());
        
        // Replace using String.replace() method for non-overlapping replacement
        String replacedText = text.replace(pattern, replacement);
        
        System.out.printf("Replaced %d occurrences of '%s' with '%s'%n", 
            positions.size(), pattern, replacement);
        
        return replacedText;
    }

    // Replace all overlapping occurrences with "*"
    public static String replaceOverlappingWithStar(String text, String pattern) {
        char[] textArray = text.toCharArray();
        List<Integer> overlappingPositions = new ArrayList<>();
        
        // Find all overlapping matches
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (text.substring(i, i + pattern.length()).equals(pattern)) {
                overlappingPositions.add(i);
            }
        }
        
        // Mark all positions that are part of any match with '*'
        boolean[] toReplace = new boolean[text.length()];
        for (int pos : overlappingPositions) {
            for (int j = 0; j < pattern.length(); j++) {
                toReplace[pos + j] = true;
            }
        }
        
        // Create result string
        for (int i = 0; i < textArray.length; i++) {
            if (toReplace[i]) {
                textArray[i] = '*';
            }
        }
        
        System.out.printf("Replaced characters from %d overlapping matches%n", overlappingPositions.size());
        return new String(textArray);
    }

}
