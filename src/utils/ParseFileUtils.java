package utils;

import java.io.File;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class ParseFileUtils {

    public static String[] parseFile(String filePath) throws Exception {
        String[] cardInfos = null;
        File file = new File(filePath);
        List<String> lines = null;
        lines = Files.readLines(file, Charsets.UTF_8);
        cardInfos = lines.get(0).split("\\|");
        return cardInfos;
    }
    
    public static String[] parseFile(File file) throws Exception {
        String[] cardInfos = null;
        List<String> lines = null;
        lines = Files.readLines(file, Charsets.UTF_8);
        cardInfos = lines.get(0).split("\\|");
        return cardInfos;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
