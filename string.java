 public class string{
    public static void main (String[] Args){
        String s="Hello Java";
        String s2="Hello";
        String s3= new String("Hello");
        //Returns num of characters in the string 
        System.out.println(s.length());
        //Returns the character at the specified index
        System.out.println(s.charAt(5));
        //Returns substring from the specified index
        System.out.println(s.substring(2));
        //Returns substring from the specified index to the end index
        System.out.println(s.substring(2,7));
        System.out.println(s.toLowerCase());
        //Convert to upper case
        System.out.println(s.toUpperCase());
        //Remove leading and trailing spaces
        System.out.println(s.trim());
        System.out.println(s.equals(s2));
       //Compare two strings
        System.out.println(s.compareTo(s2));
        //Compare two strings ignoring case
        System.out.println(s.equalsIgnoreCase(s2));
        //Checks if string contains the specified sequence of characters
        System.out.println(s.contains("Java"));
        //Check if string starts with the specified prefix
        System.out.println(s.startsWith("Hello"));
        //Check if string ends with the specified suffix
        System.out.println(s.endsWith("Java"));
        //Find the index of the first occurrence of a character
        System.out.println(s.indexOf('J'));
        //Find the index of the last occurrence of a character
        System.out.println(s.lastIndexOf('a'));
        //Replace characters in the string
        System.out.println(s.replace('H', 'h'));
        String st="Apple,Banana,Cherrry";
        //Splits string around matches of regex
        System.out.println(st.replaceAll("[0-6]", "*")); 
        //Splits string around matches of regex and returns an array
        String[] parts = st.split("=");   
    }
 }
