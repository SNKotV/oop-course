package com.github.snkotv;

import java.io.*;
import java.util.LinkedList;

public class Decoder {

    private static String keyword;
    private static File input;
    private static File output;

    public static void decode() throws IOException {
        Encoder.setInput(new File(String.valueOf(input)));
        Encoder.setOutput(new File(String.valueOf(output)));

        int shift = -33;
        while (shift <= 33) {
            Encoder.setShift(shift);
            Encoder.encode();

            if (isDecoded()) {
                return;
            }
            shift++;
        }
    }

    private static boolean isDecoded() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(output));

        String line = "";

        while ((line = br.readLine()) != null) {
            if (line.contains(keyword)) {
                return true;
            }
        }

        br.close();

        return false;
    }


    public static String getKeyword() {
        return keyword;
    }

    public static void setKeyword(String keyword) {
        Decoder.keyword = keyword;
    }

    public static File getInput() {
        return input;
    }

    public static void setInput(File input) {
        Decoder.input = input;
    }

    public static File getOutput() {
        return output;
    }

    public static void setOutput(File output) {
        Decoder.output = output;
    }
}
