package sample;

import java.util.Scanner;

public class AtBash {
    public static String encode (String text){

        String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int numberOfLetters = ALPHABET.length() - 1;
        String result = "";

        Scanner scan = new Scanner(System.in);
        System.out.println("Write the words you want to encrypt: ");
        String words = scan.nextLine();

        for(int i = 0; i < text.length(); i++){
            char ch = text.charAt(i);
            int charIndex = numberOfLetters - ALPHABET.indexOf(ch);

            if(charIndex < 0){
                result += text.charAt(i);
                continue;
            }
            result += ALPHABET.charAt(charIndex);
        }
        return result;
    }
}