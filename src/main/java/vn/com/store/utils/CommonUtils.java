package vn.com.store.utils;

import vn.com.store.constant.Constant;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s == null || s.isEmpty())
            return false;

        if (s.length() == 1 && s.charAt(0) == '-')
            return false;

        for (int i = 1; i < s.length(); i++) {
            if (Character.digit(s.charAt(i), radix) < 0)
                return false;
        }
        return true;
    }

    public static boolean isEmpty(String string) {
        if (string == null || string.length() == 0 || string.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    public static final String getTimeStamp() {
        return new SimpleDateFormat(Constant.FORMAT_TIMESTAMP).format(new Date());
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int reads = inputStream.read();
        while (reads != -1) {
            byteArrayOutputStream.write(reads);
            reads = inputStream.read();
        }
        return byteArrayOutputStream.toByteArray();
    }

}
