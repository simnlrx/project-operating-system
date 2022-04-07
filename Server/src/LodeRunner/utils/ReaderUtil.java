package LodeRunner.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class ReaderUtil {

    public static String getMessage(BufferedReader br) throws IOException {
        char[] t = new char[2500];
        br.read(t);
        boolean hasR = false;
        boolean hasN = false;
        int eol = 0;
        for (int i = 0; i < t.length; i++) {
            char c = t[i];
            if (c == '\r') {
                eol = i;
                hasR = true;
            } else if (c == '\n') {
                hasN = true;
            }
            if (hasN && hasR) {
                break;
            }
        }
        if (!hasN || !hasR) {
            System.out.println("Protocol is not correct.");
            return null;
        }
        return String.copyValueOf(t).substring(0, eol);
    }
}
