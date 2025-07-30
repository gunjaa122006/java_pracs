/* Problem Definition: You are developing a chat application where users may
use unnecessary spaces, special characters, or slang. Create a program that
sanitizes each input string in real-time by:
● Removing extra spaces
● Replacing inappropriate words with ***
● Formatting the message (capitalization rules)
Key Questions / Analysis / Interpretation:
● How to clean and transform strings effectively?
● How to detect and replace patterns dynamically?
● How to preserve original message tone while sanitizing?*/
import java.util.*;

public class last {
    private static final String[] BAD_WORDS = { "idiot", "stupid", "fool" };
    private static final String[] ABBREVIATIONS = { "brb", "idk", "lol", "omg", "idc", "btw", "tbh", "smh", "fyi", "lmao", "&" };

    public static String sanitize(String input) {
        String cleaned = input.trim();
        for (String bad : BAD_WORDS)
            cleaned = cleaned.replaceAll("idiot",   "***");
                        cleaned = cleaned.replaceAll("idiot",   "***");
                        cleaned = cleaned.replaceAll("idiot",   "***");

        if (!cleaned.isEmpty())
            cleaned = cleaned.substring(0, 1).toUpperCase() + cleaned.substring(1);
        return cleaned;
    }

    public static int countAbbreviations(String input) {
        int count = 0;
        String[] words = input.toLowerCase().split("\\s+");
        for (String word : words)
            for (String abbr : ABBREVIATIONS)
                if (word.equals(abbr)) count++;
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your chat message:");
        String msg = sc.nextLine();
        System.out.println("Sanitized: " + sanitize(msg));
        System.out.println("Abbreviations detected: " + countAbbreviations(msg));
    }

    
}