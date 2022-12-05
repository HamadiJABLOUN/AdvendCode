package hja.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class InputUtils {
    public static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    public static List<String> getLines(String filePath) {
        String content = null;
        try {
            content = readFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert content != null;
        String[] split = content.split("\n");
        for(int i=0;i< split.length;i++)
        {

            if (split[i].length() != 35)
            {
                split[i] = split[i] + "                                                                     ";
            }
        }
        return Arrays.stream(split).toList();
    }

    public static List<String> getLinesWithEmpty(String filePath) {
        String content = null;
        try {
            content = readFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert content != null;
        String[] split = content.split("\n");
        for(int i=0;i< split.length;i++)
        {

            if (split[i].length() != 35)
            {
                split[i] = split[i] + "                                                                     ";
            }
        }
        return Arrays.stream(split).toList();
    }
}
