package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        String str = "Hi there! I love learning Java programming language. I hope you enjoy it too.";
        String regex = "\\s+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        // while (matcher.find()) {
        // System.out.println(matcher.start() + "-" + matcher.end());
        // }
        // var words = str.split(regex);
        // showWords(words);
        var newStr = matcher.replaceAll("_");
        // System.out.println(newStr);
        System.out.println(matcher);
    }

    private static void showWords(String[] result) {
        for (var word : result) {
            System.out.println(word);
        }
    }
}
