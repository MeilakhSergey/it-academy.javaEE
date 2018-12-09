package javaEE.meilakh;

public class StringDefiner {

    public static String defineContent(String str) {
        if (str == null)
            return "Wrong query";
        String stringDefinition;
        if (str.matches("[+]?\\d+")) {
            stringDefinition = "Phone Number";
        } else if (str.matches(".*@.*")) {
            stringDefinition = "Email";
        } else if (str.toLowerCase().matches("[a-z]+")) {
            stringDefinition = "Family Name";
        } else
            stringDefinition = "ID insurance";
        return stringDefinition;
    }
}
