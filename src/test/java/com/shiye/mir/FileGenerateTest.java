package com.shiye.mir;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerateTest {
    public static void main(String[] args) {
        String lujing = "C:\\Users\\Thinkpad\\Desktop\\b.bat";
        File file = new File(lujing);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("C:");
            bw.newLine();
            bw.write("cd C:\\Users\\Thinkpad");
            bw.newLine();
            bw.write("CALL activate music");
            bw.newLine();
            bw.write("CALL spleeter separate -i C:\\Users\\spleeter\\splashin.mp3 -p spleeter:2stems -o output");
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fr);
            String string = bReader.readLine();
            System.out.println(string);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
