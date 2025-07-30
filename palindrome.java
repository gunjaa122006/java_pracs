/*Problem Definition: Design a utility for a book reading app that
highlights palindrome words/sentences. Read a paragraph and identify all
palindromes—words or full sentences. Ignore punctuation and case.
Key Questions / Analysis / Interpretation:
● What defines a palindrome when ignoring punctuation?
● How can string reversal and normalization be used?
● How to handle nested palindromes? */
import java.util.Scanner;

public class palindrome {
    public static String normalize(String text) {
        return text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    public static boolean isPalindrome(String text) {
        String n = normalize(text);
        return n.equals(new StringBuilder(n).reverse().toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a paragraph:");
        String[] sentences = sc.nextLine().split("\\.\\s*");

        System.out.println("Palindromes found:");
        for (String sentence : sentences) {
            for (String word : sentence.split("\\s+"))
                if (isPalindrome(word)) System.out.println("Word: " + word);
            if (isPalindrome(sentence)) System.out.println("Sentence: " + sentence);
        }
        sc.close();
    }
}
