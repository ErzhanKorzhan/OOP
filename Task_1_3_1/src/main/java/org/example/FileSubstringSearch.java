package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileSubstringSearch {
    public static List<Integer> find(String fileName, String substring) {
        List<Integer> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            int bufferSize = Math.max(10000, substring.length() * 2); // Размер буфера
            char[] buffer = new char[bufferSize];
            int offset = 0;
            int readChars;
            String remaining = "";

            while ((readChars = reader.read(buffer, 0, bufferSize)) != -1) {
                String chunk = remaining + new String(buffer, 0, readChars);
                int index = chunk.indexOf(substring);

                while (index != -1) {
                    result.add(offset + index);
                    index = chunk.indexOf(substring, index + 1);
                }

                if (readChars == bufferSize){
                    int tmp = chunk.length() - substring.length() + 1;
                    offset += tmp;
                    remaining = chunk.substring(tmp);
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading file");
            return new ArrayList<>();
        }

        return result;
    }
}