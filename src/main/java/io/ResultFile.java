package io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String str = (i + 1) * (j + 1) + " ";
                    out.write(str.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
