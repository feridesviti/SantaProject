package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileRider {

    public static String getFile(String fileName) throws IOException {
        String fullFile = "";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            fullFile = fullFile + line;
        }
        return fullFile;
    }
}
