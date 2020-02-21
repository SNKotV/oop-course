package com.github.snkotv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Encoder {

    private static int shift;
    private static File input;
    private static File output;
    
    private static class TextInfo {
        public static int smallRus = 0;
        public static int capitalRus = 0;
        public static int smallEng = 0;
        public static int capitalEng = 0;
        public static int digits = 0;
        public static int specials = 0;
        
        public static void print() {
            System.out.println();
            System.out.println("\u0418\u0441\u0445\u043e\u0434\u043d\u044b\u0439\u0020\u0442\u0435\u043a\u0441\u0442\u0020\u0441\u043e\u0434\u0435\u0440\u0436\u0430\u043b");
            System.out.println("\u0417\u0430\u0433\u043b\u0430\u0432\u043d\u044b\u0435\u0020\u0440\u0443\u0441\u0441\u043a\u0438\u0435\u0020\u0431\u0443\u043a\u0432\u044b\u003a\u0020" + capitalRus + "\u0020\u0448\u0442\u002e");
            System.out.println("\u0421\u0442\u0440\u043e\u0447\u043d\u044b\u0435\u0020\u0440\u0443\u0441\u0441\u043a\u0438\u0435\u0020\u0431\u0443\u043a\u0432\u044b\u003a\u0020" +  smallRus + "\u0020\u0448\u0442\u002e");
            System.out.println("\u0417\u0430\u0433\u043b\u0430\u0432\u043d\u044b\u0435\u0020\u043b\u0430\u0442\u0438\u043d\u0441\u043a\u0438\u0435\u0020\u0431\u0443\u043a\u0432\u044b\u003a\u0020" + capitalEng + "\u0020\u0448\u0442\u002e");
            System.out.println("\u0421\u0442\u0440\u043e\u0447\u043d\u044b\u0435\u0020\u043b\u0430\u0442\u0438\u043d\u0441\u043a\u0438\u0435\u0020\u0431\u0443\u043a\u0432\u044b\u003a\u0020" + smallEng + "\u0020\u0448\u0442\u002e");
            System.out.println("\u0426\u0438\u0444\u0440\u044b\u003a\u0020" + digits);
            System.out.println("\u0421\u043f\u0435\u0446\u0441\u0438\u043c\u0432\u043e\u043b\u044b\u003a\u0020" + specials);
        }
    }

    public static void encode() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(input));
        BufferedWriter bw = new BufferedWriter(new FileWriter(output));
        
        String line = "";

        while ((line = br.readLine()) != null) {
            bw.write(encode(line) + '\n');
        }

        br.close();
        bw.close();
    }

    private static String encode(String s) {
        char []chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++)
            switch (chars[i])
            {
                case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': 
                case 'H': case 'I': case 'J': case 'K': case 'L': case 'M': case 'N': 
                case 'O': case 'P': case 'Q': case 'R': case 'S': case 'T': case 'U': 
                case 'V': case 'W': case 'X': case 'Y': case 'Z': 
                                                                TextInfo.capitalEng++;
                                                                break;
                case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': 
                case 'h': case 'i': case 'j': case 'k': case 'l': case 'm': case 'n': 
                case 'o': case 'p': case 'q': case 'r': case 's': case 't': case 'u': 
                case 'v': case 'w': case 'x': case 'y': case 'z': 
                                                                TextInfo.smallEng++;
                                                                break;
                case '\u0410': case '\u0411': case '\u0412': case '\u0413': case '\u0414': case '\u0415': case '\u0416': 
                case '\u0417': case '\u0418': case '\u0419': case '\u041a': case '\u041b': case '\u041c': case '\u041d': 
                case '\u041e': case '\u041f': case '\u0420': case '\u0421': case '\u0422': case '\u0423': case '\u0424': 
                case '\u0425': case '\u0426': case '\u0427': case '\u0428': case '\u0429': case '\u042a': case '\u042b': 
                case '\u042c': case '\u042d': case '\u042e': case '\u042f': case '\u0401':
                                                                TextInfo.capitalRus++;
                                                                int capsNumer = Encoder.getNumberByCode(chars[i], true);
                                                                capsNumer = (capsNumer + shift % 33 + 33) % 33;
                                                                chars[i] = Encoder.getLetterByNumber(capsNumer, true);
                                                                break; 
                case '\u0430': case '\u0431': case '\u0432': case '\u0433': case '\u0434': case '\u0435': case '\u0436': 
                case '\u0437': case '\u0438': case '\u0439': case '\u043a': case '\u043b': case '\u043c': case '\u043d': 
                case '\u043e': case '\u043f': case '\u0440': case '\u0441': case '\u0442': case '\u0443': case '\u0444': 
                case '\u0445': case '\u0446': case '\u0447': case '\u0448': case '\u0449': case '\u044a': case '\u044b': 
                case '\u044c': case '\u044d': case '\u044e': case '\u044f': case '\u0451':
                                                                TextInfo.smallRus++;
                                                                int smallNumer = Encoder.getNumberByCode(chars[i], false);
                                                                smallNumer = (smallNumer + shift % 33 + 33) % 33;
                                                                chars[i] = Encoder.getLetterByNumber(smallNumer, false);
                                                                break;
                case '0': case '1': case '2': case '3': case '4': case '5': case '6': 
                case '7': case '8': case '9':                   
                                                                TextInfo.digits++;
                                                                break;
                default:
                        TextInfo.specials++;         
                              
            }   
        return new String(chars);
    }

    private static int getNumberByCode(char letter, boolean capital) {
        if (capital)    {
            if (letter == '\u0401')
                return 6;
            else if (letter <= '\u0415')
                return letter - '\u0410';
            else 
                return letter - '\u0410' + 1;  
        } else {
            if (letter == '\u0451')
                return 6;
            else if (letter <= '\u0435')
                return letter - '\u0430';
            else 
                return letter - '\u0430' + 1;            
        }
    }

    private static char getLetterByNumber(int number, boolean capital) {
        if (number <= 5 && capital)
            return (char)(number + '\u0410');
        else if (number <= 5)
            return (char)(number + '\u0430');
        else if (number == 6 && capital)
            return '\u0401';
        else if (number == 6)
            return '\u0451';
        else if (number > 5 && capital)
            return (char)(number + '\u0410' - 1);
        else 
            return (char)(number + '\u0430' - 1);
    }

    public static int getShift() {
        return shift;
    }

    public static void setShift(int shift) {
        Encoder.shift = shift;
    }

    public static File getInput() {
        return input;
    }

    public static void setInput(File input) {
        Encoder.input = input;
    }

    public static File getOutput() {
        return output;
    }

    public static void setOutput(File output) {
        Encoder.output = output;
    }
}